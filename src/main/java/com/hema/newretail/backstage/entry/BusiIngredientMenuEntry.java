package com.hema.newretail.backstage.entry;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author admin
 */
@Data
public class BusiIngredientMenuEntry implements Serializable {
    private static final long serialVersionUID = 5945436804823630488L;

    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String menuName;

    private Long isDiy;

    private Long isDeleted;

    private BigDecimal price;

    private String diyKeywords;

    private String smallPic;

    private String bigPic;

    private String anyPic;

    private String middlePic;

    private Long isRecommend;

    private BigDecimal recommendOrder;

    private Boolean status;

    private Long menuClassifyId;

    private String subTitle;

    private String menuDesc;

    private BigDecimal showOrder;

}