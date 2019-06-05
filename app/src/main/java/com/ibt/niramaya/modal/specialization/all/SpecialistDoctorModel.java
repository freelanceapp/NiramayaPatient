package com.ibt.niramaya.modal.specialization.all;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpecialistDoctorModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("doctor_data")
    @Expose
    private List<SpecialistDoctorDatum> doctorData = null;
    public final static Parcelable.Creator<SpecialistDoctorModel> CREATOR = new Creator<SpecialistDoctorModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SpecialistDoctorModel createFromParcel(Parcel in) {
            return new SpecialistDoctorModel(in);
        }

        public SpecialistDoctorModel[] newArray(int size) {
            return (new SpecialistDoctorModel[size]);
        }

    }
            ;

    protected SpecialistDoctorModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.doctorData, (SpecialistDoctorDatum.class.getClassLoader()));
    }

    public SpecialistDoctorModel() {
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

    public List<SpecialistDoctorDatum> getDoctorData() {
        return doctorData;
    }

    public void setDoctorData(List<SpecialistDoctorDatum> doctorData) {
        this.doctorData = doctorData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(doctorData);
    }

    public int describeContents() {
        return 0;
    }

}