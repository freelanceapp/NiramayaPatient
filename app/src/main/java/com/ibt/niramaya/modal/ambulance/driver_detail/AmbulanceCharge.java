package com.ibt.niramaya.modal.ambulance.driver_detail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AmbulanceCharge implements Parcelable {

    @SerializedName("ambulance_charges_id")
    @Expose
    private String ambulanceChargesId;
    @SerializedName("charges_title")
    @Expose
    private String chargesTitle;
    @SerializedName("charges_description")
    @Expose
    private String chargesDescription;
    @SerializedName("charges_service_type")
    @Expose
    private String chargesServiceType;
    @SerializedName("charges_service")
    @Expose
    private String chargesService;
    @SerializedName("charges_rate")
    @Expose
    private String chargesRate;
    @SerializedName("charges_created_date")
    @Expose
    private String chargesCreatedDate;
    public final static Parcelable.Creator<AmbulanceCharge> CREATOR = new Creator<AmbulanceCharge>() {


        @SuppressWarnings({
                "unchecked"
        })
        public AmbulanceCharge createFromParcel(Parcel in) {
            return new AmbulanceCharge(in);
        }

        public AmbulanceCharge[] newArray(int size) {
            return (new AmbulanceCharge[size]);
        }

    };

    protected AmbulanceCharge(Parcel in) {
        this.ambulanceChargesId = ((String) in.readValue((String.class.getClassLoader())));
        this.chargesTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.chargesDescription = ((String) in.readValue((String.class.getClassLoader())));
        this.chargesServiceType = ((String) in.readValue((String.class.getClassLoader())));
        this.chargesService = ((String) in.readValue((String.class.getClassLoader())));
        this.chargesRate = ((String) in.readValue((String.class.getClassLoader())));
        this.chargesCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public AmbulanceCharge() {
    }

    public String getAmbulanceChargesId() {
        return ambulanceChargesId;
    }

    public void setAmbulanceChargesId(String ambulanceChargesId) {
        this.ambulanceChargesId = ambulanceChargesId;
    }

    public String getChargesTitle() {
        return chargesTitle;
    }

    public void setChargesTitle(String chargesTitle) {
        this.chargesTitle = chargesTitle;
    }

    public String getChargesDescription() {
        return chargesDescription;
    }

    public void setChargesDescription(String chargesDescription) {
        this.chargesDescription = chargesDescription;
    }

    public String getChargesServiceType() {
        return chargesServiceType;
    }

    public void setChargesServiceType(String chargesServiceType) {
        this.chargesServiceType = chargesServiceType;
    }

    public String getChargesService() {
        return chargesService;
    }

    public void setChargesService(String chargesService) {
        this.chargesService = chargesService;
    }

    public String getChargesRate() {
        return chargesRate;
    }

    public void setChargesRate(String chargesRate) {
        this.chargesRate = chargesRate;
    }

    public String getChargesCreatedDate() {
        return chargesCreatedDate;
    }

    public void setChargesCreatedDate(String chargesCreatedDate) {
        this.chargesCreatedDate = chargesCreatedDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(ambulanceChargesId);
        dest.writeValue(chargesTitle);
        dest.writeValue(chargesDescription);
        dest.writeValue(chargesServiceType);
        dest.writeValue(chargesService);
        dest.writeValue(chargesRate);
        dest.writeValue(chargesCreatedDate);
    }

    public int describeContents() {
        return 0;
    }

}