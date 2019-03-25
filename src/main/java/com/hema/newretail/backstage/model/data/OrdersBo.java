package com.hema.newretail.backstage.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hema.newretail.backstage.entry.orderentry.OrderIngredients;
import com.hema.newretail.backstage.entry.orderentry.Properties;
import lombok.Data;
import org.bson.types.Decimal128;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Auther: 程文政
 * @Date: 2018/8/23 09:21
 * @Description:出货订单实体类 ---------------对应mongoDB
 * @Version: 1.0
 */
@Data
public class OrdersBo implements Serializable {

    /**订单编号*/
    private String id;

    /**用户手机号------待定*/
    private String phoneNum;

    /**饮品名*/
    private String menuName;

    /**售价*/
    @JsonIgnore
    private Decimal128 price;
    private BigDecimal prices;

    /**实付*/
    @JsonIgnore
    private Decimal128 amt;
    private BigDecimal amts;

    /**下单时间*/
    @JsonIgnore
    private Date orderTime;
    private String orderTimes;
    /**制作时间*/
    @JsonIgnore
    private Date takeCupTime;
    private String takeCupTimes;

    /**冷热*/
    private String water;

    @JsonIgnore
    private List<Properties> properties;




}
