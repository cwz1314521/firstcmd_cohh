package com.hema.newretail.backstage.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.mongodb.Decimal128ToBigDecimalConverter;
import com.hema.newretail.backstage.common.mongodb.MongoDBPageModel;
import com.hema.newretail.backstage.common.mongodb.SpringbootMongoDBPageable;
import com.hema.newretail.backstage.common.queryparam.usermanagementparameter.CentralBillListCondition;
import com.hema.newretail.backstage.common.queryparam.usermanagementparameter.MakeOrderCondition;
import com.hema.newretail.backstage.common.utils.*;
import com.hema.newretail.backstage.common.utils.excel.BillExcelUtils;
import com.hema.newretail.backstage.common.utils.rongyun.BigdecimalToDecimal128;
import com.hema.newretail.backstage.common.utils.rongyun.Decimal128ToBigdecimal;
import com.hema.newretail.backstage.dao.*;
import com.hema.newretail.backstage.entry.BaseMachineInfoEntry;
import com.hema.newretail.backstage.entry.OrderData;
import com.hema.newretail.backstage.entry.OrderPropertyData;
import com.hema.newretail.backstage.entry.discounts.BusiActivityEntry;
import com.hema.newretail.backstage.entry.discounts.BusiCouponEntry;
import com.hema.newretail.backstage.entry.agent.AgentUserEntry;
import com.hema.newretail.backstage.entry.grid.GridCompanyEntry;
import com.hema.newretail.backstage.entry.orderentry.*;
import com.hema.newretail.backstage.entry.orderentry.Properties;
import com.hema.newretail.backstage.model.bill.OrderBo;
import com.hema.newretail.backstage.model.billdetailmodel.OrdersDataBo;
import com.hema.newretail.backstage.service.IMakeOrderService;
import com.mongodb.BasicDBObject;
import com.mongodb.WriteResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.poi.poifs.filesystem.EntryUtils;
import org.bson.types.Decimal128;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.hema.newretail.backstage.enums.orderStatus;
import com.hema.newretail.backstage.enums.RefundCode;

/**
 * @auther: 程文政
 * @Date: 2018/8/23 11:55
 * @Description:
 * @Version: 1.0
 */
@Service
public class MakeOrderServiceImpl implements IMakeOrderService {

    @Value(value = "${excelModel.orderSrcFileName}")
    private String orderSrcFileName;
    @Value(value = "${excelModel.orderFileName}")
    private String orderFileName;

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    @Autowired
    private AgentUserMapper agentUserMapper;

    @Autowired
    private GridCompanyMapper gridCompanyMapper;

    @Autowired
    private BaseMachineInfoMapper baseMachineInfoMapper;

    @Autowired
    private MongoTemplate mongoTemplate;

	@Autowired
	private BusiCouponMapper busiCouponMapper;

    @Autowired
    private BaseGlobalInfoMapper baseGlobalInfoMapper;

    @Autowired
    private GridCompanyGeohashMapper gridCompanyGeohashMapper;

    @Autowired
    private Decimal128ToBigDecimalConverter decimal128ToBigDecimalConverter;

    @Autowired
    private BusiActivityMapper busiActivityMapper;
    private static final String EMPTY = "";
    private static final String ONES = "1";
    private static final String ZEROS = "0";
    private static final String TWOS = "2";
    private static final BigDecimal WEEKRENTAL = new BigDecimal(500);//默认终止金额
    private static final BigDecimal PREWEEKRENTAL = new BigDecimal(1);//默认初始金额
    private static final Integer MINLENGTH = 2;//拼装字符串最小长度
    private static final String SDF = "yyyy-MM-dd HH:mm:ss";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Override
    public Response gridCompany() {
        List<GridCompanyEntry> list = gridCompanyMapper.selectAll();
        return Response.success(list);
    }

    @Override
    public Response agentCompany() {
        List<AgentUserEntry> list = agentUserMapper.selectAll();
        return Response.success(list);
    }

    @Override
    public Response pageOrders(MakeOrderCondition makeOrderCondition) throws Exception {
        makeOrderCondition = pageOrdersCondition(makeOrderCondition);
        SpringbootMongoDBPageable pageable = new SpringbootMongoDBPageable();
        MongoDBPageModel pm = new MongoDBPageModel();
        pm.setPagenumber(makeOrderCondition.getPageNum());
        pm.setPagesize(makeOrderCondition.getPageSize());
        List<Order> orders = new ArrayList<>();  //排序
        orders.add(new Order(Direction.DESC, "takeCupTime"));
        Sort sort = new Sort(orders);
        pm.setSort(sort);
        pageable.setPage(pm);
        Query query = new Query();
        //开始拼装查询数据
        /**
         * 出货单号
         */
        if (makeOrderCondition.getId() != null && makeOrderCondition.getId() !="") {
            query.addCriteria(Criteria.where("id").is(makeOrderCondition.getId()));
        }
        /**
         * 账单号
         */
        if (makeOrderCondition.getBillId() != null && makeOrderCondition.getBillId() !="") {
            query.addCriteria(Criteria.where("billId").is(makeOrderCondition.getBillId()));
        }
        /**
         * 机器名
         */
        if (makeOrderCondition.getMachineName() != null) {
            query.addCriteria(Criteria.where("machineName").is(makeOrderCondition.getMachineName()));
        }
        /**
         * 时间
         */
        if (makeOrderCondition.getPreOrderTime() != null && makeOrderCondition.getOrderTime() != null) {
            Date preDate = sdf1.parse(makeOrderCondition.getPreOrderTime() + " 23:59:59");
            Date date = sdf.parse(makeOrderCondition.getOrderTime());
            query.addCriteria(Criteria.where("orderTime").gte(date).lte(preDate));//制作时间查询
        }
        //前为空
        if (makeOrderCondition.getPreOrderTime() == null && makeOrderCondition.getOrderTime() != null) {
            Date date = sdf.parse(makeOrderCondition.getOrderTime());
            query.addCriteria(Criteria.where("orderTime").gte(date).lte(new Date(date.getTime() - 30 * 3600 * 24 * 1000)));//制作时间查询
        }
        //后为空
        if (makeOrderCondition.getPreOrderTime() != null && makeOrderCondition.getOrderTime() == null) {
            Date preDate = sdf1.parse(makeOrderCondition.getPreOrderTime() + " 23:59:59");
            query.addCriteria(Criteria.where("orderTime").gte(new Date(preDate.getTime() + 30 * 3600 * 24 * 1000)).lte(preDate));//制作时间查询
        }
        //判断代理是否为空
        if (makeOrderCondition.getAgent() != null) {
            query.addCriteria(Criteria.where("agent").is(makeOrderCondition.getAgent()));//机器编号查询
        }
        /**
         0 已取杯：143
         1 出杯失败：2，3，7，8,9,11,15
         2 待制作：0，1，4，6，129，135
         **/
        //状态判断查询
        if (makeOrderCondition.getOrderStatus() != null && makeOrderCondition.getOrderStatus() !="") {
            List<String> status = new ArrayList<>();
            if(ZEROS.equals(makeOrderCondition.getOrderStatus())){
                status.add("143");
            }else if(ONES.equals(makeOrderCondition.getOrderStatus())){
                status.add("2");status.add("3");status.add("7");status.add("8");
                status.add("9");status.add("11");status.add("15");
            }else if(TWOS.equals(makeOrderCondition.getOrderStatus())){
                status.add("0");status.add("1");status.add("4");
                status.add("6");status.add("129");status.add("135");
            }else {
            }
            query.addCriteria(Criteria.where("orderStatus").in(status));
        }

        /**
         * 0优惠出杯   1普通出杯
         */
        if (makeOrderCondition.getType() != null && makeOrderCondition.getType() != null) {
            if(ZEROS.equals(makeOrderCondition.getType())){
            query.addCriteria(Criteria.where("couponId").ne(null));
            }else {
                query.addCriteria(Criteria.where("couponId").not().ne(null));
            }
        }
        //查询总条数 total
        Long count = mongoTemplate.count(query, OrdersData.class);
        System.out.println(count);
        List<OrdersData> list = mongoTemplate.find(query.with(pageable), OrdersData.class);
        List<OrderBo> result = new ArrayList<>();
        for (OrdersData data:list
             ) {
            OrderBo orderBo = new OrderBo();
            if(data.getMachineUuid() != null){
                Map map = baseMachineInfoMapper.selectOrderByUuid(data.getMachineUuid());
                if(map != null){
                    orderBo.setCompanyName(map.get("companyName").toString());
                    orderBo.setGridCompany(map.get("gridCompanyName").toString());
                    orderBo.setMachineLocation(map.get("machineLocation").toString());
                    orderBo.setMachineName(map.get("machineName").toString());
                    orderBo.setAgentName(map.get("agentName").toString());
                }
            }
            if(data.getRefundCode() != null){
                orderBo.setRefundCode(data.getRefundCode());
            }
            if(data.getOrderTime() != null){
                orderBo.setOrderTime(TimeUtil.getStringByDate(data.getOrderTime()));
            }
            if(data.getProductionTime() != null){
                orderBo.setMakeTime(TimeUtil.getStringByDate(data.getProductionTime()));
            }
            if(data.getCouponId() != null){
                BusiCouponEntry busiCouponEntry = busiCouponMapper.selectByPrimaryKey(data.getCouponId());
                if(busiCouponEntry != null){
                    orderBo.setCoupon(busiCouponEntry.getCouponName());
                    orderBo.setCouponCode(busiCouponEntry.getCouponNo());
                }
            }
            if(data.getCouponId() != null || (data.getDiscounts() != null && new Decimal128ToBigdecimal().convert(data.getDiscount()).compareTo(new BigDecimal(0)) == 1) ){
                orderBo.setCupType("优惠出杯");
            }else {
                orderBo.setCupType("普通");
            }
            if(data.getDiscount() != null){
                orderBo.setDiscounts(new Decimal128ToBigdecimal().convert(data.getDiscount()).toString());
            }
            if(data.getErrorCode() != null){
                String a = baseGlobalInfoMapper.selectByErrorCode(data.getErrorCode());
                orderBo.setErrorCode(a);
            }

            orderBo.setBillId(data.getBillId());
            orderBo.setOrderId(data.getId());
            orderBo.setMenuName(data.getMenuName());

            if(data.getBillId() != null){
                BillData id = mongoTemplate.findOne(new Query().addCriteria(Criteria.where("_id").is(data.getBillId())), BillData.class);
              if(id != null){
                  String type = id.getShipType();
                  if(ONES.equals(type)){
                      orderBo.setSendType("自提");
                  }else if(TWOS.equals(type)){
                      orderBo.setSendType("外卖");
                  }else {
                      orderBo.setSendType("");
                  }
              }else {
                  orderBo.setSendType("");
              }

            }else {
                orderBo.setSendType("");
            }
            if(data.getOrderStatus() !=null){
                Set<String> loading=new HashSet<>();
                Set<String> cupFalse=new HashSet<>();
                cupFalse.add("2");cupFalse.add("3");cupFalse.add("7");cupFalse.add("8");cupFalse.add("9");cupFalse.add("11");cupFalse.add("15");
                loading.add("0");loading.add("1");loading.add("4");loading.add("6");loading.add("129");loading.add("135");
                if(loading.contains(data.getOrderStatus())){
                    orderBo.setStatus("待制作");
                }
                if(cupFalse.contains(data.getOrderStatus())){
                    orderBo.setStatus("出杯失败");
                }
                if("143".equals(data.getOrderStatus())){
                    orderBo.setStatus("已取杯");
                }
            }
            if(data.getProperties() != null){
                for (Properties p:data.getProperties()
                     ) {
                    if("糖".equals(p.getTypeName())){
                        orderBo.setSugar(p.getProName());
                    }
                    if("温度".equals(p.getTypeName())){
                        orderBo.setTemperature(p.getProName());
                    }
                }
            }
           result.add(orderBo) ;
        }
        return Response.success(result, count, makeOrderCondition.getPageSize(), makeOrderCondition.getPageNum());
    }

    @Override
    public Response orderDetail(String id) {
        //调用公共方法orderDetailUtil
        return Response.success(orderDetailUtil(id));
    }

    @Override
    @Transactional
    public Response billDetail(String id) {
        //查询bill表
        Query query = new Query(Criteria.where("id").is(id));
        BillData billData = mongoTemplate.findOne(query, BillData.class);
        Query queryOrder = new Query(Criteria.where("billId").is(id));
        List<Map> listActivity = busiActivityMapper.selectAll();
        Map result = new HashMap();
        List<Map> lpro = new ArrayList<>();
        List<Map> lcon = new ArrayList<>();
        List<OrdersData> ordersData = mongoTemplate.find(queryOrder, OrdersData.class);
        if (null == billData) {
            return Response.failure("订单不存在");
        }
        for (OrdersData a:ordersData
             ) {
            /**
             * 购买明细、选项、金额
             */
            Map pro = new HashMap();
            pro.put("id",a.getId());
            if(a.getMenuName() != null){
                pro.put("menuName",a.getMenuName());
            }else{
                pro.put("menuName","");
            }
            if(a.getAmt() != null){
                pro.put("amt",new Decimal128ToBigdecimal().convert(a.getAmt()));
            }else {
                pro.put("amt",new BigDecimal(0));
            }
            if(a.getCouponId() != null){
                Map maps = busiCouponMapper.selectByBillDetail(a.getCouponId());
                if(maps != null){
                    pro.put("cName",maps.get("cName"));
                    pro.put("cType",maps.get("cType"));
                    pro.put("cCode",maps.get("cCode"));
                }else {
                    pro.put("cName","");
                    pro.put("cType","");
                    pro.put("cCode","");
                }

            }else {
                pro.put("cName","");
                pro.put("cType","");
                pro.put("cCode","");
            }
            if(a.getProperties() != null){
                List<Map> map1 = new ArrayList<>();
                for (Properties p:a.getProperties()
                     ) {
                    Map map = new HashMap();
                    if(p.getTypeName() != null){
                        map.put("typeName",p.getTypeName());
                    }
                    if(p.getProName() != null){
                        map.put("proName",p.getProName());
                    }
                    map1.add(map);
                }
                pro.put("properties",map1);
            }
            lpro.add(pro);
        }
        List<String> map = new ArrayList<>();
        if(billData.getActivityId() != null){
            for (Long d:billData.getActivityId()
                    ) {
                BusiActivityEntry activityEntry = busiActivityMapper.selectByPrimaryKey(d);
                if(activityEntry != null){
                    map.add(activityEntry.getActivityName());
                }
            }
        }

        result.put("properties",lpro);
        result.put("activity",map);
        result.put("billPrice",billData.getBillPrice());
        result.put("pack",0);
        result.put("send",0);
        result.put("scoutDiscount",billData.getScoutDiscount());
        result.put("activityDiscount",billData.getActivityDiscount());
        result.put("allPrice",billData.getBillPrice());
        result.put("amt",billData.getAmt());

        result.put("billTime",TimeUtil.getStringByDate(billData.getBillTime()));
        result.put("id",billData.getId());
        result.put("paySerialNumbers",billData.getPaySerialNumbers());
        if(billData.getPaymentType() != null){
            if(billData.getPaymentType().equals("001")){
                result.put("paymentType","微信");
            }else if(billData.getPaymentType().equals("002")){
                result.put("paymentType","支付宝");
            }else {
                result.put("paymentType","其他");
            }
        }else {
            result.put("paymentType","");
        }
        if(billData.getBillStatus() != null){
            String billStatus = billData.getBillStatus();
            if(billStatus.equals("001")){
                result.put("billStatus","未付款");
            }else if(billStatus.equals("002")){
                result.put("billStatus","已付款");
            }else if(billStatus.equals("003")){
                result.put("billStatus","付款中");
            }else if(billStatus.equals("004")){
                result.put("billStatus","已取消");
            }else if(billStatus.equals("005")){
                result.put("billStatus","支付失败");
            }else if(billStatus.equals("006")){
                result.put("billStatus","配送中");
            }else {
                result.put("billStatus","其他");
            }
        }else {
            result.put("billStatus","");
        }
        result.put("user",billData.getNickName());
        if(billData.getBillSource() != null){
            if(billData.getBillSource().equals("M")){
                result.put("billSource","小程序");
            }else if(billData.getBillSource().equals("A")){
                result.put("billSource","苹果");
            }else if(billData.getBillSource().equals("D")){
                result.put("billSource","安卓");
            }else{
                result.put("billSource","其他");
            }
        }else {
            result.put("billSource","其他");
        }
        if(billData.getShipType() != null){
            if(billData.getShipType().equals("1")){
                result.put("shipType","自提");
            }else if(billData.getShipType().equals("2")){
                result.put("shipType","外卖");
            }else{
                result.put("shipType","其他");
            }
        }
        if(billData.getWholeBillEndTime() != null){
            result.put("endTime",TimeUtil.getStringByDate(billData.getWholeBillEndTime()));
        }else {
            result.put("endTime","");
        }

        result.put("receiveMan",billData.getReceiveMan());
        result.put("receiveTel",billData.getReceiveTel());
        result.put("receiveAddress",billData.getReceiveAddress());
        return Response.success(result);

    }

    @Override
    public Response saveRemark(String id, String remark) {
        Update update = new Update();
        Query query = new Query(Criteria.where("_id").is(id));
        update.set("remarks", remark);
        mongoTemplate.upsert(query, update, BillData.class);
        return Response.success();
    }

    @Override
    public Response saveOrderRemark(String id, String remark) {
        Update update = new Update();
        Query query = new Query(Criteria.where("_id").is(id));
        update.set("remarks", remark);
        mongoTemplate.upsert(query, update, "billData");
        return Response.success();
    }

    /**
     * 功能描述: 拼装返回数据
     *
     * @param:
     * @return:
     * @auther: cwz
     * @date: 2018/8/31 9:01
     */
    private OrdersDataBo orderDetailUtil(String id) {
        Query query = new Query(Criteria.where("id").is(id));
        OrdersData ordersData = mongoTemplate.findOne(query, OrdersData.class);
        OrdersDataBo ordersDataBo = new OrdersDataBo();
        if (ordersData.getMachineUuid() != null) {
            BaseMachineInfoEntry baseMachineInfoEntry = baseMachineInfoMapper.selectByUId(ordersData.getMachineUuid());
            if (baseMachineInfoEntry != null) {
                ordersDataBo.setDeviceLocation(baseMachineInfoEntry.getMachineDesc());
            }
        }
        ordersDataBo.setOrderStatus(ordersData.getOrderStatus());
        ordersDataBo.setRemark(ordersData.getRemarks());
        ordersDataBo.setMenuName(ordersData.getMenuName());
        ordersDataBo.setId(ordersData.getId());
        ordersDataBo.setDeviceNumber(ordersData.getDeviceNumber());
        ordersDataBo.setAgent(ordersData.getAgent());
        ordersDataBo.setGrid(ordersData.getGrid());
        ordersDataBo.setProductionTime(ordersData.getTakeCupTime());
        ordersDataBo.setWaterTemperature(ordersData.getWaterTemperature());
        ordersDataBo.setMoney(new Decimal128ToBigdecimal().convert(ordersData.getAmt()));
        ordersDataBo.setBillId(ordersData.getBillId());
        StringBuilder prope = new StringBuilder();
        if (ordersData.getProperties() != null) {
            for (Properties properties : ordersData.getProperties()) {
                prope.append("加").append(properties.getProName()).append(properties.getTypeName()).append(",");
            }
        }
        if (prope.length() > MINLENGTH) {
            prope.substring(0, prope.length() - 1);
        }
        ordersDataBo.setOptions(prope.toString());
        return ordersDataBo;
    }

    @Override
    public Response centralBillList(CentralBillListCondition centralBillListCondition) throws Exception {
        centralBillListCondition = centralBillListCondition(centralBillListCondition);
        //拼装分页信息
        logger.info("数据拼装" + centralBillListCondition);
        SpringbootMongoDBPageable pageable = new SpringbootMongoDBPageable();
        MongoDBPageModel pm = new MongoDBPageModel();
        pm.setPagesize(centralBillListCondition.getPageSize());
        pm.setPagenumber(centralBillListCondition.getPageNum());
        //排序规则
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(Direction.DESC, "billTime"));
        Sort sort = new Sort(orders);
        pm.setSort(sort);
        pageable.setPage(pm);

        //拼装关联信息
        LookupOperation lookupOperation = LookupOperation.newLookup().
                from("ordersData").
                localField("_id").
                foreignField("billId").
                as("orders111");
        //拼装具体查询信息
        Criteria order = Criteria.where("orders111").not().size(0);
        order = orderQueryData(order, centralBillListCondition);
        AggregationOperation match = Aggregation.match(order);

        Criteria bill = new Criteria();
        bill = billQueryData(bill, centralBillListCondition);

        AggregationOperation prematch = Aggregation.match(bill);
        //查询

        Aggregation aggregation = Aggregation.newAggregation(prematch, lookupOperation, match,
                //排序
                Aggregation.sort(pageable.getSort()),
                //pagenumber
                Aggregation.skip(pageable.getPageNumber() > 1 ? (pageable.getPageNumber() - 1) * pageable.getPageSize() : 0),
                //pagesize
                Aggregation.limit(pageable.getPageSize()));
        //总条数查询
        logger.info("查询条件" + aggregation.toString());
        List<BasicDBObject> results = mongoTemplate.aggregate(aggregation, "billData", BasicDBObject.class).getMappedResults();
        logger.info("查询结果" + results.size());
        // 查询总记录数
        Aggregation countAgg = Aggregation.newAggregation(
                prematch, lookupOperation, match,
                Aggregation.group("billTime").count().as("billTime")
        );
        List<BasicDBObject> counts = mongoTemplate.aggregate(countAgg, "billData", BasicDBObject.class).getMappedResults();
        logger.info("条数" + counts.size());
        List<Map> listActivity = busiActivityMapper.selectAll();
        List<Map> result = new ArrayList<>();
        for (BasicDBObject b : results
        ) {
            JSONObject jsonObject = new JSONObject(b);
            Map map = new HashMap();
            map.put("id",jsonObject.get("_id").toString());
            map.put("remark",jsonObject.get("remarks"));
            if(jsonObject.get("billTime") != null){
                map.put("billTime",jsonObject.get("billTime").toString());
            }else {
                map.put("billTime","");
            }
            if(jsonObject.get("billPrice") != null){
                map.put("billPrice",new Decimal128ToBigdecimal().convert(new Decimal128((Long) jsonObject.get("billPrice"))));
            }else {
                map.put("billPrice","");
            }
            if(jsonObject.get("amt") != null){
                map.put("amt",jsonObject.get("amt").toString());
            }else {
                map.put("amt","");
            }
            List<String> activity = new ArrayList<>();
            if(jsonObject.get("activityId") != null){
                JSONArray list = jsonObject.getJSONArray("activityId");
                for (Object aId:list
                     ) {
                    for (Map m:listActivity
                         ) {
                        if(aId.toString().equals(m.get("id").toString())){
                            if(m.get("name") != null){
                                activity.add(m.get("name").toString());
                            }
                        }
                    }
                }
                map.put("activity",activity);
            }else {
                map.put("activity","");
            }
            if(jsonObject.get("paymentType") != null){
                String paymentType = jsonObject.get("paymentType").toString();
                if(paymentType.equals("001")){
                    map.put("paymentType","微信");
                }else if(paymentType.equals("002")){
                    map.put("paymentType","支付宝");
                }else {
                    map.put("paymentType","其他");
                }
            }
            if(jsonObject.get("shipType") != null){
                String paymentType = jsonObject.get("shipType").toString();
                if(paymentType.equals("1")){
                    map.put("shipType","自提");
                }else if(paymentType.equals("2")){
                    map.put("shipType","外卖");
                }else {
                    map.put("shipType","其他");
                }
            }
            if(jsonObject.get("billStatus") != null){
                String billStatus = jsonObject.get("billStatus").toString();
                if(billStatus.equals("001")){
                    map.put("billStatus","未付款");
                }else if(billStatus.equals("002")){
                    map.put("billStatus","已付款");
                }else if(billStatus.equals("003")){
                    map.put("billStatus","付款中");
                }else if(billStatus.equals("004")){
                    map.put("billStatus","已取消");
                }else if(billStatus.equals("005")){
                    map.put("billStatus","支付失败");
                }else if(billStatus.equals("006")){
                    map.put("billStatus","配送中");
                }else {
                    map.put("billStatus","其他");
                }
            }else {
                map.put("billStatus","");
            }
            if(jsonObject.get("nickName") != null){
                map.put("nickName",jsonObject.get("nickName").toString());
            }else {
                map.put("nickName","");
            }
            if(jsonObject.get("phoneNumber") != null){
                map.put("phoneNumber",jsonObject.get("phoneNumber").toString());
            }else {
                map.put("phoneNumber","");
            }
            if(jsonObject.get("orders111") != null){
                List<Map> mapOrder = new ArrayList<>();
                JSONArray listOrder = jsonObject.getJSONArray("orders111");
                if (listOrder.size() > 0) {
                    for (int i = 0; i < listOrder.size(); i++) {
                      Map mapMenu = new HashMap();
                      JSONObject object = listOrder.getJSONObject(i);
                      mapMenu.put("id",object.get("_id"));
                      if(object.get("menuName") != null){
                          mapMenu.put("menuName",object.get("menuName"));
                      }else {
                          mapMenu.put("menuName","");
                      }
                        if(object.get("machineName") != null){
                            mapMenu.put("machineName",object.get("machineName"));
                        }else {
                            mapMenu.put("machineName","");
                        }
                        if(object.get("machineUuid") != null){
                            BaseMachineInfoEntry baseMachineInfoEntry = baseMachineInfoMapper.selectByUId(object.get("machineUuid").toString());
                            if(baseMachineInfoEntry != null){
                                mapMenu.put("machineAddress",baseMachineInfoEntry.getMachineDesc());
                            }else {
                                mapMenu.put("machineAddress","");
                            }
                        }else {
                            mapMenu.put("machineAddress","");
                        }
                      if(object.get("orderStatus") != null){
                          if (object.get("orderStatus").equals("143")){
                              mapMenu.put("orderStatus","已取杯");
                          }else if(object.get("orderStatus").equals("0")){
                              mapMenu.put("orderStatus","待制作");
                          }else if(object.get("orderStatus").equals("1")){
                              mapMenu.put("orderStatus","待制作");
                          }else if(object.get("orderStatus").equals("4")){
                              mapMenu.put("orderStatus","待制作");
                          }else if(object.get("orderStatus").equals("6")){
                              mapMenu.put("orderStatus","待制作");
                          }else if(object.get("orderStatus").equals("129")){
                              mapMenu.put("orderStatus","待制作");
                          }else if(object.get("orderStatus").equals("135")){
                              mapMenu.put("orderStatus","待制作");
                          }else {
                              mapMenu.put("orderStatus","出杯失败");
                              /**
                               * 7确认完毕放弃制作，8退款中，9退款成功，10已退券，11退款失败
                               */
                              if(object.get("orderStatus").equals("8")){
                                  mapMenu.put("orderRefundStatus","退款中");
                              }else if(object.get("orderStatus").equals("9")){
                                  mapMenu.put("orderRefundStatus","退款成功");
                              }else if(object.get("orderStatus").equals("10")){
                                  mapMenu.put("orderRefundStatus","已退券");
                              }else if(object.get("orderStatus").equals("11")){
                                  mapMenu.put("orderRefundStatus","退款失败");
                              }else {
                                  mapMenu.put("orderRefundStatus","");
                              }
                          }
                      }
                      if(mapMenu.get("orderRefundStatus") == null){
                          mapMenu.put("orderRefundStatus","");
                      }
                        if(object.get("takeCupTime") != null){
                            mapMenu.put("takeCupTime",object.get("takeCupTime"));
                        }else {
                            mapMenu.put("takeCupTime","");
                        }


                        if(object.get("properties") != null){
                        JSONArray properties = object.getJSONArray("properties");
                        List<Map> list= new ArrayList<>();
                            if (properties.size() > 0) {
                                for (int a = 0; a < properties.size(); a++) {
                                    Map map2  =new HashMap();
                                    JSONObject propertiesJSONObject = properties.getJSONObject(a);
                                    if(propertiesJSONObject.get("typeName") != null){
                                        map2.put("typeName",propertiesJSONObject.get("typeName").toString());
                                    }else {
                                        map2.put("typeName","");
                                    }
                                    if(propertiesJSONObject.get("proName") != null){
                                        map2.put("proName",propertiesJSONObject.get("proName").toString());
                                    }else {
                                        map2.put("proName","");
                                    }
                                    list.add(map2);
                                }

                                }
                            mapMenu.put("pro",list);
                            }

                        mapOrder.add(mapMenu);
                    }
                    map.put("mapOrder",mapOrder);
                    }
            }

            result.add(map);
        }
        logger.info("返回结果");
        return Response.success(result, (long) counts.size(), pageable.getPageSize(), pageable.getPageNumber());
    }

    /**
     * 功能描述: 查询bill条件拼接----------------辅助方法
     *
     * @param: CentralBillListCondition
     * @return: Query
     * @auther: admin
     * @date: 2018/8/27 11:48
     */
    private Criteria billQueryData(Criteria bill, CentralBillListCondition centralBillListCondition) throws Exception {
        //开始拼装bill查询数据
        //判断id是否为空
        if (centralBillListCondition.getId() != null && centralBillListCondition.getId() != "") {
            //id查询 订单号
            bill.and("_id").regex(centralBillListCondition.getId());
        }
        //判断支付流水号是否为空
        if (centralBillListCondition.getPaySerialNumbers() != null && centralBillListCondition.getPaySerialNumbers() !="") {
            //机器编号查询
            bill.and("paySerialNumbers").is(centralBillListCondition.getPaySerialNumbers());
        }
        if (centralBillListCondition.getShipType() != null && centralBillListCondition.getShipType() != "") {
            //机器编号查询
            bill.and("shipType").is(centralBillListCondition.getShipType());
        }
        //判断消费金额区间
        //初始终止存在
        if (centralBillListCondition.getPreAmt() != null && centralBillListCondition.getAmt() != null) {
            //金额查询
            bill.and("amt").
                    gte(new BigdecimalToDecimal128().convert(centralBillListCondition.getPreAmt())).lte(new BigdecimalToDecimal128().convert(centralBillListCondition.getAmt()));
        }
        //初始存在
        if (centralBillListCondition.getPreAmt() != null && centralBillListCondition.getAmt() == null) {
            //金额查询
            bill.and("amt").
                    gte(new BigdecimalToDecimal128().convert(centralBillListCondition.getPreAmt())).lte(new BigdecimalToDecimal128().convert(WEEKRENTAL));
        }
        //终止存在
        if (centralBillListCondition.getPreAmt() == null && centralBillListCondition.getAmt() != null) {
            //金额查询
            bill.and("amt").
                    gte(new BigdecimalToDecimal128().convert(PREWEEKRENTAL)).lte(new BigdecimalToDecimal128().convert(centralBillListCondition.getAmt()));
        }
        //判断订单时间区间
        //前后都不为空
        if (centralBillListCondition.getPreBillTime() != null && centralBillListCondition.getBillTime() != null) {
            Date preDate = sdf.parse(centralBillListCondition.getPreBillTime());
            Date date = sdf.parse(centralBillListCondition.getBillTime() + " 23:59:59");
            //订单时间查询
            bill.and("billTime").gte(preDate).lte(date);
        }
        //前为空
        if (centralBillListCondition.getPreBillTime() == null && centralBillListCondition.getBillTime() != null) {
            Date date = sdf.parse(centralBillListCondition.getBillTime());
            //订单时间查询
            bill.and("billTime").gte(new Date(date.getTime() + 30 * 3600 * 24 * 1000)).lte(date);
        }
        //后为空
        if (centralBillListCondition.getPreBillTime() != null && centralBillListCondition.getBillTime() == null) {
            Date preDate = sdf.parse(centralBillListCondition.getPreBillTime());
            //订单时间查询
            bill.and("billTime").gte(preDate).lte(new Date(preDate.getTime() - 30 * 3600 * 24 * 1000));
        }
        //判断用户是否为空
        if (centralBillListCondition.getNickName() != null && centralBillListCondition.getNickName() != "") {
            //机器编号查询
            bill.and("nickName").is(centralBillListCondition.getNickName());
        }
        //判断手机号是否为空
        if (centralBillListCondition.getPhoneNumber() != null && centralBillListCondition.getPhoneNumber() !="") {
            bill.and("phoneNumber").is(centralBillListCondition.getPhoneNumber());
        }
        //判断支付方式是否为空
        if (centralBillListCondition.getPaymentType() != null && centralBillListCondition.getPaymentType() != "") {
            bill.and("paymentType").is(centralBillListCondition.getPaymentType());
        }
        //判断状态是否为空
        if (centralBillListCondition.getBillStatus() != null && centralBillListCondition.getBillStatus() != "") {
            bill.and("billStatus").is(centralBillListCondition.getBillStatus());
        }
        return bill;
    }

    /**
     * 功能描述: 查询order条件拼接----------------辅助方法
     *
     * @param: CentralBillListCondition
     * @return: Query
     * @auther: admin
     * @date: 2018/8/27 11:48
     */
    private Criteria orderQueryData(Criteria order, CentralBillListCondition centralBillListCondition) throws Exception {

        //判断机器编号是否为空
        if (centralBillListCondition.getDeviceNumber() != null) {
            order.elemMatch(Criteria.where("deviceNumber").is(centralBillListCondition.getDeviceNumber()));
        }
        //判断代理是否为空
        if (centralBillListCondition.getAgent() != null && centralBillListCondition.getAgent() !="") {
            order.elemMatch(Criteria.where("agent").is(centralBillListCondition.getAgent()));
        }
        //判断网格是否为空
        if (centralBillListCondition.getGrid() != null) {
            order.elemMatch(Criteria.where("grid").is(centralBillListCondition.getGrid()));
        }
        //判断省市区
        if (centralBillListCondition.getProvince() != null) {
            order.elemMatch(Criteria.where("province").is(centralBillListCondition.getProvince()));
            if (centralBillListCondition.getCity() != null) {
                order.elemMatch(Criteria.where("city").is(centralBillListCondition.getCity()));
                if (centralBillListCondition.getArea() != null) {
                    order.elemMatch(Criteria.where("area").is(centralBillListCondition.getArea()));
                }
            }
        }
        return order;
    }

    /**
     * 功能描述:
     *
     * @param:
     * @return:
     * @auther: admin
     * @date: 2018/8/28 15:10
     */
    @Override
    public Response excle(HttpServletRequest request, HttpServletResponse response, CentralBillListCondition centralBillListCondition) throws Exception {
        //拼装分页信息
        logger.info("数据拼装" + centralBillListCondition);
        SpringbootMongoDBPageable pageable = new SpringbootMongoDBPageable();
        MongoDBPageModel pm = new MongoDBPageModel();
        pm.setPagesize(centralBillListCondition.getPageSize());
        pm.setPagenumber(centralBillListCondition.getPageNum());
        //排序规则
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(Direction.DESC, "billTime"));
        Sort sort = new Sort(orders);
        pm.setSort(sort);
        pageable.setPage(pm);

        //拼装关联信息
        LookupOperation lookupOperation = LookupOperation.newLookup().
                from("ordersData").
                localField("_id").
                foreignField("billId").
                as("orders111");
        //拼装具体查询信息
        Criteria order = Criteria.where("orders111").not().size(0);
        order = orderQueryData(order, centralBillListCondition);
        AggregationOperation match = Aggregation.match(order);

        Criteria bill = new Criteria();
        bill = billQueryData(bill, centralBillListCondition);

        AggregationOperation prematch = Aggregation.match(bill);
        //查询

        Aggregation aggregation = Aggregation.newAggregation(prematch, lookupOperation, match,
                //排序
                Aggregation.sort(pageable.getSort())

               );
        //总条数查询
        logger.info("查询条件" + aggregation.toString());
        List<BasicDBObject> results = mongoTemplate.aggregate(aggregation, "billData", BasicDBObject.class).getMappedResults();
        logger.info("查询结果" + results.size());
        List<OrderExcleEntry> orderExcleEntries = new ArrayList<>();
        int mnt = 0;
        for (BasicDBObject b : results
        ) {
            mnt++;
            System.out.println("循环拼装数据" + mnt);
            OrderExcleEntry orderExcleEntry = new OrderExcleEntry();
            JSONObject jsonObject = new JSONObject(b);
            String status = "";
            String type = "";
            if (jsonObject.get("billStatus") != null) {
                System.out.println("循环拼装数据状态" + mnt);
                if ("001".equals(jsonObject.get("billStatus").toString())) {
                    status = "未付款";
                } else if ("002".equals(jsonObject.get("billStatus").toString())) {
                    status = "已付款";
                } else if ("003".equals(jsonObject.get("billStatus").toString())) {
                    status = "退款中";
                } else {
                    status = "退款成功";
                }
            }
            if (jsonObject.get("paymentType") != null) {
                System.out.println("循环拼装数据支付方式" + mnt);
                if ("001".equals(jsonObject.get("paymentType").toString())) {
                    type = "微信";
                } else if ("002".equals(jsonObject.get("paymentType").toString())) {
                    type = "支付宝";
                } else {
                    type = "未支付";
                }
            }
            StringBuilder name = new StringBuilder();
            String isover = "";
            if (jsonObject.getJSONArray("orders111") != null) {
                JSONArray array1 = jsonObject.getJSONArray("orders111");
                System.out.println("循环拼装数据子集合" + mnt);

                if (array1.size() > 0) {
                    for (int i = 0; i < array1.size(); i++) {
                        if (array1.getJSONObject(i) != null) {
                            System.out.println("循环拼装数据子集合出货信息" + mnt);
                            JSONObject job = array1.getJSONObject(i);
                            if (job.get("menuName") != null) {
                                name.append(job.get("menuName").toString()).append(",");
                            }
                            if (job.get("orderStatus") != null && "2".equals(job.get("orderStatus").toString())) {
                                isover = "出货异常";
                            } else {
                                isover = "正常";
                            }
                            if (job.getJSONArray("properties") != null) {
                                JSONArray array2 = job.getJSONArray("properties");
                                if (array2.size() > 0) {
                                    System.out.println("循环拼装数据子集合饮品信息" + mnt);
                                    for (int j = 0; j < array2.size(); j++) {
                                        JSONObject jo = array2.getJSONObject(j);
                                        String typeName;
                                        if (jo.get("typeName") == null) {
                                            typeName = "";
                                        } else {
                                            typeName = jo.get("typeName").toString();
                                        }
                                        name.append(typeName);
                                        if (jo.get("proName") != null) {
                                            System.out.println("循环拼装数据子集合饮品名称拼装" + mnt);
                                            name.append(jo.get("proName").toString()).append(",");
                                        }
                                        System.out.println("循环拼装数据子集合饮品名称拼装完成" + mnt);
                                    }
                                    name.substring(0, name.length() - 1);
                                    System.out.println("循环拼装数据子集合饮品名称拼装完成-截取最后一位" + mnt);
                                }
                                System.out.println("循环拼装数据子集合饮品名称拼装完成-2-" + mnt);
                            }
                            System.out.println("循环拼装数据子集合饮品名称拼装完成-3-" + mnt);
                        }
                        System.out.println("循环拼装数据子集合饮品名称拼装完成-4-" + mnt);
                        name.append(";/");
                    }
                    System.out.println("循环拼装数据子集合饮品名称拼装完成-5-" + mnt);
                }
                System.out.println("循环拼装数据子集合饮品名称拼装完成-6-" + mnt);
                name.substring(0, name.length() - 1);
            }
            if (jsonObject.get("_id") != null) {
                System.out.println("循环拼装数据_id" + mnt);
                orderExcleEntry.setBillId(jsonObject.get("_id").toString());
            }
            if (mnt == 366) {
                System.out.println(366);
            }
            if (jsonObject.get("billTime") != null) {
                System.out.println("循环拼装数据billTime" + mnt + "" + jsonObject.get("billTime") + " " + jsonObject.get("_id").toString());
                orderExcleEntry.setBillTime(sdf2.format((Date) jsonObject.get("billTime")));
            }
            System.out.println("循环拼装数据billTime完成");
            if (jsonObject.get("phoneNumber") == null || "".equals((jsonObject.get("phoneNumber")))) {
                orderExcleEntry.setNickName(jsonObject.get("nickName").toString());
                System.out.println("循环拼装数据phoneNumber" + mnt);
            } else {
                if (jsonObject.get("nickName") != null) {
                    orderExcleEntry.setNickName(jsonObject.get("nickName").toString() + "/" + jsonObject.get("phoneNumber").toString());
                    System.out.println("循环拼装数据nickName" + mnt);
                }
            }
            if (jsonObject.get("amt") != null) {
                System.out.println("循环拼装数据amt" + mnt);
                orderExcleEntry.setAmt(jsonObject.get("amt").toString());
            }
            System.out.println("循环拼装数据Bo" + mnt);
            orderExcleEntry.setBillStatus(status);
            orderExcleEntry.setPaymentType(type);
            if (jsonObject.get("paySerialNumbers") != null && !"".equals((jsonObject.get("paySerialNumbers")))) {
                orderExcleEntry.setPaySerialNumbers(jsonObject.get("paySerialNumbers").toString());
            }
            //remark 未添加jsonObject.get("paySerialNumbers").toString()
            orderExcleEntry.setRemark("");
            orderExcleEntry.setDetails(name.toString());
            orderExcleEntry.setClearStatus(isover);

            orderExcleEntries.add(orderExcleEntry);
        }
        System.out.println("完成拼装返回数据" + mnt);
        response.setContentType("application/octet-stream");

        StringBuilder fileName = new StringBuilder();
        StringBuilder time = new StringBuilder();
        if (centralBillListCondition.getPreBillTime() != null) {
            time.append("(").append(centralBillListCondition.getPreBillTime()).append("-").append(centralBillListCondition.getBillTime()).append(")");
            fileName.append("订单数据导出(").append(centralBillListCondition.getPreBillTime()).append("-").
                    append(centralBillListCondition.getBillTime()).append(").xlsx");
        } else {
            time.append("(").append(sdf1.format(new Date())).append(")");
            fileName.append("订单数据导出(").append(sdf1.format(new Date())).append(").xlsx");
        }
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName.toString(), "utf-8"));
/**       response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.toString().getBytes(),"iso-8859-1")+".xlsx");*/
        response.flushBuffer();
        BillExcelUtils.exportExcel(orderSrcFileName, orderExcleEntries, response.getOutputStream(), time.toString());


        return Response.success();
    }

    @Override
    public Response excleOrder(HttpServletRequest request, HttpServletResponse response, MakeOrderCondition makeOrderCondition) throws Exception {
        makeOrderCondition = pageOrdersCondition(makeOrderCondition);
        Query query = new Query();
        //开始拼装查询数据
        //判断id是否为空
        if (makeOrderCondition.getId() != null) {
            query.addCriteria(Criteria.where("id").is(makeOrderCondition.getId()));//id查询 订单号
        }
        //判断机器编号是否为空
        if (makeOrderCondition.getDeviceNumber() != null) {
            query.addCriteria(Criteria.where("deviceNumber").is(makeOrderCondition.getDeviceNumber()));//机器编号查询
        }
        //判断代理是否为空
        if (makeOrderCondition.getAgent() != null) {
            query.addCriteria(Criteria.where("agent").is(makeOrderCondition.getAgent()));//机器编号查询
        }
        //判断网格是否为空
        if (makeOrderCondition.getGrid() != null) {
            query.addCriteria(Criteria.where("grid").is(makeOrderCondition.getGrid()));//机器编号查询
        }
        //判断制作时间区间
        //前后都不为空
        if (makeOrderCondition.getPreOrderTime() != null && makeOrderCondition.getOrderTime() != null) {
            Date preDate = sdf.parse(makeOrderCondition.getPreOrderTime());
            Date date = sdf.parse(makeOrderCondition.getOrderTime() + " 23:59:59");
            query.addCriteria(Criteria.where("productionTime").gte(preDate).lte(date));//制作时间查询
        }
        //前为空
        if (makeOrderCondition.getPreOrderTime() == null && makeOrderCondition.getOrderTime() != null) {
            Date date = sdf.parse(makeOrderCondition.getOrderTime());
            query.addCriteria(Criteria.where("productionTime").gte(new Date(date.getTime() + 30 * 3600 * 24 * 1000)).lte(date));//制作时间查询
        }
        //后为空
        if (makeOrderCondition.getPreOrderTime() != null && makeOrderCondition.getOrderTime() == null) {
            Date preDate = sdf.parse(makeOrderCondition.getPreOrderTime());
            query.addCriteria(Criteria.where("productionTime").gte(preDate).lte(new Date(preDate.getTime() - 30 * 3600 * 24 * 1000)));//制作时间查询
        }
        //判断省市区
        if (makeOrderCondition.getProvince() != null) {
            query.addCriteria(Criteria.where("province").is(makeOrderCondition.getProvince())); // 省查询
            if (makeOrderCondition.getCity() != null) {
                query.addCriteria(Criteria.where("city").is(makeOrderCondition.getCity()));  //// 市查询
                if (makeOrderCondition.getArea() != null) {
                    query.addCriteria(Criteria.where("area").is(makeOrderCondition.getArea())); // 区查询
                }
            }
        }
        //状态判断查询
        if (makeOrderCondition.getOrderStatus() != null) {
            query.addCriteria(Criteria.where("orderStatus").is(makeOrderCondition.getOrderStatus()));//状态查询
        }
        //查询总条数 total
        List<OrdersData> list = mongoTemplate.find(query, OrdersData.class);
        List<OrderBo> result = new ArrayList<>();
        for (OrdersData data:list
                ) {
            OrderBo orderBo = new OrderBo();
            if(data.getMachineUuid() != null){
                Map map = baseMachineInfoMapper.selectOrderByUuid(data.getMachineUuid());
                if(map != null){
                    orderBo.setCompanyName(map.get("companyName").toString());
                    orderBo.setGridCompany(map.get("gridCompanyName").toString());
                    orderBo.setMachineLocation(map.get("machineLocation").toString());
                    orderBo.setMachineName(map.get("machineName").toString());
                    orderBo.setAgentName(map.get("agentName").toString());
                }
            }
            if(data.getOrderTime() != null){
                orderBo.setOrderTime(TimeUtil.getStringByDate(data.getOrderTime()));
            }else {
                orderBo.setOrderTime("");
            }
            if(data.getProductionTime() != null){
                orderBo.setMakeTime(TimeUtil.getStringByDate(data.getProductionTime()));
            }else {
                orderBo.setMakeTime("");
            }
            if(data.getCouponId() != null){
                BusiCouponEntry busiCouponEntry = busiCouponMapper.selectByPrimaryKey(data.getCouponId());
                if(busiCouponEntry != null){
                    orderBo.setCoupon(busiCouponEntry.getCouponName());
                    orderBo.setCouponCode(busiCouponEntry.getCouponNo());
                }else {
                    orderBo.setCoupon("");
                    orderBo.setCouponCode("");
                }
            }
            if(data.getCouponId() != null || (data.getDiscounts() != null && new Decimal128ToBigdecimal().convert(data.getDiscount()).compareTo(new BigDecimal(0)) == 1) ){
                orderBo.setCupType("优惠出杯");
            }else {
                orderBo.setCupType("普通");
            }
            if(data.getDiscount() != null){
                orderBo.setDiscounts(new Decimal128ToBigdecimal().convert(data.getDiscount()).toString());
            }else {
                orderBo.setDiscounts("");
            }
            if(data.getErrorCode() != null){
                String a =baseGlobalInfoMapper.selectByErrorCode(data.getErrorCode());
                orderBo.setErrorCode(a);
            }else {
                orderBo.setErrorCode("");
            }

            orderBo.setBillId(data.getBillId());
            orderBo.setOrderId(data.getId());
            orderBo.setMenuName(data.getMenuName());

            if(data.getBillId() != null){
           BillData billData  =  mongoTemplate.findOne(new Query().addCriteria(Criteria.where("_id").is(data.getBillId())), BillData.class);
              if(billData != null){
                  String type =billData.getShipType();
                  if(type != null){
                      if(ONES.equals(type)){
                          orderBo.setSendType("自提");
                      }else if(TWOS.equals(type)){
                          orderBo.setSendType("外卖");
                      }else {
                          orderBo.setSendType("");
                      }
                  }
              }

            }else {
                orderBo.setSendType("");
            }
            if(data.getOrderStatus() !=null){
                Set<String> loading=new HashSet<>();
                Set<String> cupFalse=new HashSet<>();
                cupFalse.add("2");cupFalse.add("3");cupFalse.add("7");cupFalse.add("8");cupFalse.add("9");cupFalse.add("11");cupFalse.add("15");
                loading.add("0");loading.add("1");loading.add("4");loading.add("6");loading.add("129");loading.add("135");
                if(loading.contains(data.getOrderStatus())){
                    orderBo.setStatus("待制作");
                }
                if(cupFalse.contains(data.getOrderStatus())){
                    orderBo.setStatus("出杯失败");
                }
                if("143".equals(data.getOrderStatus())){
                    orderBo.setStatus("已取杯");
                }
            }
            if(data.getProperties() != null){
                for (Properties p:data.getProperties()
                        ) {
                    if("甜度".equals(p.getTypeName())){
                        orderBo.setSugar(p.getProName());
                    }
                    if("温度".equals(p.getTypeName())){
                        orderBo.setTemperature(p.getProName());
                    }
                }
            }
            if(orderBo.getSugar() == null){
                orderBo.setSugar("");
            }
            if(orderBo.getTemperature() == null){
                orderBo.setTemperature("");
            }
            result.add(orderBo) ;
        }
        response.setContentType("application/octet-stream");

        StringBuilder fileName = new StringBuilder();
        StringBuilder time = new StringBuilder();
        time.append("(").append(sdf1.format(new Date())).append(")");
        fileName.append("订单数据导出(").append(sdf1.format(new Date())).append(").xlsx");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName.toString(), "utf-8"));
/**       response.setHeader("Content-Disposition", "attachment;filename="+ new String(fileName.toString().getBytes(),"iso-8859-1")+".xlsx");*/
        response.flushBuffer();
        BillExcelUtils.exportOrderExcel(orderFileName, result, response.getOutputStream(), time.toString());


        return Response.success();
    }

	@Override
	public Response refundBillList(CentralBillListCondition centralBillListCondition) {
		SpringbootMongoDBPageable pageable = new SpringbootMongoDBPageable();
		MongoDBPageModel pm = new MongoDBPageModel();
		pm.setPagesize(centralBillListCondition.getPageSize());
		pm.setPagenumber(centralBillListCondition.getPageNum());
		pageable.setPage(pm);
		//查询条件组装
		//退款原因
		LookupOperation lookupAgg =Aggregation.lookup("billData","billId","_id","billData");
		Criteria refundDesc = Criteria.where("refundDesc").exists(true);
		MatchOperation matchOperation = Aggregation.match(refundDesc);
		Aggregation aggregation1 = Aggregation.newAggregation(lookupAgg, matchOperation, Aggregation.skip(((long) (centralBillListCondition.getPageNum() - 1) * centralBillListCondition.getPageSize())), Aggregation.limit(centralBillListCondition.getPageSize()));

		if (StringUtils.isNotEmpty(centralBillListCondition.getRefundCode())) {
			refundDesc.and("refundCode").is(centralBillListCondition.getRefundCode());
		}
		//订单编号
		if (StringUtils.isNotEmpty(centralBillListCondition.getBillId())) {
			refundDesc.and("billId").is(centralBillListCondition.getBillId());
		}
		// 制作单号
		if (StringUtils.isNotEmpty(centralBillListCondition.getMakeCode())) {
			refundDesc.and("makeCode").is(centralBillListCondition.getMakeCode());
		}
		//申请退款时间
		if (StringUtils.isNotEmpty(centralBillListCondition.getRefundStartDate())&&StringUtils.isNotEmpty(centralBillListCondition.getRefundEndDate())) {
			Date start = null;
			Date end = null;
			try {
				 start = new SimpleDateFormat("yyyy-MM-dd").parse(centralBillListCondition.getRefundStartDate());
				 end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(centralBillListCondition.getRefundEndDate()+" 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			refundDesc.and("applyRefundTime").gte(start).lte(end);
		}
		//用户手机号查询
		if (StringUtils.isNotEmpty(centralBillListCondition.getPhoneNumber())) {
			refundDesc.and("phoneNum").is(centralBillListCondition.getPhoneNumber());
		}
		// 退款状态 8退款中，9退款成功，10已退券，11退款失败，12已退款退券:退款成功且amt>0
		if (StringUtils.isNotEmpty(centralBillListCondition.getRefundState())) {
		    if("12".equals(centralBillListCondition.getRefundState())){
                refundDesc.and("orderStatus").is(centralBillListCondition.getRefundState())
                .and("amt").gt(0);
            }else{
                refundDesc.and("orderStatus").is(centralBillListCondition.getRefundState());
            }
		}
		List<OrderData> ordersData = mongoTemplate.aggregate(aggregation1, "ordersData", OrderData.class).getMappedResults();
		for(OrderData order: ordersData){
		    if(order.getApplyRefundTime()!=null){
		        order.setApplyRefundTimes(TimeUtil.getStringByDate(order.getApplyRefundTime()));
            }
        }
		return Response.success(ordersData,Long.valueOf(ordersData.size()+""), centralBillListCondition.getPageSize(), centralBillListCondition.getPageNum());
	}

	@Override
	public Response addComment(CentralBillListCondition centralBillListCondition) {
		WriteResult upsert = mongoTemplate.upsert(new Query(Criteria.where("_id").is(centralBillListCondition.getId())), Update.update("remarks", centralBillListCondition.getRemarks()), "ordersData");
		boolean updateOfExisting = upsert.isUpdateOfExisting();
		if (updateOfExisting) {
			return Response.success();
		}
		return Response.failure("找不到数据");
	}

	@Override
	public Response refundBillDetail(CentralBillListCondition centralBillListCondition) {
        LookupOperation lookupAgg =Aggregation.lookup("billData","billId","_id","billData");
        Criteria criteria = Criteria.where("refundDesc").exists(true);
        MatchOperation matchOperation = Aggregation.match(criteria);
        Aggregation aggregation = Aggregation.newAggregation(lookupAgg, matchOperation);
        criteria.and("_id").is(centralBillListCondition.getId());
		List<OrderData> basicDBObjects = mongoTemplate.aggregate(aggregation,"ordersData", OrderData.class).getMappedResults();
		BusiCouponEntry busiCouponByOederId = busiCouponMapper.getBusiCouponByOederId(centralBillListCondition.getGivenScountId());
		Map<String, Object> map = new HashMap<>(4);
		map.put("ordersData", basicDBObjects);
		map.put("busiCoupon", busiCouponByOederId);
		return Response.success(map);
	}

	@Override
    public Response refund(CentralBillListCondition centralBillListCondition) {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        JSONObject orderList = new JSONObject();
        orderList.put("orderId",centralBillListCondition.getId());
        orderList.put("billId",centralBillListCondition.getBillId());
        orderList.put("payType",centralBillListCondition.getPaymentType());
        orderList.put("returnReason",centralBillListCondition.getRefundDesc());
        JSONArray array = new JSONArray();
        array.add(orderList);
        paramMap.put("orderList",array);
        paramMap.put("isAbnormalRefund",true);
        HttpPost httpPost = new HttpPost("https://wxdevelop.hemaapp.com/newRetail/unifiedPay/unifiedRefund");
        StringEntity entity1 = new StringEntity(JSONObject.toJSONString(paramMap), HTTP.UTF_8);
        entity1.setContentType("application/json");
        httpPost.setEntity(entity1);
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity resEntity = response.getEntity();
            System.out.println("响应状态："+response.getStatusLine());
            //System.out.println("响应内容："+ EntityUtils.toString(resEntity));
            JSONObject result = JSONObject.parseObject(EntityUtils.toString(resEntity));
            if(result.get("info")==null){
                return Response.failure("退款失败");
            }else{
                JSONObject info =  JSONObject.parseObject(result.get("info").toString());
                if((boolean) result.get("success") && info.get("result_code").equals("SUCCESS")){
                    return Response.success("退款成功");
                }else {
                    return Response.success("退款失败");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Response.failure("退款失败");
        }finally {
            try {
                if(httpClient!=null){
                    httpClient.close();
                }
                if(response!=null){
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}

	@Override
	public Response refundExcel( HttpServletResponse response, CentralBillListCondition centralBillListCondition) {
		//查询条件组装
		//退款原因
		LookupOperation lookupAgg =Aggregation.lookup("billData","billId","_id","billData");
		Criteria refundDesc = Criteria.where("refundDesc").exists(true);
		MatchOperation matchOperation = Aggregation.match(refundDesc);
		Aggregation aggregation1 = Aggregation.newAggregation(lookupAgg, matchOperation);

		if (StringUtils.isNotEmpty(centralBillListCondition.getRefundCode())) {
			refundDesc.and("refundCode").is(centralBillListCondition.getRefundCode());
		}
		//订单编号
		if (StringUtils.isNotEmpty(centralBillListCondition.getBillId())) {
			refundDesc.and("billId").is(centralBillListCondition.getBillId());
		}
		// 制作单号
		if (StringUtils.isNotEmpty(centralBillListCondition.getMakeCode())) {
			refundDesc.and("makeCode").is(centralBillListCondition.getMakeCode());
		}
		//申请退款时间
		if (StringUtils.isNotEmpty(centralBillListCondition.getRefundStartDate())&&StringUtils.isNotEmpty(centralBillListCondition.getRefundEndDate())) {
			Date start = null;
			Date end = null;
			try {
				start = new SimpleDateFormat("yyyy-MM-dd").parse(centralBillListCondition.getRefundStartDate());
				end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(centralBillListCondition.getRefundEndDate()+" 23:59:59");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			refundDesc.and("applyRefundTime").gte(start).lte(end);
		}
		//用户手机号查询
        if (StringUtils.isNotEmpty(centralBillListCondition.getPhoneNumber())) {
            refundDesc.and("phoneNum").is(centralBillListCondition.getPhoneNumber());
        }

		// 退款状态
		if (StringUtils.isNotEmpty(centralBillListCondition.getRefundState())) {
            if("12".equals(centralBillListCondition.getRefundState())){
                refundDesc.and("orderStatus").is(centralBillListCondition.getRefundState())
                        .and("amt").gt(0);
            }else{
                refundDesc.and("orderStatus").is(centralBillListCondition.getRefundState());
            }
		}
		List<OrderData> list = mongoTemplate.aggregate(aggregation1, "ordersData", OrderData.class).getMappedResults();
		for(OrderData order:list){
		    if(order.getSuccessRefundTime()!=null){
		        if(order.getSuccessRefundTime() instanceof String){
		            order.setSuccessRefundTimes(order.getSuccessRefundTime().toString());
                }else{
		            Date d = (Date)(order.getSuccessRefundTime());
		            order.setSuccessRefundTimes(TimeUtil.getStringByDate(d));
                }
            }
        }
		if (list != null && list.size() > 0) {
			ExcelData data = new ExcelData();
			data.setName("退款订单表");
			List<String> titles = new ArrayList<String>();
			titles.add("退款单号");
			titles.add("出杯单号");
			titles.add("退款时间");
			titles.add("退款饮品名称");
			titles.add("选项");
			titles.add("退款金额");
			titles.add("退款状态");
			titles.add("退款原因");
			titles.add("退款用户");
			titles.add("购买订单");
			titles.add("补偿券码");
			data.setTitles(titles);
			List<List<Object>> rows = new ArrayList<List<Object>>();
			orderStatus[] os = orderStatus.values();
			RefundCode[] rc = RefundCode.values();
			list.forEach(dto -> {
				List<Object> row = new ArrayList<Object>();
				row.add(dto.getRefundCode());
				row.add(dto.getMakeCode());
				row.add(dto.getSuccessRefundTimes());
				row.add(dto.getMenuName());
                List<OrderPropertyData> properties = dto.getProperties();
                String propertiesStr = "";
                for(int i=0;i<properties.size();i++){
                    OrderPropertyData dbObject = properties.get(i);
                    propertiesStr = propertiesStr + dbObject.getProName() +"|";
                }
                propertiesStr = propertiesStr.substring(0,propertiesStr.length()-1);
				row.add(propertiesStr);
                row.add(dto.getAmt());
                for(int i=0;i<os.length;i++){
                    if(dto.getOrderStatus().equals(os[i].getCode())){
                        row.add(os[i].getMessage());
                    }
                }
                if(dto.getRefundDesc().isEmpty()){
                    row.add("");
                }else{
                    Pattern p = Pattern.compile("[0-9]{1,}");
                    Matcher m = p.matcher(dto.getRefundDesc());
                    if(!m.matches()){
                        row.add(dto.getRefundDesc());
                    }else{
                        for(int i=0;i<rc.length;i++){
                            if(dto.getRefundDesc().equals(rc[i].getCode())){
                                row.add(rc[i].getMessage());
                            }
                        }
                    }
                }
                if(dto.getBillData().size()>0){
                    row.add(dto.getBillData().get(0).getNickName());
                }else{
                    row.add("");
                }
                row.add(dto.getBillId());
                row.add(dto.getGivenScountId());
				rows.add(row);
			});
			data.setRows(rows);
			try {
				ExcelUtil.exportExcel(response, "退款订单表", data);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
     * 功能描述: 参数调整 ""  转  null

     * @param:
     * @return:
     * @author: cwz
     * @date: 2018/10/12 14:32
     */
    private MakeOrderCondition pageOrdersCondition(MakeOrderCondition makeOrderCondition) {
        if (EMPTY.equals(makeOrderCondition.getOrderTime())) {
            makeOrderCondition.setOrderTime(null);
        }
        if (EMPTY.equals(makeOrderCondition.getPreOrderTime())) {
            makeOrderCondition.setPreOrderTime(null);
        }
        if (EMPTY.equals(makeOrderCondition.getAgent())) {
            makeOrderCondition.setAgent(null);
        }
        if (EMPTY.equals(makeOrderCondition.getArea())) {
            makeOrderCondition.setArea(null);
        }
        if (EMPTY.equals(makeOrderCondition.getCity())) {
            makeOrderCondition.setCity(null);
        }
        if (EMPTY.equals(makeOrderCondition.getDeviceNumber())) {
            makeOrderCondition.setDeviceNumber(null);
        }
        if (EMPTY.equals(makeOrderCondition.getGrid())) {
            makeOrderCondition.setGrid(null);
        }
        if (EMPTY.equals(makeOrderCondition.getId())) {
            makeOrderCondition.setId(null);
        }
        if (EMPTY.equals(makeOrderCondition.getOrderStatus())) {
            makeOrderCondition.setOrderStatus(null);
        }
        if (EMPTY.equals(makeOrderCondition.getProvince())) {
            makeOrderCondition.setProvince(null);
        }
        return makeOrderCondition;
    }

    /**
     * 功能描述: 参数调整 ""  转  null
     *
     * @param:
     * @return:
     * @author: cwz
     * @date: 2018/10/12 14:32
     */
    private CentralBillListCondition centralBillListCondition(CentralBillListCondition centralBillListCondition) {
        if (EMPTY.equals(centralBillListCondition.getPreBillTime())) {
            centralBillListCondition.setPreBillTime(null);
        }
        if (EMPTY.equals(centralBillListCondition.getBillTime())) {
            centralBillListCondition.setBillTime(null);
        }
        if (EMPTY.equals(centralBillListCondition.getAmt())) {
            centralBillListCondition.setAmt(null);
        }
        if (EMPTY.equals(centralBillListCondition.getPreAmt())) {
            centralBillListCondition.setPreAmt(null);
        }
        if (EMPTY.equals(centralBillListCondition.getAgent())) {
            centralBillListCondition.setAgent(null);
        }
        if (EMPTY.equals(centralBillListCondition.getArea())) {
            centralBillListCondition.setArea(null);
        }
        if (EMPTY.equals(centralBillListCondition.getBillStatus())) {
            centralBillListCondition.setBillStatus(null);
        }
        if (EMPTY.equals(centralBillListCondition.getCity())) {
            centralBillListCondition.setCity(null);
        }
        if (EMPTY.equals(centralBillListCondition.getDeviceNumber())) {
            centralBillListCondition.setDeviceNumber(null);
        }
        if (EMPTY.equals(centralBillListCondition.getGrid())) {
            centralBillListCondition.setGrid(null);
        }
        if (EMPTY.equals(centralBillListCondition.getId())) {
            centralBillListCondition.setId(null);
        }
        if (EMPTY.equals(centralBillListCondition.getNickName())) {
            centralBillListCondition.setNickName(null);
        }
        if (EMPTY.equals(centralBillListCondition.getOrderStatus())) {
            centralBillListCondition.setOrderStatus(null);
        }
        if (EMPTY.equals(centralBillListCondition.getPaymentType())) {
            centralBillListCondition.setPaymentType(null);
        }
        if (EMPTY.equals(centralBillListCondition.getPaySerialNumbers())) {
            centralBillListCondition.setPaySerialNumbers(null);
        }
        if (EMPTY.equals(centralBillListCondition.getPhoneNumber())) {
            centralBillListCondition.setPhoneNumber(null);
        }
        if (EMPTY.equals(centralBillListCondition.getProvince())) {
            centralBillListCondition.setProvince(null);
        }
        return centralBillListCondition;
    }

}
