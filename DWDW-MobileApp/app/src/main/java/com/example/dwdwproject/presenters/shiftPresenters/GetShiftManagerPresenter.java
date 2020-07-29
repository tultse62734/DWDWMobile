package com.example.dwdwproject.presenters.shiftPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.repositories.shiftRepositories.ShiftRepositories;
import com.example.dwdwproject.repositories.shiftRepositories.ShiftRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.shiftsViews.GetShiftManagerView;

import java.util.List;

public class GetShiftManagerPresenter {
    private Context mContext;
    private GetShiftManagerView mGetShiftManagerView;
    private DWDWManagement dwdwManagement;
    private ShiftRepositories mShiftRepositories;
    public GetShiftManagerPresenter(Context mContext, Application mApplication, GetShiftManagerView mGetShiftManagerView) {
        this.mContext = mContext;
        this.mGetShiftManagerView = mGetShiftManagerView;
        this.dwdwManagement = new DWDWManagement(mApplication);
        this.mShiftRepositories = new ShiftRepositoriesImpl();
    }
    public void getShiftsManager(String Token){
        this.mShiftRepositories.getShiftByManager(mContext, Token, new CallBackData<List<ShiftDTO>>() {
            @Override
            public void onSucess(List<ShiftDTO> shiftDTOS) {
                mGetShiftManagerView.getShiftManagerSuccess(shiftDTOS);
            }

            @Override
            public void onFail(String message) {
                mGetShiftManagerView.showError("Lấy dữ liệu không thành công");
            }
        });
    }

    public void getShiftManagerToken(){
        dwdwManagement.getAccessToken(new DWDWManagement.OnDataCallBackAccessToken() {
            @Override
            public void onDataSuccess(String accessToken) {
                getShiftsManager(accessToken);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
}
