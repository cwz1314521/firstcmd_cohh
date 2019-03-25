package com.hema.newretail.backstage.entry.user;

import lombok.Data;

import java.util.Date;

/**
 * @author cwz
 */
@Data
public class BusiComplaintEntry {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String userName;

    private String userTel;

    private String complaintType;

    private String picUrl;

    private Integer serviceType;

    private Long handlerId;

    private String handlerName;

    private Date handleTime;

    private Integer handleStatus;

    private Integer source;

    private String remark;

    private String description;

}