package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.NotifyDTO;
import com.example.dwdwproject.adapters.NotifyMessageAdapter;
import com.example.dwdwproject.models.NotifyMessage;
import com.example.dwdwproject.presenters.GetMessagePresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.MessageView;

import java.util.ArrayList;
import java.util.List;

public class NotifyMessageActivity extends AppCompatActivity implements View.OnClickListener, MessageView {
    private RecyclerView mRecyclerView;
    private List<NotifyMessage> messageList;
    private NotifyMessageAdapter notifyMessageAdapter;
    private String token ;
    private GetMessagePresenter getMessagePresenter;
    private LinearLayout mBtnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_message);
        initView();
        initData();
    }
    private void initData(){
        token = SharePreferenceUtils.getStringSharedPreference(NotifyMessageActivity.this,BundleString.TOKEN);
        mBtnClose.setOnClickListener(this);
        getMessagePresenter = new GetMessagePresenter(NotifyMessageActivity.this,this);
        getMessagePresenter.getMessage(token);
    }
    private void initView(){
        mRecyclerView = findViewById(R.id.rcv_notify_message);
        mBtnClose = findViewById(R.id.lnl_close_notify_message);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void updateUI(){
        if(notifyMessageAdapter ==null){
            notifyMessageAdapter = new NotifyMessageAdapter(NotifyMessageActivity.this,messageList);
            mRecyclerView.setAdapter(notifyMessageAdapter);
        }else{
            notifyMessageAdapter.notify(messageList);
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_notify_message:
                finish();
                break;
        }
    }
    @Override
    public void getListMessage(List<NotifyDTO> mNotifyDTOListx) {
        if(mNotifyDTOListx!=null){
            messageList = new ArrayList<>();
            for (int i = 0; i < mNotifyDTOListx.size() ; i++) {
                String title = mNotifyDTOListx.get(i).getMessageTitle();
                String content = mNotifyDTOListx.get(i).getMessageContent();
                String minute = mNotifyDTOListx.get(i).getMessageTime();
                messageList.add(new NotifyMessage(title,content,minute));
            }
            updateUI();
        }
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(NotifyMessageActivity.this,message);
    }
}