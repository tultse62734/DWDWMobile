package com.example.dwdwproject.views.roomViews;

import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.views.BaseView;

import java.util.List;

public interface GetListRoomView extends BaseView {
    void getListRoomSuccess(List<RoomDTO> mRoomDTOList);
}
