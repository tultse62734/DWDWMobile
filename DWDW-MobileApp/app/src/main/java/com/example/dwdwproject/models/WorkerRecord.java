package com.example.dwdwproject.models;

import java.io.Serializable;

public class WorkerRecord implements Serializable {
    private String username;
    private int totalRecord;
    private String locationCode;
    private String RoomCode;
    public WorkerRecord(String username, int totalRecord, String locationCode, String roomCode) {
        this.username = username;
        this.totalRecord = totalRecord;
        this.locationCode = locationCode;
        RoomCode = roomCode;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
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
