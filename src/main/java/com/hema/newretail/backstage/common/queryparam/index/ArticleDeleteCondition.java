package com.hema.newretail.backstage.common.queryparam.index;


import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Department 新零售
 * @ClassName ArticleDeleteCondition
 * @Description 文章列表
 * @Author ---CWZ
 * @Date 2019/2/25 15:52
 * @Version 1.0
 **/

@Data
@ApiModel(description = "ArticleDeleteCondition",value = "ArticleDeleteCondition")
public class ArticleDeleteCondition {

    private Long id;
}
