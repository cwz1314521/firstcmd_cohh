package com.hema.newretail.backstage.model.menu;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * hema-newetaril-com.hema.newretail.backstage.model.menu
 *
 * @author ZhangHaiSheng
 * @date 2018-08-23 12:10
 */
@Data
public class IngredientMenuBo implements Serializable {

    private static final long serialVersionUID = -6153542925776592617L;
    private Long id;
    private String menuName;
    private BigDecimal price;
    /**
     * 是否推荐
     */
    private Long isRecommend;
    private String smallPic;
    private String bigPic;
    private String middlePic;
    /**
     * 排序
     */
    private BigDecimal recommendOrder;
    /**
     * 展示状态 0下架 1上架
     */
    private Boolean status;

    private String anyPic;

    private BigDecimal showOrder;

    private Long imageTextId;
    /**
     * 饮品分类名称
     */
    private String menuClassifyName;
    /**
     * 水温
     */
    private String waterTemperature;


}
