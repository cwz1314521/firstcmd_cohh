package com.hema.newretail.backstage.entry.orderentry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.bson.types.Decimal128;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * Created by jiahao on 2018-08-16
 */
@Data
public class OrderIngredients implements Serializable {

    private String id;

    private String picture;//配料图

    private Integer num;

    @JsonIgnore
    private Decimal128 price;
    private BigDecimal prices;

    private String ingredientsName;


}
