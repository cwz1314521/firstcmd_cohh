package com.hema.newretail.backstage.entry;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
@Data
public class BaseUserInfoEntry implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private String userName;

    private String userPassword;

    private String salt;

    private Date loginTime;

    private String loginIp;

    private Integer loginCount;

    private Long postId;

    private Long companyId;

    private Boolean isDeleted;

    private String realName;

    private String email;

    private Integer sex;

    private String phoneNumber;

    private String qq;

    private Integer status;

    private Long orgId;

    private String orgName;

    private String orgCode;
}