package com.example.dwdwproject.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultReponseListMessage<T> {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private List<T> data;
    @SerializedName("message")
    private String message;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
