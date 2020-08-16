package com.example.dwdwproject.models;

import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultReponseListRecordDTO<T> {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private List<T> data;
    @SerializedName("message")
    private String messsage;

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

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }
}
