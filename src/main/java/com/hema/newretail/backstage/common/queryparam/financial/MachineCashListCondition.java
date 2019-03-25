package com.hema.newretail.backstage.common.queryparam.financial;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Department 新零售
 * @ClassName MachineCashListCondition
 * @Description list
 * @Author ---CWZ
 * @Date 2019/1/8 16:10
 * @Version 1.0
 **/

@Data
@ApiModel(value = "MachineCashListCondition")
public class MachineCashListCondition {

    @ApiModelProperty(value = "押金金额 -- 结束")
    private BigDecimal endMoney;

    @ApiModelProperty(value = "创建时间 -- 开始")
    private String StartGmt;

    @ApiModelProperty(value = "创建时间 -- 结束")
    private String endGmt;

    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可为空")
    private Integer pageNum;

    @ApiModelProperty(value = "每页最大数")
    @NotNull(message = "每页最大数不可为空")
    private Integer pageSize;

    @ApiModelProperty(value = "机器ID ")
    private Long machineId;

    @ApiModelProperty(value = "代理ID ")
    private Long agentId;

    @ApiModelProperty(value = "序列号 ")
    private String machineSequence;

    @ApiModelProperty(value = "押金金额 -- 开始")
    private BigDecimal StartMoney;

    @JsonIgnore
    private Date StartGmts;
    @JsonIgnore
    private Date endGmts;
}
