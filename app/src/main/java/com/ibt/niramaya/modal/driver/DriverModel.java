package com.ibt.niramaya.modal.driver;

public class DriverModel {
    
    private String driverId;
    private String lat;
    private String lng;
    private String userId;

    public DriverModel() {
    }

    public DriverModel(String driverId, String lat, String lng, String userId) {
        this.driverId = driverId;
        this.lat = lat;
        this.lng = lng;
        this.userId = userId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
