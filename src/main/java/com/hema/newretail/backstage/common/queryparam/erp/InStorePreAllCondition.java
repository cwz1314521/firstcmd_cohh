package com.hema.newretail.backstage.common.queryparam.erp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
public class InStorePreAllCondition {


        @ApiModelProperty(value = "二维码编码")
        private List<String> qrcodeCode;

    public List<String> getQrcodeCode() {
        return qrcodeCode;
    }

    public void setQrcodeCode(List<String> qrcodeCode) {
        this.qrcodeCode = qrcodeCode;
    }
}
