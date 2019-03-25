package com.hema.newretail.backstage.model.erp;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.model.erp
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-11-26 10:45
 */
@Getter
@Setter
@NoArgsConstructor
public class StockManageBo implements Serializable {
    private static final long serialVersionUID = 6785079604395677517L;
    /**
     * 溯源ID
     */
    private Long id;
    /**
     * 原料名称
     */
    private String ingredientName;
    /**
     * 溯源码
     */
    private String qrcodeCode;
    /**
     * 到期日期
     */
    private String qualityGuaranteePeriod;
    /**
     * 状态
     */
    private String status;
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 是否过期
     */
    private String isPeriod;
    /**
     * 入库人
     */
    private String companyInstoreUser;
    /**
     * 出库人
     */
    private String companyOutstoreUser;
    /**
     * 分公司入库时间
     */
    private String companyInstoreTime;
    /**
     * 分公司出库时间
     */
    private String companyOutstoreTime;
}
