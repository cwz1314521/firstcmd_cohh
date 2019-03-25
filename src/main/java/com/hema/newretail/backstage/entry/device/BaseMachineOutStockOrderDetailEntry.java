package com.hema.newretail.backstage.entry.device;
/**
 * @Department 新零售
 * @ClassName BaseMachineOutStockOrderDetailEntry
 * @Description 设备管理----出库详情实体类
 * @Author ---CWZ
 * @Date 2018/10/11 13:18
 * @Version 1.0
 **/
import lombok.Data;

import java.util.Date;
@Data
public class BaseMachineOutStockOrderDetailEntry {
    private Long id;

    private Long outstockId;

    private String machineType;

    private String machineSequence;

    private String scanPeople;

    private Date scanTime;

    private String machineUuid;
}