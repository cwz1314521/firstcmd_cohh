package com.hema.newretail.backstage.entry.orderentry;


import com.hema.newretail.backstage.common.utils.TimeUtil;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by jiahao on 2018-08-06
 */
@Data
public class BillData implements Serializable {

    private String id;
    private String openId;
    /**
     *
     */
    private String nickName;//用户昵称
    private String phoneNumber;//用户手机号
    private BigDecimal amt;
    private BigDecimal discount;
    private BigDecimal billPrice;
    private BigDecimal activityDiscount;
    private BigDecimal scoutDiscount;
    private Integer totalNum;
    private Date billTime;
    private Date wholeBillEndTime;
    private String billStatus;//账单状态：默认（001）未付款、002已付款、003退款中，004退款成功
    private String paySerialNumbers;//支付流水号（支付回调修改）
    private String paymentType;//支付方式（支付回调修改），001微信、002支付宝、默认000
    private List orders;

    private String paymentStatus;//支付状态 默认：000， 成功：001

    private String remark;

    private String shipType;
    private String billSource;

    private String billTimeStr;

    private List<Long> activityId;
    private String receiveMan;
    private String receiveTel;
    private String receiveAddress;
}

