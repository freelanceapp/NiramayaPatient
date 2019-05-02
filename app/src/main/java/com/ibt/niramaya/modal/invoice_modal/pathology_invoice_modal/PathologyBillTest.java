package com.ibt.niramaya.modal.invoice_modal.pathology_invoice_modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PathologyBillTest implements Parcelable
{

@SerializedName("pathology_bill_test_id")
@Expose
private String pathologyBillTestId;
@SerializedName("test_name")
@Expose
private String testName;
@SerializedName("test_id")
@Expose
private String testId;
@SerializedName("test_code")
@Expose
private String testCode;
@SerializedName("test_cost")
@Expose
private String testCost;
@SerializedName("test_discount")
@Expose
private String testDiscount;
@SerializedName("test_sample")
@Expose
private String testSample;
@SerializedName("test_prerequisites")
@Expose
private String testPrerequisites;
@SerializedName("test_type")
@Expose
private String testType;
@SerializedName("test_schedule")
@Expose
private String testSchedule;
@SerializedName("test_type_created_date")
@Expose
private String testTypeCreatedDate;
public final static Parcelable.Creator<PathologyBillTest> CREATOR = new Creator<PathologyBillTest>() {


@SuppressWarnings({
"unchecked"
})
public PathologyBillTest createFromParcel(Parcel in) {
return new PathologyBillTest(in);
}

public PathologyBillTest[] newArray(int size) {
return (new PathologyBillTest[size]);
}

}
;

protected PathologyBillTest(Parcel in) {
this.pathologyBillTestId = ((String) in.readValue((String.class.getClassLoader())));
this.testName = ((String) in.readValue((String.class.getClassLoader())));
this.testId = ((String) in.readValue((String.class.getClassLoader())));
this.testCode = ((String) in.readValue((String.class.getClassLoader())));
this.testCost = ((String) in.readValue((String.class.getClassLoader())));
this.testDiscount = ((String) in.readValue((String.class.getClassLoader())));
this.testSample = ((String) in.readValue((String.class.getClassLoader())));
this.testPrerequisites = ((String) in.readValue((String.class.getClassLoader())));
this.testType = ((String) in.readValue((String.class.getClassLoader())));
this.testSchedule = ((String) in.readValue((String.class.getClassLoader())));
this.testTypeCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
}

public PathologyBillTest() {
}

public String getPathologyBillTestId() {
return pathologyBillTestId;
}

public void setPathologyBillTestId(String pathologyBillTestId) {
this.pathologyBillTestId = pathologyBillTestId;
}

public String getTestName() {
return testName;
}

public void setTestName(String testName) {
this.testName = testName;
}

public String getTestId() {
return testId;
}

public void setTestId(String testId) {
this.testId = testId;
}

public String getTestCode() {
return testCode;
}

public void setTestCode(String testCode) {
this.testCode = testCode;
}

public String getTestCost() {
return testCost;
}

public void setTestCost(String testCost) {
this.testCost = testCost;
}

public String getTestDiscount() {
return testDiscount;
}

public void setTestDiscount(String testDiscount) {
this.testDiscount = testDiscount;
}

public String getTestSample() {
return testSample;
}

public void setTestSample(String testSample) {
this.testSample = testSample;
}

public String getTestPrerequisites() {
return testPrerequisites;
}

public void setTestPrerequisites(String testPrerequisites) {
this.testPrerequisites = testPrerequisites;
}

public String getTestType() {
return testType;
}

public void setTestType(String testType) {
this.testType = testType;
}

public String getTestSchedule() {
return testSchedule;
}

public void setTestSchedule(String testSchedule) {
this.testSchedule = testSchedule;
}

public String getTestTypeCreatedDate() {
return testTypeCreatedDate;
}

public void setTestTypeCreatedDate(String testTypeCreatedDate) {
this.testTypeCreatedDate = testTypeCreatedDate;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(pathologyBillTestId);
dest.writeValue(testName);
dest.writeValue(testId);
dest.writeValue(testCode);
dest.writeValue(testCost);
dest.writeValue(testDiscount);
dest.writeValue(testSample);
dest.writeValue(testPrerequisites);
dest.writeValue(testType);
dest.writeValue(testSchedule);
dest.writeValue(testTypeCreatedDate);
}

public int describeContents() {
return 0;
}

}