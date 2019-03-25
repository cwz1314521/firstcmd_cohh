package com.hema.newretail.backstage.common.queryparam.menu;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.queryparam.menu
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-02-28 14:55
 */
@Data
public class UpdateRecommendCondition {
    @NotNull(message = "id不允许为空")
    private Long id;

    @NotNull(message = "首页排序字段不允许为空")
    private BigDecimal recommendOrder;

    @NotNull(message = "是否推荐到首页字段不允许为空")
    private Long isRecommend;
}
