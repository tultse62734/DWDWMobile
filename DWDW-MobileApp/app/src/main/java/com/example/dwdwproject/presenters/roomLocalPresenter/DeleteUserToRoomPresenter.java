package com.example.dwdwproject.presenters.roomLocalPresenter;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.rooms.entities.UserItemEntities;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.views.roomLocalViews.DeleteUserView;

public class DeleteUserToRoomPresenter {
    private Application application;
    private DeleteUserView mDeleteUserView;
    private DWDWManagement dwdwManagement;

    public DeleteUserToRoomPresenter(Application application, DeleteUserView mDeleteUserView) {
        this.application = application;
        this.mDeleteUserView = mDeleteUserView;
        this.dwdwManagement = new DWDWManagement(application);
    }
    public void deleteUserTomRoom(){
        this.dwdwManagement.deleteAllUserItems(new DWDWManagement.OnDataCallBackUserData() {
            @Override
            public void onDataSuccess(UserItemEntities mItemEntities) {
                mDeleteUserView.deleteUserToRoomSucces();
            }

            @Override
            public void onDataFail(String message) {
                mDeleteUserView.showError(message);
            }
        });
    }
}
