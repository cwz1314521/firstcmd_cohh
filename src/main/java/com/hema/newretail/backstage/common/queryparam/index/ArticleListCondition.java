package com.hema.newretail.backstage.common.queryparam.index;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Department 新零售
 * @ClassName ArticleListCondition
 * @Description 文章列表
 * @Author ---CWZ
 * @Date 2019/2/25 15:52
 * @Version 1.0
 **/

@Data
@ApiModel(description = "ArticleListCondition",value = "ArticleListCondition")
public class ArticleListCondition {


    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可为空")
    private Integer pageNum;

    @ApiModelProperty(value = "每页最大数")
    @NotNull(message = "每页最大数不可为空")
    private Integer pageSize;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "类型")
    private Long type;

    @ApiModelProperty(value = "状态")
    private Integer status;

}
