package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.BaseOrganizationEntry;
import com.hema.newretail.backstage.model.organization.BaseOrganizationBo;

import java.util.List;
import java.util.Map;

public interface BaseOrganizationMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseOrganizationEntry record);

    int insertSelective(BaseOrganizationEntry record);

    BaseOrganizationEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseOrganizationEntry record);

    int updateByPrimaryKey(BaseOrganizationEntry record);

    BaseOrganizationBo SelectListBo(Map map);

    String nextCode(Map map);

    Integer repeatName(Map map);
}