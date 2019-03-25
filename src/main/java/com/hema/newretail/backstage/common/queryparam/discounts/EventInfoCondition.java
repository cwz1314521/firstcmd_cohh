package com.hema.newretail.backstage.common.queryparam.discounts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Department 新零售
 * @ClassName EventInfoCondition
 * @Description info
 * @Author ---CWZ
 * @Date 2019/1/3 18:42
 * @Version 1.0
 **/

@ApiModel(value = "EventInfoCondition",description = "EventInfoCondition")
@Data
public class EventInfoCondition {
    @ApiModelProperty(value = "id")
    @NotNull(message = "活动ID不允许为空")
    private Long id;
}
