package com.ibt.niramaya.modal.calander;

public class DateOPD {

    private String type;
    private String title;
    private String description;
    private String scheduleId;
    private String opdStartDate;
    private String opdEndDate;
    private String startTime;
    private String endTime;
    private String date;
    private String status;

    public DateOPD(String type, String startTime, String endTime, String status, String scheduleId, String date) {
        this.type = type;
        this.scheduleId = scheduleId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.status = status;
    }

    public DateOPD() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getOpdStartDate() {
        return opdStartDate;
    }

    public void setOpdStartDate(String opdStartDate) {
        this.opdStartDate = opdStartDate;
    }

    public String getOpdEndDate() {
        return opdEndDate;
    }

    public void setOpdEndDate(String opdEndDate) {
        this.opdEndDate = opdEndDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
