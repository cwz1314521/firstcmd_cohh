package com.hema.newretail.backstage.dao;

import com.hema.newretail.backstage.common.queryparam.device.outstore.DeviceOutStoreDBCondition;
import com.hema.newretail.backstage.common.queryparam.device.sold.DeviceSoldCondition;
import com.hema.newretail.backstage.common.queryparam.device.sold.SetMachineServiceCondition;
import com.hema.newretail.backstage.common.queryparam.device.sold.SetTaskCycleCondition;
import com.hema.newretail.backstage.entry.device.BaseMachineOutStockOrderEntry;
import com.hema.newretail.backstage.model.device.outsotre.DeviceOutInfoBo;
import com.hema.newretail.backstage.model.device.outsotre.DeviceOutListBo;
import com.hema.newretail.backstage.model.device.sold.SoldDetailBo;
import com.hema.newretail.backstage.model.device.sold.SoldListBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Department 新零售
 * @ClassName BaseMachineOutStockOrderMapper
 * @Description 设备管理----出库mapper
 * @Author ---CWZ
 * @Date 2018/10/11 13:18
 * @Version 1.0
 **/
public interface BaseMachineOutStockOrderMapper {


    /**
     * 功能描述:
     *
     * @param condition
     * @return List<DeviceOutListBo>
     * @author cwz
     * @date 2018/12/11 15:29
     */
    List<DeviceOutListBo> selectDeviceOutStoreMap(DeviceOutStoreDBCondition condition);


    /**
     * 功能描述: 查询今日记录数+1
     *
     * @param outstockOrderCode
     * @return int
     * @author cwz
     * @date 2018/12/12 9:43
     */
    int selectOutCodeCount(String outstockOrderCode);

    /**
     * 功能描述: 查询今日记录数+1
     *
     * @param id
     * @return DeviceOutInfoBo
     * @author cwz
     * @date 2018/12/12 9:43
     */

    DeviceOutInfoBo selectDeviceOutStoreInfoMap(@Param("id") Long id);

    /**
     * 功能描述: 主键删除
     *
     * @param id
     * @return int
     * @author cwz
     * @date 2018/12/12 21:21
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 功能描述: 插入数据
     *
     * @param record
     * @return int
     * @author cwz
     * @date 2018/12/12 21:21
     */
    int insert(BaseMachineOutStockOrderEntry record);

    /**
     * 功能描述: 主键查询
     *
     * @param id
     * @return BaseMachineOutStockOrderEntry
     * @author cwz
     * @date 2018/12/12 21:22
     */
    BaseMachineOutStockOrderEntry selectByPrimaryKey(Long id);

    /**
     * 功能描述: 已出售设备列表接口
     *
     * @param
     * @return Response
     * @author cwz
     * @date 2018/12/11 14:48
     */
    List<SoldListBo> selectSoldListMap(DeviceSoldCondition condition);

    /**
     * 功能描述: 批量设置任务周期
     *
     * @param condition
     * @return int
     * @author cwz
     * @date 2018/12/13 16:11
     */
    int updateTaskPeriod(SetTaskCycleCondition condition);

    /**
     * 功能描述: 批量手动关停服务
     *
     * @param condition
     * @return int
     * @author cwz
     * @date 2018/12/13 16:47
     */
    int updateSetMachineService(SetMachineServiceCondition condition);

    /**
     * 功能描述: 批量手动关停服务
     *
     * @param id
     * @return int
     * @author cwz
     * @date 2018/12/13 16:47
     */
    SoldDetailBo selectSoldListDetailMap(Long id);

    /**
     * 根据设备ID查询任务周期
     *
     * @param condition 设备ID集合
     * @return 任务周期
     */
    List<Map<String, Object>> selectTaskCycleByIds(SetTaskCycleCondition condition);
}