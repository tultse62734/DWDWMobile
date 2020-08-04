package com.example.dwdwproject.models;

import java.io.Serializable;

public class DataResponse<T> implements Serializable {
    private T data;
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
