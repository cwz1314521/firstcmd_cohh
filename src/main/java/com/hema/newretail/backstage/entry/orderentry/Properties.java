package com.hema.newretail.backstage.entry.orderentry;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.bson.types.Decimal128;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by jiahao on 2018-08-06
 */
@Data
public class Properties implements Serializable {

    private String propertiesId;

    private     String typeName;

    private String proName;

    @JsonIgnore
    private Decimal128 amt;

    private BigDecimal amts;

   private Ingredients ingredients;


}
