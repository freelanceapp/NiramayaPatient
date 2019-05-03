package com.ibt.niramaya.modal.hospital;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalListModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("hospital_data")
    @Expose
    private List<HospitalDatum> hospitalData = null;
    public final static Parcelable.Creator<HospitalListModel> CREATOR = new Creator<HospitalListModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public HospitalListModel createFromParcel(Parcel in) {
            return new HospitalListModel(in);
        }

        public HospitalListModel[] newArray(int size) {
            return (new HospitalListModel[size]);
        }

    }
            ;

    protected HospitalListModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.hospitalData, (com.ibt.niramaya.modal.hospital.HospitalDatum.class.getClassLoader()));
    }

    public HospitalListModel() {
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

    public List<HospitalDatum> getHospitalData() {
        return hospitalData;
    }

    public void setHospitalData(List<HospitalDatum> hospitalData) {
        this.hospitalData = hospitalData;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(hospitalData);
    }

    public int describeContents() {
        return 0;
    }

}