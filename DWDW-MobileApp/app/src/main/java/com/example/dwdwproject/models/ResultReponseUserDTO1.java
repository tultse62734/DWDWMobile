package com.example.dwdwproject.models;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO1;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultReponseUserDTO1 {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private UserDTO1 data;
    @SerializedName("errorMessage")
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public UserDTO1 getData() {
        return data;
    }
    public void setData(UserDTO1 data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
