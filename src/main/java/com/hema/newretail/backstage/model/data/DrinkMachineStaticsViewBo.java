package com.hema.newretail.backstage.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @Department 新零售
 * @ClassName DrinkMachineStaticsViewBo
 * @Description TODO
 * @Author ---yingyan
 * @Date 2018/3/16
 * @Version 1.0
 **/


@Data
public class DrinkMachineStaticsViewBo {
    /***
     * mongodb
     */
    @JsonIgnore
    private Object id;
    private String day;
    private String week;
    private Double totalSaleAmt;//总销售额
    private Double totalRefundAmt;//总退款金额
    private Integer totalMakeCupNum;//总出杯量
    private Float realIncome;//实际营收
    private Integer totalCouponNum;//优惠券发放数量
    private Integer totalUsedCouponNum;//优惠券使用数量
    private String machineUuid;

    /**
     * mysql
     * */
    private String machineName;
    private String machineSequence;
    private String machineDesc;


}
