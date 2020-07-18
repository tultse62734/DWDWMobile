package com.example.dwdwproject.presenters.recordsPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.LocationRecord;
import com.example.dwdwproject.repositories.recordRepositories.RecordRepositories;
import com.example.dwdwproject.repositories.recordRepositories.RecordRepositoriesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.recordsViews.GetLocationRecordView;

import java.util.List;
public class GetLocationRecordPresenter {
    private Context mContext;
    private GetLocationRecordView mGetLocationRecordView;
    private RecordRepositories mRecordRepositories;
    public GetLocationRecordPresenter(Context mContext, GetLocationRecordView mGetLocationRecordView) {
        this.mContext = mContext;
        this.mGetLocationRecordView = mGetLocationRecordView;
        this.mRecordRepositories = new RecordRepositoriesImpl();
    }
    public void getLocationRecord(String token){
        mRecordRepositories.getLocationRecord(mContext, token, new CallBackData<List<LocationRecord>>() {
            @Override
            public void onSucess(List<LocationRecord> locationRecords) {
                mGetLocationRecordView.getLocationRecordSuccess(locationRecords);
            }

            @Override
            public void onFail(String message) {
                    mGetLocationRecordView.showError(message);
            }
        });
    }
}
