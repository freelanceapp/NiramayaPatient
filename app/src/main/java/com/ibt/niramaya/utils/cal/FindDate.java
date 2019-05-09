package com.ibt.niramaya.utils.cal;

import com.ibt.niramaya.modal.calander.AppointmentModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class FindDate {


    public static void main(String[] args) {

        //("Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec")

        try {
            Calendar calendar = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("MM/yyyy", Locale.ENGLISH);
            DateFormat yearFormate = new SimpleDateFormat("yyyy", Locale.ENGLISH);
            DateFormat currentDateFormat = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);
            String currentDateStr = currentDateFormat.format(calendar.getTime());
            Date currentDate = currentDateFormat.parse(currentDateStr);
            String currentMonthYear = dateFormat.format(calendar.getTime());
            ArrayList<AppointmentModel> appointementList = getDate("May", Integer.parseInt(yearFormate.format(calendar.getTime())), currentDate);
            int size = appointementList.size();
            for (int i = 0; i<appointementList.size(); i++) {
                System.out.println(appointementList.get(i).getDay()+" "+appointementList.get(i).getDate());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<AppointmentModel> getDate(String month, int yr, Date currentDate) {
        ArrayList<AppointmentModel> dateList = new ArrayList<AppointmentModel>();
        int mCount = 0;
        switch (month) {
            case "Jan":
                mCount = 31;
                break;
            case "Feb":
                if (yr % 4 == 0) {
                    mCount = 29;
                } else {
                    mCount = 28;
                }
                break;
            case "Mar":
                mCount = 31;
                break;
            case "Apr":
                mCount = 30;
                break;
            case "May":
                mCount = 31;
                break;
            case "Jun":
                mCount = 30;
                break;
            case "Jul":
                mCount = 31;
                break;
            case "Aug":
                mCount = 31;
                break;
            case "Sep":
                mCount = 30;
                break;
            case "Oct":
                mCount = 31;
                break;
            case "Nov":
                mCount = 30;
                break;
            case "Dec":
                mCount = 31;
                break;
        }
        for (int i = 0; i < mCount; i++) {
            String mDate = (i + 1) + " " + month + " " + yr;//d MMM yyyy

            DateFormat originalFormat = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);
            DateFormat ddFF = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            DateFormat targetFormat = new SimpleDateFormat("EEEE", Locale.ENGLISH);
            //DateFormat targetFormat = new SimpleDateFormat("EEE d MMM yyyy");
            Date date = null;
            try {
                date = originalFormat.parse(mDate);
            } catch (ParseException e) {
                e.printStackTrace();

            }
            //String formattedDate ="testingDay";
            String formattedDate = targetFormat.format(date);
            String myDate = ddFF.format(date);
            String myDay = ddFF.format(date);
            String[] dateDetail = originalFormat.format(date).split(" ");


            /*int isCurrentDate = date.compareTo(currentDate);

            if (isCurrentDate == 0) {

                AppointmentModel addModel = new AppointmentModel("", dateDetail[0], myDate, "", "", "");

                //dateList.add(i, addModel);

            } else {
                //CalenderDataModel addModel = new CalenderDataModel(dateDetail[0], dateDetail[1], dateDetail[2], formattedDate, String.valueOf(month), false, false);

                //dateList.add(i, addModel);
            }*/

            AppointmentModel addModel = new AppointmentModel(formattedDate, myDate, null);

            dateList.add(i, addModel);

        }

        return dateList;

    }

    /***********************************************************
     * EEE, d MMM yyyy == >>  Wed, 4 Jul 2001
     * EEE, dd MM yyyy == >>  Wed, 04 07 2001
     * 01 -> 31
     * 02 -> 28/29
     * 03 -> 31
     * 04 -> 30
     * 05 -> 31
     * 06 -> 30
     * 07 -> 31
     * 08 -> 31
     * 09 -> 30
     * 10 -> 31
     * 11 -> 30
     * 12 -> 31
     *
     * MMM yyyy
     * d EEE
     *
     **************************************************************/

}