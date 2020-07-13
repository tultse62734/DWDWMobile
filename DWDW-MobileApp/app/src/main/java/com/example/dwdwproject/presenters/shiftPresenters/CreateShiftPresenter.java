package com.example.dwdwproject.presenters.shiftPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.repositories.shiftRepositories.ShiftRepositories;
import com.example.dwdwproject.repositories.shiftRepositories.ShiftRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.shiftsViews.CreateShiftView;
import com.example.dwdwproject.views.shiftsViews.GetAllShiftView;

import java.util.List;

public class CreateShiftPresenter {
    private Context mContext;
    private CreateShiftView mCreateShiftView;
    private DWDWManagement dwdwManagement;
    private ShiftRepositories mShiftRepositories;

    public CreateShiftPresenter(Context mContext, Application mApplication, CreateShiftView mCreateShiftView) {
        this.mContext = mContext;
        this.mCreateShiftView = mCreateShiftView;
        this.dwdwManagement = new DWDWManagement(mApplication);
        this.mShiftRepositories = new ShiftRepositoriesImpl();
    }

    public void createShifts(String Token, ShiftDTO mShiftDTO){
        this.mShiftRepositories.createShift(mContext, Token, mShiftDTO, new CallBackData<ShiftDTO>() {
            @Override
            public void onSucess(ShiftDTO shiftDTO) {
                mCreateShiftView.createShiftSuccess();
            }

            @Override
            public void onFail(String message) {
                mCreateShiftView.showError(message);
            }
        });
    }

    public void getAllShiftToken(final ShiftDTO mShiftDTO){
        dwdwManagement.getAccessToken(new DWDWManagement.OnDataCallBackAccessToken() {
            @Override
            public void onDataSuccess(String accessToken) {
                createShifts(accessToken, mShiftDTO);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
}
