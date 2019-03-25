package com.hema.newretail.backstage.model.financial;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Department 新零售
 * @ClassName CashListBo
 * @Description listbo
 * @Author ---CWZ
 * @Date 2019/1/8 15:00
 * @Version 1.0
 **/

@Data
public class CashListBo {

    private Long id;
    private String machineSequence;
    private String machineName;
    private String agentName;
    private BigDecimal cashPledge;
    private BigDecimal retiredCashPledge;
    private BigDecimal penalSum;
    private Integer cup;
    private BigDecimal ruleCupMoney;
    private BigDecimal minCashPledge;
    private String gmtModified;
    private String operator;
    private String remark;
    private Integer isFeeSplitting;
    private Integer isCash;
}
