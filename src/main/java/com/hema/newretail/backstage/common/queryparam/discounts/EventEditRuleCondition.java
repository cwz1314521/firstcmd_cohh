package com.hema.newretail.backstage.common.queryparam.discounts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * @Department 新零售
 * @ClassName EventEditCondition
 * @Description edit
 * @Author ---CWZ
 * @Date 2019/1/3 18:42
 * @Version 1.0
 **/

@ApiModel(value = "EventEditRuleCondition",description = "EventEditRuleCondition")
@Data
public class EventEditRuleCondition {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "规则名称")
    private String ruleName;

    @ApiModelProperty(value = "单日参与次数")
    private Integer perDayCount;

    @ApiModelProperty(value = "单日单人参与次数")
    private Integer perDaySingalCount;

    @ApiModelProperty(value = "单人总参与次数")
    private Integer singalTotalCount;

    @ApiModelProperty(value = "活动对象")
    private Integer activeObject;

    @ApiModelProperty(value = "规则")
    private String ruleCondition;


}
