package com.example.dwdwproject.presenters.locationsPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositories;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.locationsViews.GetLocationView;

public class CreateLocationPresenter {
    private Context mContext;
    private GetLocationView mGetLocationView;
    private DWDWManagement dwdwManagement;
    private LocationRepositories mLocationRepositories;

    public CreateLocationPresenter(Context mContext, Application mApplication, GetLocationView mGetLocationView) {
        this.mContext = mContext;
        this.mGetLocationView = mGetLocationView;
        this.dwdwManagement = new DWDWManagement(mApplication);
        this.mLocationRepositories = new LocationRepositoriesImpl();

    }
    public void createLocation(String token, LocationDTO mLocationDTO){
        this.mLocationRepositories.createLocation(mContext, token, mLocationDTO, new CallBackData<LocationDTO>() {
            @Override
            public void onSucess(LocationDTO locationDTO) {
                mGetLocationView.getLocationSuccess(locationDTO);
            }

            @Override
            public void onFail(String message) {
                mGetLocationView.showError(message);
            }
        });
    }
    public void createLocationToken(final LocationDTO mLocationDTO){
        this.dwdwManagement.getAccessToken(new DWDWManagement.OnDataCallBackAccessToken() {
            @Override
            public void onDataSuccess(String accessToken) {
                createLocation(accessToken,mLocationDTO);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
}
