package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.SharePreferenceUtils;

public class HomeManagerActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout mBtnMManage,mBtnLogout,mBtnProfile,mBtnDevice,mBtnAccident,mBtnShift,mBtnRoom;
    private LinearLayout mLnlProfile;
    private TextView mtxtTime,mTxtTitle;
    private String time,title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_manager);
        initVieẉ̣();
        initData();
    }
    private void initVieẉ̣(){
        mtxtTime = findViewById(R.id.txt_time_manager);
        mBtnMManage = findViewById(R.id.lnl_manage_worker);
        mBtnDevice = findViewById(R.id.lnl_manage_device);
        mBtnAccident = findViewById(R.id.lnl_manage_accident_manager);
        mBtnProfile  = findViewById(R.id.lnl_profile_manage_account);
        mBtnShift = findViewById(R.id.lnl_manage_shift);
        mBtnRoom = findViewById(R.id.lnl_manage_room);
        mLnlProfile = findViewById(R.id.lnl_info_profile_manager);
        mBtnLogout = findViewById(R.id.lnl_log_out_manage);
        mTxtTitle = findViewById(R.id.txt_title_home_manager);

    }
    private void initData(){
        time = BundleString.getSelectedDate(HomeManagerActivity.this);
        title = SharePreferenceUtils.getStringSharedPreference(HomeManagerActivity.this,BundleString.LOCATIONNAME);
        mTxtTitle.setText(title);
        mtxtTime.setText(time);
        mBtnLogout.setOnClickListener(this);
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
            case R.id.lnl_log_out_manage:
                showLogoutDialog();
                break;
        }
    }
    private void intentToManageWorker(){
        Intent intent = new Intent(HomeManagerActivity.this,ManageWorkerActivity.class);
        startActivity(intent);
    }
    private void intentToManageDevice(){
        Intent intent = new Intent(HomeManagerActivity.this,ManagerManageDeviceActivity.class);
        startActivity(intent);
    }
    private void intentToProfileActivity(){
        Intent intent = new Intent(HomeManagerActivity.this,ProfileManageActivity.class);
        startActivity(intent);
    }
    private void intentToManageAccidentActivity(){
        Intent intent = new Intent(HomeManagerActivity.this,ManageAccidentActivity.class);
        startActivity(intent);
    }
    private void intentToManageRoomActivity(){
        Intent intent = new Intent(HomeManagerActivity.this,ManagerManageRoomActivity.class);
        startActivity(intent);
    }
    private void intenToManageShiftActivty(){
        Intent intent = new Intent(HomeManagerActivity.this,ManagerShiftViewActivity.class);
        startActivity(intent);
    }
    private void showLogoutDialog() {
        final Dialog dialog = new Dialog(HomeManagerActivity.this);
        dialog.setContentView(R.layout.alert_dialog_notify_sign_out);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk = dialog.findViewById(R.id.btn_yes);
        Button buttonNo = dialog.findViewById(R.id.btn_no);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToLogOutActivity();

            }
        });
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void intentToLogOutActivity(){
        Intent intent = new Intent(HomeManagerActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}
