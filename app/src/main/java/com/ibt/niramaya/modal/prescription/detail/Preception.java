package com.ibt.niramaya.modal.prescription.detail;

import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Preception implements Parcelable {

    @SerializedName("opd_id")
    @Expose
    private String opdId;
    @SerializedName("opd_appointment_id")
    @Expose
    private String opdAppointmentId;
    @SerializedName("opd_cheif_complaints")
    @Expose
    private String opdCheifComplaints;
    @SerializedName("opd_vitals")
    @Expose
    private String opdVitals;
    @SerializedName("opd_bp")
    @Expose
    private String opdBp;
    @SerializedName("opd_heart_rate_per_min")
    @Expose
    private String opdHeartRatePerMin;
    @SerializedName("opd_resp_rate_min")
    @Expose
    private String opdRespRateMin;
    @SerializedName("opd_temp")
    @Expose
    private String opdTemp;
    @SerializedName("opd_pain_score")
    @Expose
    private String opdPainScore;
    @SerializedName("opd_treatement_given")
    @Expose
    private String opdTreatementGiven;
    @SerializedName("opd_treatment_advice")
    @Expose
    private String opdTreatmentAdvice;
    @SerializedName("opd_dr_id")
    @Expose
    private String opdDrId;
    @SerializedName("opd_follow_up_advice")
    @Expose
    private String opdFollowUpAdvice;
    @SerializedName("opd_type_of_discharge")
    @Expose
    private String opdTypeOfDischarge;
    @SerializedName("opd_status")
    @Expose
    private String opdStatus;
    @SerializedName("opd_admin_status")
    @Expose
    private String opdAdminStatus;
    @SerializedName("opd_created_date")
    @Expose
    private String opdCreatedDate;
    @SerializedName("opd_updated_date")
    @Expose
    private String opdUpdatedDate;
    @SerializedName("medicine")
    @Expose
    private List<Medicine> medicine = null;
    @SerializedName("bill_medicine")
    @Expose
    private List<BillMedicine> billMedicine = null;
    @SerializedName("test")
    @Expose
    private List<Test> test = null;
    @SerializedName("bill_test")
    @Expose
    private List<Object> billTest = null;
    public final static Parcelable.Creator<Preception> CREATOR = new Creator<Preception>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Preception createFromParcel(Parcel in) {
            return new Preception(in);
        }

        public Preception[] newArray(int size) {
            return (new Preception[size]);
        }

    };

    protected Preception(Parcel in) {
        this.opdId = ((String) in.readValue((String.class.getClassLoader())));
        this.opdAppointmentId = ((String) in.readValue((String.class.getClassLoader())));
        this.opdCheifComplaints = ((String) in.readValue((String.class.getClassLoader())));
        this.opdVitals = ((String) in.readValue((String.class.getClassLoader())));
        this.opdBp = ((String) in.readValue((String.class.getClassLoader())));
        this.opdHeartRatePerMin = ((String) in.readValue((String.class.getClassLoader())));
        this.opdRespRateMin = ((String) in.readValue((String.class.getClassLoader())));
        this.opdTemp = ((String) in.readValue((String.class.getClassLoader())));
        this.opdPainScore = ((String) in.readValue((String.class.getClassLoader())));
        this.opdTreatementGiven = ((String) in.readValue((String.class.getClassLoader())));
        this.opdTreatmentAdvice = ((String) in.readValue((String.class.getClassLoader())));
        this.opdDrId = ((String) in.readValue((String.class.getClassLoader())));
        this.opdFollowUpAdvice = ((String) in.readValue((String.class.getClassLoader())));
        this.opdTypeOfDischarge = ((String) in.readValue((String.class.getClassLoader())));
        this.opdStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.opdAdminStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.opdCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
        this.opdUpdatedDate = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.medicine, (com.ibt.niramaya.modal.prescription.detail.Medicine.class.getClassLoader()));
        in.readList(this.billMedicine, (com.ibt.niramaya.modal.prescription.detail.BillMedicine.class.getClassLoader()));
        in.readList(this.test, (com.ibt.niramaya.modal.prescription.detail.Test.class.getClassLoader()));
        in.readList(this.billTest, (java.lang.Object.class.getClassLoader()));
    }

    public Preception() {
    }

    public String getOpdId() {
        return opdId;
    }

    public void setOpdId(String opdId) {
        this.opdId = opdId;
    }

    public String getOpdAppointmentId() {
        return opdAppointmentId;
    }

    public void setOpdAppointmentId(String opdAppointmentId) {
        this.opdAppointmentId = opdAppointmentId;
    }

    public String getOpdCheifComplaints() {
        return opdCheifComplaints;
    }

    public void setOpdCheifComplaints(String opdCheifComplaints) {
        this.opdCheifComplaints = opdCheifComplaints;
    }

    public String getOpdVitals() {
        return opdVitals;
    }

    public void setOpdVitals(String opdVitals) {
        this.opdVitals = opdVitals;
    }

    public String getOpdBp() {
        return opdBp;
    }

    public void setOpdBp(String opdBp) {
        this.opdBp = opdBp;
    }

    public String getOpdHeartRatePerMin() {
        return opdHeartRatePerMin;
    }

    public void setOpdHeartRatePerMin(String opdHeartRatePerMin) {
        this.opdHeartRatePerMin = opdHeartRatePerMin;
    }

    public String getOpdRespRateMin() {
        return opdRespRateMin;
    }

    public void setOpdRespRateMin(String opdRespRateMin) {
        this.opdRespRateMin = opdRespRateMin;
    }

    public String getOpdTemp() {
        return opdTemp;
    }

    public void setOpdTemp(String opdTemp) {
        this.opdTemp = opdTemp;
    }

    public String getOpdPainScore() {
        return opdPainScore;
    }

    public void setOpdPainScore(String opdPainScore) {
        this.opdPainScore = opdPainScore;
    }

    public String getOpdTreatementGiven() {
        return opdTreatementGiven;
    }

    public void setOpdTreatementGiven(String opdTreatementGiven) {
        this.opdTreatementGiven = opdTreatementGiven;
    }

    public String getOpdTreatmentAdvice() {
        return opdTreatmentAdvice;
    }

    public void setOpdTreatmentAdvice(String opdTreatmentAdvice) {
        this.opdTreatmentAdvice = opdTreatmentAdvice;
    }

    public String getOpdDrId() {
        return opdDrId;
    }

    public void setOpdDrId(String opdDrId) {
        this.opdDrId = opdDrId;
    }

    public String getOpdFollowUpAdvice() {
        return opdFollowUpAdvice;
    }

    public void setOpdFollowUpAdvice(String opdFollowUpAdvice) {
        this.opdFollowUpAdvice = opdFollowUpAdvice;
    }

    public String getOpdTypeOfDischarge() {
        return opdTypeOfDischarge;
    }

    public void setOpdTypeOfDischarge(String opdTypeOfDischarge) {
        this.opdTypeOfDischarge = opdTypeOfDischarge;
    }

    public String getOpdStatus() {
        return opdStatus;
    }

    public void setOpdStatus(String opdStatus) {
        this.opdStatus = opdStatus;
    }

    public String getOpdAdminStatus() {
        return opdAdminStatus;
    }

    public void setOpdAdminStatus(String opdAdminStatus) {
        this.opdAdminStatus = opdAdminStatus;
    }

    public String getOpdCreatedDate() {
        return opdCreatedDate;
    }

    public void setOpdCreatedDate(String opdCreatedDate) {
        this.opdCreatedDate = opdCreatedDate;
    }

    public String getOpdUpdatedDate() {
        return opdUpdatedDate;
    }

    public void setOpdUpdatedDate(String opdUpdatedDate) {
        this.opdUpdatedDate = opdUpdatedDate;
    }

    public List<Medicine> getMedicine() {
        return medicine;
    }

    public void setMedicine(List<Medicine> medicine) {
        this.medicine = medicine;
    }

    public List<BillMedicine> getBillMedicine() {
        return billMedicine;
    }

    public void setBillMedicine(List<BillMedicine> billMedicine) {
        this.billMedicine = billMedicine;
    }

    public List<Test> getTest() {
        return test;
    }

    public void setTest(List<Test> test) {
        this.test = test;
    }

    public List<Object> getBillTest() {
        return billTest;
    }

    public void setBillTest(List<Object> billTest) {
        this.billTest = billTest;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(opdId);
        dest.writeValue(opdAppointmentId);
        dest.writeValue(opdCheifComplaints);
        dest.writeValue(opdVitals);
        dest.writeValue(opdBp);
        dest.writeValue(opdHeartRatePerMin);
        dest.writeValue(opdRespRateMin);
        dest.writeValue(opdTemp);
        dest.writeValue(opdPainScore);
        dest.writeValue(opdTreatementGiven);
        dest.writeValue(opdTreatmentAdvice);
        dest.writeValue(opdDrId);
        dest.writeValue(opdFollowUpAdvice);
        dest.writeValue(opdTypeOfDischarge);
        dest.writeValue(opdStatus);
        dest.writeValue(opdAdminStatus);
        dest.writeValue(opdCreatedDate);
        dest.writeValue(opdUpdatedDate);
        dest.writeList(medicine);
        dest.writeList(billMedicine);
        dest.writeList(test);
        dest.writeList(billTest);
    }

    public int describeContents() {
        return 0;
    }

}