package com.example.dwdwproject.models;

import java.io.Serializable;

public class Shift implements Serializable {
    private int shiftId;
    private String shiftCode;
    private String shiftLocation;
    private String shiftRoom;
    private boolean isActive;
    public Shift(int shiftId, String shiftCode, String shiftLocation, String shiftRoom,boolean isActive) {
        this.shiftId = shiftId;
        this.shiftCode = shiftCode;
        this.shiftLocation = shiftLocation;
        this.shiftRoom = shiftRoom;
        this.isActive = isActive;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
    }

    public String getShiftCode() {
        return shiftCode;
    }

    public void setShiftCode(String shiftCode) {
        this.shiftCode = shiftCode;
    }

    public String getShiftLocation() {
        return shiftLocation;
    }

    public void setShiftLocation(String shiftLocation) {
        this.shiftLocation = shiftLocation;
    }

    public String getShiftRoom() {
        return shiftRoom;
    }

    public void setShiftRoom(String shiftRoom) {
        this.shiftRoom = shiftRoom;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
