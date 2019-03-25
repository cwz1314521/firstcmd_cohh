package com.hema.newretail.backstage.model.organization;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author cwz
 */

@Data
public class BaseOrganizationBo {
    private Long id;

    private String orgCode;

    private String orgName;

    private String pOrgName;

    private String pOrgCode;

    private String leader;

    private String contactNumber;

    private String remark;

    private String gmtCreate;

    private Integer level;

    private List<BaseOrganizationBo> children;
}