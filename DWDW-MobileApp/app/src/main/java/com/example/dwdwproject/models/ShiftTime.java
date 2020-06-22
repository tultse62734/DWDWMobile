package com.example.dwdwproject.models;

import java.io.Serializable;

public class ShiftTime implements Serializable {
    private int timeId;
    private String timeCode;
    private String startTime;
    private String endTime;

    public ShiftTime(int timeId, String timeCode, String startTime, String endTime) {
        this.timeId = timeId;
        this.timeCode = timeCode;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getTimeId() {
        return timeId;
    }

    public void setTimeId(int timeId) {
        this.timeId = timeId;
    }

    public String getTimeCode() {
        return timeCode;
    }

    public void setTimeCode(String timeCode) {
        this.timeCode = timeCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
