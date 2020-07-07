package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.presenters.roomLocalPresenter.GetUserToRoomPresenter;
import com.example.dwdwproject.rooms.entities.UserItemEntities;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.views.roomLocalViews.GetInfoUserView;

public class ProfileManageActivity extends AppCompatActivity implements View.OnClickListener, GetInfoUserView {
    private LinearLayout mLnlWorker,mLnlDevice,mBtnClose;
    private GetUserToRoomPresenter mGetUserToRoomPresenter;
    private String token;
    private UserDTO mUserDTO;
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
        mGetUserToRoomPresenter = new GetUserToRoomPresenter(ProfileManageActivity.this,getApplication(),this);
        mGetUserToRoomPresenter.getAccessToken();
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
    @Override
    public void getInforUserSuccess(UserItemEntities mUserItemEntities) {
        token = mUserItemEntities.getToken();
        mUserDTO = mUserItemEntities.getmUserDTO();
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(ProfileManageActivity.this,"Không lấy được dữ liệu");
    }
}
