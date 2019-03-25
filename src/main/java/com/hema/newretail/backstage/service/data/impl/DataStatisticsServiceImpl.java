package com.hema.newretail.backstage.service.data.impl;

import com.hema.newretail.backstage.common.queryparam.data.DataSalesStructureHistogramCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.model.data.DataSalesStructureHistogramBo;
import com.hema.newretail.backstage.model.data.DataSalesStructureHistogramIngredientBo;
import com.hema.newretail.backstage.model.data.DataSalesStructureListBo;
import com.hema.newretail.backstage.model.data.DataSalesStructureListIngredientBo;
import com.hema.newretail.backstage.service.data.DataStatisticsService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.service.data.impl
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-12-18 14:39
 */
@Service
public class DataStatisticsServiceImpl implements DataStatisticsService {

    @Resource
    private MongoTemplate secondaryMongoTemplate;

    /**
     * 查询饮品销量柱状图
     *
     * @param vo vo
     * @return Response
     */
    @Override
    public Response queryHistogram(DataSalesStructureHistogramCondition vo) {
        this.initDefaultDate(vo);
        DataSalesStructureHistogramBo result = new DataSalesStructureHistogramBo();

        Criteria criteria = Criteria.where("day").gte(vo.getDateStart()).lte(vo.getDateEnd());
        result.setTotalSaleNumber(this.queryTotalSaleNumber(criteria));

        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("ingredientName").sum("saleCupNum").as("saleNumber"),
                Aggregation.project("ingredientName", "saleNumber").and("ingredientName").previousOperation(),
                Aggregation.sort(Sort.Direction.DESC, "saleNumber")
        );
        AggregationResults<DataSalesStructureHistogramIngredientBo> outputType = secondaryMongoTemplate.aggregate(agg, "drink_day_statics_view", DataSalesStructureHistogramIngredientBo.class);

        if (null != outputType.getMappedResults()) {
            List<DataSalesStructureHistogramIngredientBo> ingredintes = outputType.getMappedResults();
            int recordNum = 15;
            if (ingredintes.size() > recordNum) {
                List<DataSalesStructureHistogramIngredientBo> resultList = new ArrayList<>(ingredintes.subList(0, recordNum - 1));
                List<DataSalesStructureHistogramIngredientBo> otherList = ingredintes.subList(recordNum - 1, ingredintes.size());
                Integer other = 0;
                for (DataSalesStructureHistogramIngredientBo bo : otherList) {
                    other += bo.getSaleNumber();
                }

                DataSalesStructureHistogramIngredientBo data = new DataSalesStructureHistogramIngredientBo();
                data.setIngredientName("其他");
                data.setSaleNumber(other);
                resultList.add(data);
                result.setIngredintes(resultList);
            } else {
                result.setIngredintes(ingredintes);
            }
        }
        return Response.success(result);
    }


    /**
     * 查询饮品制杯统计列表
     *
     * @param vo vo
     * @return Response
     */
    @Override
    public Response queryList(DataSalesStructureHistogramCondition vo) {
        initDefaultDate(vo);
        Criteria criteria = Criteria.where("day").gte(vo.getDateStart()).lte(vo.getDateEnd());

        // 查询表头统计数据
        DataSalesStructureListBo result = this.queryTotalForList(criteria);

        // 查询总记录数
        Aggregation countAgg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("ingredientName").count().as("totalNum"),
                Aggregation.project("ingredientName", "totalNum").and("ingredientName").previousOperation()
        );
        AggregationResults<BasicDBObject> count = secondaryMongoTemplate.aggregate(countAgg, "drink_day_statics_view", BasicDBObject.class);
        int totalRecordNum = 0;
        if (null != count && null != count.getMappedResults()) {
            totalRecordNum = count.getMappedResults().size();
        }

        // 查询分页列表数据
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("ingredientName").sum("saleCupNum").as("saleCupNum").sum("saleAmt").as("saleAmt").sum("makeCupNum").as("makeCupNum"),
                Aggregation.project("ingredientName", "saleCupNum", "saleAmt", "makeCupNum").and("ingredientName").previousOperation(),
                Aggregation.sort(Sort.Direction.DESC, "saleCupNum"),
                Aggregation.skip(((long) (vo.getPageNum() - 1) * vo.getPageSize())),
                Aggregation.limit(vo.getPageSize())
        );
        AggregationResults<DataSalesStructureListIngredientBo> outputType = secondaryMongoTemplate.aggregate(agg, "drink_day_statics_view", DataSalesStructureListIngredientBo.class);
        if (null != outputType) {
            List<DataSalesStructureListIngredientBo> list = outputType.getMappedResults();
            for (DataSalesStructureListIngredientBo bo : list) {
                bo.setSaleAmt(null == bo.getSaleAmt() ? 0.00 : new BigDecimal(bo.getSaleAmt()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            }
            result.setListData(list);
        }
        return Response.success(result, (long) totalRecordNum, vo.getPageSize(), vo.getPageNum());
    }

    /**
     * 导出excel
     *
     * @param vo       vo
     * @param response response
     * @return Response
     */
    @Override
    public Response exportExcel(DataSalesStructureHistogramCondition vo, HttpServletResponse response) throws Exception {
        initDefaultDate(vo);
        Criteria criteria = Criteria.where("day").gte(vo.getDateStart()).lte(vo.getDateEnd());
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(criteria),
                Aggregation.group("ingredientName").sum("saleCupNum").as("saleCupNum").sum("saleAmt").as("saleAmt").sum("makeCupNum").as("makeCupNum"),
                Aggregation.project("ingredientName", "saleCupNum", "saleAmt", "makeCupNum").and("ingredientName").previousOperation(),
                Aggregation.sort(Sort.Direction.DESC, "saleCupNum")
        );
        AggregationResults<DataSalesStructureListIngredientBo> outputType = secondaryMongoTemplate.aggregate(agg, "drink_day_statics_view", DataSalesStructureListIngredientBo.class);

        List<DataSalesStructureListIngredientBo> list = null;
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("饮品销量结构统计表");
        this.createTitle(workbook, sheet);
        if (null != outputType) {
            list = outputType.getMappedResults();
            int rowNum = 1;

            for (DataSalesStructureListIngredientBo data : list) {
                this.createData(workbook, sheet, rowNum, data);
                rowNum++;
            }
        }
        String fileName = "饮品销量结构统计表.xls";

        //生成excel文件
        this.buildExcelFile(fileName, workbook);

        //浏览器下载excel
        this.buildExcelDocument(fileName, workbook, response);
        return Response.success();
    }

    /**
     * 初始化日期查询条件
     *
     * @param vo 查询条件对象
     */
    private void initDefaultDate(DataSalesStructureHistogramCondition vo) {
        if (StringUtils.isEmpty(vo.getDateStart()) && StringUtils.isEmpty(vo.getDateEnd())) {
            String first = "";
            String last = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            last = sdf.format(new Date());
            vo.setDateEnd(last);
            Calendar c = Calendar.getInstance();
            c.setTime(new Date());
            c.set(Calendar.DAY_OF_MONTH, 1);
            first = sdf.format(c.getTime());
            vo.setDateStart(first);
            System.out.println("@@first::" + first + "-----last::" + last);
        }
    }

    /**
     * 查询饮品销量柱状图总销量
     *
     * @param criteria c
     * @return 总销量
     */
    private Integer queryTotalSaleNumber(Criteria criteria) {
        String reduce = "function(doc, aggr){" +
                "            aggr.totalSaleNumber += doc.saleCupNum;" +
                "       }";
        Query query = new Query();
        if (criteria != null) {
            query.addCriteria(criteria);
        }
        BasicDBObject basicDBObject = new BasicDBObject("totalSaleNumber", 0);
        DBObject resultDb = secondaryMongoTemplate.getCollection("drink_day_statics_view")
                .group(null,
                        query.getQueryObject(),
                        basicDBObject,
                        reduce);
        Map<String, BasicDBObject> map = resultDb.toMap();
        if (map.size() > 0) {
            BasicDBObject bdbo = map.get("0");
            if (bdbo != null) {
                String totalSaleNumber = "totalSaleNumber";
                if (bdbo.get(totalSaleNumber) != null) {
                    return bdbo.getInt(totalSaleNumber);
                }
            }
        }
        return 0;
    }

    /**
     * 查询饮品销量列表统计数据
     *
     * @param criteria c
     * @return 总销量
     */
    private DataSalesStructureListBo queryTotalForList(Criteria criteria) {
        DataSalesStructureListBo result = new DataSalesStructureListBo();
        String reduce = "function(doc, aggr){" +
                "            aggr.totalSaleCupNum += doc.saleCupNum;" +
                "            aggr.totalMakeCupNum += doc.makeCupNum;" +
                "            aggr.totalSaleAmt += parseFloat((Math.round((doc.saleAmt)*100)/100).toFixed(2));" +
                "       }";
        Query query = new Query();
        if (criteria != null) {
            query.addCriteria(criteria);
        }
        BasicDBObject basicDBObject = new BasicDBObject("totalSaleCupNum", 0).append("totalMakeCupNum", 0).append("totalSaleAmt", 0.00);
        DBObject resultDb = secondaryMongoTemplate.getCollection("drink_day_statics_view")
                .group(null,
                        query.getQueryObject(),
                        basicDBObject,
                        reduce);
        Map<String, BasicDBObject> map = resultDb.toMap();
        if (map.size() > 0) {
            BasicDBObject bdbo = map.get("0");
            if (bdbo != null) {
                String totalSaleCupNum = "totalSaleCupNum";
                String totalMakeCupNum = "totalMakeCupNum";
                String totalSaleAmt = "totalSaleAmt";
                if (bdbo.get(totalSaleCupNum) != null) {
                    result.setTotalSaleCupNum(bdbo.getInt(totalSaleCupNum));
                }
                if (bdbo.get(totalMakeCupNum) != null) {
                    result.setTotalMakeCupNum(bdbo.getInt(totalMakeCupNum));
                }
                if (bdbo.get(totalSaleAmt) != null) {
                    Double total = bdbo.getDouble(totalSaleAmt);
                    BigDecimal b = new BigDecimal(total);
                    result.setTotalSaleAmt(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            }
        }
        return result;
    }

    /**
     * 创建表头
     *
     * @param workbook 工作簿
     * @param sheet    sheet
     */
    private void createTitle(HSSFWorkbook workbook, HSSFSheet sheet) {
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 600);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(0, 25 * 256);
        sheet.setColumnWidth(1, 15 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 15 * 256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontHeight(Short.MAX_VALUE);
        font.setFontHeight((short) 200);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("饮品名称");
        cell.setCellStyle(style);


        cell = row.createCell(1);
        cell.setCellValue("销售数量");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("销售金额");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("制杯量");
        cell.setCellStyle(style);
    }

    /**
     * 创建数据
     *
     * @param workbook 工作簿
     * @param sheet    sheet
     * @param rowNum   行号
     * @param data     数据对象
     */
    private void createData(HSSFWorkbook workbook, HSSFSheet sheet, int rowNum, DataSalesStructureListIngredientBo data) {
        HSSFRow row = sheet.createRow(rowNum);
        row.setHeight((short) 400);
        HSSFCellStyle style = workbook.createCellStyle();
	    style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	    style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue(data.getIngredientName());

        cell = row.createCell(1);
        cell.setCellStyle(style);
        cell.setCellValue(data.getSaleCupNum());

        cell = row.createCell(2);
        cell.setCellStyle(style);
        cell.setCellValue(data.getSaleAmt());

        cell = row.createCell(3);
        cell.setCellStyle(style);
        cell.setCellValue(data.getMakeCupNum());
    }

    /**
     * 生成excel文件
     *
     * @param filename 文件名
     * @param workbook 工作簿
     * @throws Exception exception
     */
    private void buildExcelFile(String filename, HSSFWorkbook workbook) throws Exception {
        FileOutputStream fos = new FileOutputStream(filename);
        workbook.write(fos);
        fos.flush();
        fos.close();
    }

    /**
     * 浏览器下载excel
     *
     * @param filename 文件名
     * @param workbook 工作簿
     * @param response HttpServletResponse
     * @throws Exception exception
     */
    private void buildExcelDocument(String filename, HSSFWorkbook workbook, HttpServletResponse response) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

}
