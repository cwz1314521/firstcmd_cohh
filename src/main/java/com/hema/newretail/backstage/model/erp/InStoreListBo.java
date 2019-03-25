package com.hema.newretail.backstage.model.erp;

import lombok.Data;

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
@Data
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


}
