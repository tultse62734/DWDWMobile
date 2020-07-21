package com.example.dwdwproject.presenters.userPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.repositories.userRepositories.UserRepositories;
import com.example.dwdwproject.repositories.userRepositories.UserRepositotiesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.userViews.GetAllListUserView;

import java.util.List;

public class AdminGetAllUserPresenter {
    private Context context;
    private GetAllListUserView mGetAllListUserView;
    private UserRepositories mUserRepositories;

    public AdminGetAllUserPresenter(Context context, GetAllListUserView mGetAllListUserView) {
        this.context = context;
        this.mGetAllListUserView = mGetAllListUserView;
        this.mUserRepositories = new UserRepositotiesImpl();
    }
    public void getAllUser(String token){
        this.mUserRepositories.getAll(context, token, new CallBackData<List<UserDTO>>() {
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
