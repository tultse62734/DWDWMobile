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
    private DWDWManagement dwdwManagement;
    private DeviceRepositories mDeviceRepositories;

    public GetDeviceForAdminPresenter(Context mContext, Application mApplication,GetAllDeviceView getAllDeviceView) {
        this.mContext = mContext;
        this.getAllDeviceView = getAllDeviceView;
        this.dwdwManagement = new DWDWManagement(mApplication);
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
    public void getDeviceFromLocationForAd(final int locationId){
            dwdwManagement.getAccessToken(new DWDWManagement.OnDataCallBackAccessToken() {
                @Override
                public void onDataSuccess(String accessToken) {
                    getDeviceFromLocationForAd(accessToken,locationId);
                }
                @Override
                public void onDataFail() {

                }
            });
    }
}
