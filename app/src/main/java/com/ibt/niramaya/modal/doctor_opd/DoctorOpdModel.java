package com.ibt.niramaya.modal.doctor_opd;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorOpdModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("doctor_data")
    @Expose
    private DoctorData doctorData;
    public final static Parcelable.Creator<DoctorOpdModel> CREATOR = new Creator<DoctorOpdModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DoctorOpdModel createFromParcel(Parcel in) {
            return new DoctorOpdModel(in);
        }

        public DoctorOpdModel[] newArray(int size) {
            return (new DoctorOpdModel[size]);
        }

    }
            ;

    protected DoctorOpdModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorData = ((DoctorData) in.readValue((DoctorData.class.getClassLoader())));
    }

    public DoctorOpdModel() {
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

    public DoctorData getDoctorData() {
        return doctorData;
    }

    public void setDoctorData(DoctorData doctorData) {
        this.doctorData = doctorData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeValue(doctorData);
    }

    public int describeContents() {
        return 0;
    }

}