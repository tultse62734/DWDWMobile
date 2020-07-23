package com.example.dwdwproject.presenters.shiftPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.repositories.shiftRepositories.ShiftRepositories;
import com.example.dwdwproject.repositories.shiftRepositories.ShiftRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.shiftsViews.GetAllShiftView;
import com.example.dwdwproject.views.shiftsViews.UpdateShiftView;

import java.util.List;

public class UpdateShiftPresenter {
    private Context mContext;
    private UpdateShiftView mUpdateShiftView;
    private ShiftRepositories mShiftRepositories;
    public UpdateShiftPresenter(Context mContext, UpdateShiftView mUpdateShiftView) {
        this.mContext = mContext;
        this.mUpdateShiftView = mUpdateShiftView;
        this.mShiftRepositories = new ShiftRepositoriesImpl();
    }

    public void updateShifts(String Token, int locationID,ShiftDTO mShiftDTO){
        this.mShiftRepositories.updateShift(mContext, Token,locationID, mShiftDTO, new CallBackData<ShiftDTO>() {
            @Override
            public void onSucess(ShiftDTO shiftDTO) {
                mUpdateShiftView.updateShiftSuccess();
            }

            @Override
            public void onFail(String message) {

            }
        });
    }

}
