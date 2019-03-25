package com.hema.newretail.backstage.common.queryparam.device.type;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;

/**
 * @Department 新零售
 * @ClassName DeviceBasicEditCondition
 * @Description 设备类型基础信息参数类
 * @Author ---CWZ
 * @Date 2019/1/21 15:57
 * @Version 1.0
 **/

@Data
@ApiModel(value = "DeviceBasicAddCondition",description = "DeviceBasicAddCondition")
public class DeviceBasicAddCondition {

    @ApiModelProperty(value = "设备名称")
    @Length(max = 24,message = "设备名称最大长度24个字")
    @NotBlank(message = "设备名称不可为空")
    private String machineTypeName;

    @ApiModelProperty(value = "设备型号")
    @Length(max = 24,message = "设备名称最大长度24个字")
    @NotBlank(message = "设备型号不可为空")
    @Pattern(regexp = "^[^\\u4e00-\\u9fa5]+$", message = "设备型号由数字字母符号构成")
    private String machineTypeCode;

    @ApiModelProperty(value = "料盒数量")
    @DecimalMax(value = "99",message = "输入范围有误 1-99")
    @DecimalMin(value = "1",message = "输入范围有误 1-99")
    private Integer ingredientBoxNum;

    @ApiModelProperty(value = "设备主图")
    private String picUrl;

    @ApiModelProperty(value = "状态 0启用 1停用")
    private Integer status;

    @ApiModelProperty(value = "描述-富文本")
    private String description;
}
