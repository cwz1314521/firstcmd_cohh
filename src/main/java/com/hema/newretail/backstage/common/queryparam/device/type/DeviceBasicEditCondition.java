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
@ApiModel(value = "DeviceBasicEditCondition",description = "DeviceBasicEditCondition")
public class DeviceBasicEditCondition {

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "设备名称")
    private String machineTypeName;

    @ApiModelProperty(value = "设备型号")
    private String machineTypeCode;

    @ApiModelProperty(value = "料盒数量")
    private Integer ingredientBoxNum;

    @ApiModelProperty(value = "设备主图")
    private String picUrl;

    @ApiModelProperty(value = "状态 0启用 1停用")
    private Integer status;

    @ApiModelProperty(value = "描述-富文本")
    private String description;
}
