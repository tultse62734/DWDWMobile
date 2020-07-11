package com.example.dwdwproject.views.userViews;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.views.BaseView;

import java.util.List;

public interface GetAllListUserView extends BaseView {
    void getAllUserSuccess(List<UserDTO> userDTOList);
}
