package com.hema.newretail.backstage.model.diy;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Department 新零售
 * @ClassName IngredientEventBo
 * @Description diy配料设置列表
 * @Author ---CWZ
 * @Date 2018/10/18 11:12
 * @Version 1.0
 **/
@Data
public class IngredientEventBo implements Serializable {

    private Long id ;

    private String ingredientName;

    private BigDecimal maxIngredient;

    private Integer maxTime;

    private Integer eventAccount;


}
