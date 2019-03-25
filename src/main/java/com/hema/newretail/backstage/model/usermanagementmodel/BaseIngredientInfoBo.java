package com.hema.newretail.backstage.model.usermanagementmodel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public class BaseIngredientInfoBo implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String ingredientName;

    private String ingredientCode;

    private String ingredientDescription;

    private String ingredientPic;

    private BigDecimal ingredientPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName == null ? null : ingredientName.trim();
    }

    public String getIngredientCode() {
        return ingredientCode;
    }

    public void setIngredientCode(String ingredientCode) {
        this.ingredientCode = ingredientCode == null ? null : ingredientCode.trim();
    }

    public String getIngredientDescription() {
        return ingredientDescription;
    }

    public void setIngredientDescription(String ingredientDescription) {
        this.ingredientDescription = ingredientDescription == null ? null : ingredientDescription.trim();
    }

    public String getIngredientPic() {
        return ingredientPic;
    }

    public void setIngredientPic(String ingredientPic) {
        this.ingredientPic = ingredientPic == null ? null : ingredientPic.trim();
    }

    public BigDecimal getIngredientPrice() {
        return ingredientPrice;
    }

    public void setIngredientPrice(BigDecimal ingredientPrice) {
        this.ingredientPrice = ingredientPrice;
    }
}