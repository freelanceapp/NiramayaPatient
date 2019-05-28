package com.ibt.niramaya.ui.activity.invoice_data

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.*

import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import com.ibt.niramaya.R
import com.ibt.niramaya.adapter.AppointmentDateTimeListAdapter
import com.ibt.niramaya.adapter.AppointmentWeeklyTimeListAdapter
import com.ibt.niramaya.constant.Constant
import com.ibt.niramaya.interfaces.InitScheduleList
import com.ibt.niramaya.modal.calander.AppointmentModel
import com.ibt.niramaya.modal.calander.DateOPD
import com.ibt.niramaya.modal.calander.DayOPD
import com.ibt.niramaya.modal.doctor_opd.DoctorDatum
import com.ibt.niramaya.modal.doctor_opd.OpdList
import com.ibt.niramaya.modal.patient_modal.PaitentProfile
import com.ibt.niramaya.modal.patient_modal.PatientMainModal
import com.ibt.niramaya.retrofit.RetrofitService
import com.ibt.niramaya.retrofit.WebResponse
import com.ibt.niramaya.utils.Alerts
import com.ibt.niramaya.utils.AppPreference
import com.ibt.niramaya.utils.BaseActivity
import com.ibt.niramaya.utils.LoadCalenderEvents
import com.ibt.niramaya.utils.cal.FindDate
import kotlinx.android.synthetic.main.activity_book_appointment.*
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class BookAppointmentActivityKt : BaseActivity(), View.OnClickListener, InitScheduleList {

    private val dateFormatForMonth = SimpleDateFormat("MMM - yyyy", Locale.getDefault())
    private val dateFormatForCheckDiffrennce = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val dateFormatForServer = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
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
    private var availableAppointmentList = ArrayList<AppointmentModel>()
    private var patientList: MutableList<PaitentProfile> = ArrayList()

    private var cdForDifference = ""
    private var sdForDifference = ""

    private var eventTask: LoadCalenderEvents? = null

    private var comCal: CompactCalendarView? = null

    private var tvMonth: TextView? = null
    private val tvDate: TextView? = null
    private val tvNext: TextView? = null
    private val tvPrevious: TextView? = null
    private var ivPrevious: ImageView? = null
    private var ivNext: ImageView? = null

    // new string = oldSTring+replaceAll("changeble","")

    private var tvDrName: TextView? = null
    private var tvDrDesignation: TextView? = null
    private var tvDrAddress: TextView? = null
    private var tvSelectDate: TextView? = null
    private var tvSelectTime: TextView? = null
    private var doctorData: DoctorDatum? = null
    private val opdList = ArrayList<OpdList>()
    private var spnPatient: Spinner? = null

    private var selectedPatientId = ""
    private var selectedOpdId = ""
    private var selectedOpdPrice = ""
    private var opdBookingDate = ""
    private var selectedOpdDate = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)

        doctorData = intent.extras!!.getParcelable("DoctorData")

        initViews()

    }

    private fun initViews() {

        isValidDate()

        tvDrName = findViewById(R.id.tvDrName)
        tvDrDesignation = findViewById(R.id.tvDrDesignation)
        tvDrAddress = findViewById(R.id.tvDrAddress)
        spnPatient = findViewById(R.id.spnPatient)

        tvMonth = findViewById(R.id.tvMonth)
        ivPrevious = findViewById(R.id.ivPrevious)
        ivNext = findViewById(R.id.ivNext)

        initPatientSpinner()

        tvDrName!!.text = doctorData!!.name
        //tvDrDesignation.setText(doctorData.getName());

        tvSelectDate = findViewById(R.id.tvSelectDate)
        tvSelectTime = findViewById(R.id.tvSelectTime)
        tvSelectDate!!.setOnClickListener(this)
        tvSelectTime!!.setOnClickListener(this)
        btnBookNow!!.setOnClickListener(this)

        initCalenderViews()


    }

    private fun initPatientSpinner() {
        if (cd.isNetworkAvailable) {
            val strUserId = AppPreference.getStringPreference(mContext, Constant.USER_ID)
            RetrofitService.getPatientList(Dialog(mContext), retrofitApiClient.patientList(strUserId), object : WebResponse {
                override fun onResponseSuccess(result: Response<*>) {
                    val mainModal = result.body() as PatientMainModal?
                    if (mainModal != null) {
                        patientList = mainModal.user.paitentProfile

                        if (patientList.size > 0) {
                            spnPatient?.setVisibility(View.VISIBLE)
                        }

                        val paitentProfile1 = PaitentProfile()
                        paitentProfile1.patientId = "0"
                        paitentProfile1.patientName = "Select Patient"
                        patientList.add(0, paitentProfile1)

                        val aa = ArrayAdapter(mContext, R.layout.row_light_spinner_item, patientList)
                        //aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        spnPatient?.setAdapter(aa)
                        spnPatient?.setOnItemSelectedListener(spinnerListener)
                    } else {
                        Alerts.show(mContext, mainModal!!.message)
                    }
                }

                override fun onResponseFailed(error: String) {
                    Alerts.show(mContext, error)
                }
            })
        }
    }

    internal var spinnerListener: AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            if (position>0) {
                selectedPatientId =  patientList[position].patientId
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>) {

        }
    }


    private fun initCalenderViews() {
        comCal = findViewById(R.id.calView)
        comCal!!.setUseThreeLetterAbbreviation(true)
        var monthName = ""

        try {

            cdForDifference = dateFormatForCheckDiffrennce.format(Calendar.getInstance().time)

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
                sdForDifference = dateFormatForCheckDiffrennce.format(dateClicked)
                selectedOpdDate = dateFormatForServer.format(dateClicked)
                validateClickedDate(dateClicked)
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

    private fun refreshAppointmentRecycler(selectedDayOpdList: ArrayList<DayOPD>, selectedDateOpdList: ArrayList<DateOPD>) {
        tvWeaklyAppointment.visibility = View.VISIBLE
        tvDateAppointment.visibility = View.VISIBLE
        if (selectedDayOpdList.size>0) {
            rvWeekly.visibility = View.VISIBLE
            tvRecyclerWeeklyMessage.visibility = View.GONE
            val wAdapter = AppointmentWeeklyTimeListAdapter(selectedDayOpdList, this@BookAppointmentActivityKt, this@BookAppointmentActivityKt)
            rvWeekly.layoutManager = LinearLayoutManager(this@BookAppointmentActivityKt, LinearLayoutManager.HORIZONTAL, true)
            rvWeekly.adapter = wAdapter
            wAdapter.notifyDataSetChanged()
        } else {
            rvWeekly.visibility = View.GONE
            tvRecyclerWeeklyMessage.visibility = View.VISIBLE
            tvRecyclerWeeklyMessage.text = "No Appointment Available!"
        }

        if (selectedDateOpdList.size>0) {
            rvDate.visibility = View.VISIBLE
            tvRecyclerDateMessage.visibility = View.GONE
            val dAdapter = AppointmentDateTimeListAdapter(selectedDateOpdList, this@BookAppointmentActivityKt, this@BookAppointmentActivityKt)
            rvDate.layoutManager = LinearLayoutManager(this@BookAppointmentActivityKt, LinearLayoutManager.HORIZONTAL, true)
            rvDate.adapter = dAdapter
            dAdapter.notifyDataSetChanged()
        } else {
            rvDate.visibility = View.GONE
            tvRecyclerDateMessage.visibility = View.VISIBLE
            tvRecyclerDateMessage.text = "No Appointment Available!"
        }

        /*if (day == 1) {
                tvWeaklyAppointment.visibility = View.VISIBLE
                rvWeekly.visibility = View.VISIBLE
            } else {
                tvWeaklyAppointment.visibility = View.GONE
                rvWeekly.visibility = View.GONE
            }
            if (date == 1) {
                tvDateAppointment.visibility = View.VISIBLE
                rvDate.visibility = View.VISIBLE
            } else {
                tvDateAppointment.visibility = View.GONE
                rvDate.visibility = View.GONE
            }*/
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
        val dateOpdList = ArrayList<DateOPD>()
        if (doctorData!!.opdList.size > 0) {
            for (myOpd in doctorData!!.opdList) {
                var opdStartDate = changeDateFormatFromServer(myOpd.startDate)
                var opdEndDate =changeDateFormatFromServer(myOpd.endDate)
                if (myOpd.scheduleType == "0") {
                    for (daySchedule in myOpd.schedule) {
                        val dOPD = DayOPD()
                        dOPD.type = "0"
                        dOPD.scheduleId = myOpd.scheduleId
                        dOPD.opdStartDate = opdStartDate
                        dOPD.opdEndDate = opdEndDate
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

                } else if (myOpd.scheduleType == "1" && myOpd.schedule.size > 0) {
                    for (daySchedule in myOpd.schedule) {
                        val dateOPD = DateOPD()
                        dateOPD.type = "1"
                        dateOPD.scheduleId = myOpd.scheduleId
                        dateOPD.opdStartDate = opdStartDate
                        dateOPD.opdEndDate = opdEndDate
                        dateOPD.date = daySchedule.date
                        dateOPD.startTime = daySchedule.startTime
                        dateOPD.endTime = daySchedule.endTime
                        dateOPD.status = daySchedule.status
                        dateOpdList.add(dateOPD)
                    }
                }
            }
        }

        for (i in appointmentList.indices) {
            when (appointmentList[i].day) {
                "Monday" -> {
                    appointmentList[i].dayOpdList = mondayOpdList
                }
                "Tuesday" -> {
                    appointmentList[i].dayOpdList = tuesdayOpdList
                }
                "Wednesday" -> {
                    appointmentList[i].dayOpdList = wednesdayOpdList
                }
                "Thursday" -> {
                    appointmentList[i].dayOpdList = thursdayOpdList
                }
                "Friday" -> {
                    appointmentList[i].dayOpdList = fridayOpdList
                }
                "Saturday" -> {
                    appointmentList[i].dayOpdList = saturdayOpdList
                }
                "Sunday" -> {
                    appointmentList[i].dayOpdList = sundayOpdList
                }
            }

            for (daOpd in dateOpdList) {
                if (appointmentList[i].date == daOpd.date) {
                    appointmentList[i].dateOpdList.add(daOpd)
                }
            }

        }

        /*for (i in appointmentList.indices) {
            for (daOpd in dateOpdList){
                if (appointmentList[i].date == daOpd.date){
                    appointmentList[i].dateOpdList.add(daOpd)
                }
            }
        }*/

        /*Log.d("TAg", appointmentList[0].day)
        Log.d("TAg", appointmentList[0].day)*/

        availableAppointmentList.clear()

        appointmentList.forEach {
            if (it.dateOpdList.size > 0 || it.dayOpdList.size > 0) {
                availableAppointmentList.add(it)
            }
        }
        if (availableAppointmentList.size > 0) {
            LoadCalenderEvents(availableAppointmentList)
            var day = 0
            var date = 0
            for (i in availableAppointmentList.indices) {
                if (availableAppointmentList[i].dayOpdList.size > 0) {
                    day = 1
                }
                if (availableAppointmentList[i].dateOpdList.size > 0) {
                    date = 1
                }
            }
            /*if (day == 1) {
                tvWeaklyAppointment.visibility = View.VISIBLE
                rvWeekly.visibility = View.VISIBLE
            } else {
                tvWeaklyAppointment.visibility = View.GONE
                rvWeekly.visibility = View.GONE
            }
            if (date == 1) {
                tvDateAppointment.visibility = View.VISIBLE
                rvDate.visibility = View.VISIBLE
            } else {
                tvDateAppointment.visibility = View.GONE
                rvDate.visibility = View.GONE
            }*/
            if (day == 0 && date == 0) {
                llAppointment.visibility = View.GONE
            } else {
                llAppointment.visibility = View.VISIBLE
            }
        }
        availableAppointmentList.forEach {
            if (it.date.equals(cdForDifference)) {
                refreshAppointmentRecycler(it.dayOpdList, it.dateOpdList)
            }else{
                rvWeekly.visibility = View.GONE
                tvRecyclerWeeklyMessage.visibility = View.VISIBLE
                tvRecyclerWeeklyMessage.text = "No Appointment Available!"
                rvDate.visibility = View.GONE
                tvRecyclerDateMessage.visibility = View.VISIBLE
                tvRecyclerDateMessage.text = "No Appointment Available!"
            }
        }
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
            R.id.btnBookNow -> createAppointment()
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

    private fun validateClickedDate(dateClicked: Date) {
        if (isDateAfter(sdForDifference, cdForDifference)) {
            tvSelectedDate.text = dateFormatForSelectedDay.format(dateClicked)
            availableAppointmentList.forEach {
                if (it.date.equals(sdForDifference)) {
                    refreshAppointmentRecycler(it.dayOpdList, it.dateOpdList)
                }else{
                    rvWeekly.visibility = View.GONE
                    tvRecyclerWeeklyMessage.visibility = View.VISIBLE
                    tvRecyclerWeeklyMessage.text = "No Appointment Available!"
                    rvDate.visibility = View.GONE
                    tvRecyclerDateMessage.visibility = View.VISIBLE
                    tvRecyclerDateMessage.text = "No Appointment Available!"
                }
            }
        } else {
            Alerts.show(mContext, "You can't book an OPD to Old date.")
        }
    }

    private fun isDateAfter(str1: String, str2: String): Boolean {
        var isAfter = false
        try {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
            val date1 = formatter.parse(str1)
            val date2 = formatter.parse(str2)
            if (date1.compareTo(date2) >= 0) {
                /*System.out.println("date2 is Greater than my date1");*/
                isAfter = true
            }
        } catch (e1: ParseException) {
            e1.printStackTrace()
        }

        return isAfter
    }

    override fun initScheduleList(i: Int, type: String?, dOPD: DayOPD?, dateOPD: DateOPD?) {
        when (type) {
            "Date" -> {
                Alerts.show(mContext, "Week")
                for (i in doctorData!!.opdList.indices) {
                    val opdL = doctorData!!.opdList[i]
                    if (opdL.scheduleId.equals(dateOPD?.scheduleId)) {
                        tvServicePrice.text = "₹ ${opdL.amount}"
                        selectedOpdPrice = opdL.amount
                        selectedOpdId = opdL.scheduleId
                    }
                }
            }
            "Week" -> {
                for (i in doctorData!!.opdList.indices) {
                    val opdL = doctorData!!.opdList[i]
                    if (opdL.scheduleId.equals(dOPD?.scheduleId)) {
                        tvServicePrice.text = "₹ ${opdL.amount}"
                        selectedOpdPrice = opdL.amount
                        selectedOpdId = opdL.scheduleId
                    }
                }
            }
        }
    }

    private fun createAppointment() {
        val userId = AppPreference.getStringPreference(mContext, Constant.USER_ID)
        val referredDoctorName = tvDoctorName.text.toString()
        when {
            selectedPatientId.isEmpty() -> Alerts.show(mContext, "No Patient Selected!")

            selectedOpdId.isEmpty() -> Alerts.show(mContext, "No OPD Selected!")

            selectedOpdDate.isEmpty() -> Alerts.show(mContext, "No Date Selected!")

            cd.isNetworkAvailable -> {
                RetrofitService.getServerResponse(Dialog(mContext), retrofitApiClient.bookPatientApponitment(
                        selectedPatientId, userId, selectedOpdId, "1", "1", "1", selectedOpdPrice,
                        selectedOpdDate, "0", referredDoctorName), object : WebResponse {
                    override fun onResponseSuccess(result: Response<*>?) {
                        val response = result!!.body() as ResponseBody
                        val jsonObject = JSONObject(response.string())
                        if (!jsonObject.getBoolean("error")) {
                            Alerts.show(mContext, jsonObject.getString("message"))
                        }
                    }

                    override fun onResponseFailed(error: String?) {
                        Log.v("TAG", error)
                    }
                })
            }
        }
    }

    private fun isValidDate(): Boolean{
        var status = false
        try {
            val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val str1 = "10/10/2015"
            val date1 = formatter.parse(str1)

            val str2 = "10/10/2015"
            val date2 = formatter.parse(str2)

            status = date1.compareTo(date2) <= 0
            /*status = if (date1.compareTo(date2) <= 0) {
                true //Date 2 is Greater or Equal top date 1
            }else{
                false //Date 2 is Greater or Equal top date 1
            }*/

        } catch (e1:ParseException) {
            e1.printStackTrace()
        }
        return status
    }

    fun changeDateFormatFromServer(serverDate: String): String? {
        val inputPattern = "yyyy-MM-dd HH:mm:ss"
        val outputPattern = "dd/mm/yyyy"
        val inputFormat = SimpleDateFormat(inputPattern, Locale.ENGLISH)
        val outputFormat = SimpleDateFormat(outputPattern, Locale.ENGLISH)

        var date: Date? = null
        var str: String? = null

        try {
            date = inputFormat.parse(serverDate)
            str = outputFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        return str
    }


}

