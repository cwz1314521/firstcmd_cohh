package com.hema.newretail.backstage.common.queryparam.usermanagementparameter;

import lombok.Data;

/**
 * @Auther: 程文政
 * @Date: 2018/8/23 14:10
 * @Description: 制作订单参数类
 * @Version: 1.0
 */
@Data
public class MakeOrderCondition {

    //页码
    private Integer pageNum;

    //记录数
    private Integer pageSize;

    //id查询 订单号
    private String id;

    private String billId;

    //机器编号查询
    private String deviceNumber;

    //状态查询
    private String orderStatus;

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

    //前制作时间
    private String preOrderTime;

    //后制作时间
    private String orderTime;

    private String machineName;


    private String type;





}
