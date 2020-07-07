package com.example.dwdwproject.repositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LoginDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.models.ReponseDTO;
import com.example.dwdwproject.models.User;
import com.example.dwdwproject.utils.CallBackData;

public interface DWDWRepositories {
    void Login(Context mContext, LoginDTO mLoginDTO, CallBackData<ReponseDTO>callBackData);
    void GetUsetInfor(Context context,String token, CallBackData<UserDTO> mCallBackData);
}
