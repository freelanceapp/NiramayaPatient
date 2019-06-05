package com.ibt.niramaya.modal.home;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HomeDataModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("appslider")
    @Expose
    private List<Appslider> appslider = null;
    @SerializedName("hospital_popular")
    @Expose
    private List<HospitalDatum> hospitalPopular = null;
    @SerializedName("hospital_data")
    @Expose
    private List<HospitalDatum> hospitalData = null;
    @SerializedName("system_specialization")
    @Expose
    private List<SystemSpecialization> systemSpecialization = null;
    public final static Parcelable.Creator<HomeDataModel> CREATOR = new Creator<HomeDataModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HomeDataModel createFromParcel(Parcel in) {
            return new HomeDataModel(in);
        }

        public HomeDataModel[] newArray(int size) {
            return (new HomeDataModel[size]);
        }

    }
            ;

    protected HomeDataModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.appslider, (com.ibt.niramaya.modal.home.Appslider.class.getClassLoader()));
        in.readList(this.hospitalPopular, (com.ibt.niramaya.modal.home.HospitalPopular.class.getClassLoader()));
        in.readList(this.hospitalData, (com.ibt.niramaya.modal.home.HospitalDatum.class.getClassLoader()));
        in.readList(this.systemSpecialization, (com.ibt.niramaya.modal.home.SystemSpecialization.class.getClassLoader()));
    }

    public HomeDataModel() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Appslider> getAppslider() {
        return appslider;
    }

    public void setAppslider(List<Appslider> appslider) {
        this.appslider = appslider;
    }

    public List<HospitalDatum> getHospitalPopular() {
        return hospitalPopular;
    }

    public void setHospitalPopular(List<HospitalDatum> hospitalPopular) {
        this.hospitalPopular = hospitalPopular;
    }

    public List<HospitalDatum> getHospitalData() {
        return hospitalData;
    }

    public void setHospitalData(List<HospitalDatum> hospitalData) {
        this.hospitalData = hospitalData;
    }

    public List<SystemSpecialization> getSystemSpecialization() {
        return systemSpecialization;
    }

    public void setSystemSpecialization(List<SystemSpecialization> systemSpecialization) {
        this.systemSpecialization = systemSpecialization;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(appslider);
        dest.writeList(hospitalPopular);
        dest.writeList(hospitalData);
        dest.writeList(systemSpecialization);
    }

    public int describeContents() {
        return 0;
    }

}