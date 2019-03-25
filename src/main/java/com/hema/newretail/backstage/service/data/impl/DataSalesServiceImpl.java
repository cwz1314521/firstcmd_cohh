package com.hema.newretail.backstage.service.data.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.mongodb.Decimal128ToBigDecimalConverter;
import com.hema.newretail.backstage.common.mongodb.MongoDBPageModel;
import com.hema.newretail.backstage.common.mongodb.SpringbootMongoDBPageable;
import com.hema.newretail.backstage.common.queryparam.data.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.TimeUtil;
import com.hema.newretail.backstage.common.utils.excel.DataUtils;
import com.hema.newretail.backstage.dao.*;
import com.hema.newretail.backstage.entry.BaseMachineInfoEntry;
import com.hema.newretail.backstage.entry.agent.AgentUserEntry;
import com.hema.newretail.backstage.entry.data.DDayEntry;
import com.hema.newretail.backstage.entry.data.DDayMachineUuidEntry;
import com.hema.newretail.backstage.entry.data.DMonthEntry;
import com.hema.newretail.backstage.entry.data.DMonthMachineUuidEntry;
import com.hema.newretail.backstage.model.data.*;
import com.hema.newretail.backstage.service.data.DataSalesService;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Department 新零售
 * @ClassName DataSalesServiceImpl
 * @Description 数据统计销量相关
 * @Author ---CWZ
 * @Date 2018/12/17 16:10
 * @Version 1.0
 **/
@Service
public class DataSalesServiceImpl implements DataSalesService {


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

    @Value(value = "${excelModel.cupExcelName}")
    private String cupExcelName;

    @Autowired
    private DDayMachineUuidMapper dDayMachineUuidMapper;

    @Autowired
    private DMonthMachineUuidMapper dMonthMachineUuidMapper;

    @Autowired
    private DDayMapper dDayMapper;

    @Autowired
    private DMonthMapper dMonthMapper;

    @Autowired
    private BaseMachineInfoMapper baseMachineInfoMapper;

    @Autowired
    private AgentUserMapper agentUserMapper;

    @Resource
    private MongoTemplate secondaryMongoTemplate;

    @Resource
    private MongoTemplate mongoTemplate;

    @Autowired
    private Decimal128ToBigDecimalConverter decimal128ToBigDecimalConverter;


    /**
     * 功能描述: 数据统计销量趋势接口--日
     *
     * @param condition
     * @return Response
     * @throws Exception
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @Override
    public Response trendDay(DataSalesTrendCondition condition) throws Exception {
        DataSalesTrendDBCondition data = convert(condition);
        logger.info("参数类拼装" + data);
        if (data.getMachineId() == null) {
            List<DDayEntry> list = dDayMapper.selectBaseResultMap(data);
            List<String> queryDayList = new ArrayList<>();
            for (DDayEntry entry : list
            ) {
                queryDayList.add(entry.getDay());
            }
            Query query = new Query();
            List<String> xdate = new ArrayList<>();
            List<Integer> xCupNumber = new ArrayList<>();
            List<BigDecimal> xAveragePrice = new ArrayList<>();
            query.addCriteria(Criteria.where("day").in(queryDayList));
            logger.info("查询mongodb");
            List<Map> drinkMap = secondaryMongoTemplate.find(query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "day"))), Map.class, "drink_day_statics");
            if (drinkMap.size() == 0) {
                return Response.failure("暂无数据");
            }
            for (Map m : drinkMap
            ) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(m.get("day:" + m.get("day").toString()));
                xdate.add(m.get("day").toString());
                int xCupNum = 0;
                if (jsonObject.get("totalMakeCupNum") != null) {
                    System.out.println(jsonObject.get("totalMakeCupNum").toString());
                    xCupNum = new BigDecimal(jsonObject.get("totalMakeCupNum").toString()).intValue();
                    xCupNumber.add(xCupNum);
                } else {
                    xCupNumber.add(0);
                }
                if (jsonObject.get("totalMakeAmt") != null && xCupNum != 0) {
                    BigDecimal divide = new BigDecimal(jsonObject.get("totalMakeAmt").toString());
                    xAveragePrice.add(divide.divide(new BigDecimal(xCupNum), 2, RoundingMode.HALF_UP));
                } else {
                    xAveragePrice.add(new BigDecimal(0));
                }

            }
            Integer maxCup = Collections.max(xCupNumber);
            BigDecimal maxAveragePrice = Collections.max(xAveragePrice);
            TrendsBo trendsBo = new TrendsBo();
            trendsBo.setMaxAveragePrice(maxAveragePrice);
            trendsBo.setMaxCup(maxCup);
            trendsBo.setXAveragePrice(xAveragePrice);
            trendsBo.setXCupNumber(xCupNumber);
            trendsBo.setXdate(xdate);
            return Response.success(trendsBo);
        } else {
            List<DDayMachineUuidEntry> list = dDayMachineUuidMapper.selectBaseResultMap(data);

            List<String> queryDayList = new ArrayList<>();
            for (DDayMachineUuidEntry entry : list
            ) {
                queryDayList.add(entry.getDay());
            }
            Query query = new Query();
            List<String> xdate = new ArrayList<>();
            List<Integer> xCupNumber = new ArrayList<>();
            List<BigDecimal> xAveragePrice = new ArrayList<>();
            query.addCriteria(Criteria.where("day").in(queryDayList));
            logger.info("查询mongodb");
            List<Map> drinkMap = secondaryMongoTemplate.find(query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "day"))), Map.class, "drink_day_statics");
            if (drinkMap.size() == 0) {
                return Response.failure("暂无数据");
            }
            for (Map m : drinkMap
            ) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(m.get("day:" + m.get("day").toString() + "_machineUuid:" + data.getMachineId()));
                xdate.add(m.get("day").toString());
                int xCupNum = 0;
                if (jsonObject != null) {
                    if (jsonObject.get("cupNum") != null) {
                        xCupNum = new BigDecimal(jsonObject.get("cupNum").toString()).intValue();
                    }
                }
                xCupNumber.add(xCupNum);
                if (jsonObject != null && jsonObject.get("amt") != null) {
                    BigDecimal divide = new BigDecimal(jsonObject.get("amt").toString());
                    if (xCupNum != 0) {
                        xAveragePrice.add(divide.divide(new BigDecimal(xCupNum), 2, RoundingMode.HALF_UP));
                    }

                }

            }
            Integer maxCup = Collections.max(xCupNumber);
            BigDecimal maxAveragePrice = Collections.max(xAveragePrice);
            TrendsBo trendsBo = new TrendsBo();
            trendsBo.setMaxAveragePrice(maxAveragePrice);
            trendsBo.setMaxCup(maxCup);
            trendsBo.setXAveragePrice(xAveragePrice);
            trendsBo.setXCupNumber(xCupNumber);
            trendsBo.setXdate(xdate);
            return Response.success(trendsBo);
        }
    }

    /**
     * 功能描述: 数据统计销量趋势接口---月
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @Override
    public Response trendMonth(DataSalesTrendCondition condition) throws Exception {
        DataSalesTrendDBCondition data = convert(condition);
        logger.info("参数类拼装" + data);
        if (data.getMachineId() == null) {
            List<DMonthEntry> list = dMonthMapper.selectBaseResultMap(data);
            List<String> queryDayList = new ArrayList<>();
            for (DMonthEntry entry : list
            ) {
                queryDayList.add(entry.getMonth());
            }
            Query query = new Query();
            List<String> xdate = new ArrayList<>();
            List<Integer> xCupNumber = new ArrayList<>();
            List<BigDecimal> xAveragePrice = new ArrayList<>();
            query.addCriteria(Criteria.where("month").in(queryDayList));
            logger.info("查询mongodb");
            List<Map> drinkMonth = secondaryMongoTemplate.find(query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "month"))), Map.class, "drink_month_statics");
            if (drinkMonth.size() == 0) {
                return Response.failure("暂无数据");
            }
            for (Map m : drinkMonth
            ) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(m.get("month:" + m.get("month").toString()));
                xdate.add(m.get("month").toString());
                int xCupNum = new BigDecimal(jsonObject.get("totalMakeCupNum").toString()).intValue();
                xCupNumber.add(xCupNum);
                BigDecimal divide = new BigDecimal(jsonObject.get("totalMakeAmt").toString());
                xAveragePrice.add(divide.divide(new BigDecimal(xCupNum), 2, RoundingMode.HALF_UP));
            }
            Integer maxCup = Collections.max(xCupNumber);
            BigDecimal maxAveragePrice = Collections.max(xAveragePrice);
            TrendsBo trendsBo = new TrendsBo();
            trendsBo.setMaxAveragePrice(maxAveragePrice);
            trendsBo.setMaxCup(maxCup);
            trendsBo.setXAveragePrice(xAveragePrice);
            trendsBo.setXCupNumber(xCupNumber);
            trendsBo.setXdate(xdate);
            return Response.success(trendsBo);
        } else {
            List<DMonthMachineUuidEntry> list = dMonthMachineUuidMapper.selectBaseResultMap(data);
            List<String> queryDayList = new ArrayList<>();
            for (DMonthMachineUuidEntry entry : list
            ) {
                queryDayList.add(entry.getMonth());
            }
            Query query = new Query();
            List<String> xdate = new ArrayList<>();
            List<Integer> xCupNumber = new ArrayList<>();
            List<BigDecimal> xAveragePrice = new ArrayList<>();
            query.addCriteria(Criteria.where("month").in(queryDayList));
            logger.info("查询mongodb");
            List<Map> drinkMonth = secondaryMongoTemplate.find(query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "month"))), Map.class, "drink_month_statics");
            if (drinkMonth.size() == 0) {
                return Response.failure("暂无数据");
            }
            for (Map m : drinkMonth
            ) {
                JSONObject jsonObject = (JSONObject) JSON.toJSON(m.get("month:" + m.get("month").toString() + "_machineUuid:" + data.getMachineId()));
                xdate.add(m.get("month").toString());
                int xCupNum = new BigDecimal(jsonObject.get("cupNum").toString()).intValue();
                xCupNumber.add(xCupNum);
                BigDecimal divide = new BigDecimal(jsonObject.get("amt").toString());
                xAveragePrice.add(divide.divide(new BigDecimal(xCupNum), 2, RoundingMode.HALF_UP));
            }
            Integer maxCup = Collections.max(xCupNumber);
            BigDecimal maxAveragePrice = Collections.max(xAveragePrice);
            TrendsBo trendsBo = new TrendsBo();
            trendsBo.setMaxAveragePrice(maxAveragePrice);
            trendsBo.setMaxCup(maxCup);
            trendsBo.setXAveragePrice(xAveragePrice);
            trendsBo.setXCupNumber(xCupNumber);
            trendsBo.setXdate(xdate);
            return Response.success(trendsBo);
        }
    }

    /**
     * 功能描述: 数据统计销量列表接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @Override
    public Response list(DataSalesListCondition condition) {
        /***
         * 拼装参数类
         */
        DataSalesTrendDBCondition data = new DataSalesTrendDBCondition();
        data.setDateStart(condition.getDateStart());
        data.setDateEnd(condition.getDateEnd());
        Page<DDayEntry> page = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        dDayMapper.selectBaseResultMap(data);
        /**
         * 拼装mongodb参数类
         */
        BasicDBObject dbObject = new BasicDBObject();
        BasicDBList basicDBList = new BasicDBList();
        BasicDBObject fieldsObject = new BasicDBObject();
        List<String> date = new ArrayList<>();
        /**
         * 循环拼装mongo数据和date字段
         */
        for (DDayEntry entry : page.getResult()
        ) {
            basicDBList.add(new BasicDBObject("day", entry.getDay()));
            fieldsObject.put(entry.getDimension(), true);
            date.add(entry.getDimension());
        }
        dbObject.put("$or", basicDBList);
        fieldsObject.put("_id", false);
        Query query = new BasicQuery(dbObject.toJson(), fieldsObject.toJson());
        /***
         * 查询mongo并且以日期倒叙排序
         */
        List<Map> drinkDayStatics = secondaryMongoTemplate.find(query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "day"))), Map.class, "drink_day_statics");
        fieldsObject.put("columnKey", true);
        fieldsObject.put("_id", false);
        TreeSet<String> s = new TreeSet<>();
        Query queryName = new BasicQuery(dbObject.toJson(), fieldsObject.toJson());
        List<Map> queryNames = secondaryMongoTemplate.find(queryName, Map.class, "drink_day_statics_view");
        /**
         * 利用treeset接收数据以去重
         */
        for (Map m : queryNames
        ) {
            String string = m.entrySet().iterator().next().toString();
            String substring = string.substring(25, string.length());
            s.add(substring);
        }
        ListBo bo = new ListBo();
        bo.setSet(s);
        bo.setMap(drinkDayStatics);
        Collections.reverse(date);
        bo.setDate(date);
        return Response.success(bo, page.getTotal(), condition.getPageSize(), condition.getPageNum());
    }

    /**
     * 功能描述: 数据统计销量列表excel接口
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @Override
    public Response excel(DataSalesListCondition condition, HttpServletResponse response) throws Exception {
        DataSalesTrendDBCondition data = new DataSalesTrendDBCondition();
        data.setDateStart(condition.getDateStart());
        data.setDateEnd(condition.getDateEnd());
        List<DDayEntry> list = dDayMapper.selectBaseResultMap(data);
        if (list.size() == 0) {
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("饮品销量统计表");
            //浏览器下载excel
            String fileName = "饮品销量结构统计表.xls";
            this.buildExcelDocument(fileName, workbook, response);
            return Response.failure("暂无数据");
        }
        BasicDBObject dbObject = new BasicDBObject();
        BasicDBList basicDBList = new BasicDBList();
        BasicDBObject fieldsObject = new BasicDBObject();
        List<String> date = new ArrayList<>();
        for (DDayEntry entry : list
        ) {
            basicDBList.add(new BasicDBObject("day", entry.getDay()));
            fieldsObject.put(entry.getDimension(), true);
            date.add(entry.getDimension());
        }
        dbObject.put("$or", basicDBList);
        fieldsObject.put("_id", false);
        Query query = new BasicQuery(dbObject.toJson(), fieldsObject.toJson());
        List<Map> drinkDayStatics = secondaryMongoTemplate.find(query.with(new Sort(new Sort.Order(Sort.Direction.DESC, "day"))), Map.class, "drink_day_statics");
        fieldsObject.put("columnKey", true);
        fieldsObject.put("_id", false);
        TreeSet<String> s = new TreeSet<>();
        TreeSet<String> set1 = new TreeSet<>();
        Query queryName = new BasicQuery(dbObject.toJson(), fieldsObject.toJson());
        List<Map> queryNames = secondaryMongoTemplate.find(queryName, Map.class, "drink_day_statics_view");
        for (Map m : queryNames
        ) {
            String string = m.entrySet().iterator().next().toString();
            set1.add(string.substring(25, string.length()));
            s.add(string.substring(10, string.length()));
        }
        ListBo bo = new ListBo();
        bo.setSet(s);
        List<String> set = new ArrayList<>(s);
        List<String> set2 = new ArrayList<>(set1);
        bo.setMap(drinkDayStatics);
        Collections.reverse(date);
        bo.setDate(date);
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("饮品销量统计表");
        HSSFRow row = sheet.createRow(0);
        HSSFRow rown = sheet.createRow(1);
        row.setHeight((short) 600);
        rown.setHeight((short) 600);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(0, 25 * 256);
        sheet.setColumnWidth(1, 15 * 256);
        sheet.setColumnWidth(2, 15 * 256);
        sheet.setColumnWidth(3, 15 * 256);

        //设置为居中加粗
        HSSFCellStyle style = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setBoldweight(Short.MAX_VALUE);
        font.setFontHeight((short) 200);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
        style.setFont(font);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("日期");
        cell.setCellStyle(style);


        cell = row.createCell(1);
        cell.setCellValue("总出杯量");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("平均售价");
        cell.setCellStyle(style);
        int rr = 2;
        for (int st = 2; st < ((set2.size() * TWO) + TWO); st += TWO) {
            cell = row.createCell(st + 1);
            String s1 = set2.get(rr - 2);
            String aaa = s1.substring(s1.lastIndexOf(":") + 1, s1.length());
            cell.setCellValue(s1.substring(s1.lastIndexOf(":") + 1, s1.length()));
            cell.setCellStyle(style);

            cell = rown.createCell(st + 1);
            cell.setCellValue("出杯量");
            cell.setCellStyle(style);

            cell = row.createCell(st + 2);
            cell.setCellValue("");
            cell.setCellStyle(style);

            cell = rown.createCell(st + 2);
            cell.setCellValue("实售均价");
            cell.setCellStyle(style);
            rr++;
            sheet.addMergedRegion(new CellRangeAddress(0, 0, st + 1, st + 2));
        }
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2));
        if (null != drinkDayStatics) {
            System.out.println(drinkDayStatics);
            int rowNum = 2;
            for (Map m : drinkDayStatics
            ) {

                String string = m.entrySet().iterator().next().toString();
                string = string.substring(4, 14);
                Object o = m.get("day:" + string);
                JSONObject json = (JSONObject) JSON.toJSON(o);
                HSSFRow row1 = sheet.createRow(rowNum);
                row1.setHeight((short) 400);
                HSSFCellStyle style1 = workbook.createCellStyle();
	            style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
	            style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
                HSSFCell cell1;
                int ii = 0;
                for (int i = 0; i < ((set2.size() * TWO) + THREE); i++) {


                    if (i == 0) {
                            cell1 = row1.createCell(0);
                        cell1.setCellStyle(style);
                        cell1.setCellValue(string);
                    } else if (i == 1) {
                            cell1 = row1.createCell(1);
                        cell1.setCellStyle(style);
                        if (json.get("totalMakeCupNum") != null) {
                            String totalMakeCupNum = json.get("totalMakeCupNum").toString();
                            cell1.setCellValue(totalMakeCupNum);
                        } else {
                            cell1.setCellValue("0");
                        }

                    } else if (i == 2) {
                            cell1 = row1.createCell(2);
                        cell1.setCellStyle(style);
                        if (json.get("totalMakeCupNum") != null) {
                            BigDecimal b1 = new BigDecimal(json.get("totalMakeAmt").toString());
                            BigDecimal b2 = new BigDecimal(json.get("totalMakeCupNum").toString());
                            Double rslt = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            cell1.setCellValue(rslt);
                        } else {
                            cell1.setCellValue("0");
                        }

                    } else {


                        String s1 = set2.get(ii - 3);
                        if ((ii - 3) >= set2.size()) {
                            break;
                        }

                        JSONObject jj = (JSONObject) json.get(s1);
                        if (jj != null) {
                                cell1 = row1.createCell(i);
                            cell1.setCellStyle(style1);
                            if (jj.get("makeAmt") == null || jj.get("makeCupNum") == null) {
                                cell1.setCellValue("0");
                                   cell1 = row1.createCell(i+1);
                                cell1.setCellStyle(style1);
                                cell1.setCellValue("0");
                            } else {
                                String makeAmt = jj.get("makeAmt").toString();
                                String makeCupNum = jj.get("makeCupNum").toString();
                                BigDecimal b1 = new BigDecimal(makeAmt);
                                BigDecimal b2 = new BigDecimal(makeCupNum);
                                Double rslt = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP).doubleValue();
                                cell1.setCellValue(makeCupNum);
                                   cell1 = row1.createCell(i+1);
                                cell1.setCellStyle(style1);
                                cell1.setCellValue(rslt.toString());
                            }
                        } else {
                                cell1 = row1.createCell(i);
                            cell1.setCellStyle(style1);
                            cell1.setCellValue("0");
                                cell1 = row1.createCell(i+1);
                            cell1.setCellStyle(style1);
                            cell1.setCellValue("0");
                        }
                        i++;
                    }
                    ii++;
                }
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
    public Response cup(CupListCondition condition) throws Exception {
        CupListDBCondition convert = convert(condition);
        Query query = new Query();
        //拼装分页信息
        SpringbootMongoDBPageable pageable = new SpringbootMongoDBPageable();
        MongoDBPageModel pm = new MongoDBPageModel();
        pm.setPagesize(condition.getPageSize());
        pm.setPagenumber(condition.getPageNum());
        /**
         * 排序说明
         * 0   默认排序
         * 1日出杯倒序      2日出杯正序
         * 3月出杯倒序      4月出杯正序
         * 5总出杯倒序      6总出杯正序
         * 7 Max  倒序      8 Max  正序
         * 9 平均 倒序     10平均  正序
         * */
        List<Sort.Order> orders = new ArrayList<>();
        if (ZEROS.equals(convert.getSort())) {
            orders.add(new Sort.Order(Sort.Direction.DESC, "day"));
        } else if (ONES.equals(convert.getSort())) {
            orders.add(new Sort.Order(Sort.Direction.DESC, "cupNum"));
        } else if (TWOS.equals(convert.getSort())) {
            orders.add(new Sort.Order(Sort.Direction.ASC, "cupNum"));
        } else if (THREES.equals(convert.getSort())) {
            orders.add(new Sort.Order(Sort.Direction.DESC, "monthCupNum"));
        } else if (FOURS.equals(convert.getSort())) {
            orders.add(new Sort.Order(Sort.Direction.ASC, "monthCupNum"));
        } else if (FIVES.equals(convert.getSort())) {
            orders.add(new Sort.Order(Sort.Direction.DESC, "totalCupNum"));
        } else if (SIXS.equals(convert.getSort())) {
            orders.add(new Sort.Order(Sort.Direction.ASC, "totalCupNum"));
        } else if (SEVENS.equals(convert.getSort())) {
            orders.add(new Sort.Order(Sort.Direction.DESC, "maxCupNum"));
        } else if (EIGHTS.equals(convert.getSort())) {
            orders.add(new Sort.Order(Sort.Direction.ASC, "maxCupNum"));
        } else if (NINES.equals(convert.getSort())) {
            orders.add(new Sort.Order(Sort.Direction.DESC, "averageCupNum"));
        } else if (TENS.equals(convert.getSort())) {
            orders.add(new Sort.Order(Sort.Direction.ASC, "averageCupNum"));
        }
        Sort sort = new Sort(orders);
        pm.setSort(sort);
        pageable.setPage(pm);
        if (convert.getDateStart() != null) {
            query.addCriteria(Criteria.where("day").gte(convert.getDateStart()).lte(convert.getDateEnd()));
        }
        if (convert.getAgentId() != null) {
            query.addCriteria(Criteria.where("agentId").is(convert.getAgentId()));
        }
        if (convert.getUuid() != null) {
            query.addCriteria(Criteria.where("machineUuid").is(convert.getUuid()));
        }

        Long count = secondaryMongoTemplate.count(query, "drink_agent_statics_view");
        String reduce = "function(doc, aggr){" +
                "            aggr.totalCupNum += doc.cupNum;" +
                "            if(doc.cupNum > aggr.maxCupNum){" +
                "               aggr.maxCupNum = doc.cupNum;" +
                "            }" +
                "        }";
        BasicDBObject basicDBObject = new BasicDBObject("totalCupNum", 0).append("maxCupNum", 0);
        DBObject resultDb = secondaryMongoTemplate.getCollection("drink_agent_statics_view").group(null, query.getQueryObject(), basicDBObject, reduce);

        Map<String, BasicDBObject> map = resultDb.toMap();
        int totalCupNum = 0;
        int maxCupNum = 0;
        BigDecimal averageCupNum = new BigDecimal(0);
        if (map.size() > 0) {
            BasicDBObject bdbo = map.get("0");
            if (bdbo != null) {
                String totalCupNumStr = "totalCupNum";
                String maxCupNumStr = "maxCupNum";
                if (bdbo.get(totalCupNumStr) != null) {
                    totalCupNum = bdbo.getInt(totalCupNumStr);
                }
                if (bdbo.get(maxCupNumStr) != null) {
                    maxCupNum = bdbo.getInt(maxCupNumStr);
                }
            }
        }
        if (totalCupNum > 0 && count > 0) {
            BigDecimal b1 = new BigDecimal(totalCupNum);
            BigDecimal b2 = new BigDecimal(count);
            averageCupNum = b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
        }

        List<DrinkAgentStaticsViewBo> view = secondaryMongoTemplate.find(query.with(pageable), DrinkAgentStaticsViewBo.class, "drink_agent_statics_view");
        for (DrinkAgentStaticsViewBo d : view) {
            AgentUserEntry agentUserEntry = agentUserMapper.selectByPrimaryKey(Long.valueOf(d.getAgentId()));
            if (agentUserEntry != null) {
                d.setAgentName(agentUserEntry.getCompanyName());
            }
            BaseMachineInfoEntry baseMachineInfoEntry = baseMachineInfoMapper.selectByUId(d.getMachineUuid());
            if (baseMachineInfoEntry != null) {
                d.setMachineName(baseMachineInfoEntry.getMachineName());
            }
        }
        CupBo cupBo = new CupBo();
        cupBo.setList(view);
        cupBo.setZCup(totalCupNum);
        cupBo.setMaxDayCup(maxCupNum);
        cupBo.setADayCup(averageCupNum);
        return Response.success(cupBo, count, condition.getPageSize(), condition.getPageNum());
    }

    /**
     * 功能描述: 数据出杯统计列表导出接口
     *
     * @param condition
     * @return Response
     * @throws Exception
     * @author cwz
     * @date 2018/12/11 14:48
     */
    @Override
    public Response cupExcel(CupListCondition condition, HttpServletResponse response) throws Exception {
        CupListDBCondition convert = convert(condition);
        Query query = new Query();
        /**
         * 排序说明
         * 0   默认排序
         * 1日出杯倒序      2日出杯正序
         * 3月出杯倒序      4月出杯正序
         * 5总出杯倒序      6总出杯正序
         * 7 Max  倒序      8 Max  正序
         * 9 平均 倒序     10平均  正序
         * */
        List<Sort.Order> order = new ArrayList<>();
        if (ZEROS.equals(convert.getSort())) {
            order.add(new Sort.Order(Sort.Direction.DESC, "day"));
        } else if (ONES.equals(convert.getSort())) {
            order.add(new Sort.Order(Sort.Direction.DESC, "cupNum"));
        } else if (TWOS.equals(convert.getSort())) {
            order.add(new Sort.Order(Sort.Direction.ASC, "cupNum"));
        } else if (THREES.equals(convert.getSort())) {
            order.add(new Sort.Order(Sort.Direction.DESC, "monthCupNum"));
        } else if (FOURS.equals(convert.getSort())) {
            order.add(new Sort.Order(Sort.Direction.ASC, "monthCupNum"));
        } else if (FIVES.equals(convert.getSort())) {
            order.add(new Sort.Order(Sort.Direction.DESC, "totalCupNum"));
        } else if (SIXS.equals(convert.getSort())) {
            order.add(new Sort.Order(Sort.Direction.ASC, "totalCupNum"));
        } else if (SEVENS.equals(convert.getSort())) {
            order.add(new Sort.Order(Sort.Direction.DESC, "maxCupNum"));
        } else if (EIGHTS.equals(convert.getSort())) {
            order.add(new Sort.Order(Sort.Direction.ASC, "maxCupNum"));
        } else if (NINES.equals(convert.getSort())) {
            order.add(new Sort.Order(Sort.Direction.DESC, "averageCupNum"));
        } else if (TENS.equals(convert.getSort())) {
            order.add(new Sort.Order(Sort.Direction.ASC, "averageCupNum"));
        }
        Sort sort = new Sort(order);
        if (convert.getDateStart() != null) {
            query.addCriteria(Criteria.where("day").gte(convert.getDateStart()).lte(convert.getDateEnd()));
        }
        if (convert.getAgentId() != null) {
            query.addCriteria(Criteria.where("agentId").is(convert.getAgentId()));
        }
        if (convert.getUuid() != null) {
            query.addCriteria(Criteria.where("machineUuid").is(convert.getUuid()));
        }
        List<DrinkAgentStaticsViewBo> view = secondaryMongoTemplate.find(query.with(sort), DrinkAgentStaticsViewBo.class, "drink_agent_statics_view");
        for (DrinkAgentStaticsViewBo d : view
        ) {
            AgentUserEntry agentUserEntry = agentUserMapper.selectByPrimaryKey(Long.valueOf(d.getAgentId()));
            BaseMachineInfoEntry baseMachineInfoEntry = baseMachineInfoMapper.selectByUId(d.getMachineUuid());
            if (baseMachineInfoEntry != null) {
                d.setMachineName(baseMachineInfoEntry.getMachineName());
            }
            if (agentUserEntry != null) {
                d.setAgentName(agentUserEntry.getCompanyName());
            }
        }
        response.setContentType("application/octet-stream");

        StringBuilder fileName = new StringBuilder();
        StringBuilder time = new StringBuilder();
        time.append("(").append(TimeUtil.getStringByDate(new Date())).append(")");
        fileName.append("杯数统计数据导出(").append(TimeUtil.getStringByDate(new Date())).append(").xlsx");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName.toString(), "utf-8"));
        response.flushBuffer();
        String result = DataUtils.exportCupExcel(cupExcelName, view, response.getOutputStream(), time.toString());
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
    public Response cupDetail(CupDetailCondition condition) throws Exception {
        SpringbootMongoDBPageable pageable = new SpringbootMongoDBPageable();
        MongoDBPageModel pm = new MongoDBPageModel();
        pm.setPagesize(condition.getPageSize());
        pm.setPagenumber(condition.getPageNum());
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.DESC, "takeCupTime"));
        Sort sort = new Sort(orders);
        pm.setSort(sort);
        pageable.setPage(pm);
        Date start = TimeUtil.stringToDate(condition.getDate() + " 00:00:01", "yyyy-MM-dd HH:mm:ss");
        Date end = TimeUtil.stringToDate(condition.getDate() + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        Query query = new Query();
        query.addCriteria(Criteria.where("takeCupTime").gte(start).lte(end));
        query.addCriteria(Criteria.where("machineUuid").is(condition.getUuid()));
        query.addCriteria(Criteria.where("orderStatus").is("143"));
        long count = mongoTemplate.count(query, OrdersBo.class, "ordersData");
        List<OrdersBo> list = mongoTemplate.find(query.with(pageable), OrdersBo.class, "ordersData");
        for (OrdersBo o : list
        ) {
            if (o.getAmt() != null) {
                o.setAmts(decimal128ToBigDecimalConverter.convert(o.getAmt()));
            }
            if (o.getPrice() != null) {
                o.setPrices(decimal128ToBigDecimalConverter.convert(o.getPrice()));
            }
            if (o.getProperties() != null) {
                if (o.getProperties().get(0).getProName() != null) {
                    String water = o.getProperties().get(0).getProName();
                    if ("冷".equals(water)) {
                        o.setWater("冷饮");
                    } else if ("热".equals(water)) {
                        o.setWater("热饮");
                    } else {
                        o.setWater("常温");
                    }

                }
            }
            if (o.getOrderTime() != null) {
                o.setOrderTimes(TimeUtil.getStringByDate(o.getOrderTime()));
            }
            if (o.getTakeCupTime() != null) {
                o.setTakeCupTimes(TimeUtil.getStringByDate(o.getTakeCupTime()));
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
    private CupListDBCondition convert(CupListCondition condition) throws Exception {

        CupListDBCondition listDBCondition = new CupListDBCondition();
        if (!EMPTY.equals(condition.getUuid()) && null != condition.getUuid()) {
            listDBCondition.setUuid(condition.getUuid());
        }
        if (!EMPTY.equals(condition.getAgentId()) && null != condition.getAgentId()) {
            listDBCondition.setAgentId(condition.getAgentId());
        }
        if (!EMPTY.equals(condition.getSort()) && null != condition.getSort()) {
            listDBCondition.setSort(condition.getSort());
        }
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
