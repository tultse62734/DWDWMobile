package com.example.dwdwproject.models;

import java.io.Serializable;

public class Room implements Serializable {
    private int roomId;
    private String roomName;
    private String roomCreateDate;
    private boolean isStatus;

    public Room(int roomId, String roomName, String roomCreateDate, boolean isStatus) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomCreateDate = roomCreateDate;
        this.isStatus = isStatus;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomCreateDate() {
        return roomCreateDate;
    }

    public void setRoomCreateDate(String roomCreateDate) {
        this.roomCreateDate = roomCreateDate;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }
}
