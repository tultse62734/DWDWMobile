package com.example.dwdwproject.presenters.recordsPresenters;
import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.repositories.devicesRepositories.DeviceRepositories;
import com.example.dwdwproject.repositories.devicesRepositories.DeviceRepositoriesImpl;
import com.example.dwdwproject.repositories.recordRepositories.RecordRepositories;
import com.example.dwdwproject.repositories.recordRepositories.RecordRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.devicesViews.GetAllDeviceView;
import com.example.dwdwproject.views.recordsViews.GetAllRecordsView;

import java.util.List;
public class GetRecordsByLocationIdPresenter {
    private Context mContext;
    private GetAllRecordsView mGetAllRecordsView;
    private RecordRepositories mRecordRepositories;
    public GetRecordsByLocationIdPresenter(Context mContext, GetAllRecordsView mGetAllRecordsView) {
        this.mContext = mContext;
        this.mGetAllRecordsView = mGetAllRecordsView;
        this.mRecordRepositories = new RecordRepositoriesImpl();
    }
    public void getRecordsByLocationId(String token, int locationID){
        this.mRecordRepositories.getRecordByLocationId(mContext, token, locationID, new CallBackData<List<RecordDTO>>() {
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
    public void getRecordByWorkerDate(String token,int workerId,String date){
        this.mRecordRepositories.getRecordByWorkerDate(mContext, token, workerId, date, new CallBackData<List<RecordDTO>>() {
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
