package com.hema.newretail.backstage.common.queryparam.discounts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @Department 新零售
 * @ClassName EventAddCondition
 * @Description add
 * @Author ---CWZ
 * @Date 2019/1/3 18:41
 * @Version 1.0
 **/

@ApiModel(value = "EventAddCondition",description = "EventAddCondition")
@Data
public class EventAddCondition {

    @ApiModelProperty(value = "活动名称")
    @NotBlank(message = "活动名称不可为空")
    @Length(min = 1,max = 24,message = "公司名称在1到20个字符中间")
    private String activityName;

    @ApiModelProperty(value = "活动规则ID")
    private Long activityRuleId;

    @ApiModelProperty(value = "生效时间")
    private String effectiveTime;

    @ApiModelProperty(value = "失效时间")
    private String invalidTime;

    @ApiModelProperty(value = "副标题")
    private String subTitle;

    @ApiModelProperty(value = "活动介绍")
    @Length(max = 50,message = "备注在50字以内")
    private String activityDesc;
}
