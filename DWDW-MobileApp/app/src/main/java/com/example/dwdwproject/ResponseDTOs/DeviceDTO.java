package com.example.dwdwproject.ResponseDTOs;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
public class DeviceDTO implements Serializable {
    @SerializedName("deviceId")
    private int deviceId;
    @SerializedName("deviceCode")
    private String deviceCode;
    private String createDate;
    @SerializedName("deviceStatus")
    private int deviceStatus;
    @SerializedName("isActive")
    private boolean isActive;
    public int getDeviceId() {
        return deviceId;
    }
    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }
    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
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
