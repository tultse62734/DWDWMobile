package com.example.dwdwproject.views.locationsViews;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.views.BaseView;

public interface UpdateLocatonView extends BaseView {
    void updateLocationSuccess(LocationDTO mLocationDTO);
}
