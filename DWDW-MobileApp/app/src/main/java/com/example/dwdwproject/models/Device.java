package com.example.dwdwproject.models;

import java.io.Serializable;

public class Device implements Serializable {
    private int deviceId;
    private String nameDevice;
    private String createDate;
    private String locationDevice;

    public Device(int deviceId, String nameDevice, String createDate, String locationDevice) {
        this.deviceId = deviceId;
        this.nameDevice = nameDevice;
        this.createDate = createDate;
        this.locationDevice = locationDevice;
    }
    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getNameDevice() {
        return nameDevice;
    }

    public void setNameDevice(String nameDevice) {
        this.nameDevice = nameDevice;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLocationDevice() {
        return locationDevice;
    }

    public void setLocationDevice(String locationDevice) {
        this.locationDevice = locationDevice;
    }
}
