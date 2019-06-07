package com.ibt.niramaya.modal.prescription.detail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillTest implements Parcelable {

    @SerializedName("pathology_bill_test_id")
    @Expose
    private String pathologyBillTestId;
    @SerializedName("pathology_test_name")
    @Expose
    private String pathologyTestName;
    @SerializedName("pathology_test_id")
    @Expose
    private String pathologyTestId;
    @SerializedName("pathology_bill_test_code")
    @Expose
    private String pathologyBillTestCode;
    @SerializedName("pathology_bill_test_type_created_date")
    @Expose
    private String pathologyBillTestTypeCreatedDate;
    public final static Parcelable.Creator<BillTest> CREATOR = new Creator<BillTest>() {


        @SuppressWarnings({
                "unchecked"
        })
        public BillTest createFromParcel(Parcel in) {
            return new BillTest(in);
        }

        public BillTest[] newArray(int size) {
            return (new BillTest[size]);
        }

    };

    protected BillTest(Parcel in) {
        this.pathologyBillTestId = ((String) in.readValue((String.class.getClassLoader())));
        this.pathologyTestName = ((String) in.readValue((String.class.getClassLoader())));
        this.pathologyTestId = ((String) in.readValue((String.class.getClassLoader())));
        this.pathologyBillTestCode = ((String) in.readValue((String.class.getClassLoader())));
        this.pathologyBillTestTypeCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public BillTest() {
    }

    public String getPathologyBillTestId() {
        return pathologyBillTestId;
    }

    public void setPathologyBillTestId(String pathologyBillTestId) {
        this.pathologyBillTestId = pathologyBillTestId;
    }

    public String getPathologyTestName() {
        return pathologyTestName;
    }

    public void setPathologyTestName(String pathologyTestName) {
        this.pathologyTestName = pathologyTestName;
    }

    public String getPathologyTestId() {
        return pathologyTestId;
    }

    public void setPathologyTestId(String pathologyTestId) {
        this.pathologyTestId = pathologyTestId;
    }

    public String getPathologyBillTestCode() {
        return pathologyBillTestCode;
    }

    public void setPathologyBillTestCode(String pathologyBillTestCode) {
        this.pathologyBillTestCode = pathologyBillTestCode;
    }

    public String getPathologyBillTestTypeCreatedDate() {
        return pathologyBillTestTypeCreatedDate;
    }

    public void setPathologyBillTestTypeCreatedDate(String pathologyBillTestTypeCreatedDate) {
        this.pathologyBillTestTypeCreatedDate = pathologyBillTestTypeCreatedDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(pathologyBillTestId);
        dest.writeValue(pathologyTestName);
        dest.writeValue(pathologyTestId);
        dest.writeValue(pathologyBillTestCode);
        dest.writeValue(pathologyBillTestTypeCreatedDate);
    }

    public int describeContents() {
        return 0;
    }

}