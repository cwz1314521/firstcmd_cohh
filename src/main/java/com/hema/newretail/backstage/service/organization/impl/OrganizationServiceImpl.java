package com.hema.newretail.backstage.service.organization.impl;

import com.hema.newretail.CloudBohhApplication;
import com.hema.newretail.backstage.common.queryparam.organization.*;
import com.hema.newretail.backstage.common.utils.Response;
import com.hema.newretail.backstage.dao.BaseOrganizationMapper;
import com.hema.newretail.backstage.entry.BaseOrganizationEntry;
import com.hema.newretail.backstage.model.organization.BaseOrganizationBo;
import com.hema.newretail.backstage.service.organization.OrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Department 新零售
 * @ClassName OrganizationServiceImpl
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/2/15 16:05
 * @Version 1.0
 **/


@Service
public class OrganizationServiceImpl implements OrganizationService {

    @Autowired
    private BaseOrganizationMapper baseOrganizationMapper;
    private static final Logger logger = LoggerFactory.getLogger(CloudBohhApplication.class);

    /**
     * 功能描述: 组织架构列表
     *
     * @param 
     * @return Response
     * @author cwz
     * @date 2019/2/15 15:24
     */
    @Override
    public Response list() {
        Map map = new HashMap(2);
        map.put("orgCode","000");
        map.put("level",0);
        BaseOrganizationBo baseOrganizationBos = baseOrganizationMapper.SelectListBo(map);
        return Response.success(baseOrganizationBos);
    }

    /**
     * 功能描述: 组织架构列表
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/2/15 15:24
     */
    @Override
    public Response add(OrganizationAddCondition condition) {
        Map mapName = new HashMap(2);
        mapName.put("orgName",condition.getOrgName());
        Integer integer = baseOrganizationMapper.repeatName(mapName);
        if(integer>0){
            return Response.failure("名称重复");
        }
        BaseOrganizationEntry entry = new BaseOrganizationEntry();
        entry.setContactNumber(condition.getContactNumber());
        entry.setGmtCreate(new Date());
        entry.setGmtModified(new Date());
        entry.setIsDeleted(false);
        entry.setLeader(condition.getLeader());
        Map map = new HashMap(2);
        map.put("orgCode",condition.getPorgCode());
        map.put("level",condition.getLevel());
        logger.info(map.toString());
        entry.setLevel(condition.getLevel()+1);
        String s = baseOrganizationMapper.nextCode(map);
        if(s == null){
            s=condition.getPorgCode()+"001";
        }
        entry.setOrgCode(s);
        entry.setOrgName(condition.getOrgName());
        entry.setRemark(condition.getRemark());

        int insert = baseOrganizationMapper.insert(entry);
        if(insert == 1){
            return Response.success();
        }else {
            return Response.failure();
        }

    }

    /**
     * 功能描述: 组织架构列表
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/2/15 15:24
     */
    @Override
    public Response edit(OrganizationEditCondition condition) {
        BaseOrganizationEntry entry = new BaseOrganizationEntry();

        entry.setId(condition.getId());
        entry.setOrgName(condition.getOrgName());
        entry.setLeader(condition.getLeader());
        entry.setContactNumber(condition.getContactNumber());
        entry.setRemark(condition.getRemark());
        entry.setGmtModified(new Date());
        Map map = new HashMap(2);
        map.put("id",condition.getId());
        map.put("orgName",condition.getOrgName());
        Integer integer = baseOrganizationMapper.repeatName(map);
        if(integer>0){
            return Response.failure("名称重复");
        }
        int update = baseOrganizationMapper.updateByPrimaryKeySelective(entry);
        if(update == 1){
            return Response.success();
        }else {
            return Response.failure();
        }
    }

    /**
     * 功能描述: 组织架构列表
     *
     * @param condition
     * @return Response
     * @author cwz
     * @date 2019/2/15 15:24
     */
    @Override
    public Response delete(OrganizationDeleteCondition condition) {
        Map mapName = new HashMap(2);
        mapName.put("orgCode",condition.getOrgCode());
        Integer integer = baseOrganizationMapper.repeatName(mapName);
        if(integer>1){
            return Response.failure("不能删除含有下级机构的数据");
        }
        BaseOrganizationEntry entry = new BaseOrganizationEntry();
        entry.setIsDeleted(true);
        entry.setId(condition.getId());
        int update = baseOrganizationMapper.updateByPrimaryKeySelective(entry);
        if(update == 1){
            return Response.success();
        }else {
            return Response.failure();
        }
    }
}
