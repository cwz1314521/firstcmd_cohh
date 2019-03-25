package com.hema.newretail.backstage.common.queryparam.grid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
@ApiModel(description = "GridAddCondition")
@Data
public class GridAddCondition {


    @Length(min = 6,max = 16,message = "密码最小长度6位，最长16位，请按规则填写")
    /**密码*/
    @ApiModelProperty(value = "密码")
    @Pattern(regexp = "^[^\\u4e00-\\u9fa5]+$", message = "密码由数字字母符号构成")
    private String pwd;

    /**子公司归属*/
    @ApiModelProperty(value = "子公司归属")
    private Long companyId;

    /**公司名称*/
    @ApiModelProperty(value = "公司名称")
    @NotBlank(message = "公司名称不可为空")
    @Pattern(regexp = "[^\\w\\s]+",message = "公司名称只能是汉字和符号")
    @Length(min = 1,max = 20,message = "公司名称在1到20个字符中间")
    private String companyName;


    /**账号*/
    @ApiModelProperty(value = "账号")
    @Length(min = 1,max = 20,message = "公司名称在1到20个字符中间")
    @NotBlank(message = "账号不可为空")
    @Pattern(regexp = "^[^\\u4e00-\\u9fa5]+$", message = "密码由数字字母符号构成")
    private String userName;

    /**联系方式*/
    /**联系方式*/
    @ApiModelProperty(value = "联系方式")
    @NotBlank(message = "联系方式不可为空")
    @Length(max=11,message="手机号长度不能大于11位")
    @Pattern(regexp = "^(1[1-9][0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$", message = "手机号格式错误")
    private String contactWay;

    /**联系人*/
    @ApiModelProperty(value = "联系人")
    @Length(min = 1,max = 10,message = "联系人在1到10个字中间")
    @NotBlank(message = "联系人不可为空")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]+$",message = "联系人只能是汉字")
    private String contact;

    /**省*/
    @ApiModelProperty(value = "省")
    private String province;

    /**城市*/
    @ApiModelProperty(value = "城市")
    private String city;

    /**区*/
    @ApiModelProperty(value = "区")
    private String area;

    /**具体地址*/
    @ApiModelProperty(value = "详细地址")
    @NotBlank(message = "详细地址不可为空")
    @Length(min = 1,max = 30,message = "详细地址在1到30个字符中间")
    private String addr;

    /**状态*/
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**备注*/
    @ApiModelProperty(value = "备注")
    @NotBlank(message = "备注不可为空")
    @Length(max = 150,message = "备注在150字以内")
    private String remark;

    /**开户行*/
    @ApiModelProperty(value = "开户行")
    @NotBlank(message = "开户行不可为空")
    @Length(min = 1,max = 30,message = "开户行在1到30个字符中间")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5]+$",message = "开户行只能为汉字")
    private String bank;

    /**账户*/
    @ApiModelProperty(value = "收款账号")
    @NotBlank(message = "收款账号不可为空")
    @Length(min = 1,max = 25,message = "收款账号在1到25位")
    @Pattern(regexp = "^[1-9]\\d*$",message = "收款账号只能为数字")
    private String accountNumber;

    /**账户名*/
    @ApiModelProperty(value = "账户名")
    @NotBlank(message = "账户名不可为空")
    @Pattern(regexp = "[^\\w\\s]+",message = "收款人只能是汉字和符号")
    @Length(min = 1,max = 20,message = "收款人在1到20个字符中间")
    private String accountName;


    /**每分对应金额*/
    @ApiModelProperty(value = "每分对应金额")
    @DecimalMax(value = "99999",message = "输入范围有误 0-99999")
    @DecimalMin(value = "0",message = "输入范围有误 0-99999")
    @NotNull(message = "每分对应金额不可为空")
    private BigDecimal rewardAmount;

    /**补货任务时间*/
//    @Pattern(regexp = "^[+]{0,1}(\\d+)$",message = "只能是正整数")
    @Max(value = 99999,message = "输入范围有误 0-99999")
    @Min(value = 0,message = "输入范围有误 0-99999")
    @NotNull(message = "补货任务时间不可为空")
    @ApiModelProperty(value = "补货任务时间")
    private Integer replenishmentTime;

    /**保洁任务时间*/
//    @Pattern(regexp = "^[+]{0,1}(\\d+)$",message = "只能是正整数")
    @Max(value = 99999,message = "输入范围有误 0-99999")
    @Min(value = 0,message = "输入范围有误 0-99999")
    @NotNull(message = "保洁任务时间不可为空")
    @ApiModelProperty(value = "保洁任务时间")
    private Integer cleanupReward;

    /**换件任务时间*/
//    @Pattern(regexp = "^[+]{0,1}(\\d+)$",message = "只能是正整数")
    @Max(value = 99999,message = "输入范围有误 0-99999")
    @Min(value = 0,message = "输入范围有误 0-99999")
    @NotNull(message = "换件任务时间不可为空")
    @ApiModelProperty(value = "换件任务时间")
    private Integer replaceReward;

    /**维修任务时间*/
//    @Pattern(regexp = "^[+]{0,1}(\\d+)$",message = "只能是正整数")
    @Max(value = 99999,message = "输入范围有误 0-99999")
    @Min(value = 0,message = "输入范围有误 0-99999")
    @NotNull(message = "维修任务时间不可为空")
    @ApiModelProperty(value = "维修任务时间")
    private Integer maintenanceReward;

    /**巡检任务时间*/
//    @Pattern(regexp = "^[+]{0,1}(\\d+)$",message = "只能是正整数")
    @Max(value = 99999,message = "输入范围有误 0-99999")
    @Min(value = 0,message = "输入范围有误 0-99999")
    @NotNull(message = "巡检任务时间不可为空")
    @ApiModelProperty(value = "巡检任务时间")
    private Integer inspectionReward;


}