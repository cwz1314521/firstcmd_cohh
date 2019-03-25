package com.hema.newretail.backstage.common.queryparam.common;

import lombok.Data;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.queryparam.common
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-01-11 14:57
 */
@Data
public class GlobalCondition {
    /**
     * 名称
     */
    private String aName;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 每页记录数
     */
    private Integer pageSize = 10;
}
