package com.hema.newretail.backstage.model.bill;

import org.bson.types.Decimal128;

import java.io.Serializable;

/**
 * @Auther: 程文政
 * @Date: 2018/8/23 13:17
 * @Description:
 * @Version: 1.0
 */
public class IngredientsModel implements Serializable {

    private String id;
    private Decimal128 price;
    private Integer num;
    private String picture;
    private String ingredientsName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Decimal128 getPrice() {
        return price;
    }

    public void setPrice(Decimal128 price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIngredientsName() {
        return ingredientsName;
    }

    public void setIngredientsName(String ingredientsName) {
        this.ingredientsName = ingredientsName;
    }
}
