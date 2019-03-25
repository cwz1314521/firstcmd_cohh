package com.hema.newretail.backstage.entry;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Department 新零售
 * @Author ---CWZ
 * @Date 2018/12/12 20:57
 * @Version 1.0
 **/
@ApiModel(description = "BaseCompanyData")
@Data
public class BaseCompanyData implements Serializable {
    private Long id;

    @NotEmpty(message = "公司名称不可为空")
    @NotNull
    @Size(min = 1, max = 20)
    @ApiModelProperty("公司名称")
    private String companyName;

    @NotEmpty(message = "手机号不可为空")
    @NotNull
    @Size(min = 1, max = 11)
    @ApiModelProperty("手机号")
    private String contactWay;

    @NotEmpty(message = "联系人姓名不可为空")
    @NotNull
    @Size(min = 1, max = 10)
    @ApiModelProperty("联系人姓名")
    private String contact;

    private String province;

    private String city;

    private String area;

    @NotEmpty(message = "详细地址不可为空")
    @NotNull
    @Size(min = 1, max = 30)
    @ApiModelProperty("详细地址")
    private String addr;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer status;

    @Size(min = 1, max = 300)
    @ApiModelProperty("备注")
    private String remark;

    private List<String> geoHashCodes;

    @ApiModelProperty("省+市+区")
    private String address;

    @ApiModelProperty("组织机构编码")
    private String orgCode;

    @ApiModelProperty("组织机构名称")
    private String orgName;


}