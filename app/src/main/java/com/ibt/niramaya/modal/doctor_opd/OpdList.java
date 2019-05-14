package com.ibt.niramaya.modal.doctor_opd;

import java.util.ArrayList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpdList implements Parcelable {

    @SerializedName("schedule_id")
    @Expose
    private String scheduleId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("max_patient")
    @Expose
    private String maxPatient;
    @SerializedName("payment_status")
    @Expose
    private String paymentStatus;
    @SerializedName("schedule_type")
    @Expose
    private String scheduleType;
    @SerializedName("schedule")
    @Expose
    private List<Schedule> schedule = new ArrayList<>();
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    public final static Parcelable.Creator<OpdList> CREATOR = new Creator<OpdList>() {


        @SuppressWarnings({
                "unchecked"
        })
        public OpdList createFromParcel(Parcel in) {
            return new OpdList(in);
        }

        public OpdList[] newArray(int size) {
            return (new OpdList[size]);
        }

    };

    protected OpdList(Parcel in) {
        this.scheduleId = ((String) in.readValue((String.class.getClassLoader())));
        this.title = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.startDate = ((String) in.readValue((String.class.getClassLoader())));
        this.endDate = ((String) in.readValue((String.class.getClassLoader())));
        this.amount = ((String) in.readValue((String.class.getClassLoader())));
        this.maxPatient = ((String) in.readValue((String.class.getClassLoader())));
        this.paymentStatus = ((String) in.readValue((String.class.getClassLoader())));
        this.scheduleType = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.schedule, (com.ibt.niramaya.modal.doctor_opd.Schedule.class.getClassLoader()));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
    }

    public OpdList() {
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getMaxPatient() {
        return maxPatient;
    }

    public void setMaxPatient(String maxPatient) {
        this.maxPatient = maxPatient;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public List<Schedule> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Schedule> schedule) {
        this.schedule = schedule;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(scheduleId);
        dest.writeValue(title);
        dest.writeValue(description);
        dest.writeValue(startDate);
        dest.writeValue(endDate);
        dest.writeValue(amount);
        dest.writeValue(maxPatient);
        dest.writeValue(paymentStatus);
        dest.writeValue(scheduleType);
        dest.writeList(schedule);
        dest.writeValue(createdDate);
    }

    public int describeContents() {
        return 0;
    }

}