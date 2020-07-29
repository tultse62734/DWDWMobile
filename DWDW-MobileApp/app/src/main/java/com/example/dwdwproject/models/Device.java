package com.example.dwdwproject.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Device implements Serializable {
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
    private String roomCode;

    public Device(int deviceId, String nameDevice, String createDate, String locationDevice) {
        this.deviceId = deviceId;
        this.nameDevice = nameDevice;
        this.createDate = createDate;
        this.locationDevice = locationDevice;
    }

    public Device(int deviceId, String nameDevice, String createDate, String locationDevice, String roomCode) {
        this.deviceId = deviceId;
        this.nameDevice = nameDevice;
        this.createDate = createDate;
        this.locationDevice = locationDevice;
        this.roomCode = roomCode;
    }
    public Device(int deviceId, String nameDevice, String createDate, String locationDevice, String roomCode,boolean isActive) {
        this.deviceId = deviceId;
        this.nameDevice = nameDevice;
        this.createDate = createDate;
        this.locationDevice = locationDevice;
        this.roomCode = roomCode;
        this.isActive = isActive;
    }

    public Device(int deviceId, String nameDevice, boolean isActive) {
        this.deviceId = deviceId;
        this.nameDevice = nameDevice;
        this.isActive = isActive;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
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
