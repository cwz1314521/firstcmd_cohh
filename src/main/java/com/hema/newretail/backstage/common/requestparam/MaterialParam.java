package com.hema.newretail.backstage.common.requestparam;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * Created by jiahao on 2018-08-25
 * @author admin
 */
public class MaterialParam {

    private Long ingredientId;

    @NotBlank(message = "配料名称不允许为空")
    private String ingredientName;

    @NotNull(message = "数量不允许为空")
    @DecimalMin(value = "0.1", message = "价格最小是{value}")
    @DecimalMax(value = "999.9", message = "价格最大是{value}")
    @Digits(integer = 3, fraction = 1)
    private Long num;

    public Long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }
}
