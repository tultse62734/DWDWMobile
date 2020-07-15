package com.example.dwdwproject.presenters.devicesPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.repositories.devicesRepositories.DeviceRepositories;
import com.example.dwdwproject.repositories.devicesRepositories.DeviceRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.devicesViews.CreateDeviceView;
import com.example.dwdwproject.views.devicesViews.GetDeviceIDView;

public class CreateDevivePresenter {
    private Context context;
    private CreateDeviceView mGetDeviceIDView;
    private DeviceRepositories mDeviceRepositories;

    public CreateDevivePresenter(Context context,CreateDeviceView mGetDeviceIDView) {
        this.context = context;
        this.mGetDeviceIDView = mGetDeviceIDView;
        this.mDeviceRepositories = new DeviceRepositoriesImpl();

    }
    public void createDevice(String token,DeviceDTO mDeviceDTO){
        this.mDeviceRepositories.createDevice(context, token, mDeviceDTO, new CallBackData<DeviceDTO>() {
            @Override
            public void onSucess(DeviceDTO deviceDTO) {
                mGetDeviceIDView.createDeviceSuccess(deviceDTO);
            }

            @Override
            public void onFail(String message) {
                mGetDeviceIDView.showError("Tạo thiết bị không thành công");
            }
        });
    }

}
