package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.grid.GridTaskProfitLogEntry;

public interface GridTaskProfitLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GridTaskProfitLogEntry record);

    int insertSelective(GridTaskProfitLogEntry record);

    GridTaskProfitLogEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GridTaskProfitLogEntry record);

    int updateByPrimaryKey(GridTaskProfitLogEntry record);
}