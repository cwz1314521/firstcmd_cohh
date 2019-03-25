package com.hema.newretail.backstage.service.device;


import com.hema.newretail.backstage.common.queryparam.device.outstore.CommonIdCondition;
import com.hema.newretail.backstage.common.queryparam.device.sold.*;
import com.hema.newretail.backstage.common.utils.Response;

/**
 * @Department 新零售
 * @ClassName DeviceSoldService
 * @Description 设备管理--已出售--Service
 * @Author ---CWZ
 * @Date 2018/12/12 20:41
 * @Version 1.0
 **/


public interface DeviceSoldService {


    /**
     *
     * 功能描述: 已出售设备列表接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    Response list(DeviceSoldCondition condition);

    /**
     *
     * 功能描述: 批量设置任务周期
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    Response setTaskCycle(SetTaskCycleCondition condition);

    /**
     *
     * 功能描述: 批量手动关停服务接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    Response setMachineService(SetMachineServiceCondition condition);

    /**
     *
     * 功能描述: 设备出库详情接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    Response detail(CommonIdCondition condition);

    /**
     *
     * 功能描述: 设备地址修改
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    Response addressEdit(AddressEditCondition condition);
}
