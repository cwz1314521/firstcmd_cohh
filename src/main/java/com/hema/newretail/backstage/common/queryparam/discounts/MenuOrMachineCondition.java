package com.hema.newretail.backstage.common.queryparam.discounts;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName MenuOrMachineCondition
 * @Description TODO
 * @Author ---CWZ
 * @Date 2019/3/6 14:31
 * @Version 1.0
 **/

@Data
@ApiModel(description =  "MenuOrMachineCondition",value = "MenuOrMachineCondition")
public class MenuOrMachineCondition {

    @ApiModelProperty(value = "ids")
    private List<Long> ids;
    @ApiModelProperty(value = "0为饮品1为机器")
    private String type;
    /**页码  默认为1*/
    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可以为空")
    private Integer pageNum;

    /**每页最大数  默认为10*/
    @ApiModelProperty(value = "每页最大数")
    @NotNull(message = "每页最大数不可以为空")
    private Integer pageSize;

    @ApiModelProperty(value = "关键字")
    private String name;
}
