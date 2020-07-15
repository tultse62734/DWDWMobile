package com.example.dwdwproject.presenters.roomPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.repositories.roomRepositories.RoomRepositories;
import com.example.dwdwproject.repositories.roomRepositories.RoomRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.roomViews.GetRoomView;

public class UpdateRoomPresenter {
    private Context mContext;
    private GetRoomView mGetRoomView;
    private RoomRepositories mRoomRepositories;

    public UpdateRoomPresenter(Context mContext,GetRoomView mGetRoomView) {
        this.mContext = mContext;
        this.mGetRoomView = mGetRoomView;
        this.mRoomRepositories = new RoomRepositoriesImpl();
    }
    public void updateRoom(String token, final RoomDTO mRoomDTO){
        mRoomRepositories.updateRoomById(mContext, token, mRoomDTO, new CallBackData<RoomDTO>() {
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
