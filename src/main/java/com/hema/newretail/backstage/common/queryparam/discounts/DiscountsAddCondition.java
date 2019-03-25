package com.hema.newretail.backstage.common.queryparam.discounts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName DiscountsAddCondition
 * @Description add
 * @Author ---CWZ
 * @Date 2019/1/3 18:41
 * @Version 1.0
 **/

@ApiModel(value = "DiscountsAddCondition",description = "DiscountsAddCondition")
@Data
public class DiscountsAddCondition {


    @ApiModelProperty(value = "优惠券名称")
    @NotBlank(message = "优惠券名称不可为空")
    @Length(min = 1,max = 24,message = "公司名称在1到20个字符中间")
    private String couponName;

    @ApiModelProperty(value = "副标题")
    @Length(min = 1,max = 24,message = "公司名称在1到20个字符中间")
    private String couponTitle;

    @ApiModelProperty(value = "优惠券描述")
    @Length(max = 200,message = "备注在200字以内")
    private String couponDescription;

    @ApiModelProperty(value = "优惠券图片url地址")
    private String couponPic;

    @ApiModelProperty(value = "优惠券类型 0免费券 1代金券 2折扣券")
    private Integer couponType;

    @ApiModelProperty(value = "有效期")
    @DecimalMax(value = "1000",message = "输入范围有误 1-1000")
    @DecimalMin(value = "1",message = "输入范围有误 1-1000")
    @NotNull(message = "有效期不可为空")
    private Integer period;

    @ApiModelProperty(value = "有效期的单位 0天 1月 2 长期有效")
    private Integer periodUnit;

    @ApiModelProperty(value = "发行数量")
    @DecimalMax(value = "1000000",message = "输入范围有误 1-1000000")
    @DecimalMin(value = "1",message = "输入范围有误 1-1000000")
    @NotNull(message = "发行数量不可为空")
    private Integer num;

    @ApiModelProperty(value = "优惠券面值，单位是分")
    private BigDecimal denomination;

    @ApiModelProperty(value = "面值单位 如“元” “折”")
    private String denominationUnit;

    @ApiModelProperty(value = "饮品ids  字符串  ，拼接")
    private String ids;
}
