package com.ibt.niramaya.modal.finance;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientFinanceListModel implements Parcelable
{

    @SerializedName("result")
    @Expose
    private Boolean result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("patientfinance")
    @Expose
    private List<PatientFinance> patientFinance = null;
    public final static Parcelable.Creator<PatientFinanceListModel> CREATOR = new Creator<PatientFinanceListModel>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PatientFinanceListModel createFromParcel(Parcel in) {
            return new PatientFinanceListModel(in);
        }

        public PatientFinanceListModel[] newArray(int size) {
            return (new PatientFinanceListModel[size]);
        }

    }
            ;

    protected PatientFinanceListModel(Parcel in) {
        this.result = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.patientFinance, (PatientFinance.class.getClassLoader()));
    }

    public PatientFinanceListModel() {
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<PatientFinance> getPatientFinance() {
        return patientFinance;
    }

    public void setPatientFinance(List<PatientFinance> patientFinance) {
        this.patientFinance = patientFinance;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(result);
        dest.writeValue(message);
        dest.writeList(patientFinance);
    }

    public int describeContents() {
        return 0;
    }

}