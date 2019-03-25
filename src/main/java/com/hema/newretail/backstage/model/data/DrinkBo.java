package com.hema.newretail.backstage.model.data;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName DrinkBo
 * @Description TODO
 * @Author ---yingyan
 * @Date 2019/3/16
 * @Version 1.0
 **/


@Data
public class DrinkBo {
    private Double totalSaleAmt;
    private Double totalRefundAmt;
    private Float realIncome;//实际营收
    private Integer totalMakeCupNum;
    private Integer totalCouponNum; //优惠券发放数量
    private Integer totalUsedCouponNum; //优惠券使用数量
    private List<DrinkMachineStaticsViewBo> list;
}
