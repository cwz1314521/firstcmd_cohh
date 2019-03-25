package com.hema.newretail.backstage.entry;

import lombok.Data;
/**
 * @author cwz
 */
import java.util.Date;

@Data
public class BaseOrganizationEntry {
    private Long id;

    private String orgCode;

    private String orgName;

    private String leader;

    private String contactNumber;

    private String remark;

    private Boolean isDeleted;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer level;
}