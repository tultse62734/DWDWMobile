package com.example.dwdwproject.presenters.devicesPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.AssignDeviceDTO;
import com.example.dwdwproject.repositories.devicesRepositories.DeviceRepositories;
import com.example.dwdwproject.repositories.devicesRepositories.DeviceRepositoriesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.devicesViews.AssginDeviceView;

public class AssignDevicePresenter {
    private Context mContext;
    private AssginDeviceView mAssginDeviceView;
    private DeviceRepositories mDeviceRepositories;
    public AssignDevicePresenter(Context mContext, AssginDeviceView mAssginDeviceView) {
        this.mContext = mContext;
        this.mAssginDeviceView = mAssginDeviceView;
        this.mDeviceRepositories = new DeviceRepositoriesImpl();
    }
    public void assignDevice(String token, AssignDeviceDTO mAssignDeviceDTO){
        this.mDeviceRepositories.assginDeviceIntoRoom(mContext, token, mAssignDeviceDTO, new CallBackData<AssignDeviceDTO>() {
            @Override
            public void onSucess(AssignDeviceDTO assignDeviceDTO) {
                mAssginDeviceView.assignDeviceSuccess(assignDeviceDTO);
            }

            @Override
            public void onFail(String message) {
                mAssginDeviceView.showError(message);
            }
        });
    }
}
