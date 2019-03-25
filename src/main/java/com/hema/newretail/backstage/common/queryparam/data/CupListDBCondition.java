package com.hema.newretail.backstage.common.queryparam.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Department 新零售
 * @ClassName CupListCondition
 * @Description 数据统计杯数列表参数类
 * @Author ---CWZ
 * @Date 2018/12/17 15:16
 * @Version 1.0
 **/


@Data
@ApiModel(description = "CupListCondition",value = "数据统计杯数列表参数类")
public class CupListDBCondition {

    @ApiModelProperty(value = "开始时间")
    private String dateStart;

    @ApiModelProperty(value = "结束时间")
    private String dateEnd;

    @ApiModelProperty(value = "代理iD")
    private String agentId;

    @ApiModelProperty(value = "uuid")
    private String uuid;

    @ApiModelProperty(value = "排序字段")
    private String sort;
}
