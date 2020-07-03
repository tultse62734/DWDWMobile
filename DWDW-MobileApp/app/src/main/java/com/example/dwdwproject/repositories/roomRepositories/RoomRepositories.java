package com.example.dwdwproject.repositories.roomRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.utils.CallBackData;

public interface RoomRepositories {
    void getAllRoomfromLocation(Context context, String token, int locationId, CallBackData<RoomDTO> mCallBackData);
    void getAllRoom(Context context,String token,int roomId,CallBackData<RoomDTO> mCallBackData);
    void createRoom(Context context,String token,RoomDTO roomDTO,int locationId,CallBackData<String> mCallBackData);
    void updateRoomById(Context context,String token,RoomDTO mRoomDTO,int locationId,CallBackData<String>mCallBackData);
}
