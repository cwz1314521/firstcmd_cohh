package com.hema.newretail.backstage.common.queryparam.financial;

import lombok.Data;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.queryparam.financial
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-07 15:07
 */
@Data
public class MenuProfitCondition {
    private Integer pageNum = 1;
    private Integer pageSize = 10;

    private String menuName;
    private Integer status;
    private String startDate;
    private String endDate;
}
