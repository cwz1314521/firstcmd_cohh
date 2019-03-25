package com.hema.newretail.backstage.common.queryparam.memberManager;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.queryparam.memberManager
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-03-16 14:14
 */
@Data
public class UserManagementDetailCondition {
    private Integer pageNum;

    private Integer pageSize;
    /**
     * 用户ID
     */
    @NotBlank(message = "用户ID不允许为空")
    private String id;
}
