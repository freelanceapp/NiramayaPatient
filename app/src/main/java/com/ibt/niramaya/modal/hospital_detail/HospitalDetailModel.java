package com.ibt.niramaya.modal.hospital_detail;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalDetailModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("doctor_data")
    @Expose
    private List<DoctorDatum> doctorData = null;
    @SerializedName("hospital_specialization")
    @Expose
    private List<HospitalSpecialization> hospitalSpecialization = null;
    @SerializedName("hospital_facilities")
    @Expose
    private String hospitalFacilities;
    public final static Parcelable.Creator<HospitalDetailModel> CREATOR = new Creator<HospitalDetailModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HospitalDetailModel createFromParcel(Parcel in) {
            return new HospitalDetailModel(in);
        }

        public HospitalDetailModel[] newArray(int size) {
            return (new HospitalDetailModel[size]);
        }

    }
            ;

    protected HospitalDetailModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.doctorData, (com.ibt.niramaya.modal.hospital_detail.DoctorDatum.class.getClassLoader()));
        in.readList(this.hospitalSpecialization, (com.ibt.niramaya.modal.hospital_detail.HospitalSpecialization.class.getClassLoader()));
        this.hospitalFacilities = ((String) in.readValue((String.class.getClassLoader())));
    }

    public HospitalDetailModel() {
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

    public List<DoctorDatum> getDoctorData() {
        return doctorData;
    }

    public void setDoctorData(List<DoctorDatum> doctorData) {
        this.doctorData = doctorData;
    }

    public List<HospitalSpecialization> getHospitalSpecialization() {
        return hospitalSpecialization;
    }

    public void setHospitalSpecialization(List<HospitalSpecialization> hospitalSpecialization) {
        this.hospitalSpecialization = hospitalSpecialization;
    }

    public String getHospitalFacilities() {
        return hospitalFacilities;
    }

    public void setHospitalFacilities(String hospitalFacilities) {
        this.hospitalFacilities = hospitalFacilities;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(doctorData);
        dest.writeList(hospitalSpecialization);
        dest.writeValue(hospitalFacilities);
    }

    public int describeContents() {
        return 0;
    }

}