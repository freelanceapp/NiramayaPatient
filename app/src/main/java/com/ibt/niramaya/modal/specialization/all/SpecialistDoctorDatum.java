package com.ibt.niramaya.modal.specialization.all;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpecialistDoctorDatum implements Parcelable {

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
    @SerializedName("hospital_id")
    @Expose
    private String hospitalId;
    @SerializedName("hospital_name")
    @Expose
    private String hospitalName;
    @SerializedName("hospital_house_number")
    @Expose
    private String hospitalHouseNumber;
    @SerializedName("hospital_street_name")
    @Expose
    private String hospitalStreetName;
    @SerializedName("hospital_city")
    @Expose
    private String hospitalCity;
    @SerializedName("hospital_state")
    @Expose
    private String hospitalState;
    @SerializedName("hospital_country")
    @Expose
    private String hospitalCountry;
    @SerializedName("hospital_zipcode")
    @Expose
    private String hospitalZipcode;
    @SerializedName("hospital_contact")
    @Expose
    private String hospitalContact;
    @SerializedName("rating")
    @Expose
    private String rating;
    public final static Parcelable.Creator<SpecialistDoctorDatum> CREATOR = new Creator<SpecialistDoctorDatum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SpecialistDoctorDatum createFromParcel(Parcel in) {
            return new SpecialistDoctorDatum(in);
        }

        public SpecialistDoctorDatum[] newArray(int size) {
            return (new SpecialistDoctorDatum[size]);
        }

    };

    protected SpecialistDoctorDatum(Parcel in) {
        this.doctorId = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.dateOfBirth = ((String) in.readValue((String.class.getClassLoader())));
        this.profileImage = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalId = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalName = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalHouseNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalStreetName = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalCity = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalState = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalCountry = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalZipcode = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalContact = ((String) in.readValue((String.class.getClassLoader())));
        this.rating = ((String) in.readValue((String.class.getClassLoader())));
    }

    public SpecialistDoctorDatum() {
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

    public String getHospitalHouseNumber() {
        return hospitalHouseNumber;
    }

    public void setHospitalHouseNumber(String hospitalHouseNumber) {
        this.hospitalHouseNumber = hospitalHouseNumber;
    }

    public String getHospitalStreetName() {
        return hospitalStreetName;
    }

    public void setHospitalStreetName(String hospitalStreetName) {
        this.hospitalStreetName = hospitalStreetName;
    }

    public String getHospitalCity() {
        return hospitalCity;
    }

    public void setHospitalCity(String hospitalCity) {
        this.hospitalCity = hospitalCity;
    }

    public String getHospitalState() {
        return hospitalState;
    }

    public void setHospitalState(String hospitalState) {
        this.hospitalState = hospitalState;
    }

    public String getHospitalCountry() {
        return hospitalCountry;
    }

    public void setHospitalCountry(String hospitalCountry) {
        this.hospitalCountry = hospitalCountry;
    }

    public String getHospitalZipcode() {
        return hospitalZipcode;
    }

    public void setHospitalZipcode(String hospitalZipcode) {
        this.hospitalZipcode = hospitalZipcode;
    }

    public String getHospitalContact() {
        return hospitalContact;
    }

    public void setHospitalContact(String hospitalContact) {
        this.hospitalContact = hospitalContact;
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
        dest.writeValue(hospitalId);
        dest.writeValue(hospitalName);
        dest.writeValue(hospitalHouseNumber);
        dest.writeValue(hospitalStreetName);
        dest.writeValue(hospitalCity);
        dest.writeValue(hospitalState);
        dest.writeValue(hospitalCountry);
        dest.writeValue(hospitalZipcode);
        dest.writeValue(hospitalContact);
        dest.writeValue(rating);
    }

    public int describeContents() {
        return 0;
    }

}