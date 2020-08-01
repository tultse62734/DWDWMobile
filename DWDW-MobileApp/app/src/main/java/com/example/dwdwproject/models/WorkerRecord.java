package com.example.dwdwproject.models;

import java.io.Serializable;

public class WorkerRecord implements Serializable {
    private String username;
    private String locationCode;
    private String RoomCode;
    public WorkerRecord(String username, String locationCode, String roomCode) {
        this.username = username;
        this.locationCode = locationCode;
        RoomCode = roomCode;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getRoomCode() {
        return RoomCode;
    }

    public void setRoomCode(String roomCode) {
        RoomCode = roomCode;
    }
}
