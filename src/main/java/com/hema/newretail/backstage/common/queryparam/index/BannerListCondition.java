package com.hema.newretail.backstage.common.queryparam.index;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Department 新零售
 * @ClassName BannerListCondition
 * @Description 首页banner参数类
 * @Author ---CWZ
 * @Date 2018/12/24 13:38
 * @Version 1.0
 **/

@Data
@ApiModel(description =  "BannerListCondition",value = "首页banner参数类")
public class BannerListCondition {

    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可为空")
    private Integer pageNum;

    @ApiModelProperty(value = "每页最大数")
    @NotNull(message = "每页最大数不可为空")
    private Integer pageSize;
}
