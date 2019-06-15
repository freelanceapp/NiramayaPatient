package com.ibt.niramaya.modal.ambulance;

public class DriverLocation {
    private String driverId;
    private String driverName;
    private double driverLat;
    private double driverLong;
    private boolean driverStatus;

    public DriverLocation() {

    }

    public DriverLocation(String driverId, String driverName, double driverLat, double driverLong, boolean driverStatus) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.driverLat = driverLat;
        this.driverLong = driverLong;
        this.driverStatus = driverStatus;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public double getDriverLat() {
        return driverLat;
    }

    public void setDriverLat(double driverLat) {
        this.driverLat = driverLat;
    }

    public double getDriverLong() {
        return driverLong;
    }

    public void setDriverLong(double driverLong) {
        this.driverLong = driverLong;
    }

    public boolean isDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(boolean driverStatus) {
        this.driverStatus = driverStatus;
    }
}
