package com.hema.newretail.backstage.entry;

import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * 订单配料实体类
 */

public class OrderPropertyData implements Serializable {
    private String propertiesId;
    private String proName;

    public String getPropertiesId() {
        return propertiesId;
    }

    public void setPropertiesId(String propertiesId) {
        this.propertiesId = propertiesId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }
}
