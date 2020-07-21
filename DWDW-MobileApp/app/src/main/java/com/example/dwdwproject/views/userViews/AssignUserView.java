package com.example.dwdwproject.views.userViews;

import com.example.dwdwproject.ResponseDTOs.AssignUserDTO;
import com.example.dwdwproject.views.BaseView;

public interface AssignUserView extends BaseView {
    void getAssignUserSuccess(AssignUserDTO mAssignUserDTO);
}
