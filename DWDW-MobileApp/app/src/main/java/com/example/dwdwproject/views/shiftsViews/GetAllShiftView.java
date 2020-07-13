package com.example.dwdwproject.views.shiftsViews;

import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.views.BaseView;

import java.util.List;

public interface GetAllShiftView extends BaseView {
    void getAllShiftSuccess(List<ShiftDTO> mShiftDTOList);
}
