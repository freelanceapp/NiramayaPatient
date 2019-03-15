package com.ibt.niramaya.ui.activity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.ibt.niramaya.R;
import com.ibt.niramaya.utils.BaseActivity;

import java.util.Calendar;

public class BookAppointmentActivity extends BaseActivity implements View.OnClickListener {

    private TextView tvSelectDate, tvSelectTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        initViews();

    }

    private void initViews() {
        tvSelectDate = findViewById(R.id.tvSelectDate);
        tvSelectTime = findViewById(R.id.tvSelectTime);
        tvSelectDate.setOnClickListener(this);
        tvSelectTime.setOnClickListener(this);
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

                    tvSelectDate.setText(sDay + "/" + sMonth + "/" + year);

                }
            }, dobYear, dobMonth, dobDay);
            dialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
            dialog.setTitle("");
            dialog.show();
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
}
