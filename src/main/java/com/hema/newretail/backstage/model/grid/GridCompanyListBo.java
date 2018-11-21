package com.hema.newretail.backstage.model.grid;

import java.io.Serializable;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.model.grid
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-10-17 11:20
 */
public class GridCompanyListBo implements Serializable {
    private Long id;
    private String companyName;
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
