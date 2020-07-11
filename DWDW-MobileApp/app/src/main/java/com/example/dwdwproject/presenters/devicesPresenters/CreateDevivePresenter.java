package com.example.dwdwproject.presenters.devicesPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.repositories.devicesRepositories.DeviceRepositories;
import com.example.dwdwproject.repositories.devicesRepositories.DeviceRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.devicesViews.GetDeviceIDView;

public class CreateDevivePresenter {
    private Context context;
    private DWDWManagement dwdwManagement;
    private GetDeviceIDView mGetDeviceIDView;
    private DeviceRepositories mDeviceRepositories;

    public CreateDevivePresenter(Context context, Application mApplication,GetDeviceIDView mGetDeviceIDView) {
        this.context = context;
        this.mGetDeviceIDView = mGetDeviceIDView;
        this.dwdwManagement = new DWDWManagement(mApplication);
        this.mDeviceRepositories = new DeviceRepositoriesImpl();

    }
    public void createDevice(String token,DeviceDTO mDeviceDTO){
        this.mDeviceRepositories.createDevice(context, token, mDeviceDTO, new CallBackData<DeviceDTO>() {
            @Override
            public void onSucess(DeviceDTO deviceDTO) {
                mGetDeviceIDView.getDeviceById(deviceDTO);
            }

            @Override
            public void onFail(String message) {
                mGetDeviceIDView.showError("Tạo thiết bị không thành công");
            }
        });
    }
    public void createDeviceToken(final DeviceDTO mDeviceDTO){
        dwdwManagement.getAccessToken(new DWDWManagement.OnDataCallBackAccessToken() {
            @Override
            public void onDataSuccess(String accessToken) {
                createDevice(accessToken,mDeviceDTO);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
}
