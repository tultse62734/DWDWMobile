package com.example.dwdwproject.presenters.shiftPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.repositories.shiftRepositories.ShiftRepositories;
import com.example.dwdwproject.repositories.shiftRepositories.ShiftRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.shiftsViews.GetAllShiftView;

import java.util.List;

public class GetAllShiftPresenter {
    private Context mContext;
    private GetAllShiftView mGetAllShiftView;
    private DWDWManagement dwdwManagement;
    private ShiftRepositories mShiftRepositories;

    public GetAllShiftPresenter(Context mContext, Application mApplication, GetAllShiftView mGetAllShiftView) {
        this.mContext = mContext;
        this.mGetAllShiftView = mGetAllShiftView;
        this.dwdwManagement = new DWDWManagement(mApplication);
        this.mShiftRepositories = new ShiftRepositoriesImpl();
    }

    public void getAllShifts(String Token){
        this.mShiftRepositories.getAllShift(mContext, Token, new CallBackData<List<ShiftDTO>>() {
            @Override
            public void onSucess(List<ShiftDTO> shiftDTOS) {
                mGetAllShiftView.getAllShiftSuccess(shiftDTOS);
            }

            @Override
            public void onFail(String message) {
                mGetAllShiftView.showError("Lấy dữ liệu không thành công");
            }
        });
    }

    public void getAllShiftToken(){
        dwdwManagement.getAccessToken(new DWDWManagement.OnDataCallBackAccessToken() {
            @Override
            public void onDataSuccess(String accessToken) {
                getAllShifts(accessToken);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
}
