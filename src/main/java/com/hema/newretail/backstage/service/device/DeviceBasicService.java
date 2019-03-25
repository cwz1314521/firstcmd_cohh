package com.hema.newretail.backstage.service.device;

import com.hema.newretail.backstage.common.queryparam.device.type.*;
import com.hema.newretail.backstage.common.utils.Response;

import javax.servlet.http.HttpServletRequest;

/**
 * @Department 新零售
 * @ClassName DeviceBasicService
 * @Description 设备类型基础信息 service
 * @Author ---CWZ
 * @Date 2019/1/21 16:40
 * @Version 1.0
 **/


public interface DeviceBasicService {

    /**
     *
     * 功能描述: 设备类型添加
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/21 15:56
     */
    Response add(HttpServletRequest request,DeviceBasicAddCondition condition);


    /**
     *
     * 功能描述: 设备类型隐藏
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/21 15:56
     */
    Response delete(DeviceBasicDeleteCondition condition);

    /**
     *
     * 功能描述: 设备类型修改
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/21 15:56
     */
    Response edit(HttpServletRequest request, DeviceBasicEditCondition condition);

    /**
     *
     * 功能描述: 设备类型list
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2019/1/21 15:56
     */
    Response list(DeviceBasicListCondition condition);
}
