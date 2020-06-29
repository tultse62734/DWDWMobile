package com.example.dwdwproject.presenters;

import android.content.Context;

import com.example.dwdwproject.repositories.userRepositories.UserRepositories;
import com.example.dwdwproject.repositories.userRepositories.UserRepositotiesImpl;
import com.example.dwdwproject.views.LoginView;

public class LoginPresenter {
    private Context mContext;
    private LoginView mLoginView;
    private UserRepositories mUserRepositories;

    public LoginPresenter(Context mContext, LoginView mLoginView) {
        this.mContext = mContext;
        this.mLoginView = mLoginView;
        this.mUserRepositories = new UserRepositotiesImpl();
    }

}
