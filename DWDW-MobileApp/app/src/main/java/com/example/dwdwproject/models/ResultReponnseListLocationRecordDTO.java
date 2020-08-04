package com.example.dwdwproject.models;

import com.example.dwdwproject.ResponseDTOs.LocationRecord;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultReponnseListLocationRecordDTO {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private List<LocationRecord> data;
    @SerializedName("errorMessage")
    private String messgae;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<LocationRecord> getData() {
        return data;
    }

    public void setData(List<LocationRecord> data) {
        this.data = data;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }
}
