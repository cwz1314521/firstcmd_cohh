package com.hema.newretail.backstage.common.utils;

public class LocationBean {
    public static final double MINLAT = -90;
    public static final double MAXLAT = 90;
    public static final double MINLNG = -180;
    public static final double MAXLNG = 180;
    private double lat;//纬度[-90,90]
    private double lng;//经度[-180,180]

    public LocationBean(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}