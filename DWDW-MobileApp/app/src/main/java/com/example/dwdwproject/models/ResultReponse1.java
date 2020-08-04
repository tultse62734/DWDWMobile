package com.example.dwdwproject.models;

import com.google.gson.annotations.SerializedName;

public class ResultReponse1<T> {
    @SerializedName("statusCode")
    private int statusCode;
    @SerializedName("data")
    private T data;
    @SerializedName("messgae")
    private String errorMessage;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
