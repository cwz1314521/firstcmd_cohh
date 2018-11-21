package com.hema.newretail.backstage.model.bill;

import org.bson.types.Decimal128;

import java.io.Serializable;

/**
 * Created by jiahao on 2018-08-06
 */
public class PropertiesModel implements Serializable {

    private String propertiesId;

    private     String typeName;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    private String proName;

    private Decimal128 amt;

   private IngredientsModel ingredients;

    public String getPropertiesId() {
        return propertiesId;
    }

    public void setPropertiesId(String propertiesId) {
        this.propertiesId = propertiesId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Decimal128 getAmt() {
        return amt;
    }

    public void setAmt(Decimal128 amt) {
        this.amt = amt;
    }

    public IngredientsModel getIngredients() {
        return ingredients;
    }

    public void setIngredients(IngredientsModel ingredients) {
        this.ingredients = ingredients;
    }
}
