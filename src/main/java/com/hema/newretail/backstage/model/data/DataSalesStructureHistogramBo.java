package com.hema.newretail.backstage.model.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.model.data
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-12-18 14:44
 */
@Getter
@Setter
@NoArgsConstructor
public class DataSalesStructureHistogramBo implements Serializable {
    private static final long serialVersionUID = -1678284450466163601L;
    /**
     * 饮品总销量
     */
    private Integer totalSaleNumber;
    /**
     * 统计饮品列表
     */
    private List<DataSalesStructureHistogramIngredientBo> ingredintes;
}
