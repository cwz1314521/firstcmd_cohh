package com.hema.newretail.backstage.model.zonebase;

import java.io.Serializable;

/**
 * hema-newRetail-crm-com.hema.newretail.backstage.model.zonebase
 *
 * @author ZhangHaiSheng
 * @link
 * @date 2018-10-11 11:21
 */
public class ZoneHashcodesBo implements Serializable {
    private String geoHash;

    public String getGeoHash() {
        return geoHash;
    }

    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash;
    }
}
