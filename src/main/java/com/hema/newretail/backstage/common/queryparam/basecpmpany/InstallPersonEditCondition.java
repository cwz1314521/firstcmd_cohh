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
 * @ClassName InstallPersonEditCondition
 * @Description 安装人员修改参数类
 * @Author ---CWZ
 * @Date 2018/12/7 15:24
 * @Version 1.0
 **/
@ApiModel(description = "InstallPersonEditCondition")
@Data
public class InstallPersonEditCondition {


    /**名称*/
    @ApiModelProperty(value = "姓名")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]+$",message = "名称只能是汉字")
    @Length(min = 1,max = 10,message = "名称在1到10个字中间")
    @NotBlank(message = "姓名不可为空")
    private String username;

    /**账号*/
    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账号不可为空")
    @Length(max=11,message="账号长度不能大于11位")
    @Pattern(regexp = "^(1[1-9][0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", message = "账号格式错误")
    private String mobile;

    @NotNull(message = "id 不可以为空")
    private Long id;
}
