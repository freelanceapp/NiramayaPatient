package com.ibt.niramaya.modal.prescription.detail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillMedicine implements Parcelable {

    @SerializedName("pharmacy_bill_medicine_id")
    @Expose
    private String pharmacyBillMedicineId;
    @SerializedName("pharmacy_bill_medicine_name")
    @Expose
    private String pharmacyBillMedicineName;
    @SerializedName("pharmacy_bill_medicine_quantity")
    @Expose
    private String pharmacyBillMedicineQuantity;
    @SerializedName("pharmacy_bill_medicine_created_date")
    @Expose
    private String pharmacyBillMedicineCreatedDate;
    public final static Parcelable.Creator<BillMedicine> CREATOR = new Creator<BillMedicine>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BillMedicine createFromParcel(Parcel in) {
            return new BillMedicine(in);
        }

        public BillMedicine[] newArray(int size) {
            return (new BillMedicine[size]);
        }

    };

    protected BillMedicine(Parcel in) {
        this.pharmacyBillMedicineId = ((String) in.readValue((String.class.getClassLoader())));
        this.pharmacyBillMedicineName = ((String) in.readValue((String.class.getClassLoader())));
        this.pharmacyBillMedicineQuantity = ((String) in.readValue((String.class.getClassLoader())));
        this.pharmacyBillMedicineCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public BillMedicine() {
    }

    public String getPharmacyBillMedicineId() {
        return pharmacyBillMedicineId;
    }

    public void setPharmacyBillMedicineId(String pharmacyBillMedicineId) {
        this.pharmacyBillMedicineId = pharmacyBillMedicineId;
    }

    public String getPharmacyBillMedicineName() {
        return pharmacyBillMedicineName;
    }

    public void setPharmacyBillMedicineName(String pharmacyBillMedicineName) {
        this.pharmacyBillMedicineName = pharmacyBillMedicineName;
    }

    public String getPharmacyBillMedicineQuantity() {
        return pharmacyBillMedicineQuantity;
    }

    public void setPharmacyBillMedicineQuantity(String pharmacyBillMedicineQuantity) {
        this.pharmacyBillMedicineQuantity = pharmacyBillMedicineQuantity;
    }

    public String getPharmacyBillMedicineCreatedDate() {
        return pharmacyBillMedicineCreatedDate;
    }

    public void setPharmacyBillMedicineCreatedDate(String pharmacyBillMedicineCreatedDate) {
        this.pharmacyBillMedicineCreatedDate = pharmacyBillMedicineCreatedDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pharmacyBillMedicineId);
        dest.writeValue(pharmacyBillMedicineName);
        dest.writeValue(pharmacyBillMedicineQuantity);
        dest.writeValue(pharmacyBillMedicineCreatedDate);
    }

    public int describeContents() {
        return 0;
    }

}