package com.hema.newretail.backstage.model.discounts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class ActivityListBo {

    @ApiModelProperty(value = "活动ID")
    private Long id;

    @ApiModelProperty(value = "活动名")
    private String activityName;

    @ApiModelProperty(value = "活动规则名称")
    private String activityRuleName;

    @ApiModelProperty(value = "活动规则ID")
    private Long activityRuleId;

    @ApiModelProperty(value = "开始时间")
    private String effectiveTime;

    @ApiModelProperty(value = "结束时间")
    private String invalidTime;

    @ApiModelProperty(value = "审核状态")
    private String authStatus;

    @ApiModelProperty(value = "活动状态")
    private String activityStatus;

    @ApiModelProperty(value = "申请时间")
    private String applyTime;
    @ApiModelProperty(value = "审核说明")
    private String auditDesc;
    @ApiModelProperty(value = "活动介绍或备注")
    private String activityDesc;

}
