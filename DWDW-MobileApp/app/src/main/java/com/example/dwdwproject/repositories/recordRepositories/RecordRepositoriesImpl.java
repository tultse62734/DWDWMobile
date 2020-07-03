package com.example.dwdwproject.repositories.recordRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.utils.CallBackData;

public class RecordRepositoriesImpl implements RecordRepositories {
    @Override
    public void getAllRecord(Context context, String token, CallBackData<RecordDTO> mCallBackData) {

    }

    @Override
    public void getAllRecordManager(Context context, int locationID, String token, CallBackData<RecordDTO> mCallBackData) {

    }

    @Override
    public void getRecordDetailById(Context context, String token, int recordID, CallBackData<RecordDTO> mCallBackData) {

    }
}
