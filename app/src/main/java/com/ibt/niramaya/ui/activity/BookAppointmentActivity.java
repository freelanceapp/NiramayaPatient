package com.ibt.niramaya.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.ibt.niramaya.R;
import com.ibt.niramaya.modal.calander.AppointmentModel;
import com.ibt.niramaya.modal.calander.DayOPD;
import com.ibt.niramaya.modal.doctor_opd.DoctorDatum;
import com.ibt.niramaya.modal.doctor_opd.OpdList;
import com.ibt.niramaya.modal.doctor_opd.Schedule;
import com.ibt.niramaya.utils.Alerts;
import com.ibt.niramaya.utils.BaseActivity;
import com.ibt.niramaya.utils.cal.FindDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class BookAppointmentActivity extends BaseActivity implements View.OnClickListener {

    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatForSelectedMonth = new SimpleDateFormat("MMM-yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatForSelectedDay = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
    private SimpleDateFormat dateFormatWithDay = new SimpleDateFormat("EEEE, MMMM dd", Locale.getDefault());
    private SimpleDateFormat dateFormatMonth = new SimpleDateFormat("MMMM", Locale.getDefault());
    private SimpleDateFormat dateFormatMonthN = new SimpleDateFormat("M", Locale.getDefault());
    private SimpleDateFormat dateFormatMonthString = new SimpleDateFormat("MMM", Locale.getDefault());
    private SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy", Locale.getDefault());
    private int selectedYear = 0;
    private int selectedMonth = 0;
    private int currentYear = 0;
    private int currentMonth = 0;
    private boolean isPrevious = false;
    private ArrayList<AppointmentModel> appointmentList = new ArrayList<>();

   // private LoadCalenderEvents eventTask;

    private CompactCalendarView comCal;

    private TextView tvMonth, tvDate, tvNext, tvPrevious;
    private ImageView ivPrevious, ivNext;

    private TextView tvDrName, tvDrDesignation, tvDrAddress, tvSelectDate, tvSelectTime;
    private DoctorDatum doctorData;
    private List<OpdList> opdList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        doctorData = getIntent().getExtras().getParcelable("DoctorData");

        initViews();

    }

    private void initViews() {

        tvDrName = findViewById(R.id.tvDrName);
        tvDrDesignation = findViewById(R.id.tvDrDesignation);
        tvDrAddress = findViewById(R.id.tvDrAddress);

        tvMonth = findViewById(R.id.tvMonth);
        ivPrevious = findViewById(R.id.ivPrevious);
        ivNext = findViewById(R.id.ivNext);

        tvDrName.setText(doctorData.getName());
        //tvDrDesignation.setText(doctorData.getName());

        tvSelectDate = findViewById(R.id.tvSelectDate);
        tvSelectTime = findViewById(R.id.tvSelectTime);
        tvSelectDate.setOnClickListener(this);
        tvSelectTime.setOnClickListener(this);

        initCalenderViews();

        /*try {
            Calendar calendar = Calendar.getInstance();
            DateFormat dateFormat = new SimpleDateFormat("MM/yyyy", Locale.ENGLISH);
            DateFormat yearFormate = new SimpleDateFormat("yyyy", Locale.ENGLISH);
            DateFormat currentDateFormat = new SimpleDateFormat("d MMM yyyy", Locale.ENGLISH);
            String currentDateStr = currentDateFormat.format(calendar.getTime());
            Date currentDate = currentDateFormat.parse(currentDateStr);
            String currentMonthYear = dateFormat.format(calendar.getTime());
            ArrayList<AppointmentModel> appointementList = FindDate.getDate("May", Integer.parseInt(yearFormate.format(calendar.getTime())), currentDate);
            int size = appointementList.size();
            for (int i = 0; i<appointementList.size(); i++) {
                System.out.println(appointementList.get(i).getDay()+" "+appointementList.get(i).getDate());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }*/




    }

    private void initCalenderViews() {
        comCal = findViewById(R.id.calView);
        comCal.setUseThreeLetterAbbreviation(true);
        String monthName = "";

        try {
            currentMonth = Integer.parseInt(dateFormatMonthN.format(Calendar.getInstance().getTime()));
            monthName = dateFormatMonthString.format(Calendar.getInstance().getTime());
            currentYear = Integer.parseInt(dateFormatYear.format(Calendar.getInstance().getTime()));
            Alerts.show(mContext, monthName);
            loadCalendarEvents(monthName, currentYear);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        if (!isPrevious) {
            ivPrevious.setVisibility(View.GONE);
        }else{
            ivNext.setVisibility(View.VISIBLE);
        }

        /*tvYear.setText(dateFormatYear.format(Calendar.getInstance().getTime()));*/
        tvMonth.setText(dateFormatMonth.format(Calendar.getInstance().getTime()));
        /*tvDate.setText(dateFormatWithDay.format(Calendar.getInstance().getTime()));*/

        comCal.shouldScrollMonth(false);

        comCal.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
               // tvDate.setText(dateFormatWithDay.format(dateClicked));
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                //tvYear.setText(dateFormatYear.format(firstDayOfNewMonth));
                tvMonth.setText(dateFormatMonth.format(firstDayOfNewMonth));
                try {
                    selectedMonth = Integer.parseInt(dateFormatMonthN.format(firstDayOfNewMonth));
                    String monthName1 = dateFormatMonthString.format(firstDayOfNewMonth);
                    selectedYear = Integer.parseInt(dateFormatYear.format(firstDayOfNewMonth));
                    Alerts.show(mContext, monthName1);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                if (selectedYear == currentYear) {
                    if (selectedMonth > currentMonth) {
                        isPrevious = true;
                        ivPrevious.setVisibility(View.VISIBLE);
                        ivNext.setVisibility(View.GONE);
                    } else {
                        isPrevious = false;
                        ivPrevious.setVisibility(View.GONE);
                        ivNext.setVisibility(View.VISIBLE);
                    }
                } else if (selectedYear > currentYear) {
                    isPrevious = true;
                    tvPrevious.setVisibility(View.VISIBLE);
                    ivNext.setVisibility(View.GONE);
                } else {
                    isPrevious = false;
                    tvPrevious.setVisibility(View.GONE);
                    ivNext.setVisibility(View.VISIBLE);
                }

            }
        });

        ivNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comCal.scrollRight();
            }
        });

        ivPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comCal.scrollLeft();
            }
        });

        /*imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/
    }

    private void loadCalendarEvents(String monthName, int year) {
        appointmentList = FindDate.getDate(monthName, year, null);
        ArrayList<DayOPD>  mondayOpdList= new ArrayList();
        ArrayList<DayOPD>  tuedayOpdList= new ArrayList();
        ArrayList<DayOPD>  wednesdayOpdList= new ArrayList();
        ArrayList<DayOPD>  thursdayOpdList= new ArrayList();
        ArrayList<DayOPD>  fridayOpdList= new ArrayList();
        ArrayList<DayOPD>  saturdayOpdList= new ArrayList();
        ArrayList<DayOPD>  sundayOpdList= new ArrayList();
        if (doctorData.getOpdList().size()>0){
            for (OpdList myOpd : doctorData.getOpdList()){
                if (myOpd.getScheduleType().equals("1")){
                    for (Schedule daySchedule : myOpd.getSchedule()){
                        DayOPD dOPD = new DayOPD();
                        dOPD.setType("1");
                        dOPD.setScheduleId(myOpd.getScheduleId());
                        dOPD.setStartTime(daySchedule.getStartTime());
                        dOPD.setEndTime(daySchedule.getEndTime());
                        dOPD.setStatus(daySchedule.getStatus());
                        switch (daySchedule.getDay()){
                            case "Monday":
                                mondayOpdList.add(dOPD);
                                break;
                            case "Tuesday":
                                tuedayOpdList.add(dOPD);
                                break;
                            case "Wednesday":
                                wednesdayOpdList.add(dOPD);
                                break;
                            case "Thursday":
                                thursdayOpdList.add(dOPD);
                                break;
                            case "Friday":
                                fridayOpdList.add(dOPD);
                                break;
                            case "Saturday":
                                saturdayOpdList.add(dOPD);
                                break;
                            case "Sunday":
                                sundayOpdList.add(dOPD);
                                break;
                        }
                    }

                }
            }
        }

        for (int i = 0; i<appointmentList.size(); i++){
            switch (appointmentList.get(i).getDay()){
                case "Monday":
                    appointmentList.get(i).setDayOpdList(mondayOpdList);
                    break;
                case "Tuesday":
                    appointmentList.get(i).setDayOpdList(tuedayOpdList);
                    break;
                case "Wednesday":
                    appointmentList.get(i).setDayOpdList(wednesdayOpdList);
                    break;
                case "Thursday":
                    appointmentList.get(i).setDayOpdList(thursdayOpdList);
                    break;
                case "Friday":
                    appointmentList.get(i).setDayOpdList(fridayOpdList);
                    break;
                case "Saturday":
                    appointmentList.get(i).setDayOpdList(saturdayOpdList);
                    break;
                case "Sunday":
                    appointmentList.get(i).setDayOpdList(sundayOpdList);
                    break;
            }
        }

        Log.d("TAg", appointmentList.get(0).getDay());
        Log.d("TAg", appointmentList.get(0).getDay());
        LoadCalenderEvents(appointmentList);
    }

    private void LoadCalenderEvents(final List<AppointmentModel> myEvent) {
        /*eventTask.cancel(true);
        eventTask = new LoadCalenderEvents(myEvent, new LoadCalenderEvents.EventCreated() {
            @Override
            public void onEventsCreated(List<? extends List<? extends Event>> events) {

                comCal.removeAllEvents();
                *//*for (final List<? extends Event> event : events) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            comCal.addEvent((Event) event);
                        }
                    });
                }*//*
            }
        });
        eventTask.execute();*/
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.tvSelectDate :
                openDatePicker();
                break;
             case R.id.tvSelectTime :
                openTimePicker();
                break;
        }
    }

    private void openDatePicker() {

                int dobYear = Calendar.getInstance().get(Calendar.YEAR);
                int dobMonth = Calendar.getInstance().get(Calendar.MONTH);
                int dobDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(mContext, R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                    String sDay;
                    String sMonth;

                    if (day<=9){
                        sDay = "0"+day;
                    }else{
                        sDay = String.valueOf(day);
                    }
                    if ((month + 1)<=9){
                        sMonth = "0"+(month + 1);
                    }else{
                        sMonth = String.valueOf((month + 1));
                    }

                    String date = sDay + "/" + sMonth + "/" + year;

                    tvSelectDate.setText(sDay + "/" + sMonth + "/" + year);

                    showScheduleTime(date);

                }
            }, dobYear, dobMonth, dobDay);
            //dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dialog.setTitle("");
            dialog.show();
    }

    private void showScheduleTime(String date) {
        //dd/mm/yyyy
        Alerts.show(mContext, changeDateFormat(date));
    }

    private void openTimePicker() {
        Calendar mcurrentTime = Calendar.getInstance();
        int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
        int minute = mcurrentTime.get(Calendar.MINUTE);
        TimePickerDialog mTimePicker;
        mTimePicker = new TimePickerDialog(mContext, R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                String AM_PM ;
                String sMinute ;
                String sHour ;
                int hr = selectedHour;

                if(selectedHour < 12) {
                    AM_PM = "AM";
                    if (selectedHour==0){
                        hr=12;
                    }
                } else {
                    if (selectedHour==12) {
                        hr = 12;
                    }else{
                        hr = selectedHour-12;
                    }
                    AM_PM = "PM";
                }

                if (hr<=9){
                    sHour="0"+hr;
                }else {
                    sHour = String.valueOf(hr);
                }

                if (selectedMinute<=9){
                    sMinute="0"+selectedMinute;
                }else {
                    sMinute = String.valueOf(selectedMinute);
                }

                tvSelectTime.setText( sHour + ":" + sMinute +" "+AM_PM);
            }
        }, hour, minute, false);//Yes 24 hour time
        mTimePicker.setTitle("");
        mTimePicker.show();
    }

    public String changeDateFormat(String time) {
        String inputPattern = "dd/mm/yyyy";
        String outputPattern = "EEE, dd/mm/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern, Locale.ENGLISH);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern, Locale.ENGLISH);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
}
