package com.example.dwdwproject.views.devicesViews;

import com.example.dwdwproject.ResponseDTOs.AssignDeviceDTO;
import com.example.dwdwproject.views.BaseView;

public interface AssginDeviceView extends BaseView {
    void assignDeviceSuccess(AssignDeviceDTO mAssignDeviceDTO);
}
