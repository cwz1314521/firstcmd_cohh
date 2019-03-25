package com.hema.newretail.backstage.common.queryparam.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Department 新零售
 * @ClassName MachinePicListCondition
 * @Description 大屏图片list参数类
 * @Author ---CWZ
 * @Date 2018/12/24 13:38
 * @Version 1.0
 **/

@Data
@ApiModel(description =  "MachinePicListCondition",value = "大屏图片参数类")
public class MachinePicListCondition {
    @ApiModelProperty(value = "图片状态")
    @NotNull(message = "图片状态不可为空")
    private Integer status;

    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可为空")
    private Integer pageNum;

    @ApiModelProperty(value = "每页最大数")
    @NotNull(message = "每页最大数不可为空")
    private Integer pageSize;
}
