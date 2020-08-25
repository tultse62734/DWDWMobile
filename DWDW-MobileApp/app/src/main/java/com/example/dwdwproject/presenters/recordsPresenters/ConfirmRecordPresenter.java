package com.example.dwdwproject.presenters.recordsPresenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.ConfirmReasonDTO;
import com.example.dwdwproject.repositories.recordRepositories.RecordRepositories;
import com.example.dwdwproject.repositories.recordRepositories.RecordRepositoriesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.recordsViews.ConfirmRecordView;

public class ConfirmRecordPresenter {
    private Context context;
    private ConfirmRecordView mConfirmRecordView;
    private RecordRepositories mRecordRepositories;
    public ConfirmRecordPresenter(Context context, ConfirmRecordView mConfirmRecordView) {
        this.context = context;
        this.mConfirmRecordView = mConfirmRecordView;
        this.mRecordRepositories = new RecordRepositoriesImpl();
    }
    public  void confirmRecord(String token, ConfirmReasonDTO mConfirmReasonDTO){
        mRecordRepositories.confirmRecordByWorker(context, token, mConfirmReasonDTO, new CallBackData<ConfirmReasonDTO>() {
            @Override
            public void onSucess(ConfirmReasonDTO confirmReasonDTO) {
                mConfirmRecordView.confirmRecordSuccess(confirmReasonDTO);
            }

            @Override
            public void onFail(String message) {
            mConfirmRecordView.showError(message);
            }
        });
    }
}
