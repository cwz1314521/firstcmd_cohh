package com.hema.newretail.backstage.model.erp;

import lombok.Data;

import java.io.Serializable;

/**
 * @Department 新零售
 * @ClassName InstoreRecordBo
 * @Description ru入库记录返回值类
 * @Author ---CWZ
 * @Date 2018/11/5 11:58
 * @Version 1.0
 **/
@Data
public class InstoreRecordBo implements Serializable {

    private Long id;
    /**原料名称*/
    private String ingredientName;
    /**二维码编码*/
    private String qrcodeCode;
    /**到期日期*/
    private String qualityGuaranteePeriod;
    /**状态*/
    private String status;
    /**订单编号*/
    private String orderCode;
    /**是否过期*/
    private String isPeriod;
    /**操作人*/
    private String companyInstoreUserName;
    /**入库时间*/
    private String companyInstoreTime;


}
