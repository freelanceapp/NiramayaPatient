package com.ibt.niramaya.modal.hospital_detail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalSpecialization implements Parcelable {

    @SerializedName("specialization_id")
    @Expose
    private String specializationId;
    @SerializedName("specialization_description")
    @Expose
    private String specializationDescription;
    @SerializedName("specialization_title")
    @Expose
    private String specializationTitle;
    @SerializedName("specialization_created_date")
    @Expose
    private String specializationCreatedDate;
    @SerializedName("specialization_image")
    @Expose
    private String specializationImage;
    public final static Parcelable.Creator<HospitalSpecialization> CREATOR = new Creator<HospitalSpecialization>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HospitalSpecialization createFromParcel(Parcel in) {
            return new HospitalSpecialization(in);
        }

        public HospitalSpecialization[] newArray(int size) {
            return (new HospitalSpecialization[size]);
        }

    };

    protected HospitalSpecialization(Parcel in) {
        this.specializationId = ((String) in.readValue((String.class.getClassLoader())));
        this.specializationDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.specializationTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.specializationCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
        this.specializationImage = ((String) in.readValue((String.class.getClassLoader())));
    }

    public HospitalSpecialization() {
    }

    public String getSpecializationId() {
        return specializationId;
    }

    public void setSpecializationId(String specializationId) {
        this.specializationId = specializationId;
    }

    public String getSpecializationDescription() {
        return specializationDescription;
    }

    public void setSpecializationDescription(String specializationDescription) {
        this.specializationDescription = specializationDescription;
    }

    public String getSpecializationTitle() {
        return specializationTitle;
    }

    public void setSpecializationTitle(String specializationTitle) {
        this.specializationTitle = specializationTitle;
    }

    public String getSpecializationCreatedDate() {
        return specializationCreatedDate;
    }

    public void setSpecializationCreatedDate(String specializationCreatedDate) {
        this.specializationCreatedDate = specializationCreatedDate;
    }

    public String getSpecializationImage() {
        return specializationImage;
    }

    public void setSpecializationImage(String specializationImage) {
        this.specializationImage = specializationImage;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(specializationId);
        dest.writeValue(specializationDescription);
        dest.writeValue(specializationTitle);
        dest.writeValue(specializationCreatedDate);
        dest.writeValue(specializationImage);
    }

    public int describeContents() {
        return 0;
    }

}