package com.example.dwdwproject.presenters.userPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.repositories.userRepositories.UserRepositories;
import com.example.dwdwproject.repositories.userRepositories.UserRepositotiesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.userViews.GetUserView;

public class UpdateUserPresenter {
    private Context mContext;
    private DWDWManagement management;
    private GetUserView mGetUserView;
    private UserRepositories mUserRepositories;

    public UpdateUserPresenter(Context mContext, GetUserView mGetUserView) {
        this.mContext = mContext;
        this.mGetUserView = mGetUserView;
        this.mUserRepositories = new UserRepositotiesImpl();
    }
    public void updateUser(String token,UserDTO mUserDTO){
        this.mUserRepositories.updateUserById(mContext, token, mUserDTO, new CallBackData<UserDTO>() {
            @Override
            public void onSucess(UserDTO userDTO) {
                mGetUserView.getUserSuccess(userDTO);
            }

            @Override
            public void onFail(String message) {
                mGetUserView.showError(message);
            }
        });
    }
}
