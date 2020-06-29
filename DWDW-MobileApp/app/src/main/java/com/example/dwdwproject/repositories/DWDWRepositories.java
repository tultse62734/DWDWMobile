package com.example.dwdwproject.repositories;

import android.content.Context;

import com.example.dwdwproject.models.ReponseDTO;
import com.example.dwdwproject.models.User;
import com.example.dwdwproject.utils.CallBackData;

public interface DWDWRepositories {
    void Login(Context mContext, String username, String password, CallBackData<ReponseDTO>callBackData);
}
