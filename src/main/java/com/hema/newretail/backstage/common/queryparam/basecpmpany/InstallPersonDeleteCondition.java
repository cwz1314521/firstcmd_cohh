package com.hema.newretail.backstage.common.queryparam.basecpmpany;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @Department 新零售
 * @ClassName InstallPersonDeleteCondition
 * @Description 安装人员删除参数类
 * @Author ---CWZ
 * @Date 2018/12/7 15:24
 * @Version 1.0
 **/
@ApiModel(description = "InstallPersonDeleteCondition")
@Data
public class InstallPersonDeleteCondition {

    @NotNull(message = "id 不可以为空")
    private Long id;
}
