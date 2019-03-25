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
 * @date 2018-12-19 19:47
 */
@Getter
@Setter
@NoArgsConstructor
public class DataSalesStructureListBo implements Serializable {
    private static final long serialVersionUID = -194103464882632848L;
    private Integer totalSaleCupNum = 0;
    private Integer totalMakeCupNum = 0;
    private Double totalSaleAmt = 0.00;
    private List<DataSalesStructureListIngredientBo> listData;
}
