package com.example.dwdwproject.presenters.roomPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.repositories.roomRepositories.RoomRepositories;
import com.example.dwdwproject.repositories.roomRepositories.RoomRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.roomViews.GetListRoomView;
import com.example.dwdwproject.views.roomViews.GetRoomView;

import java.util.List;

public class GetAllRoomPresenter {
    private Context mContext;
    private GetListRoomView mListRoomView;
    private RoomRepositories mRoomRepositories;
    public GetAllRoomPresenter(Context mContext,GetListRoomView mListRoomView) {
        this.mContext = mContext;
        this.mListRoomView = mListRoomView;
        this.mRoomRepositories = new RoomRepositoriesImpl();
    }
    public void getAllRoom(String token,int locationId){
        this.mRoomRepositories.getUnassignedRoomsFromLocation(mContext, token,locationId, new CallBackData<List<RoomDTO>>() {
            @Override
            public void onSucess(List<RoomDTO> roomDTOS) {
                mListRoomView.getListRoomSuccess(roomDTOS);
            }
            @Override
            public void onFail(String message) {
                mListRoomView.showError(message);
            }
        });
    }
}
