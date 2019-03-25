package com.hema.newretail.backstage.model.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @Department 新零售
 * @ClassName GridCompanyBo
 * @Description 列表-查询网格
 * @Author ---CWZ
 * @Date 2018/10/31 14:38
 * @Version 1.0
 **/
@Data
public class GridCompanyBo implements Serializable {

    private Long id;

    private String machineUuid;

    private String companyName;

}
