package com.hema.newretail.backstage.common.queryparam.financial;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Department 新零售
 * @ClassName machineCashCondition
 * @Description 设备押金管理 list 参数
 * @Author ---CWZ
 * @Date 2019/1/7 17:22
 * @Version 1.0
 **/



@Data
@ApiModel(description = "machineCashCondition",value = "machineCashCondition")
public class machineCashCondition {

    @ApiModelProperty(value = "机器ID  --  预查询")
    private Long machineId;


    @ApiModelProperty(value = "代理ID  -- 预查询")
    private Long agentId;

    
    @ApiModelProperty(value = "序列号 -- 模糊查询")
    private String machineSequence;




}
