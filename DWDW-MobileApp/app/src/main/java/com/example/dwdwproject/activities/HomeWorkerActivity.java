package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

public class HomeWorkerActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mBtnMManage,mBtnLogout,mBtnProfile,mBtnDevice,mBtnAccident,mBtnShift,mBtnRoom;
    private LinearLayout mLnlProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_worker);
        initVieẉ̣();
        initData();
    }
    private void initVieẉ̣(){
        mBtnMManage = findViewById(R.id.lnl_manage_worker);
        mBtnDevice = findViewById(R.id.lnl_manage_device);
        mBtnAccident = findViewById(R.id.lnl_manage_accident_manager);
        mBtnProfile  = findViewById(R.id.lnl_profile_manage_account);
        mBtnShift = findViewById(R.id.lnl_manage_shift);
        mBtnRoom = findViewById(R.id.lnl_manage_room);
        mLnlProfile = findViewById(R.id.lnl_info_profile_manager);
    }
    private void initData(){
        mBtnRoom.setOnClickListener(this);
        mBtnShift.setOnClickListener(this);
        mBtnProfile.setOnClickListener(this);
        mBtnAccident.setOnClickListener(this);
        mBtnDevice.setOnClickListener(this);
        mBtnMManage.setOnClickListener(this);
        mLnlProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_manage_worker:
                intentToManageWorker();
                break;
            case R.id.lnl_manage_device:
                intentToManageDevice();
                break;
            case R.id.lnl_profile_manage_account:
                intentToProfileActivity();
                break;
            case R.id.lnl_manage_accident_manager:
                intentToManageAccidentActivity();
                break;
            case R.id.lnl_manage_room:
                intentToManageRoomActivity();
                break;
            case R.id.lnl_manage_shift:
                intenToManageShiftActivty();
                break;
            case R.id.lnl_info_profile_manager:
                intentToProfileActivity();
                break;
        }
    }
    private void intentToManageWorker(){
        Intent intent = new Intent(HomeWorkerActivity.this,ManageManagerActivity.class);
        startActivity(intent);
    }
    private void intentToManageDevice(){
        Intent intent = new Intent(HomeWorkerActivity.this,ManagerManageDeviceActivity.class);
        startActivity(intent);
    }

    private void intentToProfileActivity(){
        Intent intent = new Intent(HomeWorkerActivity.this,ProfileManageActivity.class);
        startActivity(intent);
    }
    private void intentToManageAccidentActivity(){
        Intent intent = new Intent(HomeWorkerActivity.this,ManageAccidentActivity.class);
        startActivity(intent);
    }
    private void intentToManageRoomActivity(){
        Intent intent = new Intent(HomeWorkerActivity.this,ManagerManageRoomActivity.class);
        startActivity(intent);
    }
    private void intenToManageShiftActivty(){
        Intent intent = new Intent(HomeWorkerActivity.this,ManagerShiftViewActivity.class);
        startActivity(intent);
    }
}
