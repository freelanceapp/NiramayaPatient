package com.ibt.niramaya.modal.prescription.detail;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Test implements Parcelable
{

@SerializedName("opd_pathology_test_id")
@Expose
private String opdPathologyTestId;
@SerializedName("preception")
@Expose
private Integer preception;
@SerializedName("pathology_id")
@Expose
private String pathologyId;
@SerializedName("test_name")
@Expose
private String testName;
@SerializedName("opd_pathology_test_type")
@Expose
private String opdPathologyTestType;
@SerializedName("opd_pathology_test_created_date")
@Expose
private String opdPathologyTestCreatedDate;
public final static Parcelable.Creator<Test> CREATOR = new Creator<Test>() {


@SuppressWarnings({
"unchecked"
})
public Test createFromParcel(Parcel in) {
return new Test(in);
}

public Test[] newArray(int size) {
return (new Test[size]);
}

}
;

protected Test(Parcel in) {
this.opdPathologyTestId = ((String) in.readValue((String.class.getClassLoader())));
this.preception = ((Integer) in.readValue((Integer.class.getClassLoader())));
this.pathologyId = ((String) in.readValue((String.class.getClassLoader())));
this.testName = ((String) in.readValue((String.class.getClassLoader())));
this.opdPathologyTestType = ((String) in.readValue((String.class.getClassLoader())));
this.opdPathologyTestCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
}

public Test() {
}

public String getOpdPathologyTestId() {
return opdPathologyTestId;
}

public void setOpdPathologyTestId(String opdPathologyTestId) {
this.opdPathologyTestId = opdPathologyTestId;
}

public Integer getPreception() {
return preception;
}

public void setPreception(Integer preception) {
this.preception = preception;
}

public String getPathologyId() {
return pathologyId;
}

public void setPathologyId(String pathologyId) {
this.pathologyId = pathologyId;
}

public String getTestName() {
return testName;
}

public void setTestName(String testName) {
this.testName = testName;
}

public String getOpdPathologyTestType() {
return opdPathologyTestType;
}

public void setOpdPathologyTestType(String opdPathologyTestType) {
this.opdPathologyTestType = opdPathologyTestType;
}

public String getOpdPathologyTestCreatedDate() {
return opdPathologyTestCreatedDate;
}

public void setOpdPathologyTestCreatedDate(String opdPathologyTestCreatedDate) {
this.opdPathologyTestCreatedDate = opdPathologyTestCreatedDate;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(opdPathologyTestId);
dest.writeValue(preception);
dest.writeValue(pathologyId);
dest.writeValue(testName);
dest.writeValue(opdPathologyTestType);
dest.writeValue(opdPathologyTestCreatedDate);
}

public int describeContents() {
return 0;
}

}