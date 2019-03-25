package com.hema.newretail.backstage.model.device.sold;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @Department 新零售
 * @ClassName SoldListBo
 * @Description 已出售设备列表Bo
 * @Author ---CWZ
 * @Date 2018/12/13 10:58
 * @Version 1.0
 **/


@Data
@ApiModel(description = "SoldListBo",value = "已出售设备列表Bo")
public class SoldListBo {

    @ApiModelProperty(value = "设备ID")
    private Long id;

    @ApiModelProperty(value = "批量操作ID")
    private Long bmiId;

    @ApiModelProperty(value = "设备序列号")
    private String machineSequence;

    @ApiModelProperty(value = "设备名")
    private String machineName;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String area;

    @ApiModelProperty(value = "设备地址")
    private String street;

    private String machineDesc;

    @ApiModelProperty(value = "代理公司名称")
    private String agentCompany;

    @ApiModelProperty(value = "联系方式, 代理信息=代理公司名称+联系方式")
    private String contactWay;

    @ApiModelProperty(value = "设备网格，设备归属网格公司名称")
    private String gridCompany;

    @ApiModelProperty(value = "激活时间")
    private String activityTime;

    @ApiModelProperty(value = "[{key:0,value:正常}] 设备状态：0正常、1维护、2离线、3故障、4暂停服务、5未激活,")
    private Integer machineState;

    @JsonIgnore
    @ApiModelProperty(value = "uuid")
    private String machineUuid;

    @JsonIgnore
    @ApiModelProperty(value = "1未激活")
    private String isDelete;

    @JsonIgnore
    @ApiModelProperty(value = "0暂停服务")
    private String fromType;

    private Double longitude;

    private Double latitude;
}
