package com.hema.newretail.backstage.common.queryparam.device.sold;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Department 新零售
 * @ClassName DeviceSoldCondition
 * @Description 已出售设备列表接口condition
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/


@ApiModel(description = "DeviceSoldCondition")
@Data
public class DeviceSoldCondition {

    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可为空")
    private Integer pageNum;

    @ApiModelProperty(value = "每页记录条数")
    @NotNull(message = "每页记录条数不可为空")
    private Integer pageSize;

    @ApiModelProperty(value = "省")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区")
    private String area;

    @ApiModelProperty(value = "设备序列号")
    @Size(max = 24)
    private String machineSequence;

    @ApiModelProperty(value = "代理商ID")
    private Long agentCompanyId;

    @ApiModelProperty(value = "网格公司ID")
    private Long gridCompanyId;
}
