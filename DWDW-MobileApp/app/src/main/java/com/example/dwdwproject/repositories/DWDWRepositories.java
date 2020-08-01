package com.example.dwdwproject.repositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LoginDTO;
import com.example.dwdwproject.ResponseDTOs.NotifyDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.models.ReponseDTO;
import com.example.dwdwproject.utils.CallBackData;

import java.util.List;

public interface DWDWRepositories {
    void Login(Context mContext, LoginDTO mLoginDTO, CallBackData<ReponseDTO>callBackData);
    void Login2(Context mContext, LoginDTO mLoginDTO, CallBackData<String>callBackData);
    void GetUsetInfor(Context context,String token, CallBackData<UserDTO> mCallBackData);
    void UpdateAccout(Context mContext,String token ,UserDTO mUserDTO,CallBackData<UserDTO> mCallBackData);
    void getNotify(Context context, String token, CallBackData<List<NotifyDTO>> mCallBackData);
}
