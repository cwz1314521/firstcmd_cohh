package com.hema.newretail.backstage.service.device;

import com.hema.newretail.backstage.common.queryparam.device.outstore.CommonIdCondition;
import com.hema.newretail.backstage.common.queryparam.device.outstore.DeviceOutStoreAddCondition;
import com.hema.newretail.backstage.common.queryparam.device.outstore.DeviceOutStoreCondition;
import com.hema.newretail.backstage.common.utils.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Department 新零售
 * @ClassName DeviceOutStoreService
 * @Description 设备模块    service
 * @Author ---CWZ
 * @Date 2018/10/11 14:01
 * @Version 1.0
 **/
public interface DeviceOutStoreService {


    /**
     *
     * 功能描述: 设备出库列表接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     * @throws Exception
     */
    Response list(DeviceOutStoreCondition condition)throws Exception;

    /**
     *
     * 功能描述: 设备出库添加接口
     *
     * @param  condition
     * @param  request
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     * @throws Exception
     */
    Response add(HttpServletRequest request,DeviceOutStoreAddCondition condition)throws Exception;

    /**
     *
     * 功能描述: 设备出库详情接口
     *
     * @param  condition
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     */
    Response info(CommonIdCondition condition);

    /**
     *
     * 功能描述: 设备出库列表接口
     *
     * @param  condition
     * @param  response
     * @return  Response
     * @author  cwz
     * @date  2018/12/11 14:48
     * @throws Exception
     */
    Response export(DeviceOutStoreCondition condition,HttpServletResponse response)throws Exception;
}
