package com.hema.newretail.backstage.service.discounts.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.backstage.common.queryparam.discounts.*;
import com.hema.newretail.backstage.common.utils.RedisUtils;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.TimeUtil;
import com.hema.newretail.backstage.dao.BusiActivityMapper;
import com.hema.newretail.backstage.entry.BaseUserInfoEntry;
import com.hema.newretail.backstage.entry.discounts.BusiActivityEntry;
import com.hema.newretail.backstage.interceptor.AuthConstants;
import com.hema.newretail.backstage.model.discounts.ActivityInfoBo;
import com.hema.newretail.backstage.model.discounts.ActivityListBo;
import com.hema.newretail.backstage.service.discounts.EventService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * @Department 新零售
 * @ClassName EventServiceImpl
 * @Description 活动
 * @Author ---CWZ
 * @Date 2019/1/3 17:17
 * @Version 1.0
 **/
@Service
public class EventServiceImpl implements EventService {

    private static final String SDF = "yyyy-MM-dd HH:mm:ss";
    private static final String SDFEIGHT = "yyyy-MM-dd";

    private static final String EMPTY = "";

    @Autowired
    private BusiActivityMapper busiActivityMapper;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 功能描述: list
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/3 17:20
     */
    @Override
    public Response eventList(EventListCondition condition) throws Exception {
        if (condition.getStartDate() != null && !EMPTY.equals(condition.getStartDate())) {
            /*时间前有*/
            if (condition.getEndDate() != null && !EMPTY.equals(condition.getEndDate())) {
                /*时间前后都有*/
                condition.setEndDates(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndDate(), SDFEIGHT)));
                condition.setStartDates(TimeUtil.stringToDate(condition.getStartDate(), SDFEIGHT));
            } else {
                /*时间前有后没有*/
                condition.setStartDates(TimeUtil.stringToDate(condition.getStartDate(), SDFEIGHT));
                condition.setEndDates(new Date());
            }
        } else {
            /*时间前没有*/
            if (condition.getEndDate() != null && !EMPTY.equals(condition.getEndDate())) {
                /*时间前没有后有*/
                condition.setStartDates(TimeUtil.threeMonthAgo(TimeUtil.stringToDate(condition.getEndDate(), SDFEIGHT)));
                condition.setEndDates(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndDate(), SDFEIGHT)));
            }
        }
        if (condition.getActivityName() == null && EMPTY.equals(condition.getActivityName())) {
            condition.setActivityName(null);
        }
        if (condition.getAuthStatus() == null && EMPTY.equals(condition.getAuthStatus())) {
            condition.setAuthStatus(null);
        }
        if (condition.getActivityStatus() == null && EMPTY.equals(condition.getActivityStatus())) {
            condition.setActivityStatus(null);
        }
        Page<ActivityListBo> page = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        busiActivityMapper.selectList(condition);
        return Response.success(page.getResult(), page.getTotal(), condition.getPageSize(), condition.getPageNum());
    }

    /**
     * 功能描述: add
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/3 17:20
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response eventAdd(EventAddCondition condition) throws Exception {
        BusiActivityEntry entry = new BusiActivityEntry();
        entry.setSubTitle(condition.getSubTitle());
        entry.setActivityDesc(condition.getActivityDesc());
        entry.setActivityName(condition.getActivityName());
        entry.setActivityStatus(0);
        if (!StringUtils.isEmpty(condition.getEffectiveTime())) {
            entry.setEffectiveTime(TimeUtil.stringToDate(condition.getEffectiveTime(), SDF));
        }
        entry.setGmtCreate(new Date());
        entry.setGmtModified(new Date());
        entry.setIsDeleted(0);
        if (!StringUtils.isEmpty(condition.getInvalidTime())) {
            entry.setInvalidTime(TimeUtil.stringToDate(condition.getInvalidTime(), SDF));
        }
        entry.setActivityRuleId(condition.getActivityRuleId());
        entry.setAuditStatus(0);
        int insert = busiActivityMapper.insert(entry);
        if (insert == 1) {
            return Response.success();
        } else {
            return Response.failure();
        }
    }

    /**
     * 功能描述: info
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/3 17:20
     */
    @Override
    public Response eventInfo(EventInfoCondition condition) {
        ActivityInfoBo info = busiActivityMapper.selectInfo(condition.getId());
        return Response.success(info);
    }

    /**
     * 功能描述: edit
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/3 17:20
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Response eventEdit(EventEditCondition condition) throws Exception {
        BusiActivityEntry entry = new BusiActivityEntry();
        entry.setSubTitle(condition.getSubTitle());
        entry.setId(condition.getId());
        entry.setActivityDesc(condition.getActivityDesc());
        entry.setActivityName(condition.getActivityName());
        if (!StringUtils.isEmpty(condition.getEffectiveTime())) {
            entry.setEffectiveTime(TimeUtil.stringToDate(condition.getEffectiveTime(), SDF));
        }
        entry.setGmtModified(new Date());
        if (!StringUtils.isEmpty(condition.getInvalidTime())) {
            entry.setInvalidTime(TimeUtil.stringToDate(condition.getInvalidTime(), SDF));
        }
        entry.setActivityRuleId(condition.getActivityRuleId());
        int update = busiActivityMapper.updateByPrimaryKeySelective(entry);
        if (update == 1) {
            return Response.success();
        } else {
            return Response.failure();
        }
    }

    /**
     * 提交审核
     *
     * @param condition
     * @param session
     * @return
     */
    @Override
    public Response eventCheck(EventCheckCondition condition, HttpSession session) {
        BaseUserInfoEntry userInfo = getUserInfo(session.getId());
        if (null == userInfo) {
            return Response.failureValid("获取登录信息失败，请重新登录");
        }
        BusiActivityEntry entry = new BusiActivityEntry();
        BeanUtils.copyProperties(condition, entry);
        entry.setAuditStatus(1);
        entry.setApplyTime(new Date());
        entry.setApplicantId(userInfo.getId());
        entry.setApplicant(userInfo.getRealName());
        int activity = busiActivityMapper.updateByPrimaryKeySelective(entry);
        if (activity == 1) {
            return Response.success();
        } else {
            return Response.failure();
        }
    }

    /**
     * 功能描述: stop
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/3 17:20
     */
    @Override
    public Response eventStop(EventInfoCondition condition) {
        int activity = busiActivityMapper.stop(condition.getId());
        if (activity == 1) {
            return Response.success();
        } else {
            return Response.failure();
        }
    }

    /**
     * 功能描述: delete
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/3 17:20
     */
    @Override
    public Response eventDelete(EventInfoCondition condition) {
        int activity = busiActivityMapper.updateDelete(condition.getId());
        if (activity == 1) {
            return Response.success();
        } else {
            return Response.failure();
        }
    }

    /**
     * 获取当前登录用户信息
     *
     * @param sessionId
     * @return
     */
    private BaseUserInfoEntry getUserInfo(String sessionId) {
        String userInfoJson = redisUtils.hget(AuthConstants.SESSION + sessionId, AuthConstants.USER_INFO, AuthConstants.REDIS_DB_INDEX);
        if (StringUtils.isEmpty(userInfoJson)) {
            return null;
        }
        BaseUserInfoEntry userInfo = JSON.parseObject(userInfoJson, BaseUserInfoEntry.class);
        if (null == userInfo || null == userInfo.getPostId()) {
            return null;
        }
        return userInfo;
    }
}
