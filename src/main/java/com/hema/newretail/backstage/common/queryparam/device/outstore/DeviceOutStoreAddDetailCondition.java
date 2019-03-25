package com.hema.newretail.backstage.common.queryparam.device.outstore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *@ClassName DeviceOutStoreAddCondition
 *@Description 设备出库单添加参数类
 *@Author CWZ
 *@Date 2018/12/11 16:32
 *@Version 1.0
 **/
@Data
@ApiModel(description = "DeviceOutStoreAddDetailCondition")
public class DeviceOutStoreAddDetailCondition {

    @ApiModelProperty(value = "设备型号")
    @NotBlank(message = "设备型号不可为空")
    private String machineType;

    @ApiModelProperty(value = "设备序列号")
    @NotBlank(message = "设备序列号不可为空")
    @Length(max = 20, message = "设备序列号最大长度为20")
    private String machineSequence;

    @ApiModelProperty(value = "扫描人")
    private String scanPeople;

    @ApiModelProperty(value = "扫描时间")
    @NotBlank(message = "扫描时间不可为空")
    private String scanTime;
}
