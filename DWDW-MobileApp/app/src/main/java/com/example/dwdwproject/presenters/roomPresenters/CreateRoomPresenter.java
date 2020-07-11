package com.example.dwdwproject.presenters.roomPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.repositories.roomRepositories.RoomRepositories;
import com.example.dwdwproject.repositories.roomRepositories.RoomRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.roomViews.GetRoomView;

public class CreateRoomPresenter {
    private Context mContext;
    private GetRoomView mGetRoomView;
    private DWDWManagement dwdwManagement;
    private RoomRepositories mRoomRepositories;

    public CreateRoomPresenter(Context mContext, Application mApplication,GetRoomView mGetRoomView) {
        this.mContext = mContext;
        this.mGetRoomView = mGetRoomView;
        this.dwdwManagement = new DWDWManagement(mApplication);
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
    public void createRoomToken(final RoomDTO roomDTO){
        this.dwdwManagement.getAccessToken(new DWDWManagement.OnDataCallBackAccessToken() {
            @Override
            public void onDataSuccess(String accessToken) {
                createRoom(accessToken,roomDTO);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
}
