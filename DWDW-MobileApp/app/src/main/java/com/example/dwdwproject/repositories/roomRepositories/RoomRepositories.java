package com.example.dwdwproject.repositories.roomRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.utils.CallBackData;

import java.util.List;

public interface RoomRepositories {
    void getAllRoomfromLocation(Context context, String token, int locationId, CallBackData<List<RoomDTO>> mCallBackData);
    void getAllRoom(Context context,String token,CallBackData<List<RoomDTO>> mCallBackData);
    void createRoom(Context context,String token,RoomDTO roomDTO,CallBackData<RoomDTO> mCallBackData);
    void updateRoomById(Context context,String token,RoomDTO mRoomDTO,CallBackData<RoomDTO>mCallBackData);
    void getAllRoomfromLocationByManager(Context context, String token, int locationId, CallBackData<List<RoomDTO>> mCallBackData);

}
