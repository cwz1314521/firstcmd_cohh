package com.hema.newretail.backstage.common.queryparam.grid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Department 新零售
 * @ClassName GridListTaskCondition
 * @Description 网格任务列表  ---  参数类
 * @Author ---CWZ
 * @Date 2018/9/28 11:11
 * @Version 1.0
 **/
@ApiModel(value = "GridListTaskCondition",description = "网格任务列表  ---  参数类")
@Data
public class GridListTaskCondition {

    /**页码  默认为1*/
    @NotNull(message = "页码不可以为空")
    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    /**每页最大数  默认为10*/
    @ApiModelProperty(value = "每页最大数")
    @NotNull(message = "每页最大数不可以为空")

    private Integer pageSize;
    /**省*/
    @ApiModelProperty(value = "省")
    private String province;

    /**市*/
    @ApiModelProperty(value = "市")
    private String city;

    /**多选区数组*/
    @ApiModelProperty(value = "多选区数组")
    private String [] area;

    /**任务编号*/
    @ApiModelProperty(value = "任务编号")
    private String taskCode;

    /**任务编号*/
    @ApiModelProperty(value = "任务进度")
    private String [] taskStatus;

    /**任务类型*/
    @ApiModelProperty(value = "任务类型")
    private Integer [] taskType;

    /**派发时间*/
    private Date preAssignTime;
    private Date assignTime;

    /**完成时间*/
    private Date preEndTime;
    private Date endTime;

    /**设备编号*/
    @ApiModelProperty(value = "设备编号")
    private String machineUuid;

    /**网格公司*/
    @ApiModelProperty(value = "网格公司")
    private Long wId;

    /**派发时间-接收*/
    @ApiModelProperty(value = "派发时间-接收1")
    private String preAssignTimes;
    @ApiModelProperty(value = "派发时间-接收2")
    private String assignTimes;

    /**完成时间*/
    @ApiModelProperty(value = "完成时间1")
    private String preEndTimes;
    @ApiModelProperty(value = "完成时间2")
    private String endTimes;


}
