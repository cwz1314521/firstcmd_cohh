package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.data.DataSalesTrendDBCondition;
import com.hema.newretail.backstage.entry.data.DMonthMachineUuidEntry;

import java.util.List;

/**
 *
 * 功能描述:
 * @author  cwz
 * @date  2018/12/18 19:29
 */
public interface DMonthMachineUuidMapper {
    int insert(DMonthMachineUuidEntry record);

    int insertSelective(DMonthMachineUuidEntry record);

    /**
     *
     * 功能描述: 数据统计销量趋势接口
     *
     * @param condition
     * @return List<DDayMachineUuidEntry>
     * @author  cwz
     * @date  2018/12/17 20:29
     */
    List<DMonthMachineUuidEntry> selectBaseResultMap(DataSalesTrendDBCondition condition);
}