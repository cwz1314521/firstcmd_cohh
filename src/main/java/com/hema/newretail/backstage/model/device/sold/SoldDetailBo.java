package com.hema.newretail.backstage.model.device.sold;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Department 新零售
 * @ClassName SoldDetailBo
 * @Description 查看设备详情
 * @Author ---CWZ
 * @Date 2018/12/13 18;37
 * @Version 1.0
 **/


@ApiModel(description = "SoldDetailBo",value = "查看设备详情")
@Data
public class SoldDetailBo {

    @ApiModelProperty(value = "设备ID")
    private Long id;

    @ApiModelProperty(value = "设备状态----取自redis-12")
    private String machineState;

    @ApiModelProperty(value = "状态持续时间----取自redis-12---并处理")
    private String duration;

    @ApiModelProperty(value = "状态描述----取自redis-12")
    private String stateDesc;
    
    @ApiModelProperty(value = "状态类型----暂定为状态")
    private String stateType;

    @ApiModelProperty(value = "网格公司名称")
    private String gridCompany;

    @ApiModelProperty(value = "网格公司联系人")
    private String gridContact;

    @ApiModelProperty(value = "网格公司联系方式")
    private String gridContactWay;

    @ApiModelProperty(value = "代理公司联系人")
    private String agentContact;

    @ApiModelProperty(value = "代理公司联系方式")
    private String agentContactWay;

    @ApiModelProperty(value = "业务员（客户经理）---暂无")
    private String clientManager;

    @ApiModelProperty(value = "业务员联系方式")
    private String clientManagerContactWay;

    @ApiModelProperty(value = "设备型号")
    private String machineType;

    @ApiModelProperty(value = "设备序列号")
    private String machineSequence;

    @ApiModelProperty(value = "设备名称")
    private String machineName;

    @ApiModelProperty(value = "蓝牙设备锁")
    private String bluetoothLock;

    @ApiModelProperty(value = "设备手机号")
    private String machineNumber;

    @ApiModelProperty(value = "设备地址")
    private String machineAddr;

    @ApiModelProperty(value = "出库单号")
    private String outstockOrderCode;

    @ApiModelProperty(value = "出库时间")
    private String outstockTime;

    @ApiModelProperty(value = "安装人")
    private String installer;

    @ApiModelProperty(value = "激活时间")
    private String activityTime;

    @ApiModelProperty(value = "环境图片,图片的完整url地址字符串，多张图片用逗号分隔开")
    private String machinePic;

    @ApiModelProperty(value = "保洁任务周期（天）")
    private String cleanupCycle;

    @ApiModelProperty(value = "换件任务周期（天）")
    private String replaceCycle;

    @ApiModelProperty(value = "巡检任务周期（天）")
    private String inspectionCycle;

    @ApiModelProperty(value = "机器运行服务开关,设备服务开关：0开启 1关闭")
    private String machineIsDeleted;

    @JsonIgnore
    @ApiModelProperty(value = "0暂停服务")
    private String fromType;

    @JsonIgnore
    @ApiModelProperty(value = "uuid")
    private String uuid;
}
