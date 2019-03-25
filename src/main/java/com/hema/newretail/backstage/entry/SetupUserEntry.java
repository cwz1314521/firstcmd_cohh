package com.hema.newretail.backstage.entry;

import lombok.Data;

import java.util.Date;

@Data
public class SetupUserEntry {
    private Long id;

    private String username;

    private String password;

    private Long componyId;

    private String mobile;

    private Integer status;

    private Date gmtCreate;

    private Date gmtModified;


}