package com.ibt.niramaya.modal.specialization.hospital;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalSpecialistDoctorModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("doctor_data")
    @Expose
    private List<HospitalSpecialistDoctorDatum> doctorData = null;
    public final static Parcelable.Creator<HospitalSpecialistDoctorModel> CREATOR = new Creator<HospitalSpecialistDoctorModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HospitalSpecialistDoctorModel createFromParcel(Parcel in) {
            return new HospitalSpecialistDoctorModel(in);
        }

        public HospitalSpecialistDoctorModel[] newArray(int size) {
            return (new HospitalSpecialistDoctorModel[size]);
        }

    }
            ;

    protected HospitalSpecialistDoctorModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.doctorData, (HospitalSpecialistDoctorDatum.class.getClassLoader()));
    }

    public HospitalSpecialistDoctorModel() {
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

    public List<HospitalSpecialistDoctorDatum> getDoctorData() {
        return doctorData;
    }

    public void setDoctorData(List<HospitalSpecialistDoctorDatum> doctorData) {
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