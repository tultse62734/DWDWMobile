package com.example.dwdwproject.models;

import java.io.Serializable;
public class Accident implements Serializable {
    private int accidentId;
    private String accidentName;
    private String accidentDate;
    private String timeDate;
    private String username;
    private String locationAccident;
    private String image;
    private String roomAccident;
    private boolean isStatus;
    private String status;

    public Accident(int accidentId, String accidentName, String accidentDate, String time,String locationAccident, String image, boolean isStatus) {
        this.accidentId = accidentId;
        this.accidentName = accidentName;
        this.timeDate = time;
        this.accidentDate = accidentDate;
        this.locationAccident = locationAccident;
        this.image = image;
        this.isStatus = isStatus;
    }


    public Accident(int accidentId, String accidentName, String accidentDate,String time,String locationAccident, String image, String roomAccident, boolean isStatus) {
        this.accidentId = accidentId;
        this.accidentName = accidentName;
        this.accidentDate = accidentDate;
        this.timeDate = time;
        this.locationAccident = locationAccident;
        this.image = image;
        this.roomAccident = roomAccident;
        this.isStatus = isStatus;
    }

    public Accident(int accidentId, String accidentName, String accidentDate, String timeDate, String locationAccident, String image, String roomAccident, String status) {
        this.accidentId = accidentId;
        this.accidentName = accidentName;
        this.accidentDate = accidentDate;
        this.timeDate = timeDate;
        this.locationAccident = locationAccident;
        this.image = image;
        this.roomAccident = roomAccident;
        this.status = status;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(String timeDate) {
        this.timeDate = timeDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
