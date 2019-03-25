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
 * @date 2018-12-19 19:47
 */
@Getter
@Setter
@NoArgsConstructor
public class DataSalesStructureListIngredientBo implements Serializable {
    private static final long serialVersionUID = -2997395646695379621L;

    private Long ingredientId;
    private String ingredientName;
    private Integer saleCupNum;
    private Double saleAmt;
    private Integer makeCupNum;
}
