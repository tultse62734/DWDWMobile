package com.example.dwdwproject.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;
public class Location implements Serializable {
    @SerializedName("locationId")
    private int locationId;
    @SerializedName("locationCode")
    private String nameLocation;
    private String createDate;
    private String startDate;
    private String endDate;
    @SerializedName("isActive")
    private boolean isStatus;
    private List<Room> roomList;
    public Location(int locationId, String nameLocation, String createDate, boolean isStatus) {
        this.locationId = locationId;
        this.nameLocation = nameLocation;
        this.createDate = createDate;
        this.isStatus = isStatus;
    }

    public Location(int locationId, String nameLocation, boolean isStatus) {
        this.locationId = locationId;
        this.nameLocation = nameLocation;
        this.isStatus = isStatus;
    }

    public Location(int locationId, String nameLocation, String startDate, String endDate, boolean isStatus) {
        this.locationId = locationId;
        this.nameLocation = nameLocation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isStatus = isStatus;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

    public List<Room> getRoomList() {
        return roomList;
    }
    public void setRoomList(List<Room> roomList) {
        this.roomList = roomList;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
