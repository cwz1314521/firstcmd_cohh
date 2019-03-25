package com.hema.newretail.backstage.model.erp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Department 新零售
 * @ClassName InStoreTodayBo
 * @Description 分后台  当天未入库列表-一级参数类
 * @Author ---CWZ
 * @Date 2018/11/3 10:56
 * @Version 1.0
 **/
@Data
public class InStoreTodayBo implements Serializable {

    private Long id;
    private String orderCode;
    private Integer status;
    private List<InStoreTodaySonBo>  ingredients;


}
