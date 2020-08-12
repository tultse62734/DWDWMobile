package com.example.dwdwproject.models;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.ResponseDTOs.LocationRecord;
import com.google.gson.annotations.SerializedName;

public class ResultReponseLocationDTO {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private LocationDTO data;
    @SerializedName("message")
    private String messgae;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public LocationDTO getData() {
        return data;
    }

    public void setData(LocationDTO data) {
        this.data = data;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }
}
