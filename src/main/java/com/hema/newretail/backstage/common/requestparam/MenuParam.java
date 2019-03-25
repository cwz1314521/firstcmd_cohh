package com.hema.newretail.backstage.common.requestparam;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;


/**
 * @author admin
 */
@Data
public class MenuParam {

    private Long id;

    @NotBlank(message = "商品名称不允许为空")
    @Length(min = 1, max = 32, message = "商品名称的长度在1至16个字符之间")
    private String menuName;

    @NotNull(message = "商品价格不允许为空")
    @DecimalMin(value = "0.01", message = "价格最小是{value}")
    @DecimalMax(value = "99.99", message = "价格最大是{value}")
    @Digits(integer = 2, fraction = 2)
    private BigDecimal price;

    private String smallPic;

    private String middlePic;

    private String bigPic;

    private String anyPic;

    /**
     * 配料的添加
     */
    private List<MaterialParam> materialParamList;

    /**
     * 属性的选项的添加
     */
    private List<OptionParam> optionParamList;

    /**
     * 编辑时标签的修改
     */
    private List<TagParam> tagParamList;

    private Boolean status;

    private BigDecimal recommendOrder;

    private Long isRecommend;


    private Long menuClassifyId;
    private String subTitle;
    private String menuDesc;

    private BigDecimal showOrder;
    private Long imageTextId;
}
