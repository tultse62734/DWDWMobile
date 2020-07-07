package com.example.dwdwproject.views.devicesViews;

import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.models.Device;
import com.example.dwdwproject.views.BaseView;

public interface CreateDeviceView  extends BaseView {
    void createDeviceSuccess(DeviceDTO mDeviceDTO);
}
