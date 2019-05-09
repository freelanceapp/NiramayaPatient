package com.ibt.niramaya.utils

import android.graphics.Color
import android.os.AsyncTask
import com.github.sundeepk.compactcalendarview.domain.Event
import com.ibt.niramaya.modal.calander.AppointmentModel
import java.text.SimpleDateFormat
import java.util.*


class LoadCalenderEvents(private val mEvent: List<AppointmentModel>, private val listener: EventCreated)
    : AsyncTask<Void, Void, List<List<Event>>>() {

    private var serverDateFormat: SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    private val currentCalender = Calendar.getInstance(Locale.ENGLISH)

    interface EventCreated {
        fun onEventsCreated(events: List<List<Event>>)
    }

    override fun doInBackground(vararg params: Void?): List<List<Event>> {
        val list = ArrayList<List<Event>>()
        try {
            for (item in mEvent) {
                val date = serverDateFormat.parse(item.date)
                var count = 1
                try {
                    count = item.dayOpdList.size+item.dateOpdList.size
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                currentCalender.time = date
                val timeInMillis = currentCalender.timeInMillis
                val events = getEvents(timeInMillis, count)
                list.add(events)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return list
    }

    private fun getEvents(timeInMillis: Long, day: Int): List<Event> {
        return when {
            day < 2 -> Arrays.asList(Event(Color.argb(255, 0, 255, 0), timeInMillis, "MonthEvent at " + Date(timeInMillis)))
            day == 2 -> Arrays.asList(
                    Event(Color.argb(255, 0, 255, 0), timeInMillis, "MonthEvent at " + Date(timeInMillis)),
                    Event(Color.argb(255, 93, 164, 42), timeInMillis, "MonthEvent 2 at " + Date(timeInMillis)))
            else -> Arrays.asList(
                    Event(Color.argb(255, 0, 255, 0), timeInMillis, "MonthEvent at " + Date(timeInMillis)),
                    Event(Color.argb(255, 93, 212, 42), timeInMillis, "MonthEvent 2 at " + Date(timeInMillis)),
                    Event(Color.argb(255, 93, 164, 42), timeInMillis, "MonthEvent 3 at " + Date(timeInMillis)))
        }
    }


    override fun onPostExecute(result: List<List<Event>>) {
        super.onPostExecute(result)
        listener.onEventsCreated(result)
    }

}