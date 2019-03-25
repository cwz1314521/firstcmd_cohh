package com.hema.newretail.backstage.common.queryparam.memberManager;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.queryparam.memberManager
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-03-16 14:45
 */
@Data
public class GiveCouponCondition {
    /**
     * 用户的ID，多个ID之间用“，”号分隔开
     */
    @NotBlank(message = "用户ID不允许为空")
    private String ids;
}
