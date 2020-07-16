package com.example.dwdwproject.presenters.locationsPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositories;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.locationsViews.GetManagerLocatonView;

import java.util.List;

public class GetManagerLocationPresenter {
    private Context mContext;
    private GetManagerLocatonView mGetManagerLocatonView;
    private LocationRepositories mLocationRepositories;
    public GetManagerLocationPresenter(Context mContext,GetManagerLocatonView mGetManagerLocatonView) {
        this.mContext = mContext;
        this.mGetManagerLocatonView = mGetManagerLocatonView;
        this.mLocationRepositories = new LocationRepositoriesImpl();
    }
    public void getManagerLocation(String token){
        this.mLocationRepositories.getManagerLocationList(mContext, token, new CallBackData<List<LocationDTO>>() {
            @Override
            public void onSucess(List<LocationDTO> locationDTOS) {
                mGetManagerLocatonView.getManagerLocationSuccess(locationDTOS);
            }

            @Override
            public void onFail(String message) {
                mGetManagerLocatonView.showError(message);
            }
        });
    }

}
