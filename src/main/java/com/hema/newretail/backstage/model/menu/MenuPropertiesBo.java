package com.hema.newretail.backstage.model.menu;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * hema-newetaril-com.hema.newretail.backstage.model.menu
 *
 * @author ZhangHaiSheng
 * @date 2018-08-25 11:03
 */
@Data
public class MenuPropertiesBo implements Serializable {
    private static final long serialVersionUID = -5378801413712088847L;
    private Long id;
    private Long proType;
    private String typeName;
    private String proName;
    private BigDecimal price;
    private BigDecimal num;
    private Boolean status;
    private Long menuId;
    private Boolean isFrontShow;


}
