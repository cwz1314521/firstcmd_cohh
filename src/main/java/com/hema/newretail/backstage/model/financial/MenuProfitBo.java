package com.hema.newretail.backstage.model.financial;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.model.financial
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-07 15:08
 */
@Data
public class MenuProfitBo implements Serializable {
    private static final long serialVersionUID = 1231730943829448674L;
    private Long id;
    private Long menuId;
    private String menuName;
    private String menuClassifyName;
    private BigDecimal salePrice;
    private BigDecimal profitMoney;
    private Date startTime;
    private Date endTime;
    private String status;
    private String operator;
    private Boolean isDeleted;
    private String remark;
}
