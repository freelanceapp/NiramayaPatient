package com.ibt.niramaya.modal.prescription;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PrescritionListModel implements Parcelable
{

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("opd_list")
    @Expose
    private List<OpdList> opdList = null;
    public final static Parcelable.Creator<PrescritionListModel> CREATOR = new Creator<PrescritionListModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PrescritionListModel createFromParcel(Parcel in) {
            return new PrescritionListModel(in);
        }

        public PrescritionListModel[] newArray(int size) {
            return (new PrescritionListModel[size]);
        }

    }
            ;

    protected PrescritionListModel(Parcel in) {
        this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.opdList, (com.ibt.niramaya.modal.prescription.OpdList.class.getClassLoader()));
    }

    public PrescritionListModel() {
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

    public List<OpdList> getOpdList() {
        return opdList;
    }

    public void setOpdList(List<OpdList> opdList) {
        this.opdList = opdList;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(error);
        dest.writeValue(message);
        dest.writeList(opdList);
    }

    public int describeContents() {
        return 0;
    }

}