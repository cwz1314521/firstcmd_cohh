package com.hema.newretail.backstage.entry.orderentry;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class OrdersData implements Serializable {

    private String id;

    private String billId;

    @JsonIgnore
    private Decimal128 amt;

    private BigDecimal amts;

    @JsonIgnore
    private Decimal128 discount;

    private BigDecimal discounts;
	/**
	 * 饮品数量
	 */
    private Integer num;

    private String bigPic;

    private String smallPic;


    private String recommend;

    private Long machineId;//(机器编码): int （扫码制作，用户确认后提交）

    private String deviceNumber;//设备序列号（扫码制作，用户确认后提交）

    private String province;

    private String city;

    private String area;

    private String agent;//代理

    private String grid;//网络

    private Date productionTime;//制作时间

    @JsonIgnore
    private Decimal128 price;

    private BigDecimal prices;

    private Date orderTime;


    /**
     * 订单状态 0，未付款 1未制作，2，不能做，3，超时，4 待确认，6已处在制作队列中，7确认完毕放弃制作，
     * 8退款中，9退款成功，10已退券，11退款失败，129制作中，135未领取，143已领取，15制作异常
     */
    private String orderStatus;

    private Integer menuId;

    private List<OrderIngredients> orderIngredients;

    private List<Properties> properties;

    private String remarks;//备注

    private String waterTemperature;//水温 0 常温，1 热水，2 冷水

    private String deviceLocation;//设备位置

    private Date takeCupTime;

    private String machineUuid;
	/**  退款单号：待定，每次重新发起退款，重新生成*/
	private String refundCode;

	/** 出杯单号*/
	private String makeCode;
	/*** 饮品名称*/
	private String menuName;
    /**
     * 优惠券
     */
	private Long couponId;
	private String couponName;

    /**
     * 失败原因
     */
	private List<String> errorCode;

	private String machineName;

	private String cupType;



}
