package com.hema.newretail.backstage.entry;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class ZoneBase implements Serializable {
    private Long id;

    private Long MachineScreenPicId;

    private String zoneName;

    private String province;

    private String city;

    private String area;

    private Long machineTypeId;

    private Integer machineNum;

    private Date gmtCreate;

    private Date gmtModified;

    private Long boxGroupId;



}