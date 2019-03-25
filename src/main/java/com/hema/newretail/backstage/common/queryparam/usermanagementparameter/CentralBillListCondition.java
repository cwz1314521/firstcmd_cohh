package com.hema.newretail.backstage.common.queryparam.usermanagementparameter;



import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 程文政
 * @Date: 2018/8/24 13:34
 * @Description:   订单中心查询参数类
 * @Version: 1.0
 */
@Data
public class CentralBillListCondition {

    private String id;//订单号

    private String billId;//主键 账单号

    private String userId;//用户id

    private String paySerialNumbers;//支付流水号

    private BigDecimal preAmt;//订单金额

    private BigDecimal amt;//订单金额

    private String preBillTime;//订单时间

    private String billTime;//订单时间

    private String nickName;//用户名

    private String phoneNumber;//电话号

    private String paymentType;//支付方式

    private String billStatus;//状态



    private Integer pageSize;

    private Integer pageNum;


    //省查询
    private String province;

    //市查询
    private String city;

    //区查询
    private String area;

    //代理查询
    private String agent;

    //网格查询
    private String grid;

    //出货状态
    private String orderStatus;

    //机器编号查询
    private String deviceNumber;

	/**
	 * refundCode 退款单号：待定，每次重新发起退款，重新生成
	 */
	private String refundCode;
	/**
	 * 出杯单号
	 */
	private String makeCode;
	/**
	 * 申请退款时间开始
	 */
	private String refundStartDate;
	/**
	 * 申请退款结束开始
	 */
	private String refundEndDate;
	/**
	 * 退款原因
	 */
	private String refundDesc;
	/**
	 * 退款状态
	 */
	private String refundState;
	/**
	 * 备注
	 */
	private String remarks;

    private String shipType;

    private String givenScountId;
}
