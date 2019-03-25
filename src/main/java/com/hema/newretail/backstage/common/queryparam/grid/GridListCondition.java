package com.hema.newretail.backstage.common.queryparam.grid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Department 新零售
 * @ClassName GridListCondition
 * @Description GridController ---  参数类
 * @Author ---CWZ
 * @Date 2018/9/26 11:09
 * @Version 1.0
 **/
@ApiModel(value = "GridListCondition",description = "网格公司列表  ---  参数类")
@Data
public class GridListCondition {

    /**页码  默认为1*/
    @NotNull(message = "页码不可以为空")
    @ApiModelProperty(value = "页码")
    private Integer pageNum;

    /**每页最大数  默认为10*/
    @NotNull(message = "每页最大数不可以为空")
    @ApiModelProperty(value = "每页最大数")
    private Integer pageSize;

    /**名称或者编号*/
    @ApiModelProperty(value = "名称或者编号")
    private String name;

    /**省*/
    @ApiModelProperty(value = "省")
    private String province;

    /**市*/
    @ApiModelProperty(value = "市")
    private String city;

    /**主键*/
    @ApiModelProperty(value = "主键")
    private Long id;

    /**状态 正常 or 冻结*/
    @ApiModelProperty(value = "状态 正常0 or 冻结1")
    private String status;

    /**多选区*/
    @ApiModelProperty(value = "多选区")
    private String areas;

    /**多选区数组*/
    @ApiModelProperty(value = "多选区数组")
    private String [] area;

    /**组织机构编码*/
    @ApiModelProperty(value = "组织机构编码")
    private String orgCode;
}
