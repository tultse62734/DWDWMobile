package com.example.dwdwproject.utils;


import com.example.dwdwproject.repositories.Service;
import com.example.dwdwproject.repositories.devicesRepositories.ServiceDevice;
import com.example.dwdwproject.repositories.locationRepositories.ServiceLocation;

public class ClientApi extends  BaseApi {
    public Service Services(){
        return this.getService(Service.class,ConfigAPI.BASE_URL);
    }
    public ServiceDevice ServicesDevice(){
        return this.getService(ServiceDevice.class,ConfigAPI.BASE_URL);
    }

    public ServiceLocation ServicesLocation(){
        return this.getService(ServiceLocation.class,ConfigAPI.BASE_URL);
    }

}
