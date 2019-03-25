package com.hema.newretail.backstage.entry;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
@Data
public class BaseMachineInfoEntry implements Serializable {
    private Long id;

    private Date gmtCreate;

    private Date gmtModified;

    private Long colorTypeId;

    private Long agentId;



    private String machineName;

    private String machineUuid;

    private String machineCode;

    private Integer machineTypeId;

    private Double longitude;

    private Double latitude;

    private String isDeleted;

    private Integer serviceNum;

    private String hashcode;

    private String province;

    private String city;

    private String area;

    private String street;


    private String ruleRemark;

    private String adcode;

    private String fromtype;

    private String machineDesc;

    private BigDecimal penalSum;

    private BigDecimal cashPledge;

    private String machineSequence;


}