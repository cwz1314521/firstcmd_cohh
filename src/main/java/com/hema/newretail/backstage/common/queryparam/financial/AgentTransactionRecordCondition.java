package com.hema.newretail.backstage.common.queryparam.financial;

import lombok.Data;

import java.math.BigDecimal;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.queryparam.financial
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-11 15:59
 */
@Data
public class AgentTransactionRecordCondition {
    private Integer pageNum = 1;
    private Integer pageSize = 10;

    /**
     * 代理公司名称
     */
    private String agenCompanyName;
    /**
     * 交易类型
     */
    private Integer actionType;
    /**
     * 发生开始日期
     */
    private String startDate;
    /**
     * 发生结束日期
     */
    private String endDate;
    /**
     * 发生额起始值(保留两位小数)
     */
    private BigDecimal startNumber;
    /**
     * 发生额结束值(保留两位小数)
     */
    private BigDecimal endNumber;
}
