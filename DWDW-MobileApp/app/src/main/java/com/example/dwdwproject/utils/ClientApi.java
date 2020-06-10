package com.example.dwdwproject.utils;


import com.example.dwdwproject.repositories.Service;

public class ClientApi extends  BaseApi {
    public Service Services(){
        return this.getService(Service.class,ConfigAPI.BASE_URL);
    }
}
