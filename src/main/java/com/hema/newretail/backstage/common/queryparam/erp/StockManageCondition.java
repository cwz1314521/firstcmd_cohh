package com.hema.newretail.backstage.common.queryparam.erp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.queryparam.erp
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-11-26 10:34
 */
@Getter
@Setter
@NoArgsConstructor
public class StockManageCondition {
    private Integer pageNum = 1;

    private Integer pageSize = 10;

    /**
     * 溯源码
     */
    private String qrcodeCode;

    /**
     * 配料名称
     */
    private String ingredientName;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 到期时间
     */
    private String qualityGuaranteePeriod;

    /**
     * 是否过期 0全部 1不过期 2过期
     */
    private Integer isPeriod;

    /**
     * 目前状态 0全部 1在库 2已出库
     */
    private Integer state;

    /**
     * 入库时间-开始时间
     */
    private String startDate;

    /**
     * 入库时间-结束时间
     */
    private String endDate;

    /**
     * 入库人
     */
    private String companyInstoreUser;

    /**
     * 出库人
     */
    private String companyOutstoreUser;

    /**
     * 出库时间-开始时间
     */
    private String startDateOutstore;

    /**
     * 出库时间-结束时间
     */
    private String endDateOutstore;
}
