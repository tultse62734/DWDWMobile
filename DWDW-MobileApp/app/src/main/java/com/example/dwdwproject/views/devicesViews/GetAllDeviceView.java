package com.example.dwdwproject.views.devicesViews;

import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.views.BaseView;

import java.util.List;

public interface GetAllDeviceView extends BaseView {
    void getAllDeviceSuccess(List<DeviceDTO> mDeviceDTOList);
}
