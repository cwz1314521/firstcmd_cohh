package com.hema.newretail.backstage.common.queryparam.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Department 新零售
 * @ClassName BusiComplaintListCondition
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/3/23 11:12
 * @Version 1.0
 **/


@Data
@ApiModel(description = "BusiComplaintListCondition",value = "BusiComplaintListCondition")
public class BusiComplaintListCondition {

    private Long id;

    private String remark;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "反馈类型")
    private String complaintType;

    @ApiModelProperty(value = "反馈内容")
    private String description;

    @ApiModelProperty(value = "start时间")
    private String startGmt;
    @JsonIgnore
    private Date startGmts;

    @ApiModelProperty(value = "end时间")
    private String endGmt;
    @JsonIgnore
    private Date endGmts;

    @ApiModelProperty(value = "来源终端")
    private Integer source;

    @ApiModelProperty(value = "状态")
    private Integer handleStatus;

    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可为空")
    private Integer pageNum;

    @ApiModelProperty(value = "每页最大数")
    @NotNull(message = "每页最大数不可为空")
    private Integer pageSize;
}
