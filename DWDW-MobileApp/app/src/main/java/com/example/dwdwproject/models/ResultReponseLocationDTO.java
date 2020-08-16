package com.example.dwdwproject.models;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.ResponseDTOs.LocationRecord;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultReponseLocationDTO<T>{
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private List<T> data;
    @SerializedName("message")
    private String messgae;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }
}
