package com.hema.newretail.backstage.model.discounts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Department 新零售
 * @ClassName DiscountsListBo
 * @Description list
 * @Author ---CWZ
 * @Date 2019/1/4 16:24
 * @Version 1.0
 **/

@Data
@ApiModel(value = "DiscountsListBo",description = "DiscountsListBo")
public class DiscountsListBo {

    private  Long id;

    @ApiModelProperty(value = "优惠券名称")
    private String couponName;

    @ApiModelProperty(value = "优惠券类型 0免费券 1代金券 2折扣券")
    private Integer couponType;

    @ApiModelProperty(value = "优惠券面值，单位是分")
    private BigDecimal denomination;

    @ApiModelProperty(value = "面值单位 如“元” “折”")
    private String denominationUnit;

    @ApiModelProperty(value = "有效期")
    private Integer period;

    @ApiModelProperty(value = "有效期的单位 0天 1月")
    private Integer periodUnit;

    @ApiModelProperty(value = "发行数量")
    private Integer num;

    @ApiModelProperty(value = "审核状态")
    private String authStatus;

    @ApiModelProperty(value = "备注")
    private String authDesc;

    @ApiModelProperty(value = "创建时间")
    private String gmtCreate;
}
