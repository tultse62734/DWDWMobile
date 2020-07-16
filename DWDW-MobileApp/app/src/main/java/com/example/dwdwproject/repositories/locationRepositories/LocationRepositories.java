package com.example.dwdwproject.repositories.locationRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.utils.CallBackData;

import java.util.List;

public interface LocationRepositories {
    void getAllLocationList(Context mContext,String token,CallBackData<List<LocationDTO>> mCallBackData);
    void getAllLocationById(Context mContext,String token,int lcationId,CallBackData<LocationDTO>callBackData);
    void createLocation(Context mContext,String token,LocationDTO mLocation,CallBackData<LocationDTO> callBackData);
    void updateLocation(Context mContext,String token,LocationDTO mLocation,CallBackData<LocationDTO> callBackData);
    void deactiveLocation(Context mContext, String token, int locationId, CallBackData<LocationDTO> callBackData);
    void getManagerLocationList(Context mContext,String token,CallBackData<List<LocationDTO>> mCallBackData);
}
