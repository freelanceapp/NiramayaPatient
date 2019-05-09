package com.ibt.niramaya.ui.activity.invoice_data

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import com.ibt.niramaya.R
import com.ibt.niramaya.modal.calander.AppointmentModel
import com.ibt.niramaya.modal.calander.DayOPD
import com.ibt.niramaya.modal.doctor_opd.DoctorDatum
import com.ibt.niramaya.modal.doctor_opd.OpdList
import com.ibt.niramaya.utils.Alerts
import com.ibt.niramaya.utils.BaseActivity
import com.ibt.niramaya.utils.LoadCalenderEvents
import com.ibt.niramaya.utils.cal.FindDate
import kotlinx.android.synthetic.main.activity_book_appointment.*

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Calendar
import java.util.Date
import java.util.Locale

class BookAppointmentActivitykt : BaseActivity(), View.OnClickListener {

    private val dateFormatForMonth = SimpleDateFormat("MMM - yyyy", Locale.getDefault())
    private val dateFormatForSelectedMonth = SimpleDateFormat("MMM-yyyy", Locale.getDefault())
    private val dateFormatForSelectedDay = SimpleDateFormat("EEE dd-MMM-yyyy", Locale.getDefault())
    private val dateFormatWithDay = SimpleDateFormat("EEEE, MMMM dd", Locale.getDefault())
    private val dateFormatMonth = SimpleDateFormat("MMMM", Locale.getDefault())
    private val dateFormatMonthN = SimpleDateFormat("M", Locale.getDefault())
    private val dateFormatMonthString = SimpleDateFormat("MMM", Locale.getDefault())
    private val dateFormatYear = SimpleDateFormat("yyyy", Locale.getDefault())
    private var selectedYear = 0
    private var selectedMonth = 0
    private var currentYear = 0
    private var currentMonth = 0
    private var isPrevious = false
    private var appointmentList = ArrayList<AppointmentModel>()

    private var eventTask: LoadCalenderEvents? = null

    private var comCal: CompactCalendarView? = null

    private var tvMonth: TextView? = null
    private val tvDate: TextView? = null
    private val tvNext: TextView? = null
    private val tvPrevious: TextView? = null
    private var ivPrevious: ImageView? = null
    private var ivNext: ImageView? = null

    private var tvDrName: TextView? = null
    private var tvDrDesignation: TextView? = null
    private var tvDrAddress: TextView? = null
    private var tvSelectDate: TextView? = null
    private var tvSelectTime: TextView? = null
    private var doctorData: DoctorDatum? = null
    private val opdList = ArrayList<OpdList>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)

        doctorData = intent.extras!!.getParcelable("DoctorData")

        initViews()

    }

    private fun initViews() {

        tvDrName = findViewById(R.id.tvDrName)
        tvDrDesignation = findViewById(R.id.tvDrDesignation)
        tvDrAddress = findViewById(R.id.tvDrAddress)

        tvMonth = findViewById(R.id.tvMonth)
        ivPrevious = findViewById(R.id.ivPrevious)
        ivNext = findViewById(R.id.ivNext)

        tvDrName!!.text = doctorData!!.name
        //tvDrDesignation.setText(doctorData.getName());

        tvSelectDate = findViewById(R.id.tvSelectDate)
        tvSelectTime = findViewById(R.id.tvSelectTime)
        tvSelectDate!!.setOnClickListener(this)
        tvSelectTime!!.setOnClickListener(this)

        initCalenderViews()


    }

    private fun initCalenderViews() {
        comCal = findViewById(R.id.calView)
        comCal!!.setUseThreeLetterAbbreviation(true)
        var monthName = ""

        try {
            currentMonth = Integer.parseInt(dateFormatMonthN.format(Calendar.getInstance().time))
            monthName = dateFormatMonthString.format(Calendar.getInstance().time)
            currentYear = Integer.parseInt(dateFormatYear.format(Calendar.getInstance().time))
            tvSelectedDate.text = dateFormatForSelectedDay.format(Calendar.getInstance().time)
            loadCalendarEvents(monthName, currentYear)
        } catch (e: NumberFormatException) {
            e.printStackTrace()
        }

        if (!isPrevious) {
            ivPrevious!!.visibility = View.GONE
        } else {
            ivNext!!.visibility = View.VISIBLE
        }

        /*tvYear.setText(dateFormatYear.format(Calendar.getInstance().getTime()));*/
        tvMonth!!.text = dateFormatMonth.format(Calendar.getInstance().time)
        /*tvDate.setText(dateFormatWithDay.format(Calendar.getInstance().getTime()));*/

        comCal!!.shouldScrollMonth(false)

        comCal!!.setListener(object : CompactCalendarView.CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                // tvDate.setText(dateFormatWithDay.format(dateClicked));
                tvSelectedDate.text = dateFormatForSelectedDay.format(dateClicked)
                //Alerts.show(mContext, dateFormatWithDay.format(dateClicked))
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date) {
                //tvYear.setText(dateFormatYear.format(firstDayOfNewMonth));
                tvMonth!!.text = dateFormatMonth.format(firstDayOfNewMonth)
                try {
                    selectedMonth = Integer.parseInt(dateFormatMonthN.format(firstDayOfNewMonth))
                    val monthName1 = dateFormatMonthString.format(firstDayOfNewMonth)
                    selectedYear = Integer.parseInt(dateFormatYear.format(firstDayOfNewMonth))
                    //Alerts.show(mContext, monthName1)
                    loadCalendarEvents(monthName1, selectedYear)
                } catch (e: NumberFormatException) {
                    e.printStackTrace()
                }

                if (selectedYear == currentYear) {
                    if (selectedMonth > currentMonth) {
                        isPrevious = true
                        ivPrevious!!.visibility = View.VISIBLE
                        ivNext!!.visibility = View.GONE
                    } else {
                        isPrevious = false
                        ivPrevious!!.visibility = View.GONE
                        ivNext!!.visibility = View.VISIBLE
                    }
                } else if (selectedYear > currentYear) {
                    isPrevious = true
                    tvPrevious!!.visibility = View.VISIBLE
                    ivNext!!.visibility = View.GONE
                } else {
                    isPrevious = false
                    tvPrevious!!.visibility = View.GONE
                    ivNext!!.visibility = View.VISIBLE
                }

            }
        })

        ivNext!!.setOnClickListener { comCal!!.scrollRight() }

        ivPrevious!!.setOnClickListener { comCal!!.scrollLeft() }

    }

    private fun loadCalendarEvents(monthName: String, year: Int) {
        appointmentList = FindDate.getDate(monthName, year, null)
        val mondayOpdList = ArrayList<DayOPD>()
        val tuesdayOpdList = ArrayList<DayOPD>()
        val wednesdayOpdList = ArrayList<DayOPD>()
        val thursdayOpdList = ArrayList<DayOPD>()
        val fridayOpdList = ArrayList<DayOPD>()
        val saturdayOpdList = ArrayList<DayOPD>()
        val sundayOpdList = ArrayList<DayOPD>()
        if (doctorData!!.opdList.size > 0) {
            for (myOpd in doctorData!!.opdList) {
                if (myOpd.scheduleType == "1") {
                    for (daySchedule in myOpd.schedule) {
                        val dOPD = DayOPD()
                        dOPD.type = "1"
                        dOPD.scheduleId = myOpd.scheduleId
                        dOPD.startTime = daySchedule.startTime
                        dOPD.endTime = daySchedule.endTime
                        dOPD.status = daySchedule.status
                        when (daySchedule.day) {
                            "Monday" -> mondayOpdList.add(dOPD)
                            "Tuesday" -> tuesdayOpdList.add(dOPD)
                            "Wednesday" -> wednesdayOpdList.add(dOPD)
                            "Thursday" -> thursdayOpdList.add(dOPD)
                            "Friday" -> fridayOpdList.add(dOPD)
                            "Saturday" -> saturdayOpdList.add(dOPD)
                            "Sunday" -> sundayOpdList.add(dOPD)
                        }
                    }

                }else if (myOpd.scheduleType == "0" && myOpd.schedule.size>0){
                    for (daySchedule in myOpd.schedule) {
                        val dOPD = DayOPD()
                        dOPD.type = "0"
                        dOPD.scheduleId = myOpd.scheduleId
                        dOPD.startTime = daySchedule.startTime
                        dOPD.endTime = daySchedule.endTime
                        dOPD.status = daySchedule.status
                    }
                }
            }
        }

        for (i in appointmentList.indices) {
            when (appointmentList[i].day) {
                "Monday" -> appointmentList[i].dayOpdList = mondayOpdList
                "Tuesday" -> appointmentList[i].dayOpdList = tuesdayOpdList
                "Wednesday" -> appointmentList[i].dayOpdList = wednesdayOpdList
                "Thursday" -> appointmentList[i].dayOpdList = thursdayOpdList
                "Friday" -> appointmentList[i].dayOpdList = fridayOpdList
                "Saturday" -> appointmentList[i].dayOpdList = saturdayOpdList
                "Sunday" -> appointmentList[i].dayOpdList = sundayOpdList
            }
        }

        /*Log.d("TAg", appointmentList[0].day)
        Log.d("TAg", appointmentList[0].day)*/
        LoadCalenderEvents(appointmentList)
    }

    private fun LoadCalenderEvents(myEvent: List<AppointmentModel>) {
        eventTask?.cancel(true)
        eventTask = LoadCalenderEvents(myEvent, object : LoadCalenderEvents.EventCreated {
            override fun onEventsCreated(events: List<List<Event>>) {

                comCal!!.removeAllEvents()
                for (event in events) {
                    runOnUiThread {
                        comCal!!.addEvents(event)
                    }
                }
            }
        })
        eventTask!!.execute()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tvSelectDate -> openDatePicker()
            R.id.tvSelectTime -> openTimePicker()
        }
    }

    private fun openDatePicker() {

        val dobYear = Calendar.getInstance().get(Calendar.YEAR)
        val dobMonth = Calendar.getInstance().get(Calendar.MONTH)
        val dobDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(mContext, R.style.DialogTheme, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            val sDay: String
            val sMonth: String

            if (day <= 9) {
                sDay = "0$day"
            } else {
                sDay = day.toString()
            }
            if (month + 1 <= 9) {
                sMonth = "0" + (month + 1)
            } else {
                sMonth = (month + 1).toString()
            }

            val date = "$sDay/$sMonth/$year"

            tvSelectDate!!.text = "$sDay/$sMonth/$year"

            showScheduleTime(date)
        }, dobYear, dobMonth, dobDay)
        //dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dialog.setTitle("")
        dialog.show()
    }

    private fun showScheduleTime(date: String) {
        //dd/mm/yyyy
        Alerts.show(mContext, changeDateFormat(date))
    }

    private fun openTimePicker() {
        val mcurrentTime = Calendar.getInstance()
        val hour = mcurrentTime.get(Calendar.HOUR_OF_DAY)
        val minute = mcurrentTime.get(Calendar.MINUTE)
        val mTimePicker: TimePickerDialog
        mTimePicker = TimePickerDialog(mContext, R.style.DialogTheme, TimePickerDialog.OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
            val AM_PM: String
            val sMinute: String
            val sHour: String
            var hr = selectedHour

            if (selectedHour < 12) {
                AM_PM = "AM"
                if (selectedHour == 0) {
                    hr = 12
                }
            } else {
                if (selectedHour == 12) {
                    hr = 12
                } else {
                    hr = selectedHour - 12
                }
                AM_PM = "PM"
            }

            if (hr <= 9) {
                sHour = "0$hr"
            } else {
                sHour = hr.toString()
            }

            if (selectedMinute <= 9) {
                sMinute = "0$selectedMinute"
            } else {
                sMinute = selectedMinute.toString()
            }

            tvSelectTime!!.text = "$sHour:$sMinute $AM_PM"
        }, hour, minute, false)//Yes 24 hour time
        mTimePicker.setTitle("")
        mTimePicker.show()
    }

    fun changeDateFormat(time: String): String? {
        val inputPattern = "dd/mm/yyyy"
        val outputPattern = "EEE, dd/mm/yyyy"
        val inputFormat = SimpleDateFormat(inputPattern, Locale.ENGLISH)
        val outputFormat = SimpleDateFormat(outputPattern, Locale.ENGLISH)

        var date: Date? = null
        var str: String? = null

        try {
            date = inputFormat.parse(time)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return str
    }
}
