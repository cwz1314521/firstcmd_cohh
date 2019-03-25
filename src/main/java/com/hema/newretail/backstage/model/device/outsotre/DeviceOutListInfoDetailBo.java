package com.hema.newretail.backstage.model.device.outsotre;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Map;

/**
 * @Department 新零售
 * @ClassName DeviceOutListDetailBo
 * @Description 设备出库详情Bo
 * @Author ---CWZ
 * @Date 2018/12/11 14:23
 * @Version 1.0
 **/
@Data
public class DeviceOutListInfoDetailBo {

    /**设备类型*/
    private String machineType;

    /**设备数*/
    private Integer machineNum;

    /**设备序列号*/
    private String machineSequence;

    /**设备uuid*/
    private String machineUuid;

    /**扫描人*/
    private String scanPeople;

    /**扫描时间*/
    private String scanTime;

    /**设备状态*/
    private Integer machineState;


    @ApiModelProperty(value = "1未激活")
    private String isDelete;

    @JsonIgnore
    @ApiModelProperty(value = "0暂停服务")
    private String fromType;

}
