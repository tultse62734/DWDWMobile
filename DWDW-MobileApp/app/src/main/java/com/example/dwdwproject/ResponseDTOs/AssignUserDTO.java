package com.example.dwdwproject.ResponseDTOs;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
public class AssignUserDTO implements Serializable {
    @SerializedName("userId")
    private int userId;
    @SerializedName("locationId")
    private int locationId;
    @SerializedName("startDate")
    private String startDate;
    @SerializedName("endDate")
    private String endDate;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
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
