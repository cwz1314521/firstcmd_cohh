package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.device.type.DeviceBasicListCondition;
import com.hema.newretail.backstage.entry.BaseMachineTypeEntry;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BaseMachineTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseMachineTypeEntry record);

    int insertSelective(BaseMachineTypeEntry record);

    BaseMachineTypeEntry selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseMachineTypeEntry record);

    int updateByPrimaryKeyWithBLOBs(BaseMachineTypeEntry record);

    int updateByPrimaryKey(BaseMachineTypeEntry record);

    /**
     *
     * 功能描述:
     *
     * @param
     * @return
     * @author  cwz
     * @date  2019/1/22 11:19
     */
    List<BaseMachineTypeEntry> selectAll();

    /**
     *
     * 功能描述:
     *
     * @param  id
     * @return
     * @author  cwz
     * @date  2019/1/22 11:19
     */
    int selectCountByMachine(Integer id);

    /**
     *
     * 功能描述:
     *
     * @param  id
     * @return
     * @author  cwz
     * @date  2019/1/22 11:19
     */
    int updateDelete(Integer id);

    /**
     *
     * 功能描述:
     *
     * @param  condition
     * @return
     * @author  cwz
     * @date  2019/1/22 11:43
     */
    List<Map> list(DeviceBasicListCondition condition);

    /**
     *
     * 功能描述: 
     *
     * @param  map
     * @return  
     * @author  cwz
     * @date  2019/1/22 16:36
     */
    int countName(Map map);
}