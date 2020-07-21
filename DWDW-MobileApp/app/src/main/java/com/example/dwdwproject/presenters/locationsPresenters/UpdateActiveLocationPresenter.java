package com.example.dwdwproject.presenters.locationsPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositories;
import com.example.dwdwproject.repositories.locationRepositories.LocationRepositoriesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.locationsViews.UpdateActiveLocationView;

public class UpdateActiveLocationPresenter {
    private Context mContext;
    private UpdateActiveLocationView mUpdateActiveLocationView;
    private LocationRepositories mLocationRepositories;

    public UpdateActiveLocationPresenter(Context mContext, UpdateActiveLocationView mUpdateActiveLocationView) {
        this.mContext = mContext;
        this.mUpdateActiveLocationView = mUpdateActiveLocationView;
        this.mLocationRepositories = new LocationRepositoriesImpl();
    }
    public void updateActiveLocation(String token,int locationId){
        this.mLocationRepositories.updateLocationStatus(mContext, token, locationId, new CallBackData<LocationDTO>() {
            @Override
            public void onSucess(LocationDTO locationDTO) {
                mUpdateActiveLocationView.updateActiveLocationSuccess(locationDTO);
            }
            @Override
            public void onFail(String message) {
                    mUpdateActiveLocationView.showError(message);
            }
        });
    }
}
