package com.hema.newretail.backstage.common.queryparam.tag;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.queryparam.tag
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-03-06 14:24
 */
@Data
public class MenuMarkerSaveCondition {
    @NotNull(message = "角标id不允许为空")
    private Long id;

    @NotNull(message = "关联饮品不允许为空")
    private List<Long> menus;
}
