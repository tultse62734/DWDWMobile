package com.example.dwdwproject.repositories.shiftRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.utils.CallBackData;

import java.util.List;

public interface ShiftRepositories {
    void getAllShift(Context mContext,String token,CallBackData<List<ShiftDTO>> mCallBackData);
    void getShiftByManager(Context mContext,String token,CallBackData<List<ShiftDTO>> mCallBackData);
    void getShiftByWorker(Context mContext,String token,CallBackData<List<ShiftDTO>> mCallBackData);
    void createShift(Context mcontext,String token,ShiftDTO mShiftDTO,CallBackData<ShiftDTO> mCallBackData);
    void updateShift(Context mContext,String token,ShiftDTO mShiftDTO,CallBackData<ShiftDTO> callBackData);
    void updateShiftActive(Context mContext,String token,ShiftDTO mShiftDTO,CallBackData<ShiftDTO> callBackData);
}
