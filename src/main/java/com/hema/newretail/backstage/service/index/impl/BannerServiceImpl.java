package com.hema.newretail.backstage.service.index.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.index.BannerAddCondition;
import com.hema.newretail.backstage.common.queryparam.index.BannerEditCondition;
import com.hema.newretail.backstage.common.queryparam.index.BannerListCondition;
import com.hema.newretail.backstage.common.queryparam.system.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.common.utils.TimeUtil;
import com.hema.newretail.backstage.dao.BaseMachineIndexBannerMapper;
import com.hema.newretail.backstage.dao.ZoneBaseMapper;
import com.hema.newretail.backstage.entry.BaseMachineIndexBannerEntry;
import com.hema.newretail.backstage.model.index.banner.BannerListBo;
import com.hema.newretail.backstage.service.index.BannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Department 新零售
 * @ClassName BannerServiceImpl
 * @Description
 * @Author ---CWZ
 * @Date 2018/12/25 15:16
 * @Version 1.0
 **/


@Service
public class BannerServiceImpl implements BannerService {

    @Autowired
    private BaseMachineIndexBannerMapper baseMachineIndexBannerMapper;

    @Autowired
    private ZoneBaseMapper zoneBaseMapper;
    private static final String SDFEIGHT = "yyyy-MM-dd HH:mm:ss";
    private static final String ZEROS = "0";
    private static final String ONES = "1";
    private static final String EMPTY = "";


    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    /**
     * 功能描述: 机器大屏广告列表
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/24 14:26
     */
    @Override
    public Response machinePic(BannerListCondition condition) throws Exception{
        logger.info("分页参数拼装");
        Page<BannerListBo> page = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        baseMachineIndexBannerMapper.selectListMap();
        return Response.success(page.getResult(), page.getTotal(), page.getPageSize(), page.getPageNum());
    }

    /**
     * 功能描述: 机器大屏广告add
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/24 14:26
     */
    @Override
    @Transactional
    public Response machinePicAdd(BannerAddCondition condition) throws Exception {
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
        entry.setTypeId(condition.getTypeId());
        if (ONES.equals(condition.getType())) {
            entry.setType(condition.getType());
            entry.setSkipUrl(condition.getSkipUrl());
        } else {
            entry.setType(condition.getType());
            entry.setSkipUrl(EMPTY);
        }
        entry.setSource(condition.getSource());
        entry.setTypeId(condition.getTypeId());
        int insert = baseMachineIndexBannerMapper.insert(entry);
        if (insert != 1) {
            logger.error("存储失败");
            return Response.failure("操作失败");
        }
        return Response.success();
    }

    /**
     * 功能描述: 机器大屏广告edit
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/24 14:26
     */
    @Override
    public Response machinePicEdit(BannerEditCondition condition) throws Exception {
        logger.info("参数类拼装");
        BaseMachineIndexBannerEntry entry = new BaseMachineIndexBannerEntry();
        entry.setPicUrl(condition.getPicUrl());
        entry.setInfo(condition.getInfo());
        entry.setGmtStart(TimeUtil.stringToDate(condition.getGmtStart(), SDFEIGHT));
        entry.setGmtEnd(TimeUtil.stringToDate(condition.getGmtEnd(), SDFEIGHT));
        entry.setGmtModify(new Date());
        entry.setId(condition.getId());
        if (ONES.equals(condition.getType())) {
            entry.setSkipUrl(condition.getSkipUrl());
            entry.setType(condition.getType());
        } else {
            entry.setType(condition.getType());
            entry.setSkipUrl("");
        }
        entry.setSource(condition.getSource());
        entry.setTypeId(condition.getTypeId());
        int insert = baseMachineIndexBannerMapper.updateByPrimaryKeySelective(entry);
        if (insert != 1) {
            logger.error("存储失败");
            return Response.failure("操作失败");
        }
        return Response.success();
    }


    /**
     * 功能描述: 机器大屏广告delete
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2018/12/24 14:26
     */
    @Override
    public Response machinePicDelete(IdCondition condition) {
        int i = baseMachineIndexBannerMapper.delete(condition.getId());
        if (i == 1) {
            return Response.success();
        } else {
            return Response.failure("操作失败");
        }
    }
}