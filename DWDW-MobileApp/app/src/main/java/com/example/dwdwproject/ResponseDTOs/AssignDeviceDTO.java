package com.example.dwdwproject.ResponseDTOs;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AssignDeviceDTO implements Serializable {
    @SerializedName("roomDeviceId")
    private int roomDeviceId;
    @SerializedName("roomId")
    private int roomId;
    @SerializedName("deviceId")
    private int deviceId;
    @SerializedName("startDate")
    private String dateStart;
    @SerializedName("endDate")
    private String dateEnd;
    @SerializedName("isActive")
    private boolean isActive;
    @SerializedName("roomCode")
    private String roomCode;
    @SerializedName("deviceCode")
    private String deviceCode;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }
}
