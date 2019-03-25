package com.hema.newretail.backstage.common.queryparam.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.queryparam.data
 * 饮品销量统计柱状图
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-12-18 13:45
 */
@Getter
@Setter
@NoArgsConstructor
public class DataSalesStructureHistogramCondition {
    /**
     * 开始日期
     */
    private String dateStart;

    /**
     * 结束日期
     */
    private String dateEnd;

    private Integer pageNum = 1;

    private Integer pageSize = 10;
}
