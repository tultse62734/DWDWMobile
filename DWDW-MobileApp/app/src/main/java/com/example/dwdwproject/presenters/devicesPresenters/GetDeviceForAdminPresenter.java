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

public class GetDeviceForAdminPresenter {
    private Context mContext;
    private GetAllDeviceView getAllDeviceView;
    private DeviceRepositories mDeviceRepositories;

    public GetDeviceForAdminPresenter(Context mContext,GetAllDeviceView getAllDeviceView) {
        this.mContext = mContext;
        this.getAllDeviceView = getAllDeviceView;
        this.mDeviceRepositories = new DeviceRepositoriesImpl();

    }
    public void getDeviceFromLocationForAd(String token,int locationId){
        this.mDeviceRepositories.getAllDeviceFromLocationByAdmin(mContext, token, locationId, new CallBackData<List<DeviceDTO>>() {
            @Override
            public void onSucess(List<DeviceDTO> deviceDTOS) {
                getAllDeviceView.getAllDeviceSuccess(deviceDTOS);
            }

            @Override
            public void onFail(String message) {
                getAllDeviceView.showError(message);
            }
        });
    }

}
