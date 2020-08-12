package com.example.dwdwproject.models;

import com.example.dwdwproject.ResponseDTOs.AssignUserDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.google.gson.annotations.SerializedName;

public class ResultReponseAssignUserDTO {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private AssignUserDTO data;
    @SerializedName("errorMessage")
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public AssignUserDTO getData() {
        return data;
    }

    public void setData(AssignUserDTO data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
