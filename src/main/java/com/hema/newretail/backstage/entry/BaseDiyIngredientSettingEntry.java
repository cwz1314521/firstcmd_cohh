package com.hema.newretail.backstage.entry;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
@Data
public class BaseDiyIngredientSettingEntry implements Serializable {
    private Long id;

    private BigDecimal maxIngredient;

    private Integer maxTime;

    private Date gmtCreate;

    private Date gmtModified;

    private Long ingredientId;

    private String markedWords;


}