package com.hema.newretail.backstage.common.queryparam.financial;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.bson.types.Decimal128;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName RetiredCashCondition
 * @Description 设置规则
 * @Author ---CWZ
 * @Date 2019/1/8 16:45
 * @Version 1.0
 **/

@Data
@ApiModel(value = "RetiredCashCondition")
public class RetiredCashCondition {

    @ApiModelProperty(value = "杯数")
    private Integer cup ;

    @ApiModelProperty(value = "每杯返会钱数")
    private BigDecimal ruleCupMoney ;

    @ApiModelProperty(value = "最小押金")
    private BigDecimal minCashPledge ;

    @ApiModelProperty(value = "ids")
    private String ids ;

    @ApiModelProperty(value = "押金设置")
    private BigDecimal cashPledge ;

    @ApiModelProperty(value = "id")
    private Long id ;

    @JsonIgnore
    private List<Long> idList ;

    @JsonIgnore
    private Long operator;

    @ApiModelProperty(value = "违约金")
    private BigDecimal penalSum;

    @ApiModelProperty(value = "是否返还押金 0 开启  1停止")
    private Integer isCash;

    @ApiModelProperty(value = "是否分润 0 开启  1停止")
    private Integer isFeeSplitting;

    @ApiModelProperty(value = "所有备注")
    private String remark;
}
