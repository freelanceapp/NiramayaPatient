package com.ibt.niramaya.modal.hospital_detail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorSpecialization implements Parcelable {

    @SerializedName("specialization_title")
    @Expose
    private String specializationTitle;
    public final static Parcelable.Creator<DoctorSpecialization> CREATOR = new Creator<DoctorSpecialization>() {


        @SuppressWarnings({
                "unchecked"
        })
        public DoctorSpecialization createFromParcel(Parcel in) {
            return new DoctorSpecialization(in);
        }

        public DoctorSpecialization[] newArray(int size) {
            return (new DoctorSpecialization[size]);
        }

    };

    protected DoctorSpecialization(Parcel in) {
        this.specializationTitle = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DoctorSpecialization() {
    }

    public String getSpecializationTitle() {
        return specializationTitle;
    }

    public void setSpecializationTitle(String specializationTitle) {
        this.specializationTitle = specializationTitle;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(specializationTitle);
    }

    public int describeContents() {
        return 0;
    }

}