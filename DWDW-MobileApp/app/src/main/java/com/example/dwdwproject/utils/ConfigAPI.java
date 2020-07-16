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
        String CREATEDEVICE ="Device/CreateDevice";
        String UPDATEDEVICE = "Device/UpdateDevice";
        String ADMIMGETALLDEVICEFROMLOCATION = "Device/GetActiveDeviceFromLocationAdmin";
        //Location
        String GETALLLOCATION = "Location/GetLocations";
        String GETLOCATIONBYID ="Location/GetLocationById";
        String CREATELOCATION ="Location/CreateLocation";
        String UPDATELOCATION = "Location/UpdateLocation";
        String DEACTIVELOCATION = "Location/DeactiveLocation";
        //Room
        String GETALLROOM = "";
        String GETROOMBYID ="";
        String CREATEROOM ="Room/CreateRoom";
        String UPDATEROOM = "Room/UpdateRoom";
        String GETROOMFROMLOCATION="Room/GetRoomsFromLocation/{locationId}";
        //Users
        String GETALLUSER  = "";
        String GETALLUSERBYADMIN = "User/GetUserFromLocationByAdmin";
        String GETALLUSERBYMANAGER = "User/GetUserFromLocationsByManager";
        String CREATEUSER="CraeteUserByAdmin";
        String UPDATEUSER="User/UpdateUserByAdmin";
        String DELETEUSER ="";
        //Shift
        String GETALLSHIFT = "Shift/GetAllShift";
        String GETSHIFTOFMANAGER = "Shift/GetShiftManager";
        String GETSHIFTOFWORKER = "Shift/GetShiftWorker";
        String CREATESHIFT = "Shift/CreateShift";
        String UPDATESHIFT = "Shift/UpdateShift";
        String UPDATESHIFTACTIVE = "Shift/UpdateShiftActive";
    }
}
