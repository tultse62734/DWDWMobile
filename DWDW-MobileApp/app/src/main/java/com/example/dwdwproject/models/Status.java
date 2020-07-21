package com.example.dwdwproject.models;

public class Status {
    private String statusName;
    private boolean isStatus;
    private int roleId;
    public Status(String statusName, boolean isStatus) {
        this.statusName = statusName;
        this.isStatus = isStatus;
    }

    public Status(String statusName, boolean isStatus, int roleId) {
        this.statusName = statusName;
        this.isStatus = isStatus;
        this.roleId = roleId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public boolean isStatus() {
        return isStatus;
    }

    public void setStatus(boolean status) {
        isStatus = status;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
