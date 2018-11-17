package com.hema.newretail.backstage.common.queryparam.erp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Department 新零售
 * @ClassName InStoreRecordListCondition
 * @Description 分后台---入库记录--列表
 * @Author ---CWZ
 * @Date 2018/11/3 11:58
 * @Version 1.0
 **/
@Data
@ApiModel(value = "分后台---入库记录--列表",description = "分后台---入库记录--列表")
public class InStoreRecordListDBCondition {


    @ApiModelProperty(value = "二维码编码")
    private String qrcodeCode;

    @ApiModelProperty(value = "配料名称")
    private String ingredientName;


    @ApiModelProperty(value = "订单编码")
    private String orderCode;

    @ApiModelProperty(value = "过期时间")
    private Date qualityGuaranteePeriod;


    @ApiModelProperty(value = "是否过期 0不过期 1过期")
    private Integer isPeriod;


    @ApiModelProperty(value = "目前状态 0待入库 1已入库")
    private Integer status;

    @ApiModelProperty(value = "入库时间-开始时间")
    private Date startDate;

    @ApiModelProperty(value = "入库时间-结束时间")
    private Date endDate;

    @ApiModelProperty(value = "分公司入库人")
    private String  companyInstoreName;


}
