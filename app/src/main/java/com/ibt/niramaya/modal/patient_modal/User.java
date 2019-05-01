package com.ibt.niramaya.modal.patient_modal;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("user_contact")
    @Expose
    private String userContact;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("user_created_date")
    @Expose
    private String userCreatedDate;
    @SerializedName("paitent_profile")
    @Expose
    private List<PaitentProfile> paitentProfile = new ArrayList<PaitentProfile>();
    public final static Parcelable.Creator<User> CREATOR = new Creator<User>() {


        @SuppressWarnings({
                "unchecked"
        })
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return (new User[size]);
        }

    };

    protected User(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.userEmail = ((String) in.readValue((String.class.getClassLoader())));
        this.userContact = ((String) in.readValue((String.class.getClassLoader())));
        this.userName = ((String) in.readValue((String.class.getClassLoader())));
        this.userCreatedDate = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.paitentProfile, (com.ibt.niramaya.modal.patient_modal.PaitentProfile.class.getClassLoader()));
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserContact() {
        return userContact;
    }

    public void setUserContact(String userContact) {
        this.userContact = userContact;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCreatedDate() {
        return userCreatedDate;
    }

    public void setUserCreatedDate(String userCreatedDate) {
        this.userCreatedDate = userCreatedDate;
    }

    public List<PaitentProfile> getPaitentProfile() {
        return paitentProfile;
    }

    public void setPaitentProfile(List<PaitentProfile> paitentProfile) {
        this.paitentProfile = paitentProfile;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(userEmail);
        dest.writeValue(userContact);
        dest.writeValue(userName);
        dest.writeValue(userCreatedDate);
        dest.writeList(paitentProfile);
    }

    public int describeContents() {
        return 0;
    }

}