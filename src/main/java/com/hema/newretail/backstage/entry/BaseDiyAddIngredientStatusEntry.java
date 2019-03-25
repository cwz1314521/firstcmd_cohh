package com.hema.newretail.backstage.entry;

import java.io.Serializable;
import java.util.Date;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
public class BaseDiyAddIngredientStatusEntry implements Serializable {
    private Long id;

    private Integer ingredientsVolumeMin;

    private Integer ingredientsVolumeMax;

    private String stateDescription;

    private Date gmtCreate;

    private Date gmtModified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIngredientsVolumeMin() {
        return ingredientsVolumeMin;
    }

    public void setIngredientsVolumeMin(Integer ingredientsVolumeMin) {
        this.ingredientsVolumeMin = ingredientsVolumeMin;
    }

    public Integer getIngredientsVolumeMax() {
        return ingredientsVolumeMax;
    }

    public void setIngredientsVolumeMax(Integer ingredientsVolumeMax) {
        this.ingredientsVolumeMax = ingredientsVolumeMax;
    }

    public String getStateDescription() {
        return stateDescription;
    }

    public void setStateDescription(String stateDescription) {
        this.stateDescription = stateDescription == null ? null : stateDescription.trim();
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
}