package com.example.dwdwproject.views.recordsViews;

import com.example.dwdwproject.ResponseDTOs.LocationRecord;
import com.example.dwdwproject.views.BaseView;

import java.util.List;

public interface GetLocationRecordView extends BaseView {
    void getLocationRecordSuccess(List<LocationRecord> mLocationRecordList);
}
