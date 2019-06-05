package com.ibt.niramaya.modal.prescription;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpdList implements Parcelable
{

    @SerializedName("opd_id")
    @Expose
    private String opdId;
    @SerializedName("opd_title")
    @Expose
    private String opdTitle;
    @SerializedName("opd_description")
    @Expose
    private String opdDescription;
    @SerializedName("doctor_id")
    @Expose
    private String doctorId;
    @SerializedName("doctor_name")
    @Expose
    private String doctorName;
    @SerializedName("opd_created_date")
    @Expose
    private String opdCreatedDate;
    @SerializedName("hospital_id")
    @Expose
    private String hospitalId;
    @SerializedName("hospital_name")
    @Expose
    private String hospitalName;
    @SerializedName("hospital_image")
    @Expose
    private String hospitalImage;
    @SerializedName("opd_preception_status")
    @Expose
    private Boolean opdPreceptionStatus;
    public final static Parcelable.Creator<OpdList> CREATOR = new Creator<OpdList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OpdList createFromParcel(Parcel in) {
            return new OpdList(in);
        }

        public OpdList[] newArray(int size) {
            return (new OpdList[size]);
        }

    }
            ;

    protected OpdList(Parcel in) {
        this.opdId = ((String) in.readValue((String.class.getClassLoader())));
        this.opdTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.opdDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorId = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorName = ((String) in.readValue((String.class.getClassLoader())));
        this.opdCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalId = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalName = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalImage = ((String) in.readValue((String.class.getClassLoader())));
        this.opdPreceptionStatus = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
    }

    public OpdList() {
    }

    public String getOpdId() {
        return opdId;
    }

    public void setOpdId(String opdId) {
        this.opdId = opdId;
    }

    public String getOpdTitle() {
        return opdTitle;
    }

    public void setOpdTitle(String opdTitle) {
        this.opdTitle = opdTitle;
    }

    public String getOpdDescription() {
        return opdDescription;
    }

    public void setOpdDescription(String opdDescription) {
        this.opdDescription = opdDescription;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getOpdCreatedDate() {
        return opdCreatedDate;
    }

    public void setOpdCreatedDate(String opdCreatedDate) {
        this.opdCreatedDate = opdCreatedDate;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalImage() {
        return hospitalImage;
    }

    public void setHospitalImage(String hospitalImage) {
        this.hospitalImage = hospitalImage;
    }

    public Boolean getOpdPreceptionStatus() {
        return opdPreceptionStatus;
    }

    public void setOpdPreceptionStatus(Boolean opdPreceptionStatus) {
        this.opdPreceptionStatus = opdPreceptionStatus;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(opdId);
        dest.writeValue(opdTitle);
        dest.writeValue(opdDescription);
        dest.writeValue(doctorId);
        dest.writeValue(doctorName);
        dest.writeValue(opdCreatedDate);
        dest.writeValue(hospitalId);
        dest.writeValue(hospitalName);
        dest.writeValue(hospitalImage);
        dest.writeValue(opdPreceptionStatus);
    }

    public int describeContents() {
        return 0;
    }

}