package com.example.dwdwproject.ResponseDTOs;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
public class RoomDTO implements Serializable {
    @SerializedName("roomId")
    private int roomId;
    @SerializedName("roomCode")
    private String roomCode;
    @SerializedName("locationId")
    private int locationId;
    @SerializedName("isActive")
    private boolean isActive;

    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
