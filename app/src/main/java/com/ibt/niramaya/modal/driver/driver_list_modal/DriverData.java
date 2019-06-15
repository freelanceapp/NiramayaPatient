package com.ibt.niramaya.modal.driver.driver_list_modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class DriverData implements Parcelable {

    @SerializedName("driverList")
    @Expose
    private List<DriverList> driverList = new ArrayList<DriverList>();
    public final static Creator<DriverData> CREATOR = new Creator<DriverData>() {

        @SuppressWarnings({
                "unchecked"
        })
        public DriverData createFromParcel(Parcel in) {
            return new DriverData(in);
        }

        public DriverData[] newArray(int size) {
            return (new DriverData[size]);
        }

    };

    protected DriverData(Parcel in) {
        in.readList(this.driverList, (DriverList.class.getClassLoader()));
    }

    public DriverData() {
    }

    public List<DriverList> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<DriverList> driverList) {
        this.driverList = driverList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(driverList);
    }

    public int describeContents() {
        return 0;
    }

}