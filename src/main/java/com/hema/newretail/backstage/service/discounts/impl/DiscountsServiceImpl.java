package com.hema.newretail.backstage.service.discounts.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.discounts.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.StringUtil;
import com.hema.newretail.backstage.common.utils.TimeUtil;
import com.hema.newretail.backstage.dao.BusiCouponAuditingMapper;
import com.hema.newretail.backstage.dao.BusiIngredientMenuMapper;
import com.hema.newretail.backstage.dao.RefMenuCouponMapper;
import com.hema.newretail.backstage.entry.BusiIngredientMenuEntry;
import com.hema.newretail.backstage.entry.discounts.BusiCouponAuditingEntry;
import com.hema.newretail.backstage.entry.discounts.RefMenuCouponEntry;
import com.hema.newretail.backstage.model.common.AgentCompanyBo;
import com.hema.newretail.backstage.model.discounts.ActivityListBo;
import com.hema.newretail.backstage.model.discounts.DiscountsListBo;
import com.hema.newretail.backstage.service.discounts.DiscountsService;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Department 新零售
 * @ClassName DiscountsServiceImpl
 * @Description 优惠券
 * @Author ---CWZ
 * @Date 2019/1/3 17:16
 * @Version 1.0
 **/


@Service
public class DiscountsServiceImpl implements DiscountsService {

    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);
    private static final String SDF = "yyyy-MM-dd HH:mm:ss";
    private static final String SDFEIGHT = "yyyy-MM-dd";

    private static final String EMPTY = "";

    @Autowired
    private BusiCouponAuditingMapper busiCouponAuditingMapper;

    @Autowired
    private RefMenuCouponMapper refMenuCouponMapper;

    @Autowired
    private BusiIngredientMenuMapper busiIngredientMenuMapper;
    /**
     * 功能描述: list
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/3 17:20
     */
    @Override
    public Response discountsList(DiscountsListCondition condition) throws  Exception{
        if(condition.getStartDate() != null && !EMPTY.equals(condition.getStartDate())){
            /*时间前有*/
            if(condition.getEndDate() != null && !EMPTY.equals(condition.getEndDate())){
                /*时间前后都有*/
                condition.setEndDates(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndDate(), SDFEIGHT)));
                condition.setStartDates(TimeUtil.stringToDate(condition.getStartDate(), SDFEIGHT));
            }else {
                /*时间前有后没有*/
                condition.setEndDates(new Date());
                condition.setStartDates(TimeUtil.stringToDate(condition.getStartDate(), SDFEIGHT));
            }
        }else {
            /*时间前没有*/
            if(condition.getEndDate() != null && !EMPTY.equals(condition.getEndDate())){
                /*时间前没有后有*/
                condition.setStartDates(TimeUtil.threeMonthAgo(TimeUtil.stringToDate(condition.getEndDate(), SDFEIGHT)));
                condition.setEndDates(TimeUtil.getEndTime(TimeUtil.stringToDate(condition.getEndDate(), SDFEIGHT)));
            }
        }
        if(condition.getCouponName() == null && EMPTY.equals(condition.getCouponName())){
            condition.setCouponName(null);
        }
        if(condition.getCouponType() == null && EMPTY.equals(condition.getCouponType())){
            condition.setCouponType(null);
        }
        if(condition.getAuthStatus() == null && EMPTY.equals(condition.getAuthStatus())){
            condition.setAuthStatus(null);
        }
        Page<DiscountsListBo> page = PageHelper.startPage(condition.getPageNum(),condition.getPageSize());
        busiCouponAuditingMapper.list(condition);
        for (DiscountsListBo b:page.getResult()
             ) {
            if(b.getDenominationUnit() == null){
            }else {
                if (b.getDenominationUnit().equals("折")) {
                    b.setDenomination(b.getDenomination().divide(new BigDecimal(100), 2));
                }
            }
        }
        return Response.success(page.getResult(),page.getTotal(),condition.getPageSize(),condition.getPageNum());
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
    @Transactional
    public Response discountsAdd(DiscountsAddCondition condition) {
        BusiCouponAuditingEntry entry = new BusiCouponAuditingEntry();
        entry.setAuthStatus(0);
        entry.setIsDeleted(0);
        entry.setCouponDescription(condition.getCouponDescription());
        entry.setCouponName(condition.getCouponName());
        entry.setCouponPic(condition.getCouponPic());
        entry.setCouponTitle(condition.getCouponTitle());
        entry.setCouponType(condition.getCouponType());
        entry.setDenomination(condition.getDenomination());
        entry.setDenominationUnit(condition.getDenominationUnit());
        entry.setGmtCreate(new Date());
        entry.setGmtModified(new Date());
        entry.setLimitnum(0);
        entry.setNum(condition.getNum());
	    if (2!=condition.getPeriodUnit()) {
		    entry.setPeriod(condition.getPeriod());
	    }
        entry.setPeriodUnit(condition.getPeriodUnit());
        entry.setSurplusNum(condition.getNum());
        busiCouponAuditingMapper.insert(entry);
        RefMenuCouponEntry rEntry = new RefMenuCouponEntry();
         rEntry.setCouponId(entry.getId());
        //关联饮品，不选默认为全部
        if(condition.getIds() == null || EMPTY.equals(condition.getIds())){
            List<AgentCompanyBo> entries = busiIngredientMenuMapper.selectAll();
            if(entries != null){
                for (AgentCompanyBo e:entries
                     ) {
                    rEntry.setMenuId(e.getId());
                    refMenuCouponMapper.insert(rEntry);
                }
            }

        }else {
            List<Long> longs = StringUtil.stringsToLong(condition.getIds());
            if(longs != null){
                for (Long l:longs
                     ) {
                    rEntry.setMenuId(l);
                    refMenuCouponMapper.insert(rEntry);
                }
            }

        }

            return Response.success();
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
    public Response discountsInfo(DiscountsInfoCondition condition) {
        BusiCouponAuditingEntry entry = busiCouponAuditingMapper.selectByPrimaryKey(condition.getId());
        entry.setMapList(busiCouponAuditingMapper.selectMenu(condition.getId()));

        return Response.success(entry);
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
    @Transactional
    public Response discountsEdit(DiscountsEditCondition condition) {
        BusiCouponAuditingEntry entry = new BusiCouponAuditingEntry();
        entry.setId(condition.getId());
        entry.setCouponDescription(condition.getCouponDescription());
        entry.setCouponName(condition.getCouponName());
        entry.setCouponPic(condition.getCouponPic());
        entry.setCouponTitle(condition.getCouponTitle());
        entry.setCouponType(condition.getCouponType());
        entry.setDenomination(condition.getDenomination());
        entry.setDenominationUnit(condition.getDenominationUnit());
        entry.setGmtModified(new Date());
        entry.setNum(condition.getNum());
	    if (2 != condition.getPeriodUnit()) {
		    entry.setPeriod(condition.getPeriod());
	    }
        entry.setPeriodUnit(condition.getPeriodUnit());
        entry.setSurplusNum(condition.getNum());
        int update = busiCouponAuditingMapper.updateByPrimaryKeySelective(entry);
        refMenuCouponMapper.deleteByCId(entry.getId());
        RefMenuCouponEntry rEntry = new RefMenuCouponEntry();
        rEntry.setCouponId(entry.getId());
        if(condition.getIds() != null && !EMPTY.equals(condition.getIds())){
            List<Long> longs = StringUtil.stringsToLong(condition.getIds());
            if(longs != null){
                for (Long l:longs
                        ) {
                    rEntry.setMenuId(l);
                    refMenuCouponMapper.insert(rEntry);
                }
            }
        }else {
            List<AgentCompanyBo> entries = busiIngredientMenuMapper.selectAll();
            if(null != entries){
                for (AgentCompanyBo e:entries
                        ) {
                    rEntry.setMenuId(e.getId());
                    refMenuCouponMapper.insert(rEntry);
                }
            }
        }
            return Response.success();
    }

    /**
     * 功能描述: check
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/1/3 17:20
     */
    @Override
    public Response discountsCheck(EventCheckCondition condition) {
        int activity = busiCouponAuditingMapper.check(condition);
        if(activity == 1){
            return Response.success();
        }else {
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
    public Response discountsDelete(DiscountsInfoCondition condition) {
        int activity = busiCouponAuditingMapper.updateDelete(condition.getId());
        if(activity == 1){
            return Response.success();
        }else {
            return Response.failure();
        }
    }
}
