package com.example.dwdwproject.presenters.recordsPresenters;

import android.app.Application;
import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.repositories.recordRepositories.RecordRepositories;
import com.example.dwdwproject.repositories.recordRepositories.RecordRepositoriesImpl;
import com.example.dwdwproject.rooms.managements.DWDWManagement;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.recordsViews.GetAllRecordsView;

import java.util.List;

public class GetRecordsByLocationIdAndTimePresenter {
    private Context mContext;
    private GetAllRecordsView mGetAllRecordsView;
    private DWDWManagement dwdwManagement;
    private RecordRepositories mRecordRepositories;

    public GetRecordsByLocationIdAndTimePresenter(Context mContext, Application mApplication, GetAllRecordsView mGetAllRecordsView) {
        this.mContext = mContext;
        this.mGetAllRecordsView = mGetAllRecordsView;
        this.dwdwManagement = new DWDWManagement(mApplication);
        this.mRecordRepositories = new RecordRepositoriesImpl();
    }
    public void getRecordsByLocationIdAndTime(String token, int locationID, String start, String end){
        this.mRecordRepositories.getRecordsByLocationIdAndTime(mContext,token, locationID, start, end, new CallBackData<List<RecordDTO>>() {

            @Override
            public void onSucess(List<RecordDTO> recordDTOS) {
                mGetAllRecordsView.getAllRecordSuccess(recordDTOS);
            }

            @Override
            public void onFail(String message) {
                mGetAllRecordsView.showError("Lấy dữ liệu không thành công");
            }
        });
    }

}
