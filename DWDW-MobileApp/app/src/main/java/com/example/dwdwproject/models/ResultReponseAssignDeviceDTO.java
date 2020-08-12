package com.example.dwdwproject.models;

import com.example.dwdwproject.ResponseDTOs.AssignDeviceDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.google.gson.annotations.SerializedName;

public class ResultReponseAssignDeviceDTO {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private AssignDeviceDTO data;
    @SerializedName("message")
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public AssignDeviceDTO getData() {
        return data;
    }

    public void setData(AssignDeviceDTO data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
