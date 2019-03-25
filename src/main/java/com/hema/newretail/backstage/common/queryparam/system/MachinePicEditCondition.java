package com.hema.newretail.backstage.common.queryparam.system;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Department 新零售
 * @ClassName MachinePicEditCondition
 * @Description 大屏图片edit参数类
 * @Author ---CWZ
 * @Date 2018/12/24 13:38
 * @Version 1.0
 **/


@Data
@ApiModel(description =  "MachinePicEditCondition",value = "大屏图片edit参数类")
public class MachinePicEditCondition {

    @ApiModelProperty(value = "图片说明")
    @Length(max = 24,message = "图片说明最多24字")
    @NotBlank(message = "图片说明不可为空")
    private String info;

    @ApiModelProperty(value = "图片路径")
    @NotBlank(message = "图片路径不可为空")
    private String picUrl;

    @ApiModelProperty(value = "状态  1启用   2停用")
    @NotNull(message = "状态不可为空")
    private Integer status;

    @ApiModelProperty(value = "开始时间")
    @NotBlank(message = "开始时间不可为空")
    private String gmtStart;

    @ApiModelProperty(value = "结束时间")
    @NotBlank(message = "结束时间不可为空")
    private String gmtEnd;

    @ApiModelProperty(value = "排序字段")
    @DecimalMax(value = "999.99",message = "排序字段(0.01-999.99)")
    @DecimalMin(value = "0.01",message = "排序字段(0.01-999.99)")
    private BigDecimal sort;

    @ApiModelProperty(value = "ids")
    private String ids;

    @ApiModelProperty(value = "当属性值为0默认全部 当属性为1则为选定的部分")
    @NotBlank(message = "属性值不为空")
    @Length(max = 1,message = "属性值长度为一位")
    private String type;

    private Long id;

}
