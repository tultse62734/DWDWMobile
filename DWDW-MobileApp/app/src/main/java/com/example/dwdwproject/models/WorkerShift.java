package com.example.dwdwproject.models;

import java.io.Serializable;

public class WorkerShift implements Serializable {
    private int shiftId;
    private String shiftRoom;
    private String username;
    private String shiftLocation;
    private String shiftDay;

    public WorkerShift(int shiftId, String shiftRoom, String username, String shiftLocation, String shiftDay) {
        this.shiftId = shiftId;
        this.shiftRoom = shiftRoom;
        this.username = username;
        this.shiftLocation = shiftLocation;
        this.shiftDay = shiftDay;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftRoom() {
        return shiftRoom;
    }

    public void setShiftRoom(String shiftRoom) {
        this.shiftRoom = shiftRoom;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getShiftLocation() {
        return shiftLocation;
    }

    public void setShiftLocation(String shiftLocation) {
        this.shiftLocation = shiftLocation;
    }

    public String getShiftDay() {
        return shiftDay;
    }

    public void setShiftDay(String shiftDay) {
        this.shiftDay = shiftDay;
    }
}
