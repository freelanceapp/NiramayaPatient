
package com.ibt.niramaya.modal.doctor_opd_model;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorOpdData implements Parcelable
{

    @SerializedName("doctor_id")
    @Expose
    private String doctorId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("doctor_specialization")
    @Expose
    private List<DoctorSpecialization> doctorSpecialization = new ArrayList<DoctorSpecialization>();
    @SerializedName("opd_list")
    @Expose
    private List<OpdList> opdList = new ArrayList<OpdList>();
    @SerializedName("rating")
    @Expose
    private String rating;
    public final static Creator<DoctorOpdData> CREATOR = new Creator<DoctorOpdData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DoctorOpdData createFromParcel(Parcel in) {
            return new DoctorOpdData(in);
        }

        public DoctorOpdData[] newArray(int size) {
            return (new DoctorOpdData[size]);
        }

    }
    ;

    protected DoctorOpdData(Parcel in) {
        this.doctorId = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.dateOfBirth = ((String) in.readValue((String.class.getClassLoader())));
        this.profileImage = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.doctorSpecialization, (com.ibt.niramaya.modal.doctor_opd_model.DoctorSpecialization.class.getClassLoader()));
        in.readList(this.opdList, (com.ibt.niramaya.modal.doctor_opd_model.OpdList.class.getClassLoader()));
        this.rating = ((String) in.readValue((String.class.getClassLoader())));
    }

    public DoctorOpdData() {
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<DoctorSpecialization> getDoctorSpecialization() {
        return doctorSpecialization;
    }

    public void setDoctorSpecialization(List<DoctorSpecialization> doctorSpecialization) {
        this.doctorSpecialization = doctorSpecialization;
    }

    public List<OpdList> getOpdList() {
        return opdList;
    }

    public void setOpdList(List<OpdList> opdList) {
        this.opdList = opdList;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(doctorId);
        dest.writeValue(name);
        dest.writeValue(gender);
        dest.writeValue(dateOfBirth);
        dest.writeValue(profileImage);
        dest.writeValue(createdDate);
        dest.writeList(doctorSpecialization);
        dest.writeList(opdList);
        dest.writeValue(rating);
    }

    public int describeContents() {
        return  0;
    }

}
