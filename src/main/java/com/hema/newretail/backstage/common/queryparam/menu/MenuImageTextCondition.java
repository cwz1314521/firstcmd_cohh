package com.hema.newretail.backstage.common.queryparam.menu;

import com.hema.newretail.backstage.common.validator.First;
import com.hema.newretail.backstage.common.validator.Second;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.queryparam.menu
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-02-28 14:19
 */
@Data
public class MenuImageTextCondition {
    @NotNull(message = "ID不能为空", groups = {Second.class})
    private Long id;
    @NotNull(message = "饮品ID不允许为空", groups = {First.class})
    private Long menuId;
}
