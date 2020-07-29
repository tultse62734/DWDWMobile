package com.example.dwdwproject.presenters.roomLocalPresenter;

import android.app.Application;
import android.content.Context;

import androidx.core.os.UserManagerCompat;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.repositories.DWDWRepositories;
import com.example.dwdwproject.repositories.DWDWRepositoriesImpl;
import com.example.dwdwproject.rooms.entities.UserItemEntities;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.roomLocalViews.AddUserToRoomView;

public class AddUserToRoomPresenter {
    private Context mContext;
    private AddUserToRoomView mAddUserToRoomView;
    private DWDWManagement dwdwManagement;
    private DWDWRepositories mDwdwRepositories;
    public AddUserToRoomPresenter(Context mContext, Application application, AddUserToRoomView mAddUserToRoomView) {
        this.mContext = mContext;
        this.dwdwManagement = new DWDWManagement(application);
        this.mDwdwRepositories = new DWDWRepositoriesImpl();
        this.mAddUserToRoomView = mAddUserToRoomView;
    }
    public void getUserInfor(final String token){
        this.mDwdwRepositories.GetUsetInfor(mContext, token, new CallBackData<UserDTO>() {
            @Override
            public void onSucess(UserDTO userDTO) {
                UserItemEntities mUserItemEntities = new UserItemEntities();
                mUserItemEntities.setUser(userDTO);
                mUserItemEntities.setToken(token);
                addToRoom(mUserItemEntities);
                SharePreferenceUtils.saveStringSharedPreference(mContext,BundleString.LOCATIONNAME,userDTO.getLocationCode());
                SharePreferenceUtils.saveIntSharedPreference(mContext,BundleString.LOCATIONID,userDTO.getLocationId());
            }
            @Override
            public void onFail(String message) {
            }
        });
    }
    private void addToRoom(final UserItemEntities mUserItemEntities){
        this.dwdwManagement.updateUserItem(mUserItemEntities, new DWDWManagement.OnDataCallBackUserData() {
            @Override
            public void onDataSuccess(UserItemEntities mItemEntities) {
                mAddUserToRoomView.addUserSuccesṣ̣(mItemEntities);
            }
            @Override
            public void onDataFail(String message) {
                mAddUserToRoomView.showError(message);
            }
        });
    }
}
