package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.authority.OperationLogsCondition;
import com.hema.newretail.backstage.entry.BaseOperationLogs;

import java.util.List;

public interface BaseOperationLogsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BaseOperationLogs record);

    int insertSelective(BaseOperationLogs record);

    BaseOperationLogs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseOperationLogs record);

    int updateByPrimaryKey(BaseOperationLogs record);

    /**
     * 查询记录
     *
     * @param vo
     * @return
     */
    List<BaseOperationLogs> selectList(OperationLogsCondition vo);
}