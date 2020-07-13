package com.example.dwdwproject.presenters.locationsPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositories;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.locationsViews.GetLocationView;
import com.example.dwdwproject.views.locationsViews.UpdateLocatonView;

public class UpdateLocationPresenter {
    private Context mContext;
    private UpdateLocatonView mUpdateLocationView;
    private DWDWManagement dwdwManagement ;
    private LocationRepositories mLocationRepositories;
    public UpdateLocationPresenter(Context mContext, Application mApplication, UpdateLocatonView mUpdateLocationView) {
        this.mContext = mContext;
        this.mUpdateLocationView = mUpdateLocationView;
        this.mLocationRepositories = new LocationRepositoriesImpl();
    }
    public void updateLocation(String token, LocationDTO mLocationDTO){
        this.mLocationRepositories.updateLocation(mContext, token, mLocationDTO, new CallBackData<LocationDTO>() {
            @Override
            public void onSucess(LocationDTO locationDTO) {
                mUpdateLocationView.updateLocationSuccess();
            }

            @Override
            public void onFail(String message) {
                mUpdateLocationView.showError(message);
            }
        });
    }
    public void updateLocationToken(final LocationDTO mLocationDTO){
        dwdwManagement.getAccessToken(new DWDWManagement.OnDataCallBackAccessToken() {
            @Override
            public void onDataSuccess(String accessToken) {
                updateLocation(accessToken,mLocationDTO);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
}
