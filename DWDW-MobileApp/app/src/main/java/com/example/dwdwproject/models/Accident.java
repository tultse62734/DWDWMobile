package com.example.dwdwproject.models;

import java.io.Serializable;

public class Accident implements Serializable {
    private int accidentId;
    private String accidentName;
    private String accidentDate;
    private String locationAccident;
    private String roomAccident;
    private boolean isStatus;

    public Accident(int accidentId, String accidentName, String accidentDate, String locationAccident, String roomAccident, boolean isStatus) {
        this.accidentId = accidentId;
        this.accidentName = accidentName;
        this.accidentDate = accidentDate;
        this.locationAccident = locationAccident;
        this.roomAccident = roomAccident;
        this.isStatus = isStatus;
    }

    public int getAccidentId() {
        return accidentId;
    }

    public void setAccidentId(int accidentId) {
        this.accidentId = accidentId;
    }

    public String getAccidentName() {
        return accidentName;
    }

    public void setAccidentName(String accidentName) {
        this.accidentName = accidentName;
    }

    public String getAccidentDate() {
        return accidentDate;
    }

    public void setAccidentDate(String accidentDate) {
        this.accidentDate = accidentDate;
    }

    public String getLocationAccident() {
        return locationAccident;
    }

    public void setLocationAccident(String locationAccident) {
        this.locationAccident = locationAccident;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

    public String getRoomAccident() {
        return roomAccident;
    }

    public void setRoomAccident(String roomAccident) {
        this.roomAccident = roomAccident;
    }
}
