package com.example.dwdwproject.activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class HomeAdminActivity extends AppCompatActivity implements View.OnClickListener{
    private LinearLayout mBtnMManage,mBtnLogout,mBtnProfile;
    private TapBarMenu tapBarMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
        initView();
        initData();
    }
    private void initView(){
        mBtnMManage = findViewById(R.id.lnl_manage_management);
        mBtnLogout = findViewById(R.id.lnl_log_out);
        mBtnProfile = findViewById(R.id.lnl_profile);
        tapBarMenu = findViewById(R.id.tapBarMenu);

    }
    private void initData(){
        mBtnMManage.setOnClickListener(this);
        mBtnLogout.setOnClickListener(this);
        mBtnProfile.setOnClickListener(this);
        tapBarMenu.setOnClickListener(this);
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
                intentToLogOutActivity();
                break;
            case R.id.lnl_profile:
                intentToProfileActivity();
                break;
        }
    }
    private void intentManageWorḳerActivity(){
        Intent intent = new Intent(HomeAdminActivity.this,ManageWorkerActivity.class);
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
    private void intentToNewsAdminActivity(){
        Intent intent = new Intent(HomeAdminActivity.this,NewsAdminActivity.class);
        startActivity(intent);
    }
    private void intentToManagaDeviceActivity(){
        Intent intent = new Intent(HomeAdminActivity.this,ManageDeviceActivity.class);
        startActivity(intent);
    }
}
