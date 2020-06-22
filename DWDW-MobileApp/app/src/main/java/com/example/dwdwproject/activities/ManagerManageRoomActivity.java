package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.adapters.RoomAdapter;
import com.example.dwdwproject.models.Room;

import java.util.ArrayList;
import java.util.List;

public class ManagerManageRoomActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private RoomAdapter mRoomAdapter;
    private List<Room> mRoomList;
    private LinearLayout mBtnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_manage_room);
        initView();
        initData();
    }
    private void initView(){
        mRecyclerView = findViewById(R.id.rcv_manager_manage_room);
        mBtnClose = findViewById(R.id.lnl_close_manager_manage_room);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mBtnClose.setOnClickListener(this);
        mRoomList = new ArrayList<>();
        updateUI();
    }
    private void updateUI(){
        if(mRoomAdapter == null){
            mRoomAdapter = new RoomAdapter(ManagerManageRoomActivity.this,mRoomList);
            mRecyclerView.setAdapter(mRoomAdapter);
            mRoomAdapter.OnItemClickListerner(new RoomAdapter.OnItemClickListerner() {
                @Override
                public void onItemClick(int pos) {
                    Intent intent = new Intent(ManagerManageRoomActivity.this,AdminRoomDetailActivity.class);
                    startActivity(intent);
                }
            });
        }else {
            mRoomAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_manager_manage_room:
                finish();
                break;
        }
    }
}
