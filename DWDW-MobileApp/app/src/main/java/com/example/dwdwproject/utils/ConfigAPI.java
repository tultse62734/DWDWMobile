package com.example.dwdwproject.utils;
public class ConfigAPI {
    public  static final String BASE_URL = "";
    public interface Api {
        //User
        String LOGIN = "";
        String GETUSERINFOR="";
        String UPDATEINFOR ="";
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
        //Room
        String GETALLROOM = "";
        String GETROOMBYID ="";
        String CREATEROOM ="";
        String UPDATEROOM = "";
        String GETROOMFROMLOCATION="";
        //Users
        String GETALLUSER  = "";
        String GETALLUSERBYADMIN = "";
        String GETALLUSERBYMANAGER = "";
        String CREATEUSER="";
        String UPDATEUSER="";
        String DELETEUSER ="";
    }
}
