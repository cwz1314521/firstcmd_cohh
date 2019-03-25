package com.hema.newretail.backstage.common.queryparam.organization;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Department 新零售
 * @ClassName OrganizationAddCondition
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/2/15 15:41
 * @Version 1.0
 **/


@Data
@ApiModel(description = "OrganizationAddCondition",value = "OrganizationAddCondition")
public class OrganizationAddCondition {

    @ApiModelProperty(value = "组织名称")
    private String orgName;

    @ApiModelProperty(value = "上层组织机构代码")
    private String porgCode;

    @ApiModelProperty(value = "负责人")
    private String leader;

    @ApiModelProperty(value = "联系电话")
    private String contactNumber;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "等级")
    private Integer level;

}
