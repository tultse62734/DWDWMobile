package com.example.dwdwproject.repositories.recordRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.utils.CallBackData;

import java.util.List;

public interface RecordRepositories {
    void getAllRecord(Context context , String token, CallBackData<RecordDTO> mCallBackData);
    void getAllRecordManager(Context context ,int locationID,String token, CallBackData<RecordDTO> mCallBackData);
    void getRecordDetailById(Context  context,String token,int recordID,CallBackData<RecordDTO> mCallBackData );
    void getRecordsByLocationIdAndTime
            (Context mContext, String token, int locationID, String start, String end, CallBackData<List<RecordDTO>> mCallBackData);
}
