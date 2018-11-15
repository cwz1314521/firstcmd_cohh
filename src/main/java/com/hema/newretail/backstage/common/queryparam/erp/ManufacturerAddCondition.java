package com.hema.newretail.backstage.common.queryparam.erp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName ManufacturerAddCondition
 * @Description 原料厂商---添加
 * @Author ---CWZ
 * @Date 2018/10/31 9:45
 * @Version 1.0
 **/
@ApiModel(value = "原料厂商---添加",description = "原料厂商---添加")
public class ManufacturerAddCondition {


    @Length(min = 1,max = 20,message = "账号在1到20个字符中间")
    @ApiModelProperty(value = "登录账号（用户名）")
    private String userName;
    @ApiModelProperty(value = "密码")
    @Length(min = 6,max = 16,message = "密码最小长度6位，最长16位，请按规则填写")
    private String password;
    @ApiModelProperty(value = "合同图片url")
    private List<String> contractPics;
    @ApiModelProperty(value = "原料厂商名称")
    @Length(min = 1,max = 20,message = "账号在1到20个字符中间")
    private String companyName;
    @ApiModelProperty(value = "备注")
    private String remark;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getContractPics() {
        return contractPics;
    }

    public void setContractPics(List<String> contractPics) {
        this.contractPics = contractPics;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
