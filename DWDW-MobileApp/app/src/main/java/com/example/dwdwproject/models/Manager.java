package com.example.dwdwproject.models;

import java.io.Serializable;

public class Manager  implements Serializable {
    private int userId;
    private String  imageResourceId;
    private  String name;
    private  String phone;
    private  String email;
    private String roleName;
    private String locationName;
    private String createTime;
    private boolean isActive;
    public Manager(String imageResourceId, String name, String phone, String email) {
        this.imageResourceId = imageResourceId;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Manager(int userId,String name, String phone, String roleName, String locationName, String createTime, boolean isActive) {
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.roleName = roleName;
        this.locationName = locationName;
        this.createTime = createTime;
        this.isActive  = isActive;
    }
    public String getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(String imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
