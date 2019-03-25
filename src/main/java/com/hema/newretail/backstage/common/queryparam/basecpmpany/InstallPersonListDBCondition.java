package com.hema.newretail.backstage.common.queryparam.basecpmpany;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Department 新零售
 * @ClassName InstallPersonListCondition
 * @Description 安装人员列表DB参数类
 * @Author ---CWZ
 * @Date 2018/12/7 15:24
 * @Version 1.0
 **/
@ApiModel(description = "InstallPersonListCondition")
@Data
public class InstallPersonListDBCondition implements Serializable {


    /**名称*/
    private String username;

    /**账号*/
    private String mobile;
}
