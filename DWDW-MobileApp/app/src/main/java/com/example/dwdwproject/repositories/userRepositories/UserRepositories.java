package com.example.dwdwproject.repositories.userRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.utils.CallBackData;

public interface UserRepositories {
    void getAllManager(Context context, String token, CallBackData<UserDTO>mCallBackData);
    void getAllWorkerFromLocation(Context context,String token,int locationId,CallBackData<UserDTO>mCallBackData);
    void createUser(Context mContext,String token,UserDTO mUserDTO,CallBackData<String> mCallBackData);
    void updateUserById(Context context,String token,UserDTO mUserDTO,CallBackData<String>mCallBackData);
    void deleteUserById(Context context,String token,int userId,CallBackData<String>mCallBackData);
}
