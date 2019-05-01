package com.ibt.niramaya.modal.prescription;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpdList implements Parcelable {

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
    @SerializedName("opd_row")
    @Expose
    private String opdRow;
    @SerializedName("hospital_id")
    @Expose
    private String hospitalId;
    @SerializedName("hospital_name")
    @Expose
    private String hospitalName;
    @SerializedName("hospital_location")
    @Expose
    private String hospitalLocation;
    @SerializedName("hospital_lang")
    @Expose
    private String hospitalLang;
    @SerializedName("hospital_lati")
    @Expose
    private String hospitalLati;
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
    @SerializedName("hospital_banner")
    @Expose
    private String hospitalBanner;
    @SerializedName("hospial_logo")
    @Expose
    private String hospialLogo;
    @SerializedName("hospital_created_date")
    @Expose
    private String hospitalCreatedDate;
    @SerializedName("hospital_updated_date")
    @Expose
    private String hospitalUpdatedDate;
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

    };

    protected OpdList(Parcel in) {
        this.opdId = ((String) in.readValue((String.class.getClassLoader())));
        this.opdTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.opdDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorId = ((String) in.readValue((String.class.getClassLoader())));
        this.doctorName = ((String) in.readValue((String.class.getClassLoader())));
        this.opdRow = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalId = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalName = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalLocation = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalLang = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalLati = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalHouseNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalStreetName = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalCity = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalState = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalCountry = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalZipcode = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalContact = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalBanner = ((String) in.readValue((String.class.getClassLoader())));
        this.hospialLogo = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalUpdatedDate = ((String) in.readValue((String.class.getClassLoader())));
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

    public String getOpdRow() {
        return opdRow;
    }

    public void setOpdRow(String opdRow) {
        this.opdRow = opdRow;
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

    public String getHospitalLocation() {
        return hospitalLocation;
    }

    public void setHospitalLocation(String hospitalLocation) {
        this.hospitalLocation = hospitalLocation;
    }

    public String getHospitalLang() {
        return hospitalLang;
    }

    public void setHospitalLang(String hospitalLang) {
        this.hospitalLang = hospitalLang;
    }

    public String getHospitalLati() {
        return hospitalLati;
    }

    public void setHospitalLati(String hospitalLati) {
        this.hospitalLati = hospitalLati;
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

    public String getHospitalBanner() {
        return hospitalBanner;
    }

    public void setHospitalBanner(String hospitalBanner) {
        this.hospitalBanner = hospitalBanner;
    }

    public String getHospialLogo() {
        return hospialLogo;
    }

    public void setHospialLogo(String hospialLogo) {
        this.hospialLogo = hospialLogo;
    }

    public String getHospitalCreatedDate() {
        return hospitalCreatedDate;
    }

    public void setHospitalCreatedDate(String hospitalCreatedDate) {
        this.hospitalCreatedDate = hospitalCreatedDate;
    }

    public String getHospitalUpdatedDate() {
        return hospitalUpdatedDate;
    }

    public void setHospitalUpdatedDate(String hospitalUpdatedDate) {
        this.hospitalUpdatedDate = hospitalUpdatedDate;
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
        dest.writeValue(opdRow);
        dest.writeValue(hospitalId);
        dest.writeValue(hospitalName);
        dest.writeValue(hospitalLocation);
        dest.writeValue(hospitalLang);
        dest.writeValue(hospitalLati);
        dest.writeValue(hospitalHouseNumber);
        dest.writeValue(hospitalStreetName);
        dest.writeValue(hospitalCity);
        dest.writeValue(hospitalState);
        dest.writeValue(hospitalCountry);
        dest.writeValue(hospitalZipcode);
        dest.writeValue(hospitalContact);
        dest.writeValue(hospitalBanner);
        dest.writeValue(hospialLogo);
        dest.writeValue(hospitalCreatedDate);
        dest.writeValue(hospitalUpdatedDate);
        dest.writeValue(opdPreceptionStatus);
    }

    public int describeContents() {
        return 0;
    }

}