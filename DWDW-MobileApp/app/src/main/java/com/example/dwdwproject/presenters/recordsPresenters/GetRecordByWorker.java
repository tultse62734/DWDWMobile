package com.example.dwdwproject.presenters.recordsPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.repositories.recordRepositories.RecordRepositories;
import com.example.dwdwproject.repositories.recordRepositories.RecordRepositoriesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.recordsViews.GetAllRecordsView;

import java.util.List;

public class GetRecordByWorker {
    private Context mContext;
    private RecordRepositories mRecordRepositories;
    private GetAllRecordsView mGetAllRecordsView;

    public GetRecordByWorker(Context mContext, GetAllRecordsView mGetAllRecordsView) {
        this.mContext = mContext;
        this.mGetAllRecordsView = mGetAllRecordsView;
        this.mRecordRepositories = new RecordRepositoriesImpl();
    }
    public void getRecordByWorker(String token,int locationId,String date){
        this.mRecordRepositories.getRecordByWorker(mContext, token, locationId, date, new CallBackData<List<RecordDTO>>() {
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
