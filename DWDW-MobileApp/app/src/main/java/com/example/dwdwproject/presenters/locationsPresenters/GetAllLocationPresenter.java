package com.example.dwdwproject.presenters.locationsPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositories;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;

import java.util.List;

public class GetAllLocationPresenter {
    private Context mContext;
    private GetAllLocatonView mGetAllLocatonView;
    private DWDWManagement dwdwManagement;
    private LocationRepositories mLocationRepositories;
    public GetAllLocationPresenter(Context mContext, Application mApplication,GetAllLocatonView mGetAllLocatonView) {
        this.mContext = mContext;
        this.mGetAllLocatonView = mGetAllLocatonView;
        this.mLocationRepositories = new LocationRepositoriesImpl();
        this.dwdwManagement = new DWDWManagement(mApplication);
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
    public void getTokenGetAllLocation(){
        dwdwManagement.getAccessToken(new DWDWManagement.OnDataCallBackAccessToken() {
            @Override
            public void onDataSuccess(String accessToken) {
                getAllLocation(accessToken);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
}
