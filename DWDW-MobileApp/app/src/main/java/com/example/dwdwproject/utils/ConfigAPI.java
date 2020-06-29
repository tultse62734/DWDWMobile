package com.example.dwdwproject.utils;
public class ConfigAPI {
    public  static final String BASE_URL = "";
    public interface Api {
        //User
        String LOGIN = "";
        //Device
        String GETALLDEVICE = "";
        String GETDEVICEBYID ="";
        String CREATEDEVICE ="";
        String UPDATEDEVICE = "";
        String GETALLDEVICEFROMLOCATION = "";
        //Location

        String GETALLLOCATION = "";
        String GETLOCATIONBYID ="";
        String CREATELOCATION ="";
        String UPDATELOCATION = "";
    }
}
