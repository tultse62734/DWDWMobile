package com.example.dwdwproject.repositories.locationRepositories;

import android.content.Context;

import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.utils.CallBackData;

import java.util.List;

public interface LocationRepositories {
    void getAllLocationList(Context mContext, CallBackData<List<Location>> mCallBackData);
    void getAllLocationById(Context mContext,int lcationId,CallBackData<Location>callBackData);
    void createLocation(Context mContext,Location mLocation,CallBackData<String> callBackData);
    void updateLocation(Context mContext,int locationId,Location mLocation,CallBackData<String> callBackData);
}
