package com.example.dwdwproject.utils;

import android.graphics.Color;

import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class EventUtils {
    public static Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    public static List<Event> getEvents(long timeInMillis, int day) {
        if (day < 2) {
            return Arrays.asList(new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event at " + new Date(timeInMillis)));
        } else if ( day > 2 && day <= 4) {
            return Arrays.asList(
                    new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event at " + new Date(timeInMillis)),
                    new Event(Color.argb(255, 100, 68, 65), timeInMillis, "Event 2 at " + new Date(timeInMillis)));
        } else {
            return Arrays.asList(
                    new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event at " + new Date(timeInMillis) ),
                    new Event(Color.argb(255, 100, 68, 65), timeInMillis, "Event 2 at " + new Date(timeInMillis)),
                    new Event(Color.argb(255, 70, 68, 65), timeInMillis, "Event 3 at " + new Date(timeInMillis)));
        }
    }
    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
}
