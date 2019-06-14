package com.ibt.niramaya.modal.driver.driver_list_modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverList implements Parcelable {

    @SerializedName("driverId")
    @Expose
    private String driverId;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    public final static Creator<DriverList> CREATOR = new Creator<DriverList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DriverList createFromParcel(Parcel in) {
            return new DriverList(in);
        }

        public DriverList[] newArray(int size) {
            return (new DriverList[size]);
        }

    };

    protected DriverList(Parcel in) {
        this.driverId = ((String) in.readValue((String.class.getClassLoader())));
        this.latitude = ((Double) in.readValue((Double.class.getClassLoader())));
        this.longitude = ((Double) in.readValue((Double.class.getClassLoader())));
    }

    public DriverList() {
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(driverId);
        dest.writeValue(latitude);
        dest.writeValue(longitude);
    }

    public int describeContents() {
        return 0;
    }

}
