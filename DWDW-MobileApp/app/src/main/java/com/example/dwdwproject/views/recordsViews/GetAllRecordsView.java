package com.example.dwdwproject.views.recordsViews;

import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.views.BaseView;

import java.util.List;

public interface GetAllRecordsView extends BaseView {
    void getAllRecordSuccess(List<RecordDTO> mRecordDTOList);
}
