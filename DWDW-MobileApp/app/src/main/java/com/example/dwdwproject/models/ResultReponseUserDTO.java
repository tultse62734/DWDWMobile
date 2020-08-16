package com.example.dwdwproject.models;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultReponseUserDTO<T> {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private List<T> data;
    @SerializedName("message")
    private String errorMessage;
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

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
