package com.hema.newretail.backstage.common.queryparam.erp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Department 新零售
 * @ClassName InStoreListCondition
 * @Description 分后台 -列表
 * @Author ---CWZ
 * @Date 2018/11/2 14:16
 * @Version 1.0
 **/
@ApiModel(value = "分后台 -列表参数类",description = "分后台 -列表参数类")
@Data
public class InStoreListCondition {
        @ApiModelProperty(value = "页码")
        private Integer pageNum;
        @ApiModelProperty(value = "每页最大数")
        private Integer pageSize;}

