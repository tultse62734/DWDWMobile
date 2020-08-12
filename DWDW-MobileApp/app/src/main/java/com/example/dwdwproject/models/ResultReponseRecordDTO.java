package com.example.dwdwproject.models;

import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.google.gson.annotations.SerializedName;

public class ResultReponseRecordDTO {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private RecordDTO data;
    @SerializedName("message")
    private String message;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public RecordDTO getData() {
        return data;
    }

    public void setData(RecordDTO data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
