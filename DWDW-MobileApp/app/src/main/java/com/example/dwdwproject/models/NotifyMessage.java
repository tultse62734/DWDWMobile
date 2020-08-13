package com.example.dwdwproject.models;

import java.io.Serializable;

public class NotifyMessage implements Serializable {
    private String header;
    private String message;

    public NotifyMessage(String header, String message) {
        this.header = header;
        this.message = message;
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
}
