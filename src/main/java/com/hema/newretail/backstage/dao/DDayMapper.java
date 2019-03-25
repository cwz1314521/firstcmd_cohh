package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.data.DataSalesTrendDBCondition;
import com.hema.newretail.backstage.entry.data.DDayEntry;
import com.hema.newretail.backstage.entry.data.DDayMachineUuidEntry;

import java.util.List;

public interface DDayMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table d_day
     *
     * @mbggenerated
     */
    int insert(DDayEntry record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table d_day
     *
     * @mbggenerated
     */
    int insertSelective(DDayEntry record);

    /**
     *
     * 功能描述: 数据统计销量趋势接口
     *
     * @param condition
     * @return List<DDayMachineUuidEntry>
     * @author  cwz
     * @date  2018/12/17 20:29
     */
    List<DDayEntry> selectBaseResultMap(DataSalesTrendDBCondition condition);
}