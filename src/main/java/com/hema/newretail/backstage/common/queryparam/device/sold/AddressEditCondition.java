package com.hema.newretail.backstage.common.queryparam.device.sold;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName AddressEditCondition
 * @Description 批量手动关停服务接口
 * @Author ---CWZ
 * @Date 2018/12/12 21:14
 * @Version 1.0
 **/



@ApiModel(description = "AddressEditCondition",value = "AddressEditCondition")
@Data
public class AddressEditCondition {

    private Long bmiId;

    private String province;

    private String city;

    private String area;

    private Double longitude;

    private Double latitude;

    private String machineName;

    private String geoHash;

}
