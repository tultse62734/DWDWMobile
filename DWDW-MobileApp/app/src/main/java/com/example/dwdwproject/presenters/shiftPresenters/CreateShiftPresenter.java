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
    private ShiftRepositories mShiftRepositories;
    public CreateShiftPresenter(Context mContext, CreateShiftView mCreateShiftView) {
        this.mContext = mContext;
        this.mCreateShiftView = mCreateShiftView;
        this.mShiftRepositories = new ShiftRepositoriesImpl();
    }
    public void createShifts(String Token, int locationId,ShiftDTO mShiftDTO){
        this.mShiftRepositories.createShift(mContext, Token,locationId, mShiftDTO, new CallBackData<ShiftDTO>() {
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
}
