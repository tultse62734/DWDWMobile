package com.example.dwdwproject.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
public class ReponseDTO implements Serializable {
    private String token;
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
