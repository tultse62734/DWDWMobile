package com.example.dwdwproject.presenters.recordsPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.repositories.recordRepositories.RecordRepositories;
import com.example.dwdwproject.repositories.recordRepositories.RecordRepositoriesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.recordsViews.GetAllRecordsView;
import com.example.dwdwproject.views.recordsViews.GetRecordView;

import java.util.List;

public class GetRecordByManager {
    private Context context;
    private GetAllRecordsView mGetAllRecordsView;
    private RecordRepositories mRecordRepositoriesl;

    public GetRecordByManager(Context context, GetAllRecordsView mGetAllRecordsView) {
        this.context = context;
        this.mGetAllRecordsView = mGetAllRecordsView;
        this.mRecordRepositoriesl = new RecordRepositoriesImpl();

    }
    public void getSleepRecordByLocationManager(String token,int workerId,int location,String date){
        this.mRecordRepositoriesl.getSleppRecordByManager(context, token, workerId, location, date, new CallBackData<List<RecordDTO>>() {
            @Override
            public void onSucess(List<RecordDTO> recordDTOS) {
                mGetAllRecordsView.getAllRecordSuccess(recordDTOS);
            }

            @Override
            public void onFail(String message) {
                mGetAllRecordsView.showError(message);
            }
        });
    }
    public void getDeniedRecordByLocationManager(String token,int workerId,int location,String date){
        this.mRecordRepositoriesl.getDeniedRecordByManager(context, token, workerId, location, date, new CallBackData<List<RecordDTO>>() {
            @Override
            public void onSucess(List<RecordDTO> recordDTOS) {
                mGetAllRecordsView.getAllRecordSuccess(recordDTOS);
            }

            @Override
            public void onFail(String message) {
                mGetAllRecordsView.showError(message);
            }
        });
    }
}
