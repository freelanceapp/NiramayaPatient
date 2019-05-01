package com.ibt.niramaya.modal.otp_verifacation_modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OtpVerificationMainModal implements Parcelable
{

@SerializedName("error")
@Expose
private Boolean error;
@SerializedName("message")
@Expose
private String message;
@SerializedName("user")
@Expose
private User user;
public final static Parcelable.Creator<OtpVerificationMainModal> CREATOR = new Creator<OtpVerificationMainModal>() {


@SuppressWarnings({
"unchecked"
})
public OtpVerificationMainModal createFromParcel(Parcel in) {
return new OtpVerificationMainModal(in);
}

public OtpVerificationMainModal[] newArray(int size) {
return (new OtpVerificationMainModal[size]);
}

}
;

protected OtpVerificationMainModal(Parcel in) {
this.error = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
this.message = ((String) in.readValue((String.class.getClassLoader())));
this.user = ((User) in.readValue((User.class.getClassLoader())));
}

public OtpVerificationMainModal() {
}

public Boolean getError() {
return error;
}

public void setError(Boolean error) {
this.error = error;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(error);
dest.writeValue(message);
dest.writeValue(user);
}

public int describeContents() {
return 0;
}

}