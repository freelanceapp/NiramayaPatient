package com.ibt.niramaya.modal.ambulance.driver_detail;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ambulance implements Parcelable {

    @SerializedName("ambulance_id")
    @Expose
    private String ambulanceId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("facility")
    @Expose
    private String facility;
    @SerializedName("registration_number")
    @Expose
    private String registrationNumber;
    @SerializedName("rto_number")
    @Expose
    private String rtoNumber;
    @SerializedName("image")
    @Expose
    private String image;
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
    @SerializedName("hospial_logo")
    @Expose
    private String hospialLogo;
    @SerializedName("ambulance_charges")
    @Expose
    private List<AmbulanceCharge> ambulanceCharges = null;
    public final static Parcelable.Creator<Ambulance> CREATOR = new Creator<Ambulance>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Ambulance createFromParcel(Parcel in) {
            return new Ambulance(in);
        }

        public Ambulance[] newArray(int size) {
            return (new Ambulance[size]);
        }

    };

    protected Ambulance(Parcel in) {
        this.ambulanceId = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.facility = ((String) in.readValue((String.class.getClassLoader())));
        this.registrationNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.rtoNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
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
        this.hospialLogo = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.ambulanceCharges, (com.ibt.niramaya.modal.ambulance.driver_detail.AmbulanceCharge.class.getClassLoader()));
    }

    public Ambulance() {
    }

    public String getAmbulanceId() {
        return ambulanceId;
    }

    public void setAmbulanceId(String ambulanceId) {
        this.ambulanceId = ambulanceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRtoNumber() {
        return rtoNumber;
    }

    public void setRtoNumber(String rtoNumber) {
        this.rtoNumber = rtoNumber;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public String getHospialLogo() {
        return hospialLogo;
    }

    public void setHospialLogo(String hospialLogo) {
        this.hospialLogo = hospialLogo;
    }

    public List<AmbulanceCharge> getAmbulanceCharges() {
        return ambulanceCharges;
    }

    public void setAmbulanceCharges(List<AmbulanceCharge> ambulanceCharges) {
        this.ambulanceCharges = ambulanceCharges;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(ambulanceId);
        dest.writeValue(name);
        dest.writeValue(type);
        dest.writeValue(facility);
        dest.writeValue(registrationNumber);
        dest.writeValue(rtoNumber);
        dest.writeValue(image);
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
        dest.writeValue(hospialLogo);
        dest.writeList(ambulanceCharges);
    }

    public int describeContents() {
        return 0;
    }

}