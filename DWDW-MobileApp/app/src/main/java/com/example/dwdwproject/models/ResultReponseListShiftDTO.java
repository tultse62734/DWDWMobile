package com.example.dwdwproject.models;

import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultReponseListShiftDTO {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private List<ShiftDTO> data;
    @SerializedName("errorMessage")
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<ShiftDTO> getData() {
        return data;
    }

    public void setData(List<ShiftDTO> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
