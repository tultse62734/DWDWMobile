package com.example.dwdwproject.presenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.repositories.DWDWRepositories;
import com.example.dwdwproject.repositories.DWDWRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.GetUserInforTokenView;
public class GetUserInforTokenPresenter {
    private Context mContext;
    private GetUserInforTokenView mGetUserInforTokenView;
    private DWDWRepositories mDwdwRepositories;
    public GetUserInforTokenPresenter(Context mContext,GetUserInforTokenView mInforTokenView) {
        this.mContext = mContext;
        this.mGetUserInforTokenView = mInforTokenView;
        this.mDwdwRepositories = new DWDWRepositoriesImpl();
    }
    public void getInforToken(String token){
        mDwdwRepositories.GetUsetInfor(mContext, token, new CallBackData<UserDTO>() {
            @Override
            public void onSucess(UserDTO userDTO) {
                mGetUserInforTokenView.getInforSuccess(userDTO);
            }

            @Override
            public void onFail(String message) {
                mGetUserInforTokenView.showError(message);
            }
        });
    }
    public void updateInfor(String token,UserDTO mUserDTO){
        mDwdwRepositories.UpdateAccout(mContext, token, mUserDTO, new CallBackData<UserDTO>() {
            @Override
            public void onSucess(UserDTO userDTO) {
                mGetUserInforTokenView.getInforSuccess(userDTO);
            }

            @Override
            public void onFail(String message) {
                mGetUserInforTokenView.showError(message);
            }
        });
    }
}
