package com.ibt.niramaya.modal.finance;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientFinance implements Parcelable {

    @SerializedName("patient_finance_id")
    @Expose
    private String patientFinanceId;
    @SerializedName("finance_title")
    @Expose
    private String financeTitle;
    @SerializedName("finance_provider")
    @Expose
    private String financeProvider;
    @SerializedName("finance_policy_document")
    @Expose
    private String financePolicyDocument;
    @SerializedName("finance_policy_number")
    @Expose
    private String financePolicyNumber;
    @SerializedName("finance_policy_status")
    @Expose
    private String financePolicyStatus;
    @SerializedName("finance_policy_valid_time")
    @Expose
    private String financePolicyValidTime;
    @SerializedName("finance_created_date")
    @Expose
    private String financeCreatedDate;
    public final static Parcelable.Creator<PatientFinance> CREATOR = new Creator<PatientFinance>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PatientFinance createFromParcel(Parcel in) {
            return new PatientFinance(in);
        }

        public PatientFinance[] newArray(int size) {
            return (new PatientFinance[size]);
        }

    };

    protected PatientFinance(Parcel in) {
        this.patientFinanceId = ((String) in.readValue((String.class.getClassLoader())));
        this.financeTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.financeProvider = ((String) in.readValue((String.class.getClassLoader())));
        this.financePolicyDocument = ((String) in.readValue((String.class.getClassLoader())));
        this.financePolicyNumber = ((String) in.readValue((String.class.getClassLoader())));
        this.financePolicyStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.financePolicyValidTime = ((String) in.readValue((String.class.getClassLoader())));
        this.financeCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PatientFinance() {
    }

    public String getPatientFinanceId() {
        return patientFinanceId;
    }

    public void setPatientFinanceId(String patientFinanceId) {
        this.patientFinanceId = patientFinanceId;
    }

    public String getFinanceTitle() {
        return financeTitle;
    }

    public void setFinanceTitle(String financeTitle) {
        this.financeTitle = financeTitle;
    }

    public String getFinanceProvider() {
        return financeProvider;
    }

    public void setFinanceProvider(String financeProvider) {
        this.financeProvider = financeProvider;
    }

    public String getFinancePolicyDocument() {
        return financePolicyDocument;
    }

    public void setFinancePolicyDocument(String financePolicyDocument) {
        this.financePolicyDocument = financePolicyDocument;
    }

    public String getFinancePolicyNumber() {
        return financePolicyNumber;
    }

    public void setFinancePolicyNumber(String financePolicyNumber) {
        this.financePolicyNumber = financePolicyNumber;
    }

    public String getFinancePolicyStatus() {
        return financePolicyStatus;
    }

    public void setFinancePolicyStatus(String financePolicyStatus) {
        this.financePolicyStatus = financePolicyStatus;
    }

    public String getFinancePolicyValidTime() {
        return financePolicyValidTime;
    }

    public void setFinancePolicyValidTime(String financePolicyValidTime) {
        this.financePolicyValidTime = financePolicyValidTime;
    }

    public String getFinanceCreatedDate() {
        return financeCreatedDate;
    }

    public void setFinanceCreatedDate(String financeCreatedDate) {
        this.financeCreatedDate = financeCreatedDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(patientFinanceId);
        dest.writeValue(financeTitle);
        dest.writeValue(financeProvider);
        dest.writeValue(financePolicyDocument);
        dest.writeValue(financePolicyNumber);
        dest.writeValue(financePolicyStatus);
        dest.writeValue(financePolicyValidTime);
        dest.writeValue(financeCreatedDate);
    }

    public int describeContents() {
        return 0;
    }

}