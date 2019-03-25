package com.hema.newretail.backstage.model.bill;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Department 新零售
 * @ClassName OrderBo
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/3/14 14:16
 * @Version 1.0
 **/


@Data
@ApiModel(description = "OrderBo",value = "OrderBo")
public class OrderBo {

    @ApiModelProperty(value = "订单id")
    private String orderId;

    @ApiModelProperty(value = "订单时间")
    private String orderTime;

    @ApiModelProperty(value = "饮品名")
    private String menuName;

    @ApiModelProperty(value = "糖度")
    private String sugar;

    @ApiModelProperty(value = "水温")
    private String temperature;

    @ApiModelProperty(value = "订单状态")
    private String status;

    @ApiModelProperty(value = "失败原因")
    private String errorCode;

    @ApiModelProperty(value = "出杯类型")
    private String cupType;

    @ApiModelProperty(value = "优惠券")
    private String Coupon;

    @ApiModelProperty(value = "机器名")
    private String machineName;

    @ApiModelProperty(value = "订单id")
    private String billId;
    /**
     * 详情
     */
    @ApiModelProperty(value = "配送类型")
    private String sendType;

    @ApiModelProperty(value = "制作时间")
    private String makeTime;

    @ApiModelProperty(value = "优惠券码")
    private String CouponCode;

    @ApiModelProperty(value = "优惠金额")
    private String discounts;

    @ApiModelProperty(value = "机器地址")
    private String machineLocation;

    @ApiModelProperty(value = "分公司")
    private String companyName;

    @ApiModelProperty(value = "网格公司")
    private String gridCompany;

    @ApiModelProperty(value = "代理公司")
    private String agentName;
    private String refundCode;


}
