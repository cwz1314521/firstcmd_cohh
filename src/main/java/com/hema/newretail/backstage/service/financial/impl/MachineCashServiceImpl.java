package com.hema.newretail.backstage.service.financial.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.financial.MachineCashListCondition;
import com.hema.newretail.backstage.common.queryparam.financial.RetiredCashCondition;
import com.hema.newretail.backstage.common.utils.RedisUtils;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.StringUtil;
import com.hema.newretail.backstage.common.utils.TimeUtil;
import com.hema.newretail.backstage.common.utils.excel.DeviceCashExcelUtils;
import com.hema.newretail.backstage.dao.BaseMachineInfoMapper;
import com.hema.newretail.backstage.dao.BusiAccountLogMapper;
import com.hema.newretail.backstage.entry.BaseMachineInfoEntry;
import com.hema.newretail.backstage.entry.BusiAccountLog;
import com.hema.newretail.backstage.interceptor.AuthConstants;
import com.hema.newretail.backstage.model.financial.CashListBo;
import com.hema.newretail.backstage.service.financial.MachineCashService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Department 新零售
 * @ClassName MachineCashServiceImpl
 * @Description 设备押金管理
 * @Author ---CWZ
 * @Date 2019/1/7 17:10
 * @Version 1.0
 **/

@Service
public class MachineCashServiceImpl implements MachineCashService {
    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    private final static String EMPTY ="";
    private static final String SDFSIX = "yyyy-MM-dd";
    private static final String SDFEIGHT = "yyyy-MM-dd HH:mm:ss";

    @Autowired
    private BaseMachineInfoMapper baseMachineInfoMapper;

    @Value(value = "${excelModel.deviceCashExcelName}")
    private String deviceCashExcelName;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private RedisTemplate staticsRedisTemplate;

    @Autowired
    private BusiAccountLogMapper busiAccountLogMapper;


    /**
     * 功能描述:设备押金管理列表
     *
     * @return Response
     * @author cwz
     * @date 2019/1/7 17:04
     */
    @Override
    public Response list(MachineCashListCondition condition) throws Exception{
        if(condition.getStartGmt() != null && !EMPTY.equals(condition.getStartGmt())){
            /*时间前有*/
            if(condition.getEndGmt() != null && !EMPTY.equals(condition.getEndGmt())){

                /*时间前后都有*/

                condition.setStartGmts(TimeUtil.stringToDate(condition.getStartGmt(),SDFSIX));
                condition.setEndGmts(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndGmt(),SDFSIX)));
            }else {


                condition.setEndGmts(new Date());
                condition.setStartGmts(TimeUtil.stringToDate(condition.getStartGmt(),SDFSIX));
                /*时间前有后没有*/

            }
        }else {
            /*时间前没有*/
            if(condition.getEndGmt() != null && !EMPTY.equals(condition.getEndGmt())){

                condition.setStartGmts(TimeUtil.threeMonthAgo(TimeUtil.stringToDate(condition.getEndGmt(), SDFSIX)));
                /*时间前没有后有*/
                condition.setEndGmts(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndGmt(),SDFSIX)));
            }
        }
        Page<CashListBo> page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        List<CashListBo> list = baseMachineInfoMapper.selectCashList(condition);

        Map<String,List> result = new HashMap<>(2);
        result.put("list",page.getResult());
        List<Map> mapAll = baseMachineInfoMapper.selectCashAllList();
        result.put("all",mapAll);
        return Response.success(result,page.getTotal(),condition.getPageSize(),condition.getPageNum());
    }

    /**
     * 功能描述:设备押金管理列表导出（get请求  表单提交）
     *
     * @return Response
     * @author cwz
     * @date 2019/1/7 17:04
     */
    @Override
    public Response excel(HttpServletResponse response,MachineCashListCondition condition) throws Exception{

        if(condition.getStartGmt() != null && !EMPTY.equals(condition.getStartGmt())){
            /*时间前有*/
            if(condition.getEndGmt() != null && !EMPTY.equals(condition.getEndGmt())){

                /*时间前后都有*/
                condition.setEndGmts(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndGmt(),SDFSIX)));
                condition.setStartGmts(TimeUtil.stringToDate(condition.getStartGmt(),SDFSIX));
            }else {
                /*时间前有后没有*/

                condition.setStartGmts(TimeUtil.stringToDate(condition.getStartGmt(),SDFSIX));
                condition.setEndGmts(new Date());
            }
        }else {
            /*时间前没有*/
            if(condition.getEndGmt() != null && !EMPTY.equals(condition.getEndGmt())){
                /*时间前没有后有*/
                condition.setStartGmts(TimeUtil.threeMonthAgo(TimeUtil.stringToDate(condition.getEndGmt(), SDFSIX)));
                condition.setEndGmts(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndGmt(),SDFSIX)));
            }
        }
        List<CashListBo> list = baseMachineInfoMapper.selectCashList(condition);
        logger.info("设置ContentType......");
        response.setContentType("application/octet-stream");
        logger.info("设置文件名......");
        StringBuilder fileName = new StringBuilder();
        fileName.append(new Date()).append(".xlsx");
        String time = TimeUtil.getStringByDate(new Date());
        logger.info("设置Header......");
        response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName.toString(), "utf-8"));
        response.flushBuffer();
        logger.info("调用AgentExcelUtils拼装类......");
        DeviceCashExcelUtils.exportExcel(deviceCashExcelName,list,response.getOutputStream(),time);
        return Response.success();
    }

    /**
     * 功能描述:批量设置补贴规则
     *
     * @return Response
     * @author cwz
     * @date 2019/1/7 17:04
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response batchRule(HttpServletRequest request, RetiredCashCondition condition) {
        String userinfoJson = redisUtils.hget(AuthConstants.SESSION + request.getSession().getId(), AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
        if (userinfoJson == null) {
            logger.error("未检测到登录人数据");
            return Response.failure("未检测到登录人数据");
        }
        JSONObject jsStr = JSONObject.parseObject(userinfoJson);
        long userId = Long.valueOf(String.valueOf(jsStr.get("id"))).longValue();
        List<Long> longs = StringUtil.stringsToLong(condition.getIds());
        condition.setOperator(userId);
        condition.setIdList(longs);
        // add by zhs at 2019-02-13 start
        List<Map<String, Object>> list = baseMachineInfoMapper.selectById(longs);
        // add by zhs at 2019-02-13 end
        int i = baseMachineInfoMapper.updateRule(condition);

        if (i > 0) {
            // add by zhs at 2019-02-13 start
            Map<String, String> param = new HashMap<>(16);
            int defaultCupNum = 0;
            for (Map<String, Object> map : list) {
                Long id = (Long) map.get("id");
                String uuid = (String) map.get("uuid");
                if (null == id || StringUtils.isEmpty(uuid)) {
                    continue;
                }
                BigDecimal cashPledge = (BigDecimal) map.get("cashPledge");
                BigDecimal minCashPledge = (BigDecimal) map.get("minCashPledge");
                Long ruleCup = (Long) map.get("ruleCup");
                BigDecimal ruleCupMoney = (BigDecimal) map.get("ruleCupMoney");
                if (condition.getCup() != ruleCup.intValue() || !condition.getRuleCupMoney().equals(ruleCupMoney) || !condition.getMinCashPledge().equals(minCashPledge)) {
                    param.put(uuid, defaultCupNum + "");
                }
            }
            if (!param.isEmpty()) {
                HashOperations<String, String, String> hashOperations = staticsRedisTemplate.opsForHash();
                hashOperations.putAll("machineProfitStatics", param);
                Map<String, String> entries = hashOperations.entries("machineProfitStatics");
                System.out.println(entries);

//                redisUtils.hmset("machineProfitStatics", param, 1);
                System.out.println(((String)param.get("a")).equals("1"));
            }
            // add by zhs at 2019-02-13 end
            return Response.success();
        } else {
            return Response.failure();
        }
    }

    /**
     * 功能描述:设置补贴规则
     *
     * @return Response
     * @author cwz
     * @date 2019/1/7 17:04
     */
    @Override
    public Response rule(HttpServletRequest request, RetiredCashCondition condition) {
        String userinfoJson = redisUtils.hget(AuthConstants.SESSION + request.getSession().getId(), AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
        if(userinfoJson == null){
            logger.error("未检测到登录人数据");
            return Response.failure("未检测到登录人数据");
        }
        JSONObject jsStr = JSONObject.parseObject(userinfoJson);
        long userId = Long.valueOf(String.valueOf(jsStr.get("id"))).longValue();
        condition.setOperator(userId);
        /**
         * 生成代理资金账户表记录
         * 1代理收益账户 2代理原料账户 3代理押金返还 4代理原押金 5代理冻结账户
         */

        BaseMachineInfoEntry infoEntry = baseMachineInfoMapper.selectByPrimaryKey(condition.getId());
        BusiAccountLog busiAccountLog = new BusiAccountLog();
        busiAccountLog.setCompanyId(infoEntry.getAgentId().toString());
        busiAccountLog.setActionType("16");
        busiAccountLog.setContent("代理押金随机器押金变化");
        busiAccountLog.setCreateDate(new Date());
        busiAccountLog.setNumber(condition.getCashPledge().subtract(infoEntry.getCashPledge()));
        busiAccountLog.setType("4");
        System.out.println(busiAccountLog.getNumber());
        busiAccountLogMapper.insert(busiAccountLog);
        int i = baseMachineInfoMapper.updateRule(condition);
        if(i>0){
            return Response.success();
        }else {
            return Response.failure();
        }
    }

    /**
     * 功能描述:设置违约金
     *
     * @return Response
     * @author cwz
     * @date 2019/1/7 17:04
     */
    @Override
    public Response penalSum(HttpServletRequest request, RetiredCashCondition condition) {

        String userinfoJson = redisUtils.hget(AuthConstants.SESSION + request.getSession().getId(), AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
        if(userinfoJson == null){
            logger.error("未检测到登录人数据");
            return Response.failure("未检测到登录人数据");
        }
        JSONObject jsStr = JSONObject.parseObject(userinfoJson);
        long userId = Long.valueOf(String.valueOf(jsStr.get("id"))).longValue();
        BaseMachineInfoEntry entry = baseMachineInfoMapper.selectByPrimaryKey(condition.getId());
        logger.info("原数据"+entry.getPenalSum());
        logger.info("传入数据"+condition.getPenalSum());
        BigDecimal add = condition.getPenalSum().add(entry.getPenalSum());
        logger.info("加后数据"+add);
        condition.setPenalSum(add);
        logger.info("二次拼装数据"+condition.getPenalSum());
        condition.setOperator(userId);
        int i = baseMachineInfoMapper.updateRule(condition);
        if(i>0){
            return Response.success();
        }else {
            return Response.failure();
        }
    }

    /**
     * 功能描述:停止补贴
     *
     * @return Response
     * @author cwz
     * @date 2019/1/7 17:04
     */
    @Override
    public Response stopCash(HttpServletRequest request, RetiredCashCondition condition) {
        String userinfoJson = redisUtils.hget(AuthConstants.SESSION + request.getSession().getId(), AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
        if(userinfoJson == null){
            logger.error("未检测到登录人数据");
            return Response.failure("未检测到登录人数据");
        }
        JSONObject jsStr = JSONObject.parseObject(userinfoJson);
        long userId = Long.valueOf(String.valueOf(jsStr.get("id"))).longValue();
        condition.setOperator(userId);
        int i = baseMachineInfoMapper.updateRule(condition);
        if(i>0){
            return Response.success();
        }else {
            return Response.failure();
        }
    }

    /**
     * 功能描述:停止分润
     *
     * @return Response
     * @author cwz
     * @date 2019/1/7 17:04
     */
    @Override
    public Response stopSeeSplitting(HttpServletRequest request, RetiredCashCondition condition) {
                String userinfoJson = redisUtils.hget(AuthConstants.SESSION + request.getSession().getId(), AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
        if(userinfoJson == null){
            logger.error("未检测到登录人数据");
            return Response.failure("未检测到登录人数据");
        }
        JSONObject jsStr = JSONObject.parseObject(userinfoJson);
        long userId = Long.valueOf(String.valueOf(jsStr.get("id"))).longValue();
        condition.setOperator(userId);
        int i = baseMachineInfoMapper.updateRule(condition);
        if(i>0){
            return Response.success();
        }else {
            return Response.failure();
        }
    }
}
