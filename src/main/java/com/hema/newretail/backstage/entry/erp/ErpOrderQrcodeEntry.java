package com.hema.newretail.backstage.entry.erp;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ErpOrderQrcodeEntry implements Serializable {
    private Long id;

    private Long orderIngredientId;

    private String qrcodeCode;

    private String randomCode;

    private String qrcodeUrl;

    private Date gmtCreate;

    private Date qualityGuaranteePeriod;

    private Integer status;

    private Long companyId;

    private Long gridCompanyId;

    private Long machineId;

    private Long companyInstoreUserId;

    private Date companyInstoreTime;

    private Long companyOutstoreUserId;

    private Date companyOutstoreTime;

    private Long gridUserId;

    private Long gridInstoreUserId;

    private Date gridInstoreTime;

    private Long gridOutstoreUserId;

    private Date gridOutstoreTime;

    private Long gridTaskUserId;

    private Date upTime;

    private Date downTime;

    private Date discardedTime;

    private Long discardedUserId;

    private Date outFactoryTime;


}