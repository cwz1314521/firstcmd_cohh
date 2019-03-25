package com.hema.newretail.backstage.common.queryparam.device.outstore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Department 新零售
 * @ClassName CommonIdCondition
 * @Description 设备模块Id公共参数类
 * @Author ---CWZ
 * @Date 2018/12/12 10:50
 * @Version 1.0
 **/

@ApiModel(description = "CommonIdCondition")
@Data
public class CommonIdCondition {

    @ApiModelProperty(value = "ID")
    @NotNull(message = "设备的ID不可为空")
    private Long id;
}
