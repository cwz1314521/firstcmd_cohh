package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.entry.device.BaseMachineOutStockOrderDetailEntry;
/**
 * @Department 新零售
 * @ClassName BaseMachineOutStockOrderDetailMapper
 * @Description 设备管理----出库详情mapper
 * @Author ---CWZ
 * @Date 2018/10/11 13:18
 * @Version 1.0
 **/
public interface BaseMachineOutStockOrderDetailMapper {

    int selectCountByMS(String machineSequence);

    int deleteByPrimaryKey(Long id);

    int insert(BaseMachineOutStockOrderDetailEntry record);

    int insertSelective(BaseMachineOutStockOrderDetailEntry record);

    BaseMachineOutStockOrderDetailEntry selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BaseMachineOutStockOrderDetailEntry record);

    int updateByPrimaryKey(BaseMachineOutStockOrderDetailEntry record);
}