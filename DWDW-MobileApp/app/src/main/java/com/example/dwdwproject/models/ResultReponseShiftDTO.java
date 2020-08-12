package com.example.dwdwproject.models;

import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.google.gson.annotations.SerializedName;

public class ResultReponseShiftDTO {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private ShiftDTO data;
    @SerializedName("message")
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ShiftDTO getData() {
        return data;
    }

    public void setData(ShiftDTO data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
