package com.hema.newretail.backstage.common.queryparam.device.type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Department 新零售
 * @ClassName DeviceBasicEditCondition
 * @Description 设备类型基础信息参数类
 * @Author ---CWZ
 * @Date 2019/1/21 15:57
 * @Version 1.0
 **/

@Data
@ApiModel(value = "DeviceBasicListCondition",description = "DeviceBasicListCondition")
public class DeviceBasicListCondition {

    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可为空")
    private Integer pageNum;

    @ApiModelProperty(value = "每页记录条数")
    @NotNull(message = "每页记录条数不可为空")
    private Integer pageSize;

    @ApiModelProperty(value = "设备名称")
    private String machineTypeName;

    @ApiModelProperty(value = "状态 0启用 1停用")
    private Integer status;
}
