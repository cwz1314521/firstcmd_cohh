package com.hema.newretail.backstage.common.queryparam.tag;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.common.queryparam.tag
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2019-03-05 17:26
 */
@NoArgsConstructor
@Getter
@Setter
public class MenuMarkerCondition {
    /**页码  默认为1*/
    @ApiModelProperty(value = "页码")
    @NotNull(message = "页码不可以为空")
    private Integer pageNum;

    /**每页最大数  默认为10*/
    @ApiModelProperty(value = "每页最大数")
    @NotNull(message = "每页最大数不可以为空")
    private Integer pageSize;
}
