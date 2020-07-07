package com.example.dwdwproject.views.roomViews;

import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.views.BaseView;

public interface GetRoomView extends BaseView {
    void getRoomSuccess(RoomDTO mRoomDTO);
}
