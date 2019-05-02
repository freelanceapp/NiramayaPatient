package com.ibt.niramaya.modal.invoice_modal.opd_invoice_modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillDatum implements Parcelable {

    @SerializedName("appointment_id")
    @Expose
    private String appointmentId;
    @SerializedName("patient_id")
    @Expose
    private String patientId;
    @SerializedName("patient_name")
    @Expose
    private String patientName;
    @SerializedName("patient_dob")
    @Expose
    private String patientDob;
    @SerializedName("patient_gender")
    @Expose
    private String patientGender;
    @SerializedName("patinet_contact")
    @Expose
    private String patinetContact;
    @SerializedName("opd_id")
    @Expose
    private String opdId;
    @SerializedName("appointment_payment_status")
    @Expose
    private String appointmentPaymentStatus;
    @SerializedName("appointment_type")
    @Expose
    private String appointmentType;
    @SerializedName("appointment_status")
    @Expose
    private String appointmentStatus;
    @SerializedName("appointment_amount")
    @Expose
    private String appointmentAmount;
    @SerializedName("appointment_booking_date")
    @Expose
    private String appointmentBookingDate;
    @SerializedName("appointment_referred_by")
    @Expose
    private String appointmentReferredBy;
    @SerializedName("appointment_referred_doctor_name")
    @Expose
    private String appointmentReferredDoctorName;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("appointment_created_date")
    @Expose
    private String appointmentCreatedDate;
    @SerializedName("opd_information")
    @Expose
    private OpdInformation opdInformation;
    @SerializedName("hospital_bill_information")
    @Expose
    private HospitalBillInformation hospitalBillInformation;
    public final static Parcelable.Creator<BillDatum> CREATOR = new Creator<BillDatum>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BillDatum createFromParcel(Parcel in) {
            return new BillDatum(in);
        }

        public BillDatum[] newArray(int size) {
            return (new BillDatum[size]);
        }

    };

    protected BillDatum(Parcel in) {
        this.appointmentId = ((String) in.readValue((String.class.getClassLoader())));
        this.patientId = ((String) in.readValue((String.class.getClassLoader())));
        this.patientName = ((String) in.readValue((String.class.getClassLoader())));
        this.patientDob = ((String) in.readValue((String.class.getClassLoader())));
        this.patientGender = ((String) in.readValue((String.class.getClassLoader())));
        this.patinetContact = ((String) in.readValue((String.class.getClassLoader())));
        this.opdId = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentPaymentStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentType = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentAmount = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentBookingDate = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentReferredBy = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentReferredDoctorName = ((String) in.readValue((String.class.getClassLoader())));
        this.userId = ((String) in.readValue((String.class.getClassLoader())));
        this.appointmentCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
        this.opdInformation = ((OpdInformation) in.readValue((OpdInformation.class.getClassLoader())));
        this.hospitalBillInformation = ((HospitalBillInformation) in.readValue((HospitalBillInformation.class.getClassLoader())));
    }

    public BillDatum() {
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientDob() {
        return patientDob;
    }

    public void setPatientDob(String patientDob) {
        this.patientDob = patientDob;
    }

    public String getPatientGender() {
        return patientGender;
    }

    public void setPatientGender(String patientGender) {
        this.patientGender = patientGender;
    }

    public String getPatinetContact() {
        return patinetContact;
    }

    public void setPatinetContact(String patinetContact) {
        this.patinetContact = patinetContact;
    }

    public String getOpdId() {
        return opdId;
    }

    public void setOpdId(String opdId) {
        this.opdId = opdId;
    }

    public String getAppointmentPaymentStatus() {
        return appointmentPaymentStatus;
    }

    public void setAppointmentPaymentStatus(String appointmentPaymentStatus) {
        this.appointmentPaymentStatus = appointmentPaymentStatus;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(String appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public String getAppointmentAmount() {
        return appointmentAmount;
    }

    public void setAppointmentAmount(String appointmentAmount) {
        this.appointmentAmount = appointmentAmount;
    }

    public String getAppointmentBookingDate() {
        return appointmentBookingDate;
    }

    public void setAppointmentBookingDate(String appointmentBookingDate) {
        this.appointmentBookingDate = appointmentBookingDate;
    }

    public String getAppointmentReferredBy() {
        return appointmentReferredBy;
    }

    public void setAppointmentReferredBy(String appointmentReferredBy) {
        this.appointmentReferredBy = appointmentReferredBy;
    }

    public String getAppointmentReferredDoctorName() {
        return appointmentReferredDoctorName;
    }

    public void setAppointmentReferredDoctorName(String appointmentReferredDoctorName) {
        this.appointmentReferredDoctorName = appointmentReferredDoctorName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppointmentCreatedDate() {
        return appointmentCreatedDate;
    }

    public void setAppointmentCreatedDate(String appointmentCreatedDate) {
        this.appointmentCreatedDate = appointmentCreatedDate;
    }

    public OpdInformation getOpdInformation() {
        return opdInformation;
    }

    public void setOpdInformation(OpdInformation opdInformation) {
        this.opdInformation = opdInformation;
    }

    public HospitalBillInformation getHospitalBillInformation() {
        return hospitalBillInformation;
    }

    public void setHospitalBillInformation(HospitalBillInformation hospitalBillInformation) {
        this.hospitalBillInformation = hospitalBillInformation;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(appointmentId);
        dest.writeValue(patientId);
        dest.writeValue(patientName);
        dest.writeValue(patientDob);
        dest.writeValue(patientGender);
        dest.writeValue(patinetContact);
        dest.writeValue(opdId);
        dest.writeValue(appointmentPaymentStatus);
        dest.writeValue(appointmentType);
        dest.writeValue(appointmentStatus);
        dest.writeValue(appointmentAmount);
        dest.writeValue(appointmentBookingDate);
        dest.writeValue(appointmentReferredBy);
        dest.writeValue(appointmentReferredDoctorName);
        dest.writeValue(userId);
        dest.writeValue(appointmentCreatedDate);
        dest.writeValue(opdInformation);
        dest.writeValue(hospitalBillInformation);
    }

    public int describeContents() {
        return 0;
    }

}