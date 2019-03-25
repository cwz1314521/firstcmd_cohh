package com.hema.newretail.backstage.model.device.outsotre;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName DeviceOutListBo
 * @Description 设备出库列表ExcelBo
 * @Author ---CWZ
 * @Date 2018/12/11 14:13
 * @Version 1.0
 **/
@Data
public class DeviceOutListExcelBo {


    /**主键*/
    private Long id;

    /**出库单号*/
    private String outstockOrderCode;

    /**出库时间*/
    private Date outstockTime;

    /**代理名称*/
    private String agentCompany;

    /**代理联系方式*/
    private String agentContactWay;

    /**接收人*/
    private String receiver;

    /**备注*/
    private String remark;

    /**详情*/
    private String details;
}
