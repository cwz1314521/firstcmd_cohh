package com.hema.newretail.backstage.model.erp;

import java.io.Serializable;
import java.util.Date;

/**
 * @Department 新零售
 * @ClassName InStoreListBo
 * @Description 分后台  列表
 * @Author ---CWZ
 * @Date 2018/11/2 15:07
 * @Version 1.0
 **/
public class InStoreListBo implements Serializable {

    /**id*/
    private Long id;

    /**原料名称*/
    private String ingredientName;

    /**二维码编码*/
    private Date qualityGuaranteePeriod;

    /**有效期*/
    private String status;

    private String qrcodeCode;

    public String getQrcodeCode() {
        return qrcodeCode;
    }

    public void setQrcodeCode(String qrcodeCode) {
        this.qrcodeCode = qrcodeCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public Date getQualityGuaranteePeriod() {
        return qualityGuaranteePeriod;
    }

    public void setQualityGuaranteePeriod(Date qualityGuaranteePeriod) {
        this.qualityGuaranteePeriod = qualityGuaranteePeriod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
