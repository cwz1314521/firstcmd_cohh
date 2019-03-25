package com.hema.newretail.backstage.common.queryparam.device.sold;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName SetMachineServiceCondition
 * @Description 批量手动关停服务接口
 * @Author ---CWZ
 * @Date 2018/12/12 21:14
 * @Version 1.0
 **/



@ApiModel(description = "SetMachineServiceCondition",value = "批量手动关停服务接口")
@Data
public class SetMachineServiceCondition {

    @ApiModelProperty(value = "设备的ID，非空字段")
    @NotNull(message = "设备的ID不可为空")
    private String ids;

    @JsonIgnore
    private List<Long> id;

    @ApiModelProperty(value = "设备服务开关：0开启 1关闭")
    @NotNull(message = "设备的ID不可为空")
    private Integer isDeleted;

}
