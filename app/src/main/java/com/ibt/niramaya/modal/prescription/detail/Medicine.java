package com.ibt.niramaya.modal.prescription.detail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Medicine implements Parcelable {

    @SerializedName("opd_preception_id")
    @Expose
    private String opdPreceptionId;
    @SerializedName("preception")
    @Expose
    private Integer preception;
    @SerializedName("medicine_id")
    @Expose
    private String medicineId;
    @SerializedName("medicine_name")
    @Expose
    private String medicineName;
    @SerializedName("medicine_dose")
    @Expose
    private String medicineDose;
    @SerializedName("preception_type")
    @Expose
    private String preceptionType;
    @SerializedName("opd_preception_created_date")
    @Expose
    private String opdPreceptionCreatedDate;
    public final static Parcelable.Creator<Medicine> CREATOR = new Creator<Medicine>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Medicine createFromParcel(Parcel in) {
            return new Medicine(in);
        }

        public Medicine[] newArray(int size) {
            return (new Medicine[size]);
        }

    };

    protected Medicine(Parcel in) {
        this.opdPreceptionId = ((String) in.readValue((String.class.getClassLoader())));
        this.preception = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.medicineId = ((String) in.readValue((String.class.getClassLoader())));
        this.medicineName = ((String) in.readValue((String.class.getClassLoader())));
        this.medicineDose = ((String) in.readValue((String.class.getClassLoader())));
        this.preceptionType = ((String) in.readValue((String.class.getClassLoader())));
        this.opdPreceptionCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Medicine() {
    }

    public String getOpdPreceptionId() {
        return opdPreceptionId;
    }

    public void setOpdPreceptionId(String opdPreceptionId) {
        this.opdPreceptionId = opdPreceptionId;
    }

    public Integer getPreception() {
        return preception;
    }

    public void setPreception(Integer preception) {
        this.preception = preception;
    }

    public String getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(String medicineId) {
        this.medicineId = medicineId;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineDose() {
        return medicineDose;
    }

    public void setMedicineDose(String medicineDose) {
        this.medicineDose = medicineDose;
    }

    public String getPreceptionType() {
        return preceptionType;
    }

    public void setPreceptionType(String preceptionType) {
        this.preceptionType = preceptionType;
    }

    public String getOpdPreceptionCreatedDate() {
        return opdPreceptionCreatedDate;
    }

    public void setOpdPreceptionCreatedDate(String opdPreceptionCreatedDate) {
        this.opdPreceptionCreatedDate = opdPreceptionCreatedDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(opdPreceptionId);
        dest.writeValue(preception);
        dest.writeValue(medicineId);
        dest.writeValue(medicineName);
        dest.writeValue(medicineDose);
        dest.writeValue(preceptionType);
        dest.writeValue(opdPreceptionCreatedDate);
    }

    public int describeContents() {
        return 0;
    }

}