package com.hema.newretail.backstage.common.queryparam.device.outstore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Department 新零售
 * @ClassName DeviceOutStoreCondition
 * @Description 出库单列表参数类
 * @Author ---CWZ
 * @Date 2018/12/11 14:34
 * @Version 1.0
 **/

@Data
@ApiModel(description = "DeviceOutStoreCondition")
public class DeviceOutStoreCondition {


    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可为空")
    private Integer pageNum;

    @NotNull(message = "每页记录条数不可为空")
    @ApiModelProperty(value = "每页记录条数")
    private Integer pageSize;

    @ApiModelProperty(value = "出库单号")
    private String outstockOrderCode;

    @ApiModelProperty(value = "出库日期开始")
    private String outstockTimeStart;

    @ApiModelProperty(value = "出库日期结束")
    private String outstockTimeEnd;

    @ApiModelProperty(value = "代理公司名称")
    private Long agentCompany;

    @ApiModelProperty(value = "设备型号")
    private String machineType;

    @ApiModelProperty(value = "IDS")
    private String ids;

    @ApiModelProperty(value = "组织机构编码")
    private String orgCode;




}
