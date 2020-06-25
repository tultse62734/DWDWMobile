package com.example.dwdwproject.repositories.devicesRepositories;

import android.content.Context;

import com.example.dwdwproject.models.Device;
import com.example.dwdwproject.utils.CallBackData;

import java.util.List;
public interface DeviceRepositories {
    void getAllDeviceList(Context mContext, CallBackData<List<Device>>mCallBackData);
    void getAllDeviceById(Context mContext,int deviceId,CallBackData<Device>callBackData);
    void createDevice(Context mContext,Device mDevice,CallBackData<String> callBackData);
    void updateDevice(Context mContext,int deviceId,Device mDevice,CallBackData<String> callBackData);
    void getAllDeviceFromLocation(Context mContext,int locationId,CallBackData<List<Device>>mCallBackData);
}
