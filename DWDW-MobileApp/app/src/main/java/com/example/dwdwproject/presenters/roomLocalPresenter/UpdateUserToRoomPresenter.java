package com.example.dwdwproject.presenters.roomLocalPresenter;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.rooms.entities.UserItemEntities;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.views.roomLocalViews.AddUserToRoomView;
import com.example.dwdwproject.views.roomLocalViews.GetInfoUserView;

public class UpdateUserToRoomPresenter {
    private Context mContext;
    private GetInfoUserView mInfoUserView;
    private DWDWManagement dwdwManagement;

    public UpdateUserToRoomPresenter(Context mContext, Application mApplication, GetInfoUserView mInfoUserView) {
        this.mContext = mContext;
        this.mInfoUserView = mInfoUserView;
        this.dwdwManagement = new DWDWManagement(mApplication);
    }
    public void updateToRoom(UserItemEntities userItemEntities){
        this.dwdwManagement.updateUserItem(userItemEntities, new DWDWManagement.OnDataCallBackUserData() {
            @Override
            public void onDataSuccess(UserItemEntities mItemEntities) {
                mInfoUserView.getInforUserSuccess(mItemEntities);
            }

            @Override
            public void onDataFail(String message) {
                mInfoUserView.showError(message);
            }
        });
    }
}
