package com.example.dwdwproject.presenters.devicesPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.repositories.devicesRepositories.DeviceRepositories;
import com.example.dwdwproject.repositories.devicesRepositories.DeviceRepositoriesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.devicesViews.GetDeviceIDView;

public class GetDeviceByIdPresenter {
    private Context mContext;
    private GetDeviceIDView mGetDeviceIDView;
    private DeviceRepositories mDeviceRepositories;

    public GetDeviceByIdPresenter(Context mContext, GetDeviceIDView mGetDeviceIDView) {
        this.mContext = mContext;
        this.mGetDeviceIDView = mGetDeviceIDView;
        this.mDeviceRepositories = new DeviceRepositoriesImpl();
    }
    public void  getDeviceById(String token ,int deviceId){
        this.mDeviceRepositories.getAllDeviceById(mContext, token, deviceId, new CallBackData<DeviceDTO>() {
            @Override
            public void onSucess(DeviceDTO deviceDTO) {
                mGetDeviceIDView.getDeviceById(deviceDTO);
            }

            @Override
            public void onFail(String message) {
                mGetDeviceIDView.showError(message);
            }
        });
    }
}
