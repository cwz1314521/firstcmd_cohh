package com.hema.newretail.backstage.common.queryparam.basecpmpany;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Department 新零售
 * @ClassName InstallPersonListCondition
 * @Description 安装人员列表参数类
 * @Author ---CWZ
 * @Date 2018/12/7 15:24
 * @Version 1.0
 **/
@ApiModel(description = "InstallPersonListCondition")
@Data
public class InstallPersonListCondition implements Serializable {

    /**页码  */
    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可以为空")
    private Integer pageNum;

    /**每页最大数  */
    @ApiModelProperty(value = "每页最大数")
    @NotNull(message = "每页最大数不可以为空")
    private Integer pageSize;

    /**名称*/
    @ApiModelProperty(value = "姓名")
    private String username;

    /**账号*/
    @ApiModelProperty(value = "账号")
    private String mobile;
}
