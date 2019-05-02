package com.ibt.niramaya.modal.invoice_modal.opd_invoice_modal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpdInformation implements Parcelable
{

@SerializedName("schedule_id")
@Expose
private String scheduleId;
@SerializedName("title")
@Expose
private String title;
@SerializedName("description")
@Expose
private String description;
@SerializedName("amount")
@Expose
private String amount;
public final static Parcelable.Creator<OpdInformation> CREATOR = new Creator<OpdInformation>() {


@SuppressWarnings({
"unchecked"
})
public OpdInformation createFromParcel(Parcel in) {
return new OpdInformation(in);
}

public OpdInformation[] newArray(int size) {
return (new OpdInformation[size]);
}

}
;

protected OpdInformation(Parcel in) {
this.scheduleId = ((String) in.readValue((String.class.getClassLoader())));
this.title = ((String) in.readValue((String.class.getClassLoader())));
this.description = ((String) in.readValue((String.class.getClassLoader())));
this.amount = ((String) in.readValue((String.class.getClassLoader())));
}

public OpdInformation() {
}

public String getScheduleId() {
return scheduleId;
}

public void setScheduleId(String scheduleId) {
this.scheduleId = scheduleId;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getAmount() {
return amount;
}

public void setAmount(String amount) {
this.amount = amount;
}

public void writeToParcel(Parcel dest, int flags) {
dest.writeValue(scheduleId);
dest.writeValue(title);
dest.writeValue(description);
dest.writeValue(amount);
}

public int describeContents() {
return 0;
}

}
