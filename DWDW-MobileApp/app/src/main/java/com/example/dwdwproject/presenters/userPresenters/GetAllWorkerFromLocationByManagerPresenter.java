package com.example.dwdwproject.presenters.userPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.repositories.userRepositories.UserRepositories;
import com.example.dwdwproject.repositories.userRepositories.UserRepositotiesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.userViews.GetAllListUserView;

import java.util.List;

public class GetAllWorkerFromLocationByManagerPresenter {
    private Context mContext;
    private DWDWManagement dwdwManagement;
    private UserRepositories mUserRepositories;
    private GetAllListUserView mGetAllListUserView;

    public GetAllWorkerFromLocationByManagerPresenter(Context mContext, Application mApplication, GetAllListUserView mGetAllListUserView) {
        this.mContext = mContext;
        this.mGetAllListUserView = mGetAllListUserView;
        this.dwdwManagement = new DWDWManagement(mApplication);
        this.mUserRepositories = new UserRepositotiesImpl();
    }
    public void ManagerGetAllWorkerfromLocationToken(final int locationId){
        dwdwManagement.getAccessToken(new DWDWManagement.OnDataCallBackAccessToken() {
            @Override
            public void onDataSuccess(String accessToken) {
                getAllWorker(accessToken,locationId);
            }

            @Override
            public void onDataFail() {

            }
        });
    }
    public void getAllWorker(String token,int locationId){
        mUserRepositories.getAllWorkerFromLocationByManager(mContext, token, locationId, new CallBackData<List<UserDTO>>() {
            @Override
            public void onSucess(List<UserDTO> userDTOS) {
                mGetAllListUserView.getAllUserSuccess(userDTOS);
            }

            @Override
            public void onFail(String message) {
                mGetAllListUserView.showError(message);
            }
        });
    }
}

