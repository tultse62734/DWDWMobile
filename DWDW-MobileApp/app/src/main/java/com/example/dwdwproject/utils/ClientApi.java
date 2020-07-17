package com.example.dwdwproject.utils;


import com.example.dwdwproject.repositories.Service;
import com.example.dwdwproject.repositories.devicesRepositories.ServiceDevice;
import com.example.dwdwproject.repositories.locationRepositories.ServiceLocation;
import com.example.dwdwproject.repositories.recordRepositories.ServiceRecord;
import com.example.dwdwproject.repositories.roomRepositories.ServiceRoom;
import com.example.dwdwproject.repositories.shiftRepositories.ServiceShift;
import com.example.dwdwproject.repositories.userRepositories.ServiceUsers;

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
    public ServiceRoom ServicesRoom(){
        return this.getService(ServiceRoom.class,ConfigAPI.BASE_URL);
    }

    public ServiceShift ServicesShift(){
        return this.getService(ServiceShift.class,ConfigAPI.BASE_URL);
    }

    public ServiceUsers ServicesUsers(){
        return this.getService(ServiceUsers.class,ConfigAPI.BASE_URL);
    }
    public ServiceRecord ServiceRecord(){
        return this.getService(ServiceRecord.class,ConfigAPI.BASE_URL);
    }
}
