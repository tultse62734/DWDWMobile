package com.example.dwdwproject.repositories.roomRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.utils.CallBackData;

public class RoomRepositoriesImpl implements RoomRepositories {
    @Override
    public void getAllRoomfromLocation(Context context, String token, int locationId, CallBackData<RoomDTO> mCallBackData) {

    }
    @Override
    public void getAllRoom(Context context, String token, int roomId, CallBackData<RoomDTO> mCallBackData) {

    }

    @Override
    public void createRoom(Context context, String token, RoomDTO roomDTO, int locationId, CallBackData<String> mCallBackData) {

    }

    @Override
    public void updateRoomById(Context context, String token, RoomDTO mRoomDTO, int locationId, CallBackData<String> mCallBackData) {

    }
}
