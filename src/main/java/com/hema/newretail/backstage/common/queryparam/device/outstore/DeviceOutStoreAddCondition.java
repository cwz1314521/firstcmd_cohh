package com.hema.newretail.backstage.common.queryparam.device.outstore;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 *@ClassName DeviceOutStoreAddCondition
 *@Description 设备出库单添加参数类
 *@Author CWZ
 *@Date 2018/12/11 16:32
 *@Version 1.0
 **/
@Data
@ApiModel(description = "DeviceOutStoreAddCondition")
public class DeviceOutStoreAddCondition {

    @ApiModelProperty(value = "代理公司ID")
    @NotNull(message = "代理公司不可为空")
    private Long agentCompanyId;

    @ApiModelProperty(value = "接收人")
    @Size(max = 24)
    @NotBlank(message = "请输入 设备领取人姓名")
    private String receiver;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "详情")
    @NotNull(message = "详情不可为空")
    @Valid
    private List<DeviceOutStoreAddDetailCondition> details;

    private Long userId;
}
