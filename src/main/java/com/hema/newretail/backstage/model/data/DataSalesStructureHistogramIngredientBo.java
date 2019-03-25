package com.hema.newretail.backstage.model.data;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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
public class DataSalesStructureHistogramIngredientBo implements Serializable {
    private static final long serialVersionUID = 3931527923788967664L;
    /**
     * 饮品名称
     */
    private String ingredientName;
    /**
     * 饮品销量
     */
    private Integer saleNumber;
}
