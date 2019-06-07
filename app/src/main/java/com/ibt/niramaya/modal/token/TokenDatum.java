package com.ibt.niramaya.modal.token;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TokenDatum implements Parcelable {

    @SerializedName("appointment_id")
    @Expose
    private String appointmentId;
    @SerializedName("appointment_type")
    @Expose
    private String appointmentType;
    @SerializedName("appointment_token")
    @Expose
    private String appointmentToken;
    @SerializedName("current_token")
    @Expose
    private String currentToken;
    @SerializedName("patient")
    @Expose
    private String patient;
    @SerializedName("patient_id")
    @Expose
    private String patientId;
    @SerializedName("appointment_status")
    @Expose
    private String appointmentStatus;
    @SerializedName("opd_title")
    @Expose
    private String opdTitle;
    @SerializedName("opd_start_time")
    @Expose
    private String opdStartTime;
    @SerializedName("opd_end_time")
    @Expose
    private String opdEndTime;
    public final static Parcelable.Creator<TokenDatum> CREATOR = new Creator<TokenDatum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public TokenDatum createFromParcel(Parcel in) {
            return new TokenDatum(in);
        }

        public TokenDatum[] newArray(int size) {
            return (new TokenDatum[size]);
        }

    };

    protected TokenDatum(Parcel in) {
        this.appointmentId = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentType = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentToken = ((String) in.readValue((String.class.getClassLoader())));
        this.currentToken = ((String) in.readValue((String.class.getClassLoader())));
        this.patient = ((String) in.readValue((String.class.getClassLoader())));
        this.patientId = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.opdTitle = ((String) in.readValue((String.class.getClassLoader())));
        this.opdStartTime = ((String) in.readValue((String.class.getClassLoader())));
        this.opdEndTime = ((String) in.readValue((String.class.getClassLoader())));
    }

    public TokenDatum() {
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getAppointmentToken() {
        return appointmentToken;
    }

    public void setAppointmentToken(String appointmentToken) {
        this.appointmentToken = appointmentToken;
    }

    public String getCurrentToken() {
        return currentToken;
    }

    public void setCurrentToken(String currentToken) {
        this.currentToken = currentToken;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getOpdTitle() {
        return opdTitle;
    }

    public void setOpdTitle(String opdTitle) {
        this.opdTitle = opdTitle;
    }

    public String getOpdStartTime() {
        return opdStartTime;
    }

    public void setOpdStartTime(String opdStartTime) {
        this.opdStartTime = opdStartTime;
    }

    public String getOpdEndTime() {
        return opdEndTime;
    }

    public void setOpdEndTime(String opdEndTime) {
        this.opdEndTime = opdEndTime;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(appointmentId);
        dest.writeValue(appointmentType);
        dest.writeValue(appointmentToken);
        dest.writeValue(currentToken);
        dest.writeValue(patient);
        dest.writeValue(patientId);
        dest.writeValue(appointmentStatus);
        dest.writeValue(opdTitle);
        dest.writeValue(opdStartTime);
        dest.writeValue(opdEndTime);
    }

    public int describeContents() {
        return 0;
    }

}