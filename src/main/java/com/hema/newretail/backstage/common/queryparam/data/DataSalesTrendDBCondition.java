package com.hema.newretail.backstage.common.queryparam.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * @Department 新零售
 * @ClassName DataSalesTrendCondition
 * @Description 数据统计销售趋势图参数类
 * @Author ---CWZ
 * @Date 2018/12/17 15:16
 * @Version 1.0
 **/


@Data
@ApiModel(description = "DataSalesTrendCondition",value = "数据统计销售趋势图参数类")
public class DataSalesTrendDBCondition {

    @ApiModelProperty(value = "设备ID")
    private String machineId;

    @ApiModelProperty(value = "开始时间")
    private String dateStart;

    @ApiModelProperty(value = "结束时间")
    private String dateEnd;
}
