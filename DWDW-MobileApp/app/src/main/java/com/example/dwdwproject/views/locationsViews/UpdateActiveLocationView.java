package com.example.dwdwproject.views.locationsViews;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.views.BaseView;

public interface UpdateActiveLocationView extends BaseView {
    void updateActiveLocationSuccess(LocationDTO mLocationDTO);
}
