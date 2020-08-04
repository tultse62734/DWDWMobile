package com.example.dwdwproject.models;

import com.example.dwdwproject.ResponseDTOs.LocationRecord;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.google.gson.annotations.SerializedName;

public class ResultReponnseLocationRecordDTO {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private LocationRecord data;
    @SerializedName("errorMessage")
    private String messgae;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public LocationRecord getData() {
        return data;
    }

    public void setData(LocationRecord data) {
        this.data = data;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }
}
