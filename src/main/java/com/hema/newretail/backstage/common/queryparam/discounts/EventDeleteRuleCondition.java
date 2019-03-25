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
 * @ClassName EventEditCondition
 * @Description edit
 * @Author ---CWZ
 * @Date 2019/1/3 18:42
 * @Version 1.0
 **/

@ApiModel(value = "EventDeleteRuleCondition",description = "EventDeleteRuleCondition")
@Data
public class EventDeleteRuleCondition {

    @ApiModelProperty(value = "id")
    private Long id;

}
