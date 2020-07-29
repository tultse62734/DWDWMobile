package com.example.dwdwproject.presenters.shiftPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.repositories.shiftRepositories.ShiftRepositories;
import com.example.dwdwproject.repositories.shiftRepositories.ShiftRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.shiftsViews.GetAllShiftView;
import com.example.dwdwproject.views.shiftsViews.UpdateShiftActiveView;
import com.example.dwdwproject.views.shiftsViews.UpdateShiftView;

import java.util.List;

public class UpdateShiftActivePresenter {
    private Context mContext;
    private UpdateShiftActiveView mUpdateShiftActiveView;
    private ShiftRepositories mShiftRepositories;
    public UpdateShiftActivePresenter(Context mContext, UpdateShiftActiveView mUpdateShiftActiveView) {
        this.mContext = mContext;
        this.mUpdateShiftActiveView = mUpdateShiftActiveView;
        this.mShiftRepositories = new ShiftRepositoriesImpl();
    }
    public void updateShiftsActive(String Token, ShiftDTO mShiftDTO){
        this.mShiftRepositories.updateShiftActive(mContext, Token, mShiftDTO, new CallBackData<ShiftDTO>() {
            @Override
            public void onSucess(ShiftDTO shiftDTO) {
                mUpdateShiftActiveView.updateShiftActiveSuccess();
            }

            @Override
            public void onFail(String message) {

            }
        });
    }


}
