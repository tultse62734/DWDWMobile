package com.example.dwdwproject.models;

import java.io.Serializable;

public class WorkerShift implements Serializable {
    private String shiftTime;
    private String shiftRoom;
    private String shiftNameManager;
    private String shiftLocation;
    private String shiftDay;
    private String shiftStatus;
    private String shiftStart;
    private String shiftEnd;

    public WorkerShift(String shiftTime, String shiftRoom, String shiftNameManager, String shiftLocation, String shiftDay, String shiftStatus, String shiftStart, String shiftEnd) {
        this.shiftTime = shiftTime;
        this.shiftRoom = shiftRoom;
        this.shiftNameManager = shiftNameManager;
        this.shiftLocation = shiftLocation;
        this.shiftDay = shiftDay;
        this.shiftStatus = shiftStatus;
        this.shiftStart = shiftStart;
        this.shiftEnd = shiftEnd;
    }

    public String getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    public String getShiftRoom() {
        return shiftRoom;
    }

    public void setShiftRoom(String shiftRoom) {
        this.shiftRoom = shiftRoom;
    }

    public String getShiftNameManager() {
        return shiftNameManager;
    }

    public void setShiftNameManager(String shiftNameManager) {
        this.shiftNameManager = shiftNameManager;
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

    public String getShiftStatus() {
        return shiftStatus;
    }

    public void setShiftStatus(String shiftStatus) {
        this.shiftStatus = shiftStatus;
    }

    public String getShiftStart() {
        return shiftStart;
    }

    public void setShiftStart(String shiftStart) {
        this.shiftStart = shiftStart;
    }

    public String getShiftEnd() {
        return shiftEnd;
    }

    public void setShiftEnd(String shiftEnd) {
        this.shiftEnd = shiftEnd;
    }
}
