package com.hema.newretail.backstage.model.tag;

import lombok.Data;

import java.io.Serializable;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.model.tag
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-03-06 11:49
 */
@Data
public class MenuMarkerListBo implements Serializable {
    private static final long serialVersionUID = 4718225429256305919L;
    private Long id;
    private Long menuId;
    private String menuName;
}
