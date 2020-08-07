package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.adapters.RoomAdapter;
import com.example.dwdwproject.models.Room;
import com.example.dwdwproject.presenters.roomPresenters.GetAllRoomFromLocationPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.roomViews.GetListRoomView;

import java.util.ArrayList;
import java.util.List;

public class ManagerManageRoomActivity extends AppCompatActivity implements View.OnClickListener, GetListRoomView {
    private RecyclerView mRecyclerView;
    private RoomAdapter mRoomAdapter;
    private List<Room> mRoomList;
    private String token;
    private LinearLayout mBtnClose;
    private GetAllRoomFromLocationPresenter mGetAllRoomFromLocationPresenter;
    private int locationId;
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
//        mRoomList = new ArrayList<>();
//        mRoomList.add(new Room(1,"100","12-11-2020",true));
//        mRoomList.add(new Room(2,"200","12-11-2020",true));
//        mRoomList.add(new Room(3,"300","12-11-2020",true));
//        mRoomList.add(new Room(4,"300","12-11-2020",true));
//        mRoomList.add(new Room(5,"400","12-11-2020",true));
//        updateUI();
        token = SharePreferenceUtils.getStringSharedPreference(ManagerManageRoomActivity.this,BundleString.TOKEN);
        locationId = SharePreferenceUtils.getIntSharedPreference(ManagerManageRoomActivity.this, BundleString.LOCATIONID);
        mGetAllRoomFromLocationPresenter = new GetAllRoomFromLocationPresenter(ManagerManageRoomActivity.this,this);
        mGetAllRoomFromLocationPresenter.getAllRoomFromLocationByManager(token,locationId);
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

    @Override
    public void getListRoomSuccess(List<RoomDTO> mRoomDTOList) {
        if(mRoomDTOList!=null){
            mRoomList = new ArrayList<>();
            for (int i = 0; i < mRoomDTOList.size(); i++) {

                int roomId = mRoomDTOList.get(i).getRoomId();
                String roomName = mRoomDTOList.get(i).getRoomCode();
                boolean isActive = mRoomDTOList.get(i).isActive();
                mRoomList.add(new Room(roomId,roomName,isActive));
            }
            updateUI();
        }
    }

    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(ManagerManageRoomActivity.this,"Data is error");
    }
}
