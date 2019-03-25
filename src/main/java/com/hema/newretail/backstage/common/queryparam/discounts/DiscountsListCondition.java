package com.hema.newretail.backstage.common.queryparam.discounts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Department 新零售
 * @ClassName DiscountsListCondition
 * @Description list
 * @Author ---CWZ
 * @Date 2019/1/3 18:40
 * @Version 1.0
 **/

@ApiModel(value = "DiscountsListCondition",description = "DiscountsListCondition")
@Data
public class DiscountsListCondition {

    /**页码  默认为1*/
    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可以为空")
    private Integer pageNum;

    /**每页最大数  默认为10*/
    @ApiModelProperty(value = "每页最大数")
    @NotNull(message = "每页最大数不可以为空")
    private Integer pageSize;

    @ApiModelProperty(value = "创建时间-开始时间")
    private String startDate;

    @ApiModelProperty(value = "创建时间-结束时间")
    private String endDate;

    @ApiModelProperty(value = "优惠券名字")
    private String couponName;

    @ApiModelProperty(value = "优惠券类型 0免费券 1代金券 2折扣券")
    private Integer couponType;

    @ApiModelProperty(value = "审核状态 0待审核 1已通过 2驳回")
    private Integer authStatus;

    @JsonIgnore
    private Date startDates;

    @JsonIgnore
    private Date endDates;
	private String getnum;

}
