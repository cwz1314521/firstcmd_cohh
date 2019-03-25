package com.hema.newretail.backstage.common.queryparam.agent;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * @Department 新零售
 * @ClassName TestControllerCondition
 * @Description TODO
 * @Author ---CWZ
 * @Date 2018/10/22 15:59
 * @Version 1.0
 **/
@ApiModel(description = "TestControllerCondition")
@Data
public class TestControllerCondition {





    private BigDecimal tel;
}
