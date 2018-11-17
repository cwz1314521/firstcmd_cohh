package com.hema.newretail.backstage.common.queryparam.mobile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Department 新零售
 * @ClassName IngredientNameByIdCondition
 * @Description 根据原料id查询原料名字 ---  加入redis缓存  参数类
 * @Author ---CWZ
 * @Date 2018/11/16 14:50
 * @Version 1.0
 **/
@ApiModel(value = "根据原料id查询原料名字",description = "根据原料id查询原料名字")
@Data
public class IngredientNameByIdCondition {

    @ApiModelProperty(value = "id")
    private Long id;


}
