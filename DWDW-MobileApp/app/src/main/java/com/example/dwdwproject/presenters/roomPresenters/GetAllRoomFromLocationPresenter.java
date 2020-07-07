package com.example.dwdwproject.presenters.roomPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.repositories.roomRepositories.RoomRepositories;
import com.example.dwdwproject.repositories.roomRepositories.RoomRepositoriesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.roomViews.GetListRoomView;

import java.util.List;

public class GetAllRoomFromLocationPresenter {
    private Context mContext;
    private GetListRoomView mGetListRoomView;
    private RoomRepositories mRoomRepositories;

    public GetAllRoomFromLocationPresenter(Context mContext, GetListRoomView mGetListRoomView) {
        this.mContext = mContext;
        this.mGetListRoomView = mGetListRoomView;
        this.mRoomRepositories = new RoomRepositoriesImpl();

    }
    public void getAllRoomFromLocation(String token,int locationId){
        this.mRoomRepositories.getAllRoomfromLocation(mContext, token, locationId, new CallBackData<List<RoomDTO>>() {
            @Override
            public void onSucess(List<RoomDTO> roomDTOS) {
                mGetListRoomView.getListRoomSuccess(roomDTOS);
            }

            @Override
            public void onFail(String message) {
                mGetListRoomView.showError(message);
            }
        });
    }
}
