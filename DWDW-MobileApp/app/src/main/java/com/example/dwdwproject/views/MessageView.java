package com.example.dwdwproject.views;

import com.example.dwdwproject.ResponseDTOs.NotifyDTO;

import java.util.List;

public interface MessageView extends BaseView{
    void getListMessage(List<NotifyDTO> mNotifyDTOListx);
}
