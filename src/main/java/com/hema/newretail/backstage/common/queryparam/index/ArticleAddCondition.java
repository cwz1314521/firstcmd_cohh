package com.hema.newretail.backstage.common.queryparam.index;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Department 新零售
 * @ClassName ArticleAddCondition
 * @Description 文章列表
 * @Author ---CWZ
 * @Date 2019/2/25 15:52
 * @Version 1.0
 **/

@Data
@ApiModel(description = "ArticleAddCondition",value = "ArticleAddCondition")
public class ArticleAddCondition {

    @ApiModelProperty(value = "类型id")
    private Long type;

    @ApiModelProperty(value = "主题")
    private String title;

    @ApiModelProperty(value = "开始时间")
    private String gmtStart;

    @ApiModelProperty(value = "结束时间")
    private String gmtEnd;

    @ApiModelProperty(value = "跳转url")
    private String skipUrl;

    @ApiModelProperty(value = "排序字段")
    private Double sort;

    @ApiModelProperty(value = "图片url")
    private String picUrl;

    @ApiModelProperty(value = "状态   0正常  1  隐藏")
    private Integer status;

    @ApiModelProperty(value = "摘要")
    private String articleAbstract;

    @ApiModelProperty(value = "富文本")
    private String info;
}
