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
    private LinearLayout mBtnmenu1,mBtnmenu2,mBtnmenu3,mBtnmenu4;
    private TapBarMenu tapBarMenu;
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
        tapBarMenu = findViewById(R.id.tapBarMenu);
        mBtnDevice = findViewById(R.id.lnl_managa_device_home);
        mBtnLocation = findViewById(R.id.lnl_manage_location_home);
        mBtnmenu1 = tapBarMenu.findViewById(R.id.tap_menu_1);
        mBtnmenu2 = tapBarMenu.findViewById(R.id.tap_menu_2);
        mBtnmenu3 = tapBarMenu.findViewById(R.id.tap_menu_3);
        mBtnmenu4 = tapBarMenu.findViewById(R.id.tap_menu_4);
    }
    private void initData(){
        mBtnMManageWorker.setOnClickListener(this);
        mBtnLogout.setOnClickListener(this);
        mBtnProfile.setOnClickListener(this);
        tapBarMenu.setOnClickListener(this);
        mBtnDevice.setOnClickListener(this);
        mBtnLocation.setOnClickListener(this);
        mBtnmenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBarMenu.close();
                intentManageWorḳerActivity();
            }
        });
        mBtnmenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBarMenu.close();
                intentToManagaDeviceActivity();
            }
        });
        mBtnmenu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBarMenu.close();
                intentToProfileActivity();
            }
        });
        mBtnmenu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBarMenu.close();
                    intentToLocationActivity();
            }
        });
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.tapBarMenu:
                tapBarMenu.toggle();
                break;
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
        }
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
