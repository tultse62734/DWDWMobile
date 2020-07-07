package com.example.dwdwproject.presenters.locationsPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositories;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositoriesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.locationsViews.GetLocationView;

public class UpdateLocationPresenter {
    private Context mContext;
    private GetLocationView mGetLocationView;
    private LocationRepositories mLocationRepositories;

    public UpdateLocationPresenter(Context mContext, GetLocationView mGetLocationView) {
        this.mContext = mContext;
        this.mGetLocationView = mGetLocationView;
        this.mLocationRepositories = new LocationRepositoriesImpl();
    }
    public void updateLocation(String token, LocationDTO mLocationDTO){
        this.mLocationRepositories.updateLocation(mContext, token, mLocationDTO, new CallBackData<LocationDTO>() {
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
}
