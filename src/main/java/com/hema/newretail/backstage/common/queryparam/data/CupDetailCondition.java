package com.hema.newretail.backstage.common.queryparam.data;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

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
@ApiModel(description = "CupListCondition",value = "数据统计杯数详细参数类")
public class CupDetailCondition {

    @ApiModelProperty(value = "时间")
    private String date;


    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可为空")
    private Integer pageNum;

    @ApiModelProperty(value = "每页最大数")
    @NotNull(message = "每页最大数不可为空")
    private Integer pageSize;

    @ApiModelProperty(value = "uuid")
    private String uuid;

}
