package com.hema.newretail.backstage.common.queryparam.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Department 新零售
 * @ClassName CupListCondition
 * @Description 设备饮品销售列表参数类
 * @Author ---yingyan
 * @Date 2018/12/17 15:16
 * @Version 1.0
 **/


@Data
@ApiModel(description = "DrinkSalesListCondition",value = "设备饮品销售列表参数类")
public class DrinkSalesListCondition {

    @ApiModelProperty(value = "开始时间")
    private String dateStart;

    @ApiModelProperty(value = "结束时间")
    private String dateEnd;

    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可为空")
    private Integer pageNum;

    @ApiModelProperty(value = "每页最大数")
    @NotNull(message = "每页最大数不可为空")
    private Integer pageSize;
}
