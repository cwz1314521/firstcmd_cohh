package com.hema.newretail.backstage.model.bill;

import org.bson.types.Decimal128;

import java.io.Serializable;


/**
 * Created by jiahao on 2018-08-16
 */
public class OrderIngredientsModel implements Serializable {

    private String id;

    private String picture;//配料图

    private Integer num;

    private Decimal128 price;

    private String ingredientsName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }


    public Decimal128 getPrice() {
        return price;
    }

    public void setPrice(Decimal128 price) {
        this.price = price;
    }

    public String getIngredientsName() {
        return ingredientsName;
    }

    public void setIngredientsName(String ingredientsName) {
        this.ingredientsName = ingredientsName;
    }
}
