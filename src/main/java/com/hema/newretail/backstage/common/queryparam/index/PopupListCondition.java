package com.hema.newretail.backstage.common.queryparam.index;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Department 新零售
 * @ClassName PopupListCondition
 * @Description 弹窗列表
 * @Author ---CWZ
 * @Date 2019/2/25 15:52
 * @Version 1.0
 **/

@Data
@ApiModel(description = "PopupListCondition",value = "PopupListCondition")
public class PopupListCondition {


    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可为空")
    private Integer pageNum;

    @ApiModelProperty(value = "每页最大数")
    @NotNull(message = "每页最大数不可为空")
    private Integer pageSize;

    @ApiModelProperty(value = "说明")
    private String info;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "创建时间-开始时间")
    private String startDate;

    @ApiModelProperty(value = "创建时间-结束时间")
    private String endDate;

    @ApiModelProperty(value = "创建时间-开始时间")
    @JsonIgnore
    private Date startDates;

    @ApiModelProperty(value = "创建时间-结束时间")
    @JsonIgnore
    private Date endDates;

}
