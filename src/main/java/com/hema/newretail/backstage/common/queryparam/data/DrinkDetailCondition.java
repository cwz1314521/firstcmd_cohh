package com.hema.newretail.backstage.common.queryparam.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Department 新零售
 * @ClassName DrinkDetailCondition
 * @Description 设备饮品销售明细参数类
 * @Author ---CWZ
 * @Date 2018/12/17 15:16
 * @Version 1.0
 **/


@Data
@ApiModel(description = "DrinkDetailCondition",value = "设备饮品销售明细参数类")
public class DrinkDetailCondition {

    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    @ApiModelProperty(value = "每页最大数")
    private Integer pageSize;

    @ApiModelProperty(value = "day")
    private String day;

}
