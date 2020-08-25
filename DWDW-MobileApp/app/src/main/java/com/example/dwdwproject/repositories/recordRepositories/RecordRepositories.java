package com.example.dwdwproject.repositories.recordRepositories;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.ConfirmReasonDTO;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.ResponseDTOs.LocationRecord;
import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.utils.CallBackData;

import java.util.List;

public interface RecordRepositories {
    void getAllRecord(Context context, String token, CallBackData<RecordDTO> mCallBackData);
    void getRecordDetailById(Context context, String token, int recordID, CallBackData<RecordDTO> mCallBackData);
    void getRecordByLocationId(Context context,String token,int locationId,CallBackData<List<RecordDTO>>mCallBackData);
    void getLocationRecord(Context context, String token,String startDate,String endDate, CallBackData<List<LocationRecord>> mCallBackData);
    void getRecordsByLocationIdAndTime
            (Context mContext, String token, int locationID, String start, String end, CallBackData<List<RecordDTO>> mCallBackData);
    void getRecordByWorkerDate(Context context,String token,int workerID,String date,CallBackData<List<RecordDTO>> mCallBackData);
    void getRecordByWorker(Context context,String token,int locationId,String date,CallBackData<List<RecordDTO>> mCallBackData);
    void getConfirmRecordByWorker(Context context,String token,int locationId,String date,CallBackData<List<RecordDTO>> mCallBackData);
    void getUnknowRecordByWorker(Context context,String token,int locationId,String date,CallBackData<List<RecordDTO>> mCallBackData);
    void confirmRecordByWorker(Context context, String token, ConfirmReasonDTO confirmReasonDTO,CallBackData<ConfirmReasonDTO> mCallBackData);
}
