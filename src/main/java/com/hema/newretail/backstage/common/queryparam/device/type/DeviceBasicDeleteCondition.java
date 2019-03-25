package com.hema.newretail.backstage.common.queryparam.device.type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Department 新零售
 * @ClassName DeviceBasicEditCondition
 * @Description 设备类型基础信息参数类
 * @Author ---CWZ
 * @Date 2019/1/21 15:57
 * @Version 1.0
 **/

@Data
@ApiModel(value = "DeviceBasicDeleteCondition",description = "DeviceBasicDeleteCondition")
public class DeviceBasicDeleteCondition {
    @ApiModelProperty(value = "id")
    private Integer id;
}
