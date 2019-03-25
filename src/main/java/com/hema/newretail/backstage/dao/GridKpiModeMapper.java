package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.grid.GridKpiModeEntry;

import java.util.List;
import java.util.Map;

public interface GridKpiModeMapper {


    List<GridKpiModeEntry> selectAll();

    int deleteByPrimaryKey(Long id);

    int insert(GridKpiModeEntry record);

    int insertSelective(GridKpiModeEntry record);

    GridKpiModeEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GridKpiModeEntry record);

    int updateByPrimaryKey(GridKpiModeEntry record);

    /**
     * 生成网格公司内部KPI加分规则
     * @param map
     */

    void addCompanySelfRule(Map<String,Object> map);
}