package com.ibt.niramaya.modal.prescription.detail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrescriptionDetailModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("preception")
    @Expose
    private Preception preception;
    public final static Parcelable.Creator<PrescriptionDetailModel> CREATOR = new Creator<PrescriptionDetailModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PrescriptionDetailModel createFromParcel(Parcel in) {
            return new PrescriptionDetailModel(in);
        }

        public PrescriptionDetailModel[] newArray(int size) {
            return (new PrescriptionDetailModel[size]);
        }

    }
            ;

    protected PrescriptionDetailModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.preception = ((Preception) in.readValue((Preception.class.getClassLoader())));
    }

    public PrescriptionDetailModel() {
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

    public Preception getPreception() {
        return preception;
    }

    public void setPreception(Preception preception) {
        this.preception = preception;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeValue(preception);
    }

    public int describeContents() {
        return 0;
    }

}