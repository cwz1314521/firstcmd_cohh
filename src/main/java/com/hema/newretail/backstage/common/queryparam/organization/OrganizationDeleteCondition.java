package com.hema.newretail.backstage.common.queryparam.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Department 新零售
 * @ClassName OrganizationAddCondition
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/2/15 15:41
 * @Version 1.0
 **/


@Data
@ApiModel(description = "OrganizationDeleteCondition",value = "OrganizationDeleteCondition")
public class OrganizationDeleteCondition {

    @ApiModelProperty(value = "id")
    private Long id;

    @NotBlank(message = "组织机构代码不可为空")
    private String orgCode;

}
