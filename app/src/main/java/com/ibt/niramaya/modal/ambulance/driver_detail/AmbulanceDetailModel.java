package com.ibt.niramaya.modal.ambulance.driver_detail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AmbulanceDetailModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ambulance")
    @Expose
    private Ambulance ambulance;
    public final static Parcelable.Creator<AmbulanceDetailModel> CREATOR = new Creator<AmbulanceDetailModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AmbulanceDetailModel createFromParcel(Parcel in) {
            return new AmbulanceDetailModel(in);
        }

        public AmbulanceDetailModel[] newArray(int size) {
            return (new AmbulanceDetailModel[size]);
        }

    }
            ;

    protected AmbulanceDetailModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.ambulance = ((Ambulance) in.readValue((Ambulance.class.getClassLoader())));
    }

    public AmbulanceDetailModel() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Ambulance getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(Ambulance ambulance) {
        this.ambulance = ambulance;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeValue(ambulance);
    }

    public int describeContents() {
        return 0;
    }

}