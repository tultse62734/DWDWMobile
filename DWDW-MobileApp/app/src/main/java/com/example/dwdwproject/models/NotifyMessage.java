package com.example.dwdwproject.models;

import java.io.Serializable;

public class NotifyMessage implements Serializable {
    private String header;
    private String message;
    private String minute;

    public NotifyMessage(String header, String message, String minute) {
        this.header = header;
        this.message = message;
        this.minute = minute;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }
}
