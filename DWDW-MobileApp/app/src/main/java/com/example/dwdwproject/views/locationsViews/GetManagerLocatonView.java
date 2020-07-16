package com.example.dwdwproject.views.locationsViews;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.views.BaseView;

import java.util.List;

public interface GetManagerLocatonView extends BaseView {
    void getManagerLocationSuccess(List<LocationDTO> mLocationDTOList);
}
