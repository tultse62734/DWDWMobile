package com.example.dwdwproject.utils;

import android.content.Context;
public class BundleString {
    public static final String LOCATION_INFO = "LOCATION_INFO";
    public static final String FILTER_DATE_IS_SELECTE = "FILTER_DATE_IS_SELECTE";
    public static final String LOCATIONID = "LOCATIONID";
    public static final String LOCATIONNAME = "LOCATIONNAME";
    public static final String ACCOUNT = "ACCOUNT";
    public static final String ROOMDETAIL = "ROOMDETAIL";
    public static final String DEVICEDETAIL = "DEVICEDETAIL";
    public static final String LOCATIONDETAIL = "LOCATIONDETAIL";
    public static final String RECORDDETAIL = "RECORDDETAIL";
    public static final String MANAGERDETAIL = "MANAGERDETAIL";
    public static final String SHIFTDETAIL = "SHIFTDETAIL";
    public static final String TOKEN = "TOKEN";
    public static final String DEVICEASSGINT = "DEVICEASSGINT";
    public static final String INTENTHOME = "INTENTHOME";
    public static final String USERASSIGN = "USERASSIGN";
    public static String getSelectedDate(Context context){
        return SharePreferenceUtils.getStringSharedPreference(context, BundleString.FILTER_DATE_IS_SELECTE);
    }


}
