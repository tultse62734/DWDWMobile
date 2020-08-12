package com.example.dwdwproject.views;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO1;
public interface GetUserInforTokenView extends BaseView {
    void getInforSuccess(UserDTO1 mUserDTO);
}
