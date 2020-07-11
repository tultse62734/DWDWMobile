package com.example.dwdwproject.utils;
public class ConfigAPI {
    public  static final String BASE_URL = "https://172.16.3.48:45460/api/";
    public interface Api {
        //User
        String LOGIN = "User/LoginAsync";
        String GETUSERINFOR="User/GetUserInfoToken";
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
