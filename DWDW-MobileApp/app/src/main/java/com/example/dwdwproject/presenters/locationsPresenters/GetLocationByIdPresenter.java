package com.example.dwdwproject.presenters.locationsPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositories;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositoriesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.locationsViews.GetLocationView;

public class GetLocationByIdPresenter {
    private Context mContext;
    private GetLocationView mGetLocationView;
    private LocationRepositories mLocationRepositories;

    public GetLocationByIdPresenter(Context mContext, GetLocationView mGetLocationView) {
        this.mContext = mContext;
        this.mGetLocationView = mGetLocationView;
        this.mLocationRepositories = new LocationRepositoriesImpl();

    }
    public void getLocationById(String token,int locationId){
        this.mLocationRepositories.getAllLocationById(mContext, token, locationId, new CallBackData<LocationDTO>() {
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
