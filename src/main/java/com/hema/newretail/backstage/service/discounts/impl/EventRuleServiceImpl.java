package com.hema.newretail.backstage.service.discounts.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.backstage.common.queryparam.discounts.*;
import com.hema.newretail.backstage.common.utils.RedisUtils;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.TimeUtil;
import com.hema.newretail.backstage.dao.BusiActivityRuleMapper;
import com.hema.newretail.backstage.entry.BusiActivityRuleEntry;
import com.hema.newretail.backstage.interceptor.AuthConstants;
import com.hema.newretail.backstage.service.discounts.EventRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Department 新零售
 * @ClassName EventRuleServiceImpl
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/2/28 14:23
 * @Version 1.0
 **/

@Service
public class EventRuleServiceImpl implements EventRuleService {

    private static final Logger logger = LoggerFactory.getLogger(EventRuleServiceImpl.class);
    private static final String SDFEIGHT = "yyyy-MM-dd";
    private static final String EMPTY = "";
    private static final String ONES = "1";
    private static final String ZEROS = "0";
    @Autowired
    private BusiActivityRuleMapper busiActivityRuleMapper;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 功能描述: list
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/2/28 11:11
     */
    @Override
    public Response eventRuleList(EventRuleListCondition condition) throws Exception{
        if(condition.getStartDate() != null && !EMPTY.equals(condition.getStartDate())){
            /*时间前有*/
            if(condition.getEndDate() != null && !EMPTY.equals(condition.getEndDate())){
                /*时间前后都有*/
                condition.setEndDates(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndDate(), SDFEIGHT)));
                condition.setStartDates(TimeUtil.stringToDate(condition.getStartDate(), SDFEIGHT));
            }else {
                /*时间前有后没有*/
                condition.setStartDates(TimeUtil.stringToDate(condition.getStartDate(), SDFEIGHT));
                condition.setEndDates(new Date());
            }
        }else {
            /*时间前没有*/
            if(condition.getEndDate() != null && !EMPTY.equals(condition.getEndDate())){
                /*时间前没有后有*/
                condition.setStartDates(TimeUtil.threeMonthAgo(TimeUtil.stringToDate(condition.getEndDate(), SDFEIGHT)));
                condition.setEndDates(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndDate(), SDFEIGHT)));
            }
        }
        Page<BusiActivityRuleEntry> page  = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        busiActivityRuleMapper.list(condition);
        return Response.success(page.getResult(),page.getTotal(),condition.getPageSize(),condition.getPageNum());
    }

    /**
     * 功能描述: add
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/2/28 11:11
     */
    @Override
    @Transactional
    public Response eventRuleAdd(HttpServletRequest request, EventAddRuleCondition condition) {
        String userinfoJson = redisUtils.hget(AuthConstants.SESSION + request.getSession().getId(), AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
        if(userinfoJson == null){
            logger.error("未检测到登录人数据");
            return Response.failure("未检测到登录人数据");
        }
        JSONObject jsStr = JSONObject.parseObject(userinfoJson);
        long userId = Long.valueOf(String.valueOf(jsStr.get("id"))).longValue();
        String userName = jsStr.get("userName").toString();
        /*检查重名*/
        Map map = new HashMap(1);
        map.put("ruleName",condition.getRuleName());
        int i = busiActivityRuleMapper.selectResetName(map);
        if(i > 0){
            return Response.failure("规则名重复");
        }
        BusiActivityRuleEntry busiActivityRuleEntry = new BusiActivityRuleEntry();
        busiActivityRuleEntry.setActiveObject(condition.getActiveObject());
        busiActivityRuleEntry.setGmtCreate(new Date());
        busiActivityRuleEntry.setGmtModified(new Date());
        busiActivityRuleEntry.setIsDeleted(0);
        busiActivityRuleEntry.setPerDayCount(condition.getPerDayCount());
        busiActivityRuleEntry.setPerDaySingalCount(condition.getPerDaySingalCount());
        busiActivityRuleEntry.setRuleName(condition.getRuleName());
        busiActivityRuleEntry.setSingalTotalCount(condition.getSingalTotalCount());
        busiActivityRuleEntry.setRuleCondition(condition.getRuleCondition());
        busiActivityRuleEntry.setCreateId(userId);
        busiActivityRuleEntry.setCreatename(userName);
        busiActivityRuleMapper.insert(busiActivityRuleEntry);

        return Response.success();
    }

    /**
     * 功能描述: edit
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/2/28 11:11
     */
    @Override
    public Response eventRuleEdit(EventEditRuleCondition condition) {
        int active = busiActivityRuleMapper.selectWithActive(condition.getId());
        if(active > 0){
            return Response.failure("本条规则关联活动,不能进行修改操作");
        }
        /*检查重名*/
        Map map = new HashMap(1);
        map.put("ruleName",condition.getRuleName());
        map.put("id",condition.getId());
        int i = busiActivityRuleMapper.selectResetName(map);
        if(i > 0){
            return Response.failure("规则名重复");
        }
        BusiActivityRuleEntry busiActivityRuleEntry = new BusiActivityRuleEntry();
        busiActivityRuleEntry.setId(condition.getId());
        busiActivityRuleEntry.setActiveObject(condition.getActiveObject());
        busiActivityRuleEntry.setGmtCreate(new Date());
        busiActivityRuleEntry.setGmtModified(new Date());
        busiActivityRuleEntry.setIsDeleted(0);
        busiActivityRuleEntry.setPerDayCount(condition.getPerDayCount());
        busiActivityRuleEntry.setPerDaySingalCount(condition.getPerDaySingalCount());
        busiActivityRuleEntry.setRuleName(condition.getRuleName());
        busiActivityRuleEntry.setSingalTotalCount(condition.getSingalTotalCount());
        busiActivityRuleEntry.setRuleCondition(condition.getRuleCondition());
        busiActivityRuleMapper.updateByPrimaryKeySelective(busiActivityRuleEntry);

        return Response.success();
    }

    /**
     * 功能描述: delete
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/2/28 11:11
     */
    @Override
    @Transactional
    public Response eventRuleDelete(EventDeleteRuleCondition condition) {
        int active = busiActivityRuleMapper.selectWithActive(condition.getId());
        if(active > 0){
            return Response.failure("本条规则关联活动,不能进行删除操作");
        }
        int i = busiActivityRuleMapper.deleteByRuleId(condition.getId());
        if(i == 1){
            return Response.success();
        }else {
            return Response.failure();
        }
    }

    /**
     * 功能描述: 查询当前占用饮品和设备
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/2/28 11:11
     */
    @Override
    public Response menuOrMachine(MenuOrMachineCondition condition) {
        Page<Map> maps = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        if(ZEROS.equals(condition.getType())){
            /*饮品*/
            busiActivityRuleMapper.selectMenuName(condition);
        }
        if(ONES.equals(condition.getType())){
            /*机器*/
            busiActivityRuleMapper.selectMachineName(condition);
        }
        for (Map m:maps.getResult()
             ) {
            if(m.get("isTrue").toString().equals("0")){
                m.put("isTrued",true);
            }else {
                m.put("isTrued",false);
            }
        }
        return Response.success(maps.getResult(),maps.getTotal(),condition.getPageSize(),condition.getPageNum());
    }
}
