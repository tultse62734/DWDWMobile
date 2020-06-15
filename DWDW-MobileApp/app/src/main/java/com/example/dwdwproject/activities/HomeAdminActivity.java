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

import com.example.dwdwproject.R;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

public class HomeAdminActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout mBtnMManageWorker,mBtnLogout,mBtnProfile,mBtnLocation,mBtnDevice;
    private LinearLayout mBtnManageRoom,mBtnManageAccident,mBtnManageProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        initView();
        initData();
    }
    private void initView(){
        mBtnMManageWorker = findViewById(R.id.lnl_manage_management);
        mBtnLogout = findViewById(R.id.lnl_log_out);
        mBtnProfile = findViewById(R.id.lnl_profile);
        mBtnDevice = findViewById(R.id.lnl_managa_device_home);
        mBtnLocation = findViewById(R.id.lnl_manage_location_home);
        mBtnManageRoom = findViewById(R.id.lnl_manage_room_admin);
        mBtnManageAccident = findViewById(R.id.lnl_manage_accident);
        mBtnManageProfile = findViewById(R.id.lnl_manage_profile);
    }
    private void initData(){
        mBtnManageProfile.setOnClickListener(this);
        mBtnMManageWorker.setOnClickListener(this);
        mBtnLogout.setOnClickListener(this);
        mBtnProfile.setOnClickListener(this);
        mBtnManageRoom.setOnClickListener(this);
        mBtnDevice.setOnClickListener(this);
        mBtnLocation.setOnClickListener(this);
        mBtnManageAccident.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_manage_management:
                intentManageWorḳerActivity();
                break;
            case R.id.lnl_log_out:
                showLogoutDialog();
                break;
            case R.id.lnl_managa_device_home:
                intentToManagaDeviceActivity();
                break;
            case R.id.lnl_manage_location_home:
                intentToLocationActivity();
                break;
            case R.id.lnl_profile:
                intentToProfileActivity();
                break;
            case R.id.lnl_manage_room_admin:
                intentToManagerRoomActivity();
                break;
            case R.id.lnl_manage_accident:
                intentToManageAccidentActivity();
                break;
            case R.id.lnl_manage_profile:
                intentToProfileActivity();
                break;
        }
    }
    private void intentToManageAccidentActivity(){
        Intent intent = new Intent(HomeAdminActivity.this,ManageAccidentActivity.class);
        startActivity(intent);
    }
    private void intentManageWorḳerActivity(){
        Intent intent = new Intent(HomeAdminActivity.this, ManageManagerActivity.class);
        startActivity(intent);
    }
    private void intentToLogOutActivity(){
        Intent intent = new Intent(HomeAdminActivity.this,LoginActivity.class);
        startActivity(intent);
    }
    private void intentToProfileActivity(){
        Intent intent = new Intent(HomeAdminActivity.this,ProfileManageActivity.class);
        startActivity(intent);
    }
    private void intentToManagerRoomActivity(){
        Intent intent = new Intent(HomeAdminActivity.this,ManageRoomActivity.class);
        startActivity(intent);
    }
    private void intentToLocationActivity(){
        Intent intent = new Intent(HomeAdminActivity.this, ManageLocationActivity.class);
        startActivity(intent);
    }
    private void intentToManagaDeviceActivity(){
        Intent intent = new Intent(HomeAdminActivity.this,ManageDeviceActivity.class);
        startActivity(intent);
    }
    private void showLogoutDialog() {
        final Dialog dialog = new Dialog(HomeAdminActivity.this);
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


}
