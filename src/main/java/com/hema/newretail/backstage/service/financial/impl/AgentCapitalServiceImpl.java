package com.hema.newretail.backstage.service.financial.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.financial.AgentCapitalCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.TimeUtil;
import com.hema.newretail.backstage.common.utils.excel.DeviceCashExcelUtils;
import com.hema.newretail.backstage.dao.AgentUserMapper;
import com.hema.newretail.backstage.dao.BusiAccountLogMapper;
import com.hema.newretail.backstage.dao.BusiCompanyAccountMapper;
import com.hema.newretail.backstage.entry.BusiAccountLog;
import com.hema.newretail.backstage.service.financial.AgentCapitalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Department 新零售
 * @ClassName AgentCapitalServiceImpl
 * @Description
 * @Author ---CWZ
 * @Date 2019/1/9 18:48
 * @Version 1.0
 **/

@Service
public class AgentCapitalServiceImpl implements AgentCapitalService {


    @Resource
    private MongoTemplate secondaryMongoTemplate;

    @Autowired
    private AgentUserMapper agentUserMapper;

    @Autowired
    private BusiAccountLogMapper busiAccountLogMapper;

    @Value(value = "${excelModel.agentAccountExcelName}")
    private String agentAccountExcelName;

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    private final static String EMPTY ="";
    private static final String SDFSIX = "yyyy-MM-dd";
    private static final String RAWAMOUNT = "rawAmount";
    private static final String PREAMOUNT = "preAmount";
    private static final String AMOUNT = "amount";
    private static final String EARNINGAMOUNT = "earningAmount";
    private static final String FREEZEAMOUNT = "freezeAmount";
    /**
     * 功能描述:  list
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/9 17:43
     */
    @Override
    public Response list(AgentCapitalCondition condition) throws Exception{
        if(condition.getStartGmt() != null && !EMPTY.equals(condition.getStartGmt())){
            /*时间前有*/
            if(condition.getEndGmt() != null && !EMPTY.equals(condition.getEndGmt())){

                /*时间前后都有*/
                condition.setEndGmts(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndGmt(),SDFSIX)));
                condition.setStartGmts(TimeUtil.stringToDate(condition.getStartGmt(),SDFSIX));
            }else {
                /*时间前有后没有*/
                condition.setEndGmts(new Date());
                condition.setStartGmts(TimeUtil.stringToDate(condition.getStartGmt(),SDFSIX));
            }
        }else {
            /*时间前没有*/
            if(condition.getEndGmt() != null && !EMPTY.equals(condition.getEndGmt())){

                condition.setStartGmts(TimeUtil.threeMonthAgo(TimeUtil.stringToDate(condition.getEndGmt(), SDFSIX)));
                /*时间前没有后有*/
                condition.setEndGmts(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndGmt(),SDFSIX)));
            }
        }
        Page<Map> page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        agentUserMapper.selectAgentCapitalList(condition);
        return Response.success(page.getResult(),page.getTotal(),condition.getPageSize(),condition.getPageNum());
    }

    /**
     * 功能描述:清算
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/9 17:43
     */
    @Override
    @Transactional
    public Response clearing(AgentCapitalCondition condition) {
        AgentCapitalCondition capitalCondition = new AgentCapitalCondition();
        capitalCondition.setAgentId(condition.getAgentId());
        List<Map> mapList = agentUserMapper.selectAgentCapitalList(capitalCondition);
        BusiAccountLog busiAccountLog = new BusiAccountLog();
        if(condition.getAgentId() != null) {
            busiAccountLog.setCompanyId(condition.getAgentId().toString());
        }
        busiAccountLog.setActionType("6");
        busiAccountLog.setContent(condition.getRemark());
        busiAccountLog.setCreateDate(new Date());
        /**
         * 1代理收益账户 2代理原料账户 3代理押金返还 4代理原押金
         */
        if(mapList.get(0) != null && mapList.get(0).get(RAWAMOUNT) != null){
            BigDecimal rawAmount = new BigDecimal(mapList.get(0).get(RAWAMOUNT).toString());
            busiAccountLog.setNumber(rawAmount);
            busiAccountLog.setType("2");
            busiAccountLogMapper.insert(busiAccountLog);
        }
        if(mapList.get(0) != null && mapList.get(0).get(PREAMOUNT) != null){
            BigDecimal rawAmount = new BigDecimal(mapList.get(0).get(PREAMOUNT).toString());
            busiAccountLog.setNumber(rawAmount);
            busiAccountLog.setType("4");
            busiAccountLogMapper.insert(busiAccountLog);
        }
        if(mapList.get(0) != null && mapList.get(0).get(AMOUNT) != null){
            BigDecimal rawAmount = new BigDecimal(mapList.get(0).get(AMOUNT).toString());
            busiAccountLog.setNumber(rawAmount);
            busiAccountLog.setType("3");
            busiAccountLogMapper.insert(busiAccountLog);
        }

            agentUserMapper.updateClearing(condition);
            return Response.success();

    }

    /**
     * 功能描述:修改信息
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/9 17:43
     */
    @Override
    @Transactional
    public Response edit(AgentCapitalCondition condition) {
        AgentCapitalCondition capitalCondition = new AgentCapitalCondition();
        capitalCondition.setAgentId(condition.getAgentId());
        List<Map> mapList = agentUserMapper.selectAgentCapitalList(capitalCondition);

        BusiAccountLog busiAccountLog = new BusiAccountLog();
        if(condition.getAgentId() != null) {
            busiAccountLog.setCompanyId(condition.getAgentId().toString());
        }
        busiAccountLog.setActionType("16");
        busiAccountLog.setContent(condition.getRemark());
        busiAccountLog.setCreateDate(new Date());
        /**
         * 1代理收益账户 2代理原料账户 3代理押金返还 4代理原押金 5代理冻结账户
         * SET a.amount = #{earningAmount,jdbcType=DECIMAL},
         *  a.freeze_amount = #{freezeAmount,jdbcType=DECIMAL},
         *  a.gmt_modified = NOW(),
         *  b.amount = #{rawAmount,jdbcType=DECIMAL},
         */

        if (mapList.get(0) != null && mapList.get(0).get(EARNINGAMOUNT) != null && null != condition.getEarningAmount() && condition.getEarningAmount().doubleValue() != 0) {
            BigDecimal rawAmount = new BigDecimal(mapList.get(0).get(EARNINGAMOUNT).toString());
            busiAccountLog.setNumber(condition.getEarningAmount());
            busiAccountLog.setType("1");
            System.out.println(busiAccountLog.getNumber());
            busiAccountLogMapper.insert(busiAccountLog);
            condition.setEarningAmount(rawAmount.add(condition.getEarningAmount()));
        }

        if (mapList.get(0) != null && mapList.get(0).get(FREEZEAMOUNT) != null && null != condition.getFreezeAmount() && condition.getFreezeAmount().doubleValue() != 0) {
            BigDecimal rawAmount = new BigDecimal(mapList.get(0).get(FREEZEAMOUNT).toString());
            busiAccountLog.setNumber(condition.getFreezeAmount());
            busiAccountLog.setType("5");
            busiAccountLogMapper.insert(busiAccountLog);
            condition.setFreezeAmount(rawAmount.add(condition.getFreezeAmount()));
        }
        if (mapList.get(0) != null && mapList.get(0).get(RAWAMOUNT) != null && null != condition.getRawAmount() && condition.getRawAmount().doubleValue() != 0) {
            BigDecimal rawAmount = new BigDecimal(mapList.get(0).get(RAWAMOUNT).toString());
            busiAccountLog.setNumber(condition.getRawAmount());
            busiAccountLog.setType("2");
            busiAccountLogMapper.insert(busiAccountLog);
            condition.setRawAmount(rawAmount.add(condition.getRawAmount()));
        }
        agentUserMapper.updateEdit(condition);
        return Response.success();

    }

    /**
     * 功能描述:  excel
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/9 17:43
     */
    @Override
    public Response excel(HttpServletResponse response, AgentCapitalCondition condition) throws Exception{
        if(condition.getStartGmt() != null && !EMPTY.equals(condition.getStartGmt())){
            /*时间前有*/
            if(condition.getEndGmt() != null && !EMPTY.equals(condition.getEndGmt())){

                /*时间前后都有*/
                condition.setEndGmts(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndGmt(),SDFSIX)));
                condition.setStartGmts(TimeUtil.stringToDate(condition.getStartGmt(),SDFSIX));
            }else {
                /*时间前有后没有*/
                condition.setEndGmts(new Date());
                condition.setStartGmts(TimeUtil.stringToDate(condition.getStartGmt(),SDFSIX));
            }
        }else {
            /*时间前没有*/
            if(condition.getEndGmt() != null && !EMPTY.equals(condition.getEndGmt())){

                condition.setStartGmts(TimeUtil.threeMonthAgo(TimeUtil.stringToDate(condition.getEndGmt(), SDFSIX)));
                /*时间前没有后有*/
                condition.setEndGmts(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndGmt(),SDFSIX)));
            }
        }

        List<Map> mapList = agentUserMapper.selectAgentCapitalList(condition);
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
        DeviceCashExcelUtils.exportAgentAccountExcel(agentAccountExcelName,mapList,response.getOutputStream(),time);
        return Response.success();
    }
}
