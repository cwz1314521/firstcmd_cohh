package com.hema.newretail.backstage.common.queryparam.device.outstore;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName DeviceOutStoreCondition
 * @Description 出库单列表参数类
 * @Author ---CWZ
 * @Date 2018/12/11 14:34
 * @Version 1.0
 **/

@Data
public class DeviceOutStoreDBCondition {

    private String outstockOrderCode;

    private Date outstockTimeStart;

    private Date outstockTimeEnd;

    private Long agentCompany;

    private String machineType;

    private List<Long>  ids;

    private String orgCode;
}
