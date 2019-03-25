package com.hema.newretail.backstage.common.queryparam.organization;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Department 新零售
 * @ClassName OrganizationListCondition
 * @Description 组织架构列表
 * @Author ---CWZ
 * @Date 2019/2/15 15:25
 * @Version 1.0
 **/


@Data
@ApiModel(description = "OrganizationListCondition",value = "OrganizationListCondition")
public class OrganizationListCondition {

    @NotNull(message = "页码不可为空")
    private Integer pageNum;

    @NotNull(message = "每页最大数不可为空")
    private Integer pageSize;

}
