package com.ibt.niramaya.modal.doctor_opd;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorData implements Parcelable
{

@SerializedName("doctor_id")
@Expose
private String doctorId;
@SerializedName("name")
@Expose
private String name;
@SerializedName("gender")
@Expose
private String gender;
@SerializedName("date_of_birth")
@Expose
private String dateOfBirth;
@SerializedName("profile_image")
@Expose
private String profileImage;
@SerializedName("created_date")
@Expose
private String createdDate;
@SerializedName("doctor_specialization")
@Expose
private List<DoctorSpecialization> doctorSpecialization = new ArrayList<>();
@SerializedName("opd_list")
@Expose
private List<OpdList> opdList = new ArrayList<>();
@SerializedName("rating")
@Expose
private String rating;
public final static Parcelable.Creator<DoctorData> CREATOR = new Creator<DoctorData>() {


@SuppressWarnings({
"unchecked"
})
public DoctorData createFromParcel(Parcel in) {
return new DoctorData(in);
}

public DoctorData[] newArray(int size) {
return (new DoctorData[size]);
}

}
;

protected DoctorData(Parcel in) {
this.doctorId = ((String) in.readValue((String.class.getClassLoader())));
this.name = ((String) in.readValue((String.class.getClassLoader())));
this.gender = ((String) in.readValue((String.class.getClassLoader())));
this.dateOfBirth = ((String) in.readValue((String.class.getClassLoader())));
this.profileImage = ((String) in.readValue((String.class.getClassLoader())));
this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
in.readList(this.doctorSpecialization, (DoctorSpecialization.class.getClassLoader()));
in.readList(this.opdList, (OpdList.class.getClassLoader()));
this.rating = ((String) in.readValue((String.class.getClassLoader())));
}

public DoctorData() {
}

public String getDoctorId() {
return doctorId;
}

public void setDoctorId(String doctorId) {
this.doctorId = doctorId;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;
}

public String getDateOfBirth() {
return dateOfBirth;
}

public void setDateOfBirth(String dateOfBirth) {
this.dateOfBirth = dateOfBirth;
}

public String getProfileImage() {
return profileImage;
}

public void setProfileImage(String profileImage) {
this.profileImage = profileImage;
}

public String getCreatedDate() {
return createdDate;
}

public void setCreatedDate(String createdDate) {
this.createdDate = createdDate;
}

public List<DoctorSpecialization> getDoctorSpecialization() {
return doctorSpecialization;
}

public void setDoctorSpecialization(List<DoctorSpecialization> doctorSpecialization) {
this.doctorSpecialization = doctorSpecialization;
}

public List<OpdList> getOpdList() {
return opdList;
}

public void setOpdList(List<OpdList> opdList) {
this.opdList = opdList;
}

public String getRating() {
return rating;
}

public void setRating(String rating) {
this.rating = rating;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(doctorId);
dest.writeValue(name);
dest.writeValue(gender);
dest.writeValue(dateOfBirth);
dest.writeValue(profileImage);
dest.writeValue(createdDate);
dest.writeList(doctorSpecialization);
dest.writeList(opdList);
dest.writeValue(rating);
}

public int describeContents() {
return 0;
}

}