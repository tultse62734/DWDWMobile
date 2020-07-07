package com.example.dwdwproject.views;

import com.example.dwdwproject.ResponseDTOs.UserDTO;

public interface GetUserInforTokenView extends BaseView {
    void getInforSuccess(UserDTO mUserDTO);
}
