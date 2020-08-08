package com.example.dwdwproject.utils;
public class ConfigAPI {

    public  static final String BASE_URL = "https://dwdw-api-gv6.conveyor.cloud/api/";
    public interface Api {
        //User
        String LOGIN = "User/Login";
        String GETUSERINFOR="User/GetUserInfoToken";
        String UPDATEINFOR ="User/UpdatePersonalInfo";
        //Device
        String GETALLDEVICE = "Device/GetAllDevice";
        String GETDEVICEBYID ="";
        String CREATEDEVICE ="Device/CreateDevice";
        String UPDATEDEVICE = "Device/UpdateDevice";
        String ADMIMGETALLDEVICEFROMLOCATION = "Device/GetActiveDeviceFromLocationAdmin";
        String MANAGERGETDEVICEFROMLOCATION = "Device/GetActiveDeviceFromLocationManager";
        String ADMINGETALLDEVICEFROMROOM = "Device/GetActiveDeviceFromRoomAdmin";
        String MANAGERGETDEVICEFROMROOM = "Device/GetActiveDeviceFromRoomManager";
        String ASSIGNDEVICEINTOROOM = "Device/AssignDeviceToRoom";
        String UPDATEDEVICESTATUS = "Device/UpdateDeviceActive";
        //Location
        String GETALLLOCATION = "Location/GetAllActiveLocations";
        String GETLOCATIONBYID ="Location/GetLocationById";
        String CREATELOCATION ="Location/CreateLocation";
        String UPDATELOCATION = "Location/UpdateLocation";
        String DEACTIVELOCATION = "/api/Location/DeactiveLocation/{locationId}";
        String ACTIVELOCATION = "Location/UpdateLocationStatus";
        String GETMANAGERLOCATION = "Location/GetLocationsByManagerWorker";
        //Room
        String GETALLROOM = "";
        String GETROOMBYID ="";
        String CREATEROOM ="Room/CreateRoom";
        String UPDATEROOM = "Room/UpdateRoom";
        String GETROOMFROMLOCATION="Room/GetRoomsFromLocation/{locationId}";
        String GETROOMFROMLOCATIONBYMANAGER="Room/GetRoomsFromLocationByManager/{locationId}";
        String UPDATEROOMSTATUS = "Room/DeactiveRoom/{roomId}";
        //Users
        String GETALLUSER  = "User/GetAllByAdmin";
        String GETALLUSERBYADMIN = "User/GetUserFromLocationByAdmin";
        String GETALLUSERBYMANAGER = "User/GetUserFromLocationsByManager";
        String CREATEUSER="User/CreateUserByAdmin";
        String UPDATEUSER="User/UpdateUserByAdmin";
        String DELETEUSER ="";
        String ASSGINUSER = "User/AssignUserToLocationByAdmin";
        String DEASSGINUSER ="User/DeassignUserFromLocationByAdmin";
        String GETWORKERFROMLOCATION = "User/GetWorkerFromLocationsByManager";
        String UPDATEUSERSTATUS ="User/UpdateUserActiveByAdmin";
        String SEARCHUSERBYID ="User/GetByIDAdmin";
        //Shift
        String GETALLSHIFT = "Shift/GetAllShift";
        String GETSHIFTOFMANAGER = "Shift/GetShiftManager";
        String GETSHIFTOFWORKER = "Shift/GetShiftFromLocationByDateWorker";
        String CREATESHIFT = "Shift/CreateShift";
        String UPDATESHIFT = "Shift/UpdateShift";
        String UPDATESHIFTACTIVE = "Shift/UpdateShiftActive";
        String GETSHIFTFROMLOCATION = "Shift/GetShiftFromLocationByDateManager";
        //Record
        String GETRECORDSBYLOCATIONIDANDTIME = "Record/GetRecordsByLocationIdAndTime";
        String GETRECORDBYLOCATION="Record/GetRecordsByLocationId";
        String GETLOCATIONRECORD = "Location/GetLocationsRecord";
        String GETRECORDBYWORKERDATE = "Record/GetRecordByWorkerDateForManager";
        //Notify
        String NOTIFY = "Notification/GetAllNotifications";
    }
}
