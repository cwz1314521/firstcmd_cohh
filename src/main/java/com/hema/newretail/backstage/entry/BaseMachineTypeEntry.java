package com.hema.newretail.backstage.entry;

import lombok.Data;

import java.util.Date;
@Data
public class BaseMachineTypeEntry {
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    private String machineTypeName;

    private String machineTypeCode;

    private Integer ingredientBoxNum;

    private String picUrl;

    private Integer isDeleted;

    private Integer status;

    private String remark;

    private String operator;

    private String description;
}