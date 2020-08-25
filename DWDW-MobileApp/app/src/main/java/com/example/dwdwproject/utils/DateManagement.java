package com.example.dwdwproject.utils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateManagement {
    public static String convertDayOfWeek(int dayOfWeek) {
        String convert = "";
        switch (dayOfWeek) {
            case 1:
                convert = "Chủ Nhật";
                break;
            case 2:
                convert = "Monday";
                break;
            case 3:
                convert = "Tuesday";
                break;
            case 4:
                convert = "Wednesday ";
                break;
            case 5:
                convert = "Thursday";
                break;
            case 6:
                convert = "Friday";
                break;
            case 7:
                convert = "Saturday";
                break;
        }
        return convert;
    }
    public static String fortmatIntToDate(int date) {
        String day = "";
        if (date < 10) {
            day = "0" + String.valueOf(date);
        } else {
            day = String.valueOf(date);
        }
        return day;
    }
    public static String changeDateStringToString(String input){
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a");
        Date date = null;
        try {
            date = parser.parse(input);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = formatter.format(date);
        return formattedDate;
    }
    public static Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return cal.getTime();
    }
    public static String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(yesterday());
    }
    public static String getTodayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(today());
    }
    public static Date toDate(String value) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
    }

    public static boolean isDateAfter(String startDate, String endDate)
    {
        try
        {
            Date date1 = toDate(endDate+" 00:00:00");
            Date startingDate = toDate(startDate+"  00:00:00");
            if (date1.after(startingDate))
                return true;
            else
                return false;
        }
        catch (Exception e)
        {

            return false;
        }
    }
    public static Date today() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        return cal.getTime();
    }
    public static Date getStartPreviosMonth() {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }
    public static Date getEndPreviosMonth() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        int dayOfPreviosMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DATE, dayOfPreviosMonth);
        return cal.getTime();
    }
    public static String getStartPreviosMonthDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(getStartPreviosMonth());
    }
    public static String getEndPreviosMonthDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(getEndPreviosMonth());
    }
    public static String getStartThisMonthDateString() {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);
        cal.add(Calendar.MONTH, 0);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(cal.getTime()).toString();
    }
    public static String getEndThisMonthDateString() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 0);
        int dayOfPreviosMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        int dayNow = cal.get(Calendar.DATE);
        if (dayOfPreviosMonth >= dayNow) {
            dayOfPreviosMonth = dayNow-1;
        }
        cal.set(Calendar.DATE, dayOfPreviosMonth);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(cal.getTime()).toString();
    }
    public static String getToday() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String dayWeek = convertDayOfWeek(dayOfWeek);
        String today = dayWeek + ", " + getTodayDateString();
        return today;
    }
    public static String getToday1() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String dayWeek = convertDayOfWeek(dayOfWeek);
        String today = getTodayDateString();
        return today;
    }
    public static String getYesterDay() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 8;
        }
        String dayWeek = convertDayOfWeek(dayOfWeek);

        String today = dayWeek + ", " + getYesterdayDateString();
        return today;
    }

    public static Date getStartPreviosWeek() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_YEAR, -1);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        return cal.getTime();
    }
    public static Date getEndPreviosWeek() {
        final Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }
    public static String getStartPreviosWeekDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(getStartPreviosWeek());
    }
    public static String getEndPreviosWeekDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(getEndPreviosWeek());
    }
    public static Date getStartThisWeek() {
        final Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_YEAR, 0);
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        return cal.getTime();
    }
    public static String changeFormatDate(String date){
        String resutl = null;
        SimpleDateFormat sTo = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sTo1 = new SimpleDateFormat("yyyy/MM/dd");
        try {
             resutl=  sTo.format(sTo1.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return resutl;
    }

    public static String changeFormatDate1(String date){
        String resutl = null;
        if(date!=null) {

            SimpleDateFormat sTo = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sTo1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            try {
                resutl = sTo.format(sTo1.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }else
            resutl = "No Assign";

        return resutl;
        }

    public static String getStartThisWeekDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(getStartThisWeek());
    }
    public static String getEndThisWeekDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final Calendar cal = Calendar.getInstance();
        int endDay = cal.get(Calendar.DATE-1);
        int endOfThisWeek = cal.getFirstDayOfWeek();
        if(endDay<=endOfThisWeek){
            return dateFormat.format(cal.getTime());
        }else {
            return dateFormat.format(yesterday());
        }
    }
    public static String formatXAxisCombineChart(String time){
        String timeFormat="";
        String tmp[] = time.split("/");
        timeFormat=tmp[0]+"/"+tmp[1];

        return timeFormat;
    }

}
