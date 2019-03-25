package com.hema.newretail.backstage.entry.device;
/**
 * @Department 新零售
 * @ClassName BaseMachineOutStockOrderEntry
 * @Description 设备管理----出库实体类
 * @Author ---CWZ
 * @Date 2018/10/11 13:18
 * @Version 1.0
 **/
import lombok.Data;

import java.util.Date;
@Data
public class BaseMachineOutStockOrderEntry {
    private Long id;

    private String outstockOrderCode;

    private Long agentCompanyId;

    private String receiver;

    private String outstockUserName;

    private Date outstockTime;

    private String remark;

    private Long outstockUserId;
}