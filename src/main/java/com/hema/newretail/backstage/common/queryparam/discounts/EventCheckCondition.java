package com.hema.newretail.backstage.common.queryparam.discounts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Department 新零售
 * @ClassName EventCheckCondition
 * @Description check
 * @Author ---CWZ
 * @Date 2019/1/3 18:43
 * @Version 1.0
 **/


@ApiModel(value = "EventCheckCondition",description = "EventCheckCondition")
@Data
public class EventCheckCondition {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "预计参与人数")
    private Integer peopleNumber;

    @ApiModelProperty(value = "预计补贴金额")
    private BigDecimal subsidy;

    @ApiModelProperty(value = "活动补贴上限")
    private BigDecimal activitySubsidyLimit;

    @ApiModelProperty(value = "活动备注")
    private String activityRemark;
}
