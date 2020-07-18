package com.example.dwdwproject.presenters.devicesPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.repositories.devicesRepositories.DeviceRepositories;
import com.example.dwdwproject.repositories.devicesRepositories.DeviceRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.devicesViews.GetAllDeviceView;

import java.util.List;

public class GetAllDevicePresenter {
    private Context mContext;
    private GetAllDeviceView mGetAllDeviceView;
    private DeviceRepositories mDeviceRepositories;
    public GetAllDevicePresenter(Context mContext, GetAllDeviceView mGetAllDeviceView) {
        this.mContext = mContext;
        this.mGetAllDeviceView = mGetAllDeviceView;
        this.mDeviceRepositories = new DeviceRepositoriesImpl();
    }
    public void getAllDevice(String token){
        this.mDeviceRepositories.getAllDeviceList(mContext, token, new CallBackData<List<DeviceDTO>>() {
            @Override
            public void onSucess(List<DeviceDTO> deviceDTOS) {
                mGetAllDeviceView.getAllDeviceSuccess(deviceDTOS);
            }
            @Override
            public void onFail(String message) {
                mGetAllDeviceView.showError(message);
            }
        });
    }

}
