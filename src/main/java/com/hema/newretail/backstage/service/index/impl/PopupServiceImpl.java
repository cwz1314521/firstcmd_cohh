package com.hema.newretail.backstage.service.index.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.backstage.common.queryparam.diy.IdCondition;
import com.hema.newretail.backstage.common.queryparam.index.PopupAddCondition;
import com.hema.newretail.backstage.common.queryparam.index.PopupEditCondition;
import com.hema.newretail.backstage.common.queryparam.index.PopupListCondition;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.TimeUtil;
import com.hema.newretail.backstage.dao.BaseMachineIndexBannerMapper;
import com.hema.newretail.backstage.entry.BaseMachineIndexBannerEntry;
import com.hema.newretail.backstage.service.discounts.impl.EventRuleServiceImpl;
import com.hema.newretail.backstage.service.index.PopupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @Department 新零售
 * @ClassName PopupServiceImpl
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/3/11 14:44
 * @Version 1.0
 **/
@Service
public class PopupServiceImpl implements PopupService {

    @Autowired
    private BaseMachineIndexBannerMapper baseMachineIndexBannerMapper;


    private static final Logger logger = LoggerFactory.getLogger(EventRuleServiceImpl.class);
    private static final String SDFEIGHT = "yyyy-MM-dd";
    private static final String EMPTY = "";
    private static final String ONES = "1";
    private static final String ZEROS = "0";
    /**
     * 功能描述:
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/3/11 13:58
     */
    @Override
    public Response list(PopupListCondition condition) throws Exception{
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
        Page<Map> page = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        baseMachineIndexBannerMapper.popupList(condition);
        return Response.success(page.getResult(),page.getTotal(),page.getPageSize(),page.getPageNum());
    }

    /**
     * 功能描述:
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/3/11 13:58
     */
    @Override
    public Response add(PopupAddCondition condition) throws Exception{
        logger.info("参数类拼装");
        BaseMachineIndexBannerEntry entry = new BaseMachineIndexBannerEntry();
        entry.setPicUrl(condition.getPicUrl());
        entry.setInfo(condition.getInfo());
        entry.setGmtStart(TimeUtil.stringToDate(condition.getGmtStart(), SDFEIGHT));
        entry.setGmtEnd(TimeUtil.stringToDate(condition.getGmtEnd(), SDFEIGHT));
        entry.setGmtCreate(new Date());
        entry.setGmtModify(new Date());
        entry.setIsDelete(ZEROS);
        entry.setSource(condition.getSource());
        entry.setTypeId(2L);
        if (ONES.equals(condition.getType())) {
            entry.setType(condition.getType());
            entry.setSkipUrl(condition.getSkipUrl());
        } else {
            entry.setType(condition.getType());
            entry.setSkipUrl(EMPTY);
        }
        entry.setSource(condition.getSource());
        entry.setPopUpSet(condition.getPopUpSet());
        entry.setSorder(condition.getSorder());
        int insert = baseMachineIndexBannerMapper.insert(entry);
        if (insert != 1) {
            logger.error("存储失败");
            return Response.failure("操作失败");
        }
        return Response.success();
    }

    /**
     * 功能描述:
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/3/11 13:58
     */
    @Override
    public Response edit(PopupEditCondition condition) throws Exception {
        logger.info("参数类拼装");
        BaseMachineIndexBannerEntry entry = new BaseMachineIndexBannerEntry();
        entry.setId(condition.getId());
        entry.setPicUrl(condition.getPicUrl());
        entry.setInfo(condition.getInfo());
        entry.setGmtStart(TimeUtil.stringToDate(condition.getGmtStart(), SDFEIGHT));
        entry.setGmtEnd(TimeUtil.stringToDate(condition.getGmtEnd(), SDFEIGHT));
        entry.setGmtCreate(new Date());
        entry.setGmtModify(new Date());
        entry.setIsDelete(ZEROS);
        entry.setSource(condition.getSource());
        entry.setTypeId(2L);
        if (ONES.equals(condition.getType())) {
            entry.setType(condition.getType());
            entry.setSkipUrl(condition.getSkipUrl());
        } else {
            entry.setType(condition.getType());
            entry.setSkipUrl(EMPTY);
        }
        entry.setSource(condition.getSource());
        entry.setPopUpSet(condition.getPopUpSet());
        entry.setSorder(condition.getSorder());
        int insert = baseMachineIndexBannerMapper.updateByPrimaryKeySelective(entry);
        if (insert != 1) {
            logger.error("存储失败");
            return Response.failure("操作失败");
        }
        return Response.success();
    }

    /**
     * 功能描述:
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/3/11 13:58
     */
    @Override
    public Response delete(IdCondition condition) {
        int i = baseMachineIndexBannerMapper.delete(condition.getId());
        if (i == 1) {
            return Response.success();
        } else {
            return Response.failure("操作失败");
        }
    }

}
