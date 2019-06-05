
package com.ibt.niramaya.modal.doctor_opd_model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorOpdDataModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("doctor_data")
    @Expose
    private DoctorOpdData doctorOpdData;
    public final static Creator<DoctorOpdDataModel> CREATOR = new Creator<DoctorOpdDataModel>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DoctorOpdDataModel createFromParcel(Parcel in) {
            return new DoctorOpdDataModel(in);
        }

        public DoctorOpdDataModel[] newArray(int size) {
            return (new DoctorOpdDataModel[size]);
        }

    }
    ;

    protected DoctorOpdDataModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorOpdData = ((DoctorOpdData) in.readValue((DoctorOpdData.class.getClassLoader())));
    }

    public DoctorOpdDataModel() {
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

    public DoctorOpdData getDoctorOpdData() {
        return doctorOpdData;
    }

    public void setDoctorOpdData(DoctorOpdData doctorOpdData) {
        this.doctorOpdData = doctorOpdData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeValue(doctorOpdData);
    }

    public int describeContents() {
        return  0;
    }

}
