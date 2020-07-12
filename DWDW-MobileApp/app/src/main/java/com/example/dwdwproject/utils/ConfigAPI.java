package com.example.dwdwproject.utils;
public class ConfigAPI {
    public  static final String BASE_URL = "https://dwdw-api.conveyor.cloud/api/";
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
        String GETALLLOCATION = "Location/GetLocations";
        String GETLOCATIONBYID ="Location/GetLocationById";
        String CREATELOCATION ="Location/CreateLocation";
        String UPDATELOCATION = "Location/UpdateLocation";
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
