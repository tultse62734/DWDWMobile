package com.example.dwdwproject.presenters.roomLocalPresenter;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.repositories.DWDWRepositories;
import com.example.dwdwproject.repositories.DWDWRepositoriesImpl;
import com.example.dwdwproject.rooms.entities.UserItemEntities;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.roomLocalViews.AddUserToRoomView;
import com.example.dwdwproject.views.roomLocalViews.GetInfoUserView;

public class GetUserToRoomPresenter {
    private Context mContext;
    private GetInfoUserView mGetUserToRoomView;
    private DWDWManagement dwdwManagement;
    private DWDWRepositories mDwdwRepositories;
    public GetUserToRoomPresenter(Context mContext, Application application, GetInfoUserView mGetUserToRoomView) {
        this.mContext = mContext;
        this.dwdwManagement = new DWDWManagement(application);
        this.mDwdwRepositories = new DWDWRepositoriesImpl();
        this.mGetUserToRoomView = mGetUserToRoomView;
    }
    public void getUserInfor(final String token){
        this.mDwdwRepositories.GetUsetInfor(mContext, token, new CallBackData<UserDTO>() {
            @Override
            public void onSucess(UserDTO userDTO) {
                UserItemEntities mUserItemEntities = new UserItemEntities();
                mUserItemEntities.setUser(userDTO);
                mUserItemEntities.setToken(token);
                updateToRoom(mUserItemEntities);
            }
            @Override
            public void onFail(String message) {
            }
        });
    }
    public void getAccessToken(){
        dwdwManagement.getAccessToken(new DWDWManagement.OnDataCallBackAccessToken() {
            @Override
            public void onDataSuccess(String accessToken) {
                getUserInfor(accessToken);
            }
            @Override
            public void onDataFail() {

            }
        });
    }
    private void updateToRoom(final UserItemEntities mUserItemEntities){
        this.dwdwManagement.updateUserItem(mUserItemEntities, new DWDWManagement.OnDataCallBackUserData() {
            @Override
            public void onDataSuccess(UserItemEntities mItemEntities) {
                mGetUserToRoomView.getInforUserSuccess(mItemEntities);
            }
            @Override
            public void onDataFail(String message) {
                mGetUserToRoomView.showError(message);
            }
        });
    }
}
