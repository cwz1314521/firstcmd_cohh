package com.hema.newretail.backstage.entry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hema.newretail.backstage.entry.orderentry.BillData;
import com.hema.newretail.backstage.entry.orderentry.OrderIngredients;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 */
@Data
public class OrderData implements Serializable {
    private String id;
    private String userId;
    private String userName;
    private String billId;
    private BigDecimal orderPrice;
    private BigDecimal amt;
    private String makeCode;

    private String refundCode;

    private List<BillData> billData;

    /**
     * 单杯活动优惠金额
     */
    private BigDecimal activityDiscount;

    /**
     * 单杯优惠券优惠金额
     */
    private BigDecimal discount;

    /**
     * 优惠类型
     */
    private Integer discountType;
    /**
     * 优惠方式
     */
    private Integer discountMode;

    /**
     * 优惠券id
     */
    private Long couponId;
    /**
     * 优惠券名称
     */
    private String couponName;

    private Integer num;
    private String bigPic;
    private String smallPic;
    private String anyPic;

    private String middlePic;
    private String menuName;
    private String recommend;
    /**
     * (机器编码): int （扫码制作，用户确认后提交）
     */
    private String machineId;
    private String machineName;
    /**
     * 设备序列号（扫码制作，用户确认后提交）
     */
    private String deviceNumber;
    private String province;
    private String city;
    private String area;
    private Long agent;
    private Long grid;
    /**
     * 制作时间
     */
    private Date productionTime;

    private BigDecimal price;

    private Date orderTime;

    private Date takeCupTime;
    private String againStatus;
    private String orderStatus;
    private Boolean compensationRefund;

    private BigDecimal compensationMoney;
    /**
     * 退款原因，退款回调
     */
    private String refundDesc;
    /**
     * 退款申请时间，退款回调
     */
    @JsonIgnore
    private Date applyRefundTime;
    private String applyRefundTimes;
    /**
     * 退款成功时间，退款通知接口回调
     */
    @JsonIgnore
    private Object successRefundTime;
    private String successRefundTimes;
    private Long menuId;
    private String gmtModified;
    private Integer diy;
    private List<OrderIngredients> orderIngredients;
    private List<OrderPropertyData> properties;
    private String givenScountId;

    /**
     * 订单下标，默认65636：退款、下单、继续制作
     */
    private Integer orderIndex;

    /**
     * 机器UUID码
     */
    private String machineUuid;

    private String phoneNum;
}
