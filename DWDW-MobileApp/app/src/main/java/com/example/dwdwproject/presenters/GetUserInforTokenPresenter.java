package com.example.dwdwproject.presenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.repositories.DWDWRepositories;
import com.example.dwdwproject.repositories.DWDWRepositoriesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.GetUserInforTokenView;

public class GetUserInforTokenPresenter {
    private Context mContext;
    private GetUserInforTokenView mGetUserInforTokenView;
    private DWDWRepositories mDwdwRepositories;
    public GetUserInforTokenPresenter(Context mContext, GetUserInforTokenView mGetUserInforTokenView) {
        this.mContext = mContext;
        this.mGetUserInforTokenView = mGetUserInforTokenView;
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
}
