package com.example.dwdwproject.views.recordsViews;

import com.example.dwdwproject.ResponseDTOs.ConfirmReasonDTO;
import com.example.dwdwproject.views.BaseView;

public interface ConfirmRecordView extends BaseView {
     void confirmRecordSuccess(ConfirmReasonDTO mConfirmReasonDTO);
}
