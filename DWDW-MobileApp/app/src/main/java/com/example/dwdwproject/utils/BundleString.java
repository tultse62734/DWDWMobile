package com.example.dwdwproject.utils;

import android.content.Context;

public class BundleString {
    public static final String LOCATION_INFO = "LOCATION_INFO";
    public static final String FILTER_DATE_IS_SELECTE = "FILTER_DATE_IS_SELECTE";

    public static String getSelectedDate(Context context){
        return SharePreferenceUtils.getStringSharedPreference(context, BundleString.FILTER_DATE_IS_SELECTE);
    }

}
