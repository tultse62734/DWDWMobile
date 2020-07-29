package com.example.dwdwproject.repositories.userRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.AssignUserDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.utils.CallBackData;

import java.util.List;
public interface UserRepositories {
    void getAll(Context context, String token, CallBackData<List<UserDTO>>mCallBackData);
    void getAllUserFromLocationByAdmin(Context context,String token,int locationId,CallBackData<List<UserDTO>>mCallBackData);
    void getAllWorkerFromLocationByManager(Context context,String token,int locationId,CallBackData<List<UserDTO>> mCallBackData);
    void createUser(Context mContext,String token,UserDTO mUserDTO,CallBackData<UserDTO> mCallBackData);
    void updateUserById(Context context,String token,UserDTO mUserDTO,CallBackData<UserDTO>mCallBackData);
    void deleteUserById(Context context,String token,int userId,CallBackData<String>mCallBackData);
    void assignUserToLocation(Context context, String token, AssignUserDTO assignUserDTO,CallBackData<AssignUserDTO> mCallBackData);
    void updateUserStatus(Context context ,String token,int userId,boolean isActive,CallBackData<UserDTO> mCallBackData);
}
