package com.ibt.niramaya.modal.driver.driver_list_modal;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverMainModal implements Parcelable {

    @SerializedName("driver_data")
    @Expose
    private DriverData driverData;
    public final static Creator<DriverMainModal> CREATOR = new Creator<DriverMainModal>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DriverMainModal createFromParcel(Parcel in) {
            return new DriverMainModal(in);
        }

        public DriverMainModal[] newArray(int size) {
            return (new DriverMainModal[size]);
        }

    };

    protected DriverMainModal(Parcel in) {
        this.driverData = ((DriverData) in.readValue((DriverData.class.getClassLoader())));
    }

    public DriverMainModal() {
    }

    public DriverData getDriverData() {
        return driverData;
    }

    public void setDriverData(DriverData driverData) {
        this.driverData = driverData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(driverData);
    }

    public int describeContents() {
        return 0;
    }

}
