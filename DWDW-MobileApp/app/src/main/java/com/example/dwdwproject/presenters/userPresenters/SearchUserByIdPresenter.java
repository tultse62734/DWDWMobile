package com.example.dwdwproject.presenters.userPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.activities.UserAssginDetailActivity;
import com.example.dwdwproject.repositories.userRepositories.UserRepositories;
import com.example.dwdwproject.repositories.userRepositories.UserRepositotiesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.userViews.GetUserView;

public class SearchUserByIdPresenter {
    private Context context;
    private GetUserView mGetUserView;
    private UserRepositories mUserRepositories;

    public SearchUserByIdPresenter(Context context, GetUserView mGetUserView) {
        this.context = context;
        this.mGetUserView = mGetUserView;
        this.mUserRepositories = new UserRepositotiesImpl();
    }
    public void searchUserById( String token, int userId){
        this.mUserRepositories.searchUserId(context, token, userId, new CallBackData<UserDTO>() {
            @Override
            public void onSucess(UserDTO userDTO) {
                mGetUserView.getUserSuccess(userDTO);
            }

            @Override
            public void onFail(String message) {
                mGetUserView.showError(message);
            }
        });
    }
}
