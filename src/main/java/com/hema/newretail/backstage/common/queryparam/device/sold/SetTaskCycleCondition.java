package com.hema.newretail.backstage.common.queryparam.device.sold;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName SetTaskCycleCondition
 * @Description 批量设置任务周期
 * @Author ---CWZ
 * @Date 2018/12/12 21:09
 * @Version 1.0
 **/


@Data
@ApiModel(description = "SetTaskCycleCondition",value = "批量设置任务周期")
public class SetTaskCycleCondition {

    @ApiModelProperty(value = "设备的ID，非空字段")
    @NotNull(message = "设备的ID不可为空")
    private String ids;

    @JsonIgnore
    private List<Long> id;

    @ApiModelProperty(value = "保洁任务周期，单位“天”")
    @Max(value = 999,message = "输入范围有误 0-999")
    @Min(value = 1,message = "输入范围有误 0-999")
    @NotNull(message = "补货任务时间不可为空")
    private Integer cleanupCycle;

    @ApiModelProperty(value = "换件任务周期，单位“天”")
    @Max(value = 999,message = "输入范围有误 0-999")
    @Min(value = 1,message = "输入范围有误 0-999")
    @NotNull(message = "补货任务时间不可为空")
    private Integer replaceCycle;

    @ApiModelProperty(value = "巡检任务周期，单位“天”")
    @Max(value = 999,message = "输入范围有误 0-999")
    @Min(value = 1,message = "输入范围有误 0-999")
    @NotNull(message = "补货任务时间不可为空")
    private Integer inspectionCycle;
}
