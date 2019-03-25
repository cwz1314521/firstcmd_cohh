package com.hema.newretail.backstage.common.queryparam.menuclassify;

import lombok.Data;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.queryparam.menuclassify
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-12-24 15:28
 */
@Data
public class MenuClassifyCondition {
    /**
     * 饮品分类名称
     */
    private String name;

    /**
     * 状态：-1全部 0启用 1禁用
     */
    private Integer status;

    private Integer pageNum = 1;
    private Integer pageSize = 10;
}
