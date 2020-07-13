package com.example.dwdwproject.presenters.shiftPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.repositories.shiftRepositories.ShiftRepositories;
import com.example.dwdwproject.repositories.shiftRepositories.ShiftRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.shiftsViews.GetShiftManagerView;
import com.example.dwdwproject.views.shiftsViews.GetShiftWorkerView;

import java.util.List;

public class GetShiftWorkerPresenter {
    private Context mContext;
    private GetShiftWorkerView mGetShiftWorkerView;
    private DWDWManagement dwdwManagement;
    private ShiftRepositories mShiftRepositories;

    public GetShiftWorkerPresenter(Context mContext, Application mApplication, GetShiftWorkerView mGetShiftWorkerView) {
        this.mContext = mContext;
        this.mGetShiftWorkerView = mGetShiftWorkerView;
        this.dwdwManagement = new DWDWManagement(mApplication);
        this.mShiftRepositories = new ShiftRepositoriesImpl();
    }

    public void getShiftsWorker(String Token){
        this.mShiftRepositories.getShiftByWorker(mContext, Token, new CallBackData<List<ShiftDTO>>() {
            @Override
            public void onSucess(List<ShiftDTO> shiftDTOS) {
                mGetShiftWorkerView.getShiftWorkerSuccess(shiftDTOS);
            }

            @Override
            public void onFail(String message) {
                mGetShiftWorkerView.showError("Lấy dữ liệu không thành công");
            }
        });
    }

    public void getShiftManagerToken(){
        dwdwManagement.getAccessToken(new DWDWManagement.OnDataCallBackAccessToken() {
            @Override
            public void onDataSuccess(String accessToken) {
                getShiftsWorker(accessToken);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
}
