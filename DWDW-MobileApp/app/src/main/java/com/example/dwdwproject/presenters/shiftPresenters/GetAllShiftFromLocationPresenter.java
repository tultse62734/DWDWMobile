package com.example.dwdwproject.presenters.shiftPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.repositories.shiftRepositories.ShiftRepositories;
import com.example.dwdwproject.repositories.shiftRepositories.ShiftRepositoriesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.shiftsViews.GetShiftManagerView;

import java.util.List;

public class GetAllShiftFromLocationPresenter {
    private Context context;
    private GetShiftManagerView getShiftManagerView;
    private ShiftRepositories mShiftRepositories;

    public GetAllShiftFromLocationPresenter(Context context, GetShiftManagerView getShiftManagerView) {
        this.context = context;
        this.mShiftRepositories = new ShiftRepositoriesImpl();
        this.getShiftManagerView = getShiftManagerView;
    }
    public void getAllShiftFromLocation(String token,int locationId,String date){
        this.mShiftRepositories.getShiftFromLocation(context, token, locationId, date, new CallBackData<List<ShiftDTO>>() {
            @Override
            public void onSucess(List<ShiftDTO> shiftDTOS) {
             getShiftManagerView.getShiftManagerSuccess(shiftDTOS);
            }
            @Override
            public void onFail(String message) {
                    getShiftManagerView.showError(message);
            }
        });
    }
}
