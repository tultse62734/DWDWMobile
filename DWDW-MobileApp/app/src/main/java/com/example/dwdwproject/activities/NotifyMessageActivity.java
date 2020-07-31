package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dwdwproject.R;
import com.example.dwdwproject.models.NotifyMessage;

import java.util.List;

public class NotifyMessageActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<NotifyMessage> messageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_message);
        initView();
        initData();
    }
    private void initData(){

    }
    private void initView(){
        updateUI();
    }
    private void updateUI(){

    }
}