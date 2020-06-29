package com.example.dwdwproject.utils;

public interface CallBackData<T> {
    void onSucess(T t);
    void onFail(String message);
}
