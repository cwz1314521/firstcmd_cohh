package com.hema.newretail.backstage.model.taskkafka;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.model.taskkafka
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-11-21 13:49
 */
@Setter
@Getter
@NoArgsConstructor
public class IngredientBoxBo implements Serializable {
    private static final long serialVersionUID = -2519403568929243505L;
    private Integer boxCode;
    private Long ingredientId;
    private Integer capacity;
    private Integer maxCopies;
    private Float warnPercent;
}
