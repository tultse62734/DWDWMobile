package com.example.dwdwproject.models;

import java.io.Serializable;

public class Worker implements Serializable {
    private String  imageResourceId;
    private  String name;
    private  String phone;
    private  String email;
    public Worker(String imageResourceId, String name, String phone, String email) {
        this.imageResourceId = imageResourceId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(String imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
