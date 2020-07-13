package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.presenters.GetUserInforTokenPresenter;
import com.example.dwdwproject.presenters.roomLocalPresenter.GetUserToRoomPresenter;
import com.example.dwdwproject.rooms.entities.UserItemEntities;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.GetUserInforTokenView;
import com.example.dwdwproject.views.roomLocalViews.GetInfoUserView;

public class ProfileManageActivity extends AppCompatActivity implements View.OnClickListener,GetInfoUserView {
    private LinearLayout mLnlWorker,mLnlDevice,mBtnClose;
    private String token;
    private TextView mTxtNameProfile,mTxtBirthDayProfile,mTxtPhoneProfile,mTxtRoleProfile;
    private UserDTO mUserDTO;

    private GetUserInforTokenPresenter mGetUserInforTokenPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_manage);
        getDateServer();
    }
    private void initView(){
        mLnlWorker = findViewById(R.id.lnl_profile_admin_worker);
        mLnlDevice = findViewById(R.id.lnl_profile_admin_device);
        mBtnClose = findViewById(R.id.lnl_close_manage_profile);
        mTxtNameProfile = findViewById(R.id.txt_name_profile);
        mTxtBirthDayProfile = findViewById(R.id.txt_email_profile);
        mTxtPhoneProfile = findViewById(R.id.txt_phone_profile);
        mTxtRoleProfile = findViewById(R.id.txt_role_profile);
    }
    private void getDateServer(){
        token = SharePreferenceUtils.getStringSharedPreference(ProfileManageActivity.this, BundleString.TOKEN);
        mGetUserInforTokenPresenter = new GetUserInforTokenPresenter(ProfileManageActivity.this, (GetUserInforTokenView) this);
        mGetUserInforTokenPresenter.getInforToken(token);
    }
    private void initData(){
        mTxtNameProfile.setText(mUserDTO.getUserName()+"");
        mTxtBirthDayProfile.setText(mUserDTO.getDateOfBirth()+"");
        mTxtPhoneProfile.setText(mUserDTO.getPhone()+"");
        if(mUserDTO.getRoleId() == 1){
            mTxtRoleProfile.setText("Admin");
        }
        if(mUserDTO.getRoleId() == 2){
            mTxtRoleProfile.setText("Manager");
        }
        if(mUserDTO.getRoleId() == 3){
            mTxtRoleProfile.setText("Worker");
        }
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

    @Override
    public void getInforUserSuccess(UserItemEntities mUserItemEntities) {
        mUserDTO = mUserItemEntities.getUser();
        initView();
        initData();
    }

    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(ProfileManageActivity.this,"Data fail");
    }
}
