package com.example.dwdwproject.presenters.locationsPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositories;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositoriesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;

import java.util.List;

public class GetAllLocationPresenter {
    private Context mContext;
    private GetAllLocatonView mGetAllLocatonView;
    private LocationRepositories mLocationRepositories;

    public GetAllLocationPresenter(Context mContext, GetAllLocatonView mGetAllLocatonView) {
        this.mContext = mContext;
        this.mGetAllLocatonView = mGetAllLocatonView;
        this.mLocationRepositories = new LocationRepositoriesImpl();

    }
    public void getAllLocation(String token){
        this.mLocationRepositories.getAllLocationList(mContext, token, new CallBackData<List<LocationDTO>>() {
            @Override
            public void onSucess(List<LocationDTO> locationDTOS) {
                mGetAllLocatonView.getAllLocationSuccess(locationDTOS);
            }

            @Override
            public void onFail(String message) {
                mGetAllLocatonView.showError(message);
            }
        });
    }
}
