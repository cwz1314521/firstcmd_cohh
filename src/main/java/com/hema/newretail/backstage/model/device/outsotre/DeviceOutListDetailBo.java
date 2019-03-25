package com.hema.newretail.backstage.model.device.outsotre;

import lombok.Data;

/**
 * @Department 新零售
 * @ClassName DeviceOutListDetailBo
 * @Description 设备出库列表详情Bo
 * @Author ---CWZ
 * @Date 2018/12/11 14:23
 * @Version 1.0
 **/
@Data
public class DeviceOutListDetailBo {

    /**设备类型*/
    private String machineType;
    /**设备数*/
    private Integer machineNum;
}
