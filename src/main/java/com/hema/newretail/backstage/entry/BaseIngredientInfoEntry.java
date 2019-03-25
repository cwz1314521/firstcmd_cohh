package com.hema.newretail.backstage.entry;

import lombok.Data;

import javax.validation.constraints.Size;
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
public class BaseIngredientInfoEntry implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String ingredientName;

    @Size(message = "最大长度1",max = 1)
    private String ingredientCode;

    private String ingredientDescription;

    private String ingredientPic;

    private BigDecimal ingredientPrice;

    private BigDecimal ingredientUnitPrice;

    private BigDecimal diyPrice;


}