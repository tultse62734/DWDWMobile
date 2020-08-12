package com.example.dwdwproject.ResponseDTOs;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Role implements Serializable {
    @SerializedName("roleId")
    private int roleId;
    @SerializedName("roleName")
    private String roleName;
    @SerializedName("isActive")
    private boolean isActive;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
