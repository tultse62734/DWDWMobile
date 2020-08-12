package com.example.dwdwproject.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResultReponse implements Serializable {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private String data;
    @SerializedName("messgae")
    private String errorMessage;
    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
