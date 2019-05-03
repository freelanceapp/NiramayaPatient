package com.ibt.niramaya.modal.hospital;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalDatum implements Parcelable {

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
    @SerializedName("hospital_about_us")
    @Expose
    private String hospitalAboutUs;
    @SerializedName("hospital_banner")
    @Expose
    private String hospitalBanner;
    @SerializedName("hospial_logo")
    @Expose
    private String hospialLogo;
    public final static Parcelable.Creator<HospitalDatum> CREATOR = new Creator<HospitalDatum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HospitalDatum createFromParcel(Parcel in) {
            return new HospitalDatum(in);
        }

        public HospitalDatum[] newArray(int size) {
            return (new HospitalDatum[size]);
        }

    };

    protected HospitalDatum(Parcel in) {
        this.hospitalName = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalHouseNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalStreetName = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalCity = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalState = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalCountry = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalZipcode = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalContact = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalAboutUs = ((String) in.readValue((String.class.getClassLoader())));
        this.hospitalBanner = ((String) in.readValue((String.class.getClassLoader())));
        this.hospialLogo = ((String) in.readValue((String.class.getClassLoader())));
    }

    public HospitalDatum() {
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

    public String getHospitalAboutUs() {
        return hospitalAboutUs;
    }

    public void setHospitalAboutUs(String hospitalAboutUs) {
        this.hospitalAboutUs = hospitalAboutUs;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(hospitalName);
        dest.writeValue(hospitalHouseNumber);
        dest.writeValue(hospitalStreetName);
        dest.writeValue(hospitalCity);
        dest.writeValue(hospitalState);
        dest.writeValue(hospitalCountry);
        dest.writeValue(hospitalZipcode);
        dest.writeValue(hospitalContact);
        dest.writeValue(hospitalAboutUs);
        dest.writeValue(hospitalBanner);
        dest.writeValue(hospialLogo);
    }

    public int describeContents() {
        return 0;
    }

}