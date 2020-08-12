package com.example.dwdwproject.views.recordsViews;

import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.views.BaseView;

public interface GetRecordView extends BaseView {
    void getRecodeSucessfull(RecordDTO mRecordDTO);
}
