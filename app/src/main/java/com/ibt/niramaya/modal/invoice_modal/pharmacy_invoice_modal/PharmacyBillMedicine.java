package com.ibt.niramaya.modal.invoice_modal.pharmacy_invoice_modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PharmacyBillMedicine implements Parcelable
{

@SerializedName("pharmacy_bill_medicine_id")
@Expose
private String pharmacyBillMedicineId;
@SerializedName("medicine_name")
@Expose
private String medicineName;
@SerializedName("medicine_id")
@Expose
private String medicineId;
@SerializedName("medicine_quantity")
@Expose
private String medicineQuantity;
@SerializedName("medicine_mrp")
@Expose
private String medicineMrp;
@SerializedName("medicine_amount")
@Expose
private String medicineAmount;
@SerializedName("medicine_company_name")
@Expose
private String medicineCompanyName;
@SerializedName("medicine_expire_date")
@Expose
private String medicineExpireDate;
@SerializedName("medicine_quantity_type")
@Expose
private String medicineQuantityType;
@SerializedName("pharmacy_bill_medicine_created_date")
@Expose
private String pharmacyBillMedicineCreatedDate;
public final static Parcelable.Creator<PharmacyBillMedicine> CREATOR = new Creator<PharmacyBillMedicine>() {


@SuppressWarnings({
"unchecked"
})
public PharmacyBillMedicine createFromParcel(Parcel in) {
return new PharmacyBillMedicine(in);
}

public PharmacyBillMedicine[] newArray(int size) {
return (new PharmacyBillMedicine[size]);
}

}
;

protected PharmacyBillMedicine(Parcel in) {
this.pharmacyBillMedicineId = ((String) in.readValue((String.class.getClassLoader())));
this.medicineName = ((String) in.readValue((String.class.getClassLoader())));
this.medicineId = ((String) in.readValue((String.class.getClassLoader())));
this.medicineQuantity = ((String) in.readValue((String.class.getClassLoader())));
this.medicineMrp = ((String) in.readValue((String.class.getClassLoader())));
this.medicineAmount = ((String) in.readValue((String.class.getClassLoader())));
this.medicineCompanyName = ((String) in.readValue((String.class.getClassLoader())));
this.medicineExpireDate = ((String) in.readValue((String.class.getClassLoader())));
this.medicineQuantityType = ((String) in.readValue((String.class.getClassLoader())));
this.pharmacyBillMedicineCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
}

public PharmacyBillMedicine() {
}

public String getPharmacyBillMedicineId() {
return pharmacyBillMedicineId;
}

public void setPharmacyBillMedicineId(String pharmacyBillMedicineId) {
this.pharmacyBillMedicineId = pharmacyBillMedicineId;
}

public String getMedicineName() {
return medicineName;
}

public void setMedicineName(String medicineName) {
this.medicineName = medicineName;
}

public String getMedicineId() {
return medicineId;
}

public void setMedicineId(String medicineId) {
this.medicineId = medicineId;
}

public String getMedicineQuantity() {
return medicineQuantity;
}

public void setMedicineQuantity(String medicineQuantity) {
this.medicineQuantity = medicineQuantity;
}

public String getMedicineMrp() {
return medicineMrp;
}

public void setMedicineMrp(String medicineMrp) {
this.medicineMrp = medicineMrp;
}

public String getMedicineAmount() {
return medicineAmount;
}

public void setMedicineAmount(String medicineAmount) {
this.medicineAmount = medicineAmount;
}

public String getMedicineCompanyName() {
return medicineCompanyName;
}

public void setMedicineCompanyName(String medicineCompanyName) {
this.medicineCompanyName = medicineCompanyName;
}

public String getMedicineExpireDate() {
return medicineExpireDate;
}

public void setMedicineExpireDate(String medicineExpireDate) {
this.medicineExpireDate = medicineExpireDate;
}

public String getMedicineQuantityType() {
return medicineQuantityType;
}

public void setMedicineQuantityType(String medicineQuantityType) {
this.medicineQuantityType = medicineQuantityType;
}

public String getPharmacyBillMedicineCreatedDate() {
return pharmacyBillMedicineCreatedDate;
}

public void setPharmacyBillMedicineCreatedDate(String pharmacyBillMedicineCreatedDate) {
this.pharmacyBillMedicineCreatedDate = pharmacyBillMedicineCreatedDate;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(pharmacyBillMedicineId);
dest.writeValue(medicineName);
dest.writeValue(medicineId);
dest.writeValue(medicineQuantity);
dest.writeValue(medicineMrp);
dest.writeValue(medicineAmount);
dest.writeValue(medicineCompanyName);
dest.writeValue(medicineExpireDate);
dest.writeValue(medicineQuantityType);
dest.writeValue(pharmacyBillMedicineCreatedDate);
}

public int describeContents() {
return 0;
}

}