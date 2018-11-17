package com.hema.newretail.backstage.common.queryparam.erp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Department 新零售
 * @ClassName OrderListCondition
 * @Description 订单列表
 * @Author ---CWZ
 * @Date 2018/10/31 9:45
 * @Version 1.0
 **/
@ApiModel(value = "订单列表",description = "订单列表")
@Data
public class OrderListCondition {

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页最大数")
    private Integer pageSize;

    @ApiModelProperty(value = "订单编号")
    private String orderCode;

    @ApiModelProperty(value = "原料厂商ID")
    private Long manufacturerId;

    @ApiModelProperty(value = "分公司ID")
    private Long companyId;

    @ApiModelProperty(value = "配料ID")
    private Long ingredientId;

    @ApiModelProperty(value = "价格区间-开始价格")
    private BigDecimal startPrice;

    @ApiModelProperty(value = "价格区间-结束价格")
    private BigDecimal endPrice;

    @ApiModelProperty(value = "创建时间-开始时间")
    private String startDate;

    @ApiModelProperty(value = "创建时间-结束时间")
    private String endDate;

    private Integer status;


}
