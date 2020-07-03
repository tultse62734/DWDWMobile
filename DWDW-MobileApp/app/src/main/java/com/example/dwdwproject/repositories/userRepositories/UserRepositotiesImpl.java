package com.example.dwdwproject.repositories.userRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.utils.CallBackData;

public class UserRepositotiesImpl implements UserRepositories {
    @Override
    public void getAllManager(Context context, String token, CallBackData<UserDTO> mCallBackData) {

    }

    @Override
    public void getAllWorkerFromLocation(Context context, String token, int locationId, CallBackData<UserDTO> mCallBackData) {

    }

    @Override
    public void createUser(Context mContext, String token, UserDTO mUserDTO, CallBackData<String> mCallBackData) {

    }

    @Override
    public void updateUserById(Context context, String token, UserDTO mUserDTO, CallBackData<String> mCallBackData) {

    }

    @Override
    public void deleteUserById(Context context, String token, int userId, CallBackData<String> mCallBackData) {

    }
}
