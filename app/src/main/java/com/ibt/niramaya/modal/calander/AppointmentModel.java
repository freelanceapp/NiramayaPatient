package com.ibt.niramaya.modal.calander;

import java.util.ArrayList;

public class AppointmentModel {

    private String day;
    private String date;
    private ArrayList<DayOPD> dayOpdList = new ArrayList();
    private ArrayList<DateOPD> dateOpdList = new ArrayList();

    public AppointmentModel(String day, String date, ArrayList<DayOPD> dayOpdList) {
        this.day = day;
        this.date = date;
        this.dayOpdList = dayOpdList;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<DayOPD> getDayOpdList() {
        return dayOpdList;
    }

    public void setDayOpdList(ArrayList<DayOPD> dayOpdList) {
        this.dayOpdList = dayOpdList;
    }

    public ArrayList<DateOPD> getDateOpdList() {
        return dateOpdList;
    }

    public void setDateOpdList(ArrayList<DateOPD> dateOpdList) {
        this.dateOpdList = dateOpdList;
    }
}
