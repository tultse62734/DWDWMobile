package com.example.dwdwproject.views.userViews;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.views.BaseView;

public interface GetUserView extends BaseView {
    void getUserSuccess(UserDTO userDTO);
}
