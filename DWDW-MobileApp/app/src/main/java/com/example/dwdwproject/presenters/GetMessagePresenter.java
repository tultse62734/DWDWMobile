package com.example.dwdwproject.presenters;

import android.content.Context;

import com.example.dwdwproject.ResponseDTOs.NotifyDTO;
import com.example.dwdwproject.repositories.DWDWRepositories;
import com.example.dwdwproject.repositories.DWDWRepositoriesImpl;
import com.example.dwdwproject.utils.CallBackData;
import com.example.dwdwproject.views.MessageView;

import java.util.List;

public class GetMessagePresenter {
    private Context context;
    private MessageView messageView;
    private DWDWRepositories mDwdwRepositories;

    public GetMessagePresenter(Context context, MessageView messageView) {
        this.context = context;
        this.messageView = messageView;
        this.mDwdwRepositories = new DWDWRepositoriesImpl();
    }
    public void getMessage(String token){
        this.mDwdwRepositories.getNotify(context, token, new CallBackData<List<NotifyDTO>>() {
            @Override
            public void onSucess(List<NotifyDTO> notifyDTO) {
                messageView.getListMessage(notifyDTO);
            }

            @Override
            public void onFail(String message) {
                    messageView.showError(message);
            }
        });
    }
}
