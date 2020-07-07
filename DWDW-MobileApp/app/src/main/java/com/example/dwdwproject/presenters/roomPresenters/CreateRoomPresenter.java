package com.example.dwdwproject.presenters.roomPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.repositories.roomRepositories.RoomRepositories;
import com.example.dwdwproject.repositories.roomRepositories.RoomRepositoriesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.roomViews.GetRoomView;

public class CreateRoomPresenter {
    private Context mContext;
    private GetRoomView mGetRoomView;
    private RoomRepositories mRoomRepositories;

    public CreateRoomPresenter(Context mContext, GetRoomView mGetRoomView) {
        this.mContext = mContext;
        this.mGetRoomView = mGetRoomView;
        this.mRoomRepositories = new RoomRepositoriesImpl();
    }
    public void createRoom(String token, RoomDTO mRoomDTO){
        mRoomRepositories.createRoom(mContext, token, mRoomDTO, new CallBackData<RoomDTO>() {
            @Override
            public void onSucess(RoomDTO roomDTO) {
                mGetRoomView.getRoomSuccess(roomDTO);
            }

            @Override
            public void onFail(String message) {
                mGetRoomView.showError(message);
            }
        });
    }
}
