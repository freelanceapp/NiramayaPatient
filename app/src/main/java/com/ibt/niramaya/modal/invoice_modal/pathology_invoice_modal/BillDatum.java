package com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillDatum implements Parcelable
{

@SerializedName("bill_id")
@Expose
private String billId;
@SerializedName("bill_invoice")
@Expose
private String billInvoice;
@SerializedName("bill_patient_id")
@Expose
private String billPatientId;
@SerializedName("user_id")
@Expose
private String userId;
@SerializedName("patient_name")
@Expose
private String patientName;
@SerializedName("patient_age")
@Expose
private String patientAge;
@SerializedName("patient_gender")
@Expose
private String patientGender;
@SerializedName("opd_id")
@Expose
private String opdId;
@SerializedName("bill_type")
@Expose
private String billType;
@SerializedName("bill_amount")
@Expose
private String billAmount;
@SerializedName("bill_discount")
@Expose
private String billDiscount;
@SerializedName("bill_hospital_id")
@Expose
private String billHospitalId;
@SerializedName("bill_gst_number")
@Expose
private String billGstNumber;
@SerializedName("gst")
@Expose
private String gst;
@SerializedName("bill_status")
@Expose
private String billStatus;
@SerializedName("bill_created_date")
@Expose
private String billCreatedDate;
@SerializedName("pathology_bill_test")
@Expose
private List<PathologyBillTest> pathologyBillTest = new ArrayList<PathologyBillTest>();
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

}
;

protected BillDatum(Parcel in) {
this.billId = ((String) in.readValue((String.class.getClassLoader())));
this.billInvoice = ((String) in.readValue((String.class.getClassLoader())));
this.billPatientId = ((String) in.readValue((String.class.getClassLoader())));
this.userId = ((String) in.readValue((String.class.getClassLoader())));
this.patientName = ((String) in.readValue((String.class.getClassLoader())));
this.patientAge = ((String) in.readValue((String.class.getClassLoader())));
this.patientGender = ((String) in.readValue((String.class.getClassLoader())));
this.opdId = ((String) in.readValue((String.class.getClassLoader())));
this.billType = ((String) in.readValue((String.class.getClassLoader())));
this.billAmount = ((String) in.readValue((String.class.getClassLoader())));
this.billDiscount = ((String) in.readValue((String.class.getClassLoader())));
this.billHospitalId = ((String) in.readValue((String.class.getClassLoader())));
this.billGstNumber = ((String) in.readValue((String.class.getClassLoader())));
this.gst = ((String) in.readValue((String.class.getClassLoader())));
this.billStatus = ((String) in.readValue((String.class.getClassLoader())));
this.billCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
in.readList(this.pathologyBillTest, (com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal.PathologyBillTest.class.getClassLoader()));
this.hospitalBillInformation = ((HospitalBillInformation) in.readValue((HospitalBillInformation.class.getClassLoader())));
}

public BillDatum() {
}

public String getBillId() {
return billId;
}

public void setBillId(String billId) {
this.billId = billId;
}

public String getBillInvoice() {
return billInvoice;
}

public void setBillInvoice(String billInvoice) {
this.billInvoice = billInvoice;
}

public String getBillPatientId() {
return billPatientId;
}

public void setBillPatientId(String billPatientId) {
this.billPatientId = billPatientId;
}

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getPatientName() {
return patientName;
}

public void setPatientName(String patientName) {
this.patientName = patientName;
}

public String getPatientAge() {
return patientAge;
}

public void setPatientAge(String patientAge) {
this.patientAge = patientAge;
}

public String getPatientGender() {
return patientGender;
}

public void setPatientGender(String patientGender) {
this.patientGender = patientGender;
}

public String getOpdId() {
return opdId;
}

public void setOpdId(String opdId) {
this.opdId = opdId;
}

public String getBillType() {
return billType;
}

public void setBillType(String billType) {
this.billType = billType;
}

public String getBillAmount() {
return billAmount;
}

public void setBillAmount(String billAmount) {
this.billAmount = billAmount;
}

public String getBillDiscount() {
return billDiscount;
}

public void setBillDiscount(String billDiscount) {
this.billDiscount = billDiscount;
}

public String getBillHospitalId() {
return billHospitalId;
}

public void setBillHospitalId(String billHospitalId) {
this.billHospitalId = billHospitalId;
}

public String getBillGstNumber() {
return billGstNumber;
}

public void setBillGstNumber(String billGstNumber) {
this.billGstNumber = billGstNumber;
}

public String getGst() {
return gst;
}

public void setGst(String gst) {
this.gst = gst;
}

public String getBillStatus() {
return billStatus;
}

public void setBillStatus(String billStatus) {
this.billStatus = billStatus;
}

public String getBillCreatedDate() {
return billCreatedDate;
}

public void setBillCreatedDate(String billCreatedDate) {
this.billCreatedDate = billCreatedDate;
}

public List<PathologyBillTest> getPathologyBillTest() {
return pathologyBillTest;
}

public void setPathologyBillTest(List<PathologyBillTest> pathologyBillTest) {
this.pathologyBillTest = pathologyBillTest;
}

public HospitalBillInformation getHospitalBillInformation() {
return hospitalBillInformation;
}

public void setHospitalBillInformation(HospitalBillInformation hospitalBillInformation) {
this.hospitalBillInformation = hospitalBillInformation;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(billId);
dest.writeValue(billInvoice);
dest.writeValue(billPatientId);
dest.writeValue(userId);
dest.writeValue(patientName);
dest.writeValue(patientAge);
dest.writeValue(patientGender);
dest.writeValue(opdId);
dest.writeValue(billType);
dest.writeValue(billAmount);
dest.writeValue(billDiscount);
dest.writeValue(billHospitalId);
dest.writeValue(billGstNumber);
dest.writeValue(gst);
dest.writeValue(billStatus);
dest.writeValue(billCreatedDate);
dest.writeList(pathologyBillTest);
dest.writeValue(hospitalBillInformation);
}

public int describeContents() {
return 0;
}

}