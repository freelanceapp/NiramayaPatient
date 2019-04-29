package com.ibt.niramaya.modal.patient_modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaitentProfile implements Parcelable {

    @SerializedName("patient_id")
    @Expose
    private String patientId;
    @SerializedName("patient_number")
    @Expose
    private String patientNumber;
    @SerializedName("patient_name")
    @Expose
    private String patientName;
    @SerializedName("patient_bloodgroup")
    @Expose
    private String patientBloodgroup;
    @SerializedName("patient_contact")
    @Expose
    private String patientContact;
    @SerializedName("patient_date_of_birth")
    @Expose
    private String patientDateOfBirth;
    @SerializedName("patient_email")
    @Expose
    private String patientEmail;
    @SerializedName("patient_password")
    @Expose
    private String patientPassword;
    @SerializedName("patient_house_number")
    @Expose
    private String patientHouseNumber;
    @SerializedName("patient_street_name")
    @Expose
    private String patientStreetName;
    @SerializedName("patient_city")
    @Expose
    private String patientCity;
    @SerializedName("patient_state")
    @Expose
    private String patientState;
    @SerializedName("patient_country")
    @Expose
    private String patientCountry;
    @SerializedName("patient_zipcode")
    @Expose
    private String patientZipcode;
    @SerializedName("relationship_status")
    @Expose
    private String relationshipStatus;
    @SerializedName("patient_profile_picture")
    @Expose
    private String patientProfilePicture;
    @SerializedName("patient_gender")
    @Expose
    private String patientGender;
    @SerializedName("patient_gardian_name")
    @Expose
    private String patientGardianName;
    @SerializedName("patient_relationship_with_gardian")
    @Expose
    private String patientRelationshipWithGardian;
    @SerializedName("patient_gardian_contact")
    @Expose
    private String patientGardianContact;
    @SerializedName("patient_gardian_address")
    @Expose
    private String patientGardianAddress;
    @SerializedName("patient_aadhar_number")
    @Expose
    private String patientAadharNumber;
    @SerializedName("patient_verification_status")
    @Expose
    private String patientVerificationStatus;
    @SerializedName("last_verfication_date")
    @Expose
    private String lastVerficationDate;
    @SerializedName("patient_created_date")
    @Expose
    private String patientCreatedDate;
    public final static Parcelable.Creator<PaitentProfile> CREATOR = new Creator<PaitentProfile>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PaitentProfile createFromParcel(Parcel in) {
            return new PaitentProfile(in);
        }

        public PaitentProfile[] newArray(int size) {
            return (new PaitentProfile[size]);
        }

    };

    protected PaitentProfile(Parcel in) {
        this.patientId = ((String) in.readValue((String.class.getClassLoader())));
        this.patientNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.patientName = ((String) in.readValue((String.class.getClassLoader())));
        this.patientBloodgroup = ((String) in.readValue((String.class.getClassLoader())));
        this.patientContact = ((String) in.readValue((String.class.getClassLoader())));
        this.patientDateOfBirth = ((String) in.readValue((String.class.getClassLoader())));
        this.patientEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.patientPassword = ((String) in.readValue((String.class.getClassLoader())));
        this.patientHouseNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.patientStreetName = ((String) in.readValue((String.class.getClassLoader())));
        this.patientCity = ((String) in.readValue((String.class.getClassLoader())));
        this.patientState = ((String) in.readValue((String.class.getClassLoader())));
        this.patientCountry = ((String) in.readValue((String.class.getClassLoader())));
        this.patientZipcode = ((String) in.readValue((String.class.getClassLoader())));
        this.relationshipStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.patientProfilePicture = ((String) in.readValue((String.class.getClassLoader())));
        this.patientGender = ((String) in.readValue((String.class.getClassLoader())));
        this.patientGardianName = ((String) in.readValue((String.class.getClassLoader())));
        this.patientRelationshipWithGardian = ((String) in.readValue((String.class.getClassLoader())));
        this.patientGardianContact = ((String) in.readValue((String.class.getClassLoader())));
        this.patientGardianAddress = ((String) in.readValue((String.class.getClassLoader())));
        this.patientAadharNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.patientVerificationStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.lastVerficationDate = ((String) in.readValue((String.class.getClassLoader())));
        this.patientCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PaitentProfile() {
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientBloodgroup() {
        return patientBloodgroup;
    }

    public void setPatientBloodgroup(String patientBloodgroup) {
        this.patientBloodgroup = patientBloodgroup;
    }

    public String getPatientContact() {
        return patientContact;
    }

    public void setPatientContact(String patientContact) {
        this.patientContact = patientContact;
    }

    public String getPatientDateOfBirth() {
        return patientDateOfBirth;
    }

    public void setPatientDateOfBirth(String patientDateOfBirth) {
        this.patientDateOfBirth = patientDateOfBirth;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getPatientPassword() {
        return patientPassword;
    }

    public void setPatientPassword(String patientPassword) {
        this.patientPassword = patientPassword;
    }

    public String getPatientHouseNumber() {
        return patientHouseNumber;
    }

    public void setPatientHouseNumber(String patientHouseNumber) {
        this.patientHouseNumber = patientHouseNumber;
    }

    public String getPatientStreetName() {
        return patientStreetName;
    }

    public void setPatientStreetName(String patientStreetName) {
        this.patientStreetName = patientStreetName;
    }

    public String getPatientCity() {
        return patientCity;
    }

    public void setPatientCity(String patientCity) {
        this.patientCity = patientCity;
    }

    public String getPatientState() {
        return patientState;
    }

    public void setPatientState(String patientState) {
        this.patientState = patientState;
    }

    public String getPatientCountry() {
        return patientCountry;
    }

    public void setPatientCountry(String patientCountry) {
        this.patientCountry = patientCountry;
    }

    public String getPatientZipcode() {
        return patientZipcode;
    }

    public void setPatientZipcode(String patientZipcode) {
        this.patientZipcode = patientZipcode;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    public String getPatientProfilePicture() {
        return patientProfilePicture;
    }

    public void setPatientProfilePicture(String patientProfilePicture) {
        this.patientProfilePicture = patientProfilePicture;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatientGardianName() {
        return patientGardianName;
    }

    public void setPatientGardianName(String patientGardianName) {
        this.patientGardianName = patientGardianName;
    }

    public String getPatientRelationshipWithGardian() {
        return patientRelationshipWithGardian;
    }

    public void setPatientRelationshipWithGardian(String patientRelationshipWithGardian) {
        this.patientRelationshipWithGardian = patientRelationshipWithGardian;
    }

    public String getPatientGardianContact() {
        return patientGardianContact;
    }

    public void setPatientGardianContact(String patientGardianContact) {
        this.patientGardianContact = patientGardianContact;
    }

    public String getPatientGardianAddress() {
        return patientGardianAddress;
    }

    public void setPatientGardianAddress(String patientGardianAddress) {
        this.patientGardianAddress = patientGardianAddress;
    }

    public String getPatientAadharNumber() {
        return patientAadharNumber;
    }

    public void setPatientAadharNumber(String patientAadharNumber) {
        this.patientAadharNumber = patientAadharNumber;
    }

    public String getPatientVerificationStatus() {
        return patientVerificationStatus;
    }

    public void setPatientVerificationStatus(String patientVerificationStatus) {
        this.patientVerificationStatus = patientVerificationStatus;
    }

    public String getLastVerficationDate() {
        return lastVerficationDate;
    }

    public void setLastVerficationDate(String lastVerficationDate) {
        this.lastVerficationDate = lastVerficationDate;
    }

    public String getPatientCreatedDate() {
        return patientCreatedDate;
    }

    public void setPatientCreatedDate(String patientCreatedDate) {
        this.patientCreatedDate = patientCreatedDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(patientId);
        dest.writeValue(patientNumber);
        dest.writeValue(patientName);
        dest.writeValue(patientBloodgroup);
        dest.writeValue(patientContact);
        dest.writeValue(patientDateOfBirth);
        dest.writeValue(patientEmail);
        dest.writeValue(patientPassword);
        dest.writeValue(patientHouseNumber);
        dest.writeValue(patientStreetName);
        dest.writeValue(patientCity);
        dest.writeValue(patientState);
        dest.writeValue(patientCountry);
        dest.writeValue(patientZipcode);
        dest.writeValue(relationshipStatus);
        dest.writeValue(patientProfilePicture);
        dest.writeValue(patientGender);
        dest.writeValue(patientGardianName);
        dest.writeValue(patientRelationshipWithGardian);
        dest.writeValue(patientGardianContact);
        dest.writeValue(patientGardianAddress);
        dest.writeValue(patientAadharNumber);
        dest.writeValue(patientVerificationStatus);
        dest.writeValue(lastVerficationDate);
        dest.writeValue(patientCreatedDate);
    }

    public int describeContents() {
        return 0;
    }

}