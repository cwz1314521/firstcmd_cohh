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
 * @date 2019-01-14 16:01
 */
@Data
public class AgentTransactionRecordBo implements Serializable {
    private static final long serialVersionUID = 1434195139725127935L;
    private Long id;
    private Long agentCompanyId;
    private String agentCompanyName;
    private String actionType;
    private BigDecimal incomeAccount;
    private BigDecimal materialAccount;
    private BigDecimal depositAccount;
    private BigDecimal freezAmount;
    private Date createDate;
    private String remark;
}
