package com.example.dwdwproject.presenters.userPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.repositories.userRepositories.UserRepositories;
import com.example.dwdwproject.repositories.userRepositories.UserRepositotiesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.userViews.GetUserView;

public class CreateUserPresenter {
    private Context mContext;
    private DWDWManagement management;
    private GetUserView mGetUserView;
    private UserRepositories mUserRepositories;

    public CreateUserPresenter(Context mContext, Application mApplication,GetUserView mGetUserView) {
        this.mContext = mContext;
        this.mGetUserView = mGetUserView;
        this.mUserRepositories = new UserRepositotiesImpl();
        this.management = new DWDWManagement(mApplication);

    }
    public void createUserToken(final UserDTO mUserDTO){
        management.getAccessToken(new DWDWManagement.OnDataCallBackAccessToken() {
            @Override
            public void onDataSuccess(String accessToken) {
                createUser(accessToken,mUserDTO);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
    public void createUser(String token,UserDTO mUserDTO){
        this.mUserRepositories.createUser(mContext, token, mUserDTO, new CallBackData<UserDTO>() {
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
