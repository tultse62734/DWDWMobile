package com.example.dwdwproject.models;

public class Status {
    private String statusName;
    private boolean isStatus;

    public Status(String statusName, boolean isStatus) {
        this.statusName = statusName;
        this.isStatus = isStatus;
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
}
