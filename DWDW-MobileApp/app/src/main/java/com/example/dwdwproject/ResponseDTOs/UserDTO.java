package com.example.dwdwproject.ResponseDTOs;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserDTO implements Serializable,Cloneable {
    @SerializedName("userId")
    private int userId;
    @SerializedName("userName")
    private String userName;
    @SerializedName("phone")
    private String phone;
    @SerializedName("dateOfBirth")
    private String dateOfBirth;
    @SerializedName("gender")
    private int gender;
    @SerializedName("roleId")
    private int roleId;
    @SerializedName("isActive")
    private boolean isActive;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
