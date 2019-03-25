package com.hema.newretail.backstage.model.common;

import java.io.Serializable;

/**
 * @Department 新零售
 * @ClassName AgentCompanyBo
 * @Description 列表-查询代理
 * @Author ---CWZ
 * @Date 2018/10/31 14:38
 * @Version 1.0
 **/
public class AgentCompanyBo implements Serializable {

    private Long id;

    private String companyName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
