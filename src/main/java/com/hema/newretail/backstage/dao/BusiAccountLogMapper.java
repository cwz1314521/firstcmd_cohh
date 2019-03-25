package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.financial.AgentTransactionRecordCondition;
import com.hema.newretail.backstage.entry.BusiAccountLog;
import com.hema.newretail.backstage.model.financial.AgentTransactionRecordBo;

import java.util.List;

public interface BusiAccountLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BusiAccountLog record);

    int insertSelective(BusiAccountLog record);

    BusiAccountLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BusiAccountLog record);

    int updateByPrimaryKey(BusiAccountLog record);

    /**
     * 查询
     *
     * @param vo
     * @return
     */
    List<AgentTransactionRecordBo> selectList(AgentTransactionRecordCondition vo);
}