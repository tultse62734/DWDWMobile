package com.example.dwdwproject.presenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LoginDTO;
import com.example.dwdwproject.models.ReponseDTO;
import com.example.dwdwproject.repositories.DWDWRepositories;
import com.example.dwdwproject.repositories.DWDWRepositoriesImpl;
import com.example.dwdwproject.repositories.userRepositories.UserRepositories;
import com.example.dwdwproject.repositories.userRepositories.UserRepositotiesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.LoginView;

public class LoginPresenter {
    private Context mContext;
    private LoginView mLoginView;
    private DWDWRepositories mDwdwRepositories;
    public LoginPresenter(Context mContext, LoginView mLoginView) {
        this.mContext = mContext;
        this.mLoginView = mLoginView;
        this.mDwdwRepositories = new DWDWRepositoriesImpl();
    }
    public void login(LoginDTO mLoginDTO){
//        this.mDwdwRepositories.Login(mContext, mLoginDTO, new CallBackData<ReponseDTO>() {
//            @Override
//            public void onSucess(ReponseDTO reponseDTO) {
//                mLoginView.loginSuccess(reponseDTO);
//            }
//
//            @Override
//            public void onFail(String message) {
//                mLoginView.showError(message);
//            }
//        });
        this.mDwdwRepositories.Login2(mContext, mLoginDTO, new CallBackData<String>() {
            @Override
            public void onSucess(String s) {
                mLoginView.loginSuccessString̣̣̣(s);
            }

            @Override
            public void onFail(String message) {
            mLoginView.showError(message);
            }
        });
    }
}
