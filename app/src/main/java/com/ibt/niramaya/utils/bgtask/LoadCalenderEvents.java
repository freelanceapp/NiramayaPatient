package com.ibt.niramaya.utils.bgtask;

import android.graphics.Color;
import android.os.AsyncTask;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.ibt.niramaya.modal.calander.AppointmentModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;


public final class LoadCalenderEvents extends AsyncTask {
   private SimpleDateFormat serverDateFormat;
   private final Calendar currentCalender;
   private final List mEvent;
   private final LoadCalenderEvents.EventCreated listener;

   @NotNull
   protected List doInBackground(@NotNull Void... params) {
      Intrinsics.checkParameterIsNotNull(params, "params");
      ArrayList list = new ArrayList();

      try {
         Iterator var4 = this.mEvent.iterator();

         while(var4.hasNext()) {
            AppointmentModel item = (AppointmentModel)var4.next();
            Date date = this.serverDateFormat.parse(item.getDate());
            int count = 1;

            try {
               count = item.getDayOpdList().size();
            } catch (Exception var10) {
               var10.printStackTrace();
            }

            Calendar var10000 = this.currentCalender;
            Intrinsics.checkExpressionValueIsNotNull(var10000, "currentCalender");
            var10000.setTime(date);
            var10000 = this.currentCalender;
            Intrinsics.checkExpressionValueIsNotNull(var10000, "currentCalender");
            long timeInMillis = var10000.getTimeInMillis();
            List events = this.getEvents(timeInMillis, count);
            list.add(events);
         }
      } catch (Exception var11) {
         var11.printStackTrace();
      }

      return (List)list;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object doInBackground(Object[] var1) {
      return this.doInBackground((Void[])var1);
   }

   private final List getEvents(long timeInMillis, int day) {
      List var10000;
      if (day < 2) {
         var10000 = Arrays.asList(new Event(Color.argb(255, 169, 68, 65), timeInMillis, "MonthEvent at " + new Date(timeInMillis)));
         Intrinsics.checkExpressionValueIsNotNull(var10000, "Arrays.asList(Event(Colo… \" + Date(timeInMillis)))");
      } else if (day == 2) {
         var10000 = Arrays.asList(new Event(Color.argb(255, 169, 68, 65), timeInMillis, "MonthEvent at " + new Date(timeInMillis)), new Event(Color.argb(255, 100, 68, 65), timeInMillis, "MonthEvent 2 at " + new Date(timeInMillis)));
         Intrinsics.checkExpressionValueIsNotNull(var10000, "Arrays.asList(\n         … \" + Date(timeInMillis)))");
      } else {
         var10000 = Arrays.asList(new Event(Color.argb(255, 169, 68, 65), timeInMillis, "MonthEvent at " + new Date(timeInMillis)), new Event(Color.argb(255, 100, 68, 65), timeInMillis, "MonthEvent 2 at " + new Date(timeInMillis)), new Event(Color.argb(255, 70, 68, 65), timeInMillis, "MonthEvent 3 at " + new Date(timeInMillis)));
         Intrinsics.checkExpressionValueIsNotNull(var10000, "Arrays.asList(\n         … \" + Date(timeInMillis)))");
      }

      return var10000;
   }

   protected void onPostExecute(@NotNull List result) {
      Intrinsics.checkParameterIsNotNull(result, "result");
      super.onPostExecute(result);
      this.listener.onEventsCreated(result);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void onPostExecute(Object var1) {
      this.onPostExecute((List)var1);
   }

   public LoadCalenderEvents(@NotNull List mEvent, @NotNull LoadCalenderEvents.EventCreated listener) {
      super();
      Intrinsics.checkParameterIsNotNull(mEvent, "mEvent");
      Intrinsics.checkParameterIsNotNull(listener, "listener");
      this.mEvent = mEvent;
      this.listener = listener;
      this.serverDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
      this.currentCalender = Calendar.getInstance(Locale.ENGLISH);
   }

   public interface EventCreated {
      void onEventsCreated(@NotNull List var1);
   }
}
