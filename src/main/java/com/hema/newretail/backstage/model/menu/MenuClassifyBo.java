package com.hema.newretail.backstage.model.menu;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.model.menu
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-12-24 15:50
 */
@Data
public class MenuClassifyBo implements Serializable {
    private static final long serialVersionUID = -8774070517015196574L;

    private Long id;

    private BigDecimal sort;

    private String name;

    private String picUrl;

    private Integer status;
}
