package com.example.dwdwproject.presenters.locationsPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositories;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.locationsViews.DeactiveLocatonView;
import com.example.dwdwproject.views.locationsViews.UpdateLocatonView;

public class DeactiveLocationPresenter {
    private Context mContext;
    private DeactiveLocatonView mDeactiveLocationView;
    private DWDWManagement dwdwManagement ;
    private LocationRepositories mLocationRepositories;
    public DeactiveLocationPresenter(Context mContext, Application mApplication, DeactiveLocatonView mDeactiveLocationView) {
        this.mContext = mContext;
        this.mDeactiveLocationView = mDeactiveLocationView;
        this.mLocationRepositories = new LocationRepositoriesImpl();
    }
    public void deactiveLocation(String token,int locationId){
        this.mLocationRepositories.deactiveLocation(mContext, token, locationId, new CallBackData<LocationDTO>() {
            @Override
            public void onSucess(LocationDTO locationDTO) {
                mDeactiveLocationView.deactiveLocationSuccess(locationDTO);
            }

            @Override
            public void onFail(String message) {
                mDeactiveLocationView.showError(message);
            }
        });
    }
    public void deactiveLocationToken(final int locationId){
        dwdwManagement.getAccessToken(new DWDWManagement.OnDataCallBackAccessToken() {
            @Override
            public void onDataSuccess(String accessToken) {
                deactiveLocation(accessToken,locationId);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
}
