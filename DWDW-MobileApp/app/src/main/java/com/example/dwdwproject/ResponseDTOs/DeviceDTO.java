package com.example.dwdwproject.ResponseDTOs;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeviceDTO implements Serializable {
    @SerializedName("deviceId")
    private int deviceId;
    @SerializedName("deviceCode")
    private String nameDevice;
    private String createDate;
    private String locationDevice;
    @SerializedName("deviceStatus")
    private int deviceStatus;
    @SerializedName("isActive")
    private boolean isActive;

    public DeviceDTO(int deviceId, String nameDevice, String createDate, String locationDevice) {
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

    public int getDeviceStatus() {
        return deviceStatus;
    }

    public void setDeviceStatus(int deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
