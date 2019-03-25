package com.hema.newretail.backstage.common.queryparam.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Department 新零售
 * @ClassName CupListCondition
 * @Description 设备饮品销售列表参数类
 * @Author ---CWZ
 * @Date 2018/12/17 15:16
 * @Version 1.0
 **/


@Data
@ApiModel(description = "DrinkSalesListDBCondition",value = "设备饮品销售列表参数类")
public class DrinkSalesListDBCondition {

    @ApiModelProperty(value = "开始时间")
    private String dateStart;

    @ApiModelProperty(value = "结束时间")
    private String dateEnd;
}
