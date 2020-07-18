package com.example.dwdwproject.ResponseDTOs;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LocationRecord implements Serializable {
    @SerializedName("locationId")
    private int locationId;
    @SerializedName("locationCode")
    private String locationCode;
    @SerializedName("totalRecord")
    private float totalRecord;
    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public float getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(float totalRecord) {
        this.totalRecord = totalRecord;
    }
}
