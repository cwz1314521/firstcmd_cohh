package com.hema.newretail.backstage.model.discounts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Department 新零售
 * @ClassName ActivityListBo
 * @Description list
 * @Author ---CWZ
 * @Date 2019/1/4 10:42
 * @Version 1.0
 **/

@Data
@ApiModel(description = "ActivityListBo",value = "ActivityListBo")
public class ActivityInfoBo {
    @ApiModelProperty(value = "活动ID")
    private Long id;

    @ApiModelProperty(value = "活动名")
    private String activityName;

    @ApiModelProperty(value = "活动规则ID")
    private Long activityRuleId;

    @ApiModelProperty(value = "开始时间")
    private String effectiveTime;

    @ApiModelProperty(value = "结束时间")
    private String invalidTime;

    @ApiModelProperty(value = "活动说明")
    private String activityDesc;

    @ApiModelProperty(value = "副标题")
    private String subTitle;

    @ApiModelProperty(value = "预计参与人数")
    private Integer peopleNumber;

    @ApiModelProperty(value = "预计补贴金额")
    private BigDecimal subsidy;

    @ApiModelProperty(value = "活动补贴上限")
    private BigDecimal activitySubsidyLimit;

    @ApiModelProperty(value = "活动备注")
    private String activityRemark;

    @ApiModelProperty(value = "申请时间")
    private String applyTime;

    @ApiModelProperty(value = "申请人")
    private String applicant;
}
