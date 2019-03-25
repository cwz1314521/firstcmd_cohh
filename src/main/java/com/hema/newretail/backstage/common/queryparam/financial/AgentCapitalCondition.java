package com.hema.newretail.backstage.common.queryparam.financial;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Department 新零售
 * @ClassName AgentCapitalCondition
 * @Description
 * @Author ---CWZ
 * @Date 2019/1/9 17:46
 * @Version 1.0
 **/

@ApiModel(description = "AgentCapitalCondition",value = "AgentCapitalCondition")
@Data
public class AgentCapitalCondition {

    /**
     * list
     */

    @ApiModelProperty(value = "代理Id")
    private Long agentId;

    @ApiModelProperty(value = "开始时间")
    private String startGmt;

    @ApiModelProperty(value = "结束时间")
    private String endGmt;

    @JsonIgnore
    private Date StartGmts;

    @JsonIgnore
    private Date endGmts;

    @ApiModelProperty(value = "页码")
//    @NotNull(message = "页码不可为空")
    private Integer pageNum;

    @ApiModelProperty(value = "每页最大数")
//    @NotNull(message = "每页最大数不可为空")
    private Integer pageSize;

    /***
     * 清算
     */
    @ApiModelProperty(value = "备注")
    private String remark;

    /***
     * 修改
     */
    @ApiModelProperty(value = "收益账户")
    private BigDecimal earningAmount;

    @ApiModelProperty(value = "原料账户")
    private BigDecimal rawAmount;

    @ApiModelProperty(value = "冻结账户")
    private BigDecimal freezeAmount;
}
