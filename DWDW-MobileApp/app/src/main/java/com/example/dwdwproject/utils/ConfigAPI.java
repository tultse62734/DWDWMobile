package com.example.dwdwproject.utils;
public class ConfigAPI {
    public  static final String BASE_URL = "https://dwdw-api-gv6.conveyor.cloud/api/";
    public interface Api {
        //User
        String LOGIN = "User/LoginAsync";
        String GETUSERINFOR="User/GetUserInfoToken";
        String UPDATEINFOR ="";
        //Device
        String GETALLDEVICE = "Device/GetAllDevice";
        String GETDEVICEBYID ="";
        String CREATEDEVICE ="";
        String UPDATEDEVICE = "";
        String ADMIMGETALLDEVICEFROMLOCATION = "Device/GetActiveDeviceFromLocationAdmin";
        //Location
        String GETALLLOCATION = "Location/GetLocations";
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
