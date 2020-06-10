package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;

public class ProfileManageActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLnlWorker,mLnlDevice,mBtnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_manage);
        initView();
        initData();
    }
    private void initView(){
        mLnlWorker = findViewById(R.id.lnl_profile_admin_worker);
        mLnlDevice = findViewById(R.id.lnl_profile_admin_device);
        mBtnClose = findViewById(R.id.lnl_close_manage_profile);
    }
    private void initData(){
        mLnlWorker.setOnClickListener(this);
        mLnlDevice.setOnClickListener(this);
        mBtnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_profile_admin_worker:
                intentToManageWorkerActivty();
                break;
            case R.id.lnl_profile_admin_device:
                intentToManageDeviceActivity();
                break;
            case R.id.lnl_close_manage_profile:
                finish();
                break;
        }
    }
    private void intentToManageWorkerActivty(){
            Intent intent = new Intent(ProfileManageActivity.this, ManageManagerActivity.class);
            startActivity(intent);
    }
    private void intentToManageDeviceActivity(){
        Intent intent = new Intent(ProfileManageActivity.this,ManageDeviceActivity.class);
        startActivity(intent);
    }
}
