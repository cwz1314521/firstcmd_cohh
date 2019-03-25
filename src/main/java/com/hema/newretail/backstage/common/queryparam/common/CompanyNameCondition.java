package com.hema.newretail.backstage.common.queryparam.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * @Department 新零售
 * @ClassName NameCondition
 * @Description 预查询公共接口controller
 * @Author ---CWZ
 * @Date 2018/12/11 10:03
 * @Version 1.0
 **/

@Data
@ApiModel(description = "NameCondition")
public class CompanyNameCondition {
    @ApiModelProperty(name = "name")
    private String companyName;

    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可为空")
    private Integer pageNum;

    @ApiModelProperty(value = "每页最大数")
    @NotNull(message = "每页最大数不可为空")
    private Integer pageSize;
}
