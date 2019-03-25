package com.hema.newretail.backstage.service.data.impl;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.mongodb.Decimal128ToBigDecimalConverter;
import com.hema.newretail.backstage.common.mongodb.MongoDBPageModel;
import com.hema.newretail.backstage.common.mongodb.SpringbootMongoDBPageable;
import com.hema.newretail.backstage.common.queryparam.data.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.TimeUtil;
import com.hema.newretail.backstage.common.utils.excel.DataUtils;
import com.hema.newretail.backstage.dao.BaseMachineInfoMapper;
import com.hema.newretail.backstage.entry.BaseMachineInfoEntry;
import com.hema.newretail.backstage.model.data.*;
import com.hema.newretail.backstage.service.data.DrinkSalesService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.poi.hssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Department 新零售
 * @ClassName DrinkSalesServiceImpl
 * @Description 数据统计销量相关
 * @Author ---yingyan
 * @Date 2019/3/16
 * @Version 1.0
 **/
@Service
public class DrinkSalesServiceImpl implements DrinkSalesService {


    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    private final static String EMPTY = "";
    private static final String SDFEIGHT = "yyyy-MM-dd";
    private final static String ZEROS = "0";
    private final static String ONES = "1";
    private final static String TWOS = "2";
    private final static Integer TWO = 2;
    private final static Integer THREE = 3;
    private final static String THREES = "3";
    private final static String FOURS = "4";
    private final static String FIVES = "5";
    private final static String SIXS = "6";
    private final static String SEVENS = "7";
    private final static String EIGHTS = "8";
    private final static String NINES = "9";
    private final static String TENS = "10";
    private static final String SUCCESS = "success";

    @Value(value = "${excelModel.drinkExcelName}")
    private String drinkExcelName;

    @Resource
    private MongoTemplate secondaryMongoTemplate;

    @Resource
    private MongoTemplate mongoTemplate;

    @Autowired
    private BaseMachineInfoMapper baseMachineInfoMapper;

    @Autowired
    private Decimal128ToBigDecimalConverter decimal128ToBigDecimalConverter;


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

    /**
     * 功能描述: 数据出杯统计列表接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @Override
    public Response drink(DrinkSalesListCondition condition) throws Exception {
        DrinkSalesListDBCondition convert = convert(condition);
        Query query = new Query();
        //拼装分页信息
        SpringbootMongoDBPageable pageable = new SpringbootMongoDBPageable();
        MongoDBPageModel pm = new MongoDBPageModel();
        pm.setPagesize(condition.getPageSize());
        pm.setPagenumber(condition.getPageNum());
        /**
         * 排序说明
         * 按照日期降序排序
         * */
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "day"));
        pm.setSort(sort);
        pageable.setPage(pm);
        if (convert.getDateStart() != null) {
            query.addCriteria(Criteria.where("day").gte(convert.getDateStart()).lte(convert.getDateEnd()));
        }

        Long count = secondaryMongoTemplate.count(query, "drink_day_statics_view2");
        String reduce = "function(doc, aggr){" +
                "            aggr.totalSaleAmt += doc.totalSaleAmt;" +
                "            aggr.totalRefundAmt += doc.totalRefundAmt;"+
                "            aggr.totalMakeCupNum += doc.totalMakeCupNum;"+
                "            aggr.totalCouponNum += doc.totalCouponNum;"+
                "            aggr.totalUsedCouponNum += doc.totalUsedCouponNum;"+
                "        }";
        BasicDBObject basicDBObject = new BasicDBObject("totalSaleAmt", 0).append("totalRefundAmt", 0).append("totalMakeCupNum",0).append("totalCouponNum",0).append("totalUsedCouponNum",0);
        DBObject resultDb = secondaryMongoTemplate.getCollection("drink_day_statics_view2").group(null, query.getQueryObject(), basicDBObject, reduce);

        Map<String, BasicDBObject> map = resultDb.toMap();
        double totalSaleAmt = 0.0;
        double totalRefundAmt = 0.0;
        int totalMakeCupNum = 0;
        int totalCouponNum = 0;
        int totalUsedCouponNum = 0;
        if (map.size() > 0) {
            BasicDBObject bdbo = map.get("0");
            if (bdbo != null) {
                String totalSaleAmtStr = "totalSaleAmt";
                String totalRefundAmtStr = "totalRefundAmt";
                String totalMakeCupNumStr = "totalMakeCupNum";
                String totalCouponNumStr = "totalCouponNum";
                String totalUsedCouponNumStr = "totalUsedCouponNum";
                if (bdbo.get(totalSaleAmtStr) != null) {
                    totalSaleAmt = bdbo.getDouble(totalSaleAmtStr);
                }
                if (bdbo.get(totalRefundAmtStr) != null) {
                    totalRefundAmt = bdbo.getDouble(totalRefundAmtStr);
                }
                if (bdbo.get(totalMakeCupNumStr) != null) {
                    totalMakeCupNum = bdbo.getInt(totalMakeCupNumStr);
                }
                if (bdbo.get(totalCouponNumStr) != null) {
                    totalCouponNum = bdbo.getInt(totalCouponNumStr);
                }
                if (bdbo.get(totalUsedCouponNumStr) != null) {
                    totalUsedCouponNum = bdbo.getInt(totalUsedCouponNumStr);
                }
            }
        }

        List<DrinkMachineStaticsViewBo> view = secondaryMongoTemplate.find(query.with(pageable), DrinkMachineStaticsViewBo.class, "drink_day_statics_view2");
        for (DrinkMachineStaticsViewBo d : view) {
            BigDecimal d1 = new BigDecimal(d.getTotalSaleAmt());
            BigDecimal d2 = new BigDecimal(d.getTotalRefundAmt());
            d.setRealIncome(d1.subtract(d2).floatValue());
            d.setWeek(TimeUtil.dateToWeek(d.getDay()));
        }
        DrinkBo drinkBo = new DrinkBo();
        drinkBo.setList(view);
        drinkBo.setTotalMakeCupNum(totalMakeCupNum);
        drinkBo.setRealIncome(new BigDecimal(totalSaleAmt).subtract(new BigDecimal(totalRefundAmt)).floatValue());
        drinkBo.setTotalSaleAmt(totalSaleAmt);
        drinkBo.setTotalRefundAmt(totalRefundAmt);
        drinkBo.setTotalCouponNum(totalCouponNum);
        drinkBo.setTotalUsedCouponNum(totalUsedCouponNum);
        return Response.success(drinkBo, count, condition.getPageSize(), condition.getPageNum());
    }

    /**
     * 功能描述: 设备饮品销售列表导出接口
     *
     * @param condition
     * @return Response
     * @throws Exception
     * @author yingyan
     * @date 2019/3/18
     */
    @Override
    public Response drinkExcel(DrinkSalesListCondition condition, HttpServletResponse response) throws Exception {
        DrinkSalesListDBCondition convert = convert(condition);
        Query query = new Query();
        /**
         * 排序说明
         * 0   默认排序
         * */
        Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "day"));
        if (convert.getDateStart() != null) {
            query.addCriteria(Criteria.where("day").gte(convert.getDateStart()).lte(convert.getDateEnd()));
        }
        List<DrinkMachineStaticsViewBo> view = secondaryMongoTemplate.find(query.with(sort), DrinkMachineStaticsViewBo.class, "drink_day_statics_view2");
        for (DrinkMachineStaticsViewBo d : view) {
            //实际营收
            BigDecimal d1 = new BigDecimal(d.getTotalSaleAmt());
            BigDecimal d2 = new BigDecimal(d.getTotalRefundAmt());
            d.setRealIncome(d1.subtract(d2).floatValue());
            //日期转星期
            d.setWeek(TimeUtil.dateToWeek(d.getDay()));
        }
        response.setContentType("application/octet-stream");

        StringBuilder fileName = new StringBuilder();
        StringBuilder time = new StringBuilder();
        time.append("(").append(TimeUtil.getStringByDate(new Date())).append(")");
        fileName.append("设备饮品销售统计数据导出(").append(TimeUtil.getStringByDate(new Date())).append(").xlsx");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName.toString(), "utf-8"));
        response.flushBuffer();
        String result = DataUtils.exportDrinkExcel(drinkExcelName, view, response.getOutputStream(), time.toString());
        if (SUCCESS.equals(result)) {
            return Response.success();
        }
        return Response.failure("导出失败");
    }

    /**
     * 功能描述: 数据出杯统计详细接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @Override
    public Response drinkDetail(DrinkDetailCondition condition) throws Exception {
        SpringbootMongoDBPageable pageable = new SpringbootMongoDBPageable();
        MongoDBPageModel pm = new MongoDBPageModel();
        pm.setPagesize(condition.getPageSize());
        pm.setPagenumber(condition.getPageNum());
        pageable.setPage(pm);
        Query query = new Query();
        query.addCriteria(Criteria.where("day").is(condition.getDay()));
        long count = secondaryMongoTemplate.count(query, DrinkMachineStaticsViewBo.class, "drink_day_statics_view3");
        List<DrinkMachineStaticsViewBo> list = secondaryMongoTemplate.find(query.with(pageable), DrinkMachineStaticsViewBo.class, "drink_day_statics_view3");
        //根据machineUuid查询MySQL数据库设备相关信息
        for(DrinkMachineStaticsViewBo drinkbo:list){
            //实际营收
            drinkbo.setRealIncome(new BigDecimal(drinkbo.getTotalSaleAmt()).subtract(new BigDecimal(drinkbo.getTotalRefundAmt())).floatValue());
            //设备相关信息
            BaseMachineInfoEntry machineInfoEntry = baseMachineInfoMapper.selectByUId(drinkbo.getMachineUuid());
            if(machineInfoEntry!=null){
                if(!machineInfoEntry.getMachineName().isEmpty()){
                    drinkbo.setMachineName(machineInfoEntry.getMachineName());
                }
                if(!machineInfoEntry.getMachineSequence().isEmpty()){
                    drinkbo.setMachineSequence(machineInfoEntry.getMachineSequence());
                }
                if(!machineInfoEntry.getMachineDesc().isEmpty()){
                    drinkbo.setMachineDesc(machineInfoEntry.getMachineDesc());
                }
            }
        }
        return Response.success(list, count, condition.getPageSize(), condition.getPageNum());
    }

    /**
     * 功能描述:
     *
     * @throws Exception
     * @param: condition
     * @return: CupListDBCondition
     * @author: cwz
     * @date: 2018/12/19 19:48
     */
    private DrinkSalesListDBCondition convert(DrinkSalesListCondition condition) throws Exception {

        DrinkSalesListDBCondition listDBCondition = new DrinkSalesListDBCondition();
        if (condition.getDateStart() != null && !EMPTY.equals(condition.getDateStart())) {
            /*时间前有*/
            if (condition.getDateEnd() != null && !EMPTY.equals(condition.getDateEnd())) {

                /*时间前后都有*/
                listDBCondition.setDateEnd(condition.getDateEnd());
                listDBCondition.setDateStart(condition.getDateStart());
            } else {
                /*时间前有后没有*/
                listDBCondition.setDateEnd(TimeUtil.getStringByDateSix(new Date()));
                listDBCondition.setDateStart(condition.getDateStart());
            }
        } else {
            /*时间前没有*/
            if (condition.getDateEnd() != null && !EMPTY.equals(condition.getDateEnd())) {
                /*时间前没有后有*/
                listDBCondition.setDateStart(TimeUtil.getStringByDateSix(TimeUtil.threeMonthAgo(TimeUtil.stringToDate(condition.getDateEnd(), SDFEIGHT))));
                listDBCondition.setDateEnd(condition.getDateEnd());
            }

        }
        return listDBCondition;
    }

    /**
     * 功能描述: 数据统计销量趋势接口--天--参数处理类
     *
     * @param condition
     * @return DataSalesTrendDBCondition
     * @throws Exception
     * @author cwz
     * @date 2018/12/17 20:07
     */
    DataSalesTrendDBCondition convert(DataSalesTrendCondition condition) throws Exception {
        DataSalesTrendDBCondition dbCondition = new DataSalesTrendDBCondition();
        if (!EMPTY.equals(condition.getMachineId()) && null != condition.getMachineId()) {
            dbCondition.setMachineId(condition.getMachineId());
        }
        if (condition.getDateStart() != null && !EMPTY.equals(condition.getDateStart())) {
            /*时间前有*/
            if (condition.getDateEnd() != null && !EMPTY.equals(condition.getDateEnd())) {

                /*时间前后都有*/
                dbCondition.setDateEnd(condition.getDateEnd());
                dbCondition.setDateStart(condition.getDateStart());
            } else {
                /*时间前有后没有*/
                dbCondition.setDateStart(condition.getDateStart());
                dbCondition.setDateEnd(TimeUtil.getStringByDateSix(new Date()));
            }
        } else {
            /*时间前没有*/
            if (condition.getDateEnd() != null && !EMPTY.equals(condition.getDateEnd())) {
                /*时间前没有后有*/
                dbCondition.setDateStart(TimeUtil.getStringByDateSix(TimeUtil.threeMonthAgo(TimeUtil.stringToDate(condition.getDateEnd(), SDFEIGHT))));
                dbCondition.setDateEnd(condition.getDateEnd());
            }


        }


        return dbCondition;
    }
}
