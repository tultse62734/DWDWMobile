package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO1;
import com.example.dwdwproject.presenters.GetUserInforTokenPresenter;
import com.example.dwdwproject.presenters.roomLocalPresenter.GetUserToRoomPresenter;
import com.example.dwdwproject.rooms.entities.UserItemEntities;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.GetUserInforTokenView;
import com.example.dwdwproject.views.roomLocalViews.GetInfoUserView;

public class ProfileManageActivity extends AppCompatActivity implements View.OnClickListener,GetUserInforTokenView {
    private LinearLayout mBtnClose,mBtnUpdate;
    private String token;
    private TextView mTxtNameProfile,mTxtBirthDayProfile,mTxtPhoneProfile,mTxtRoleProfile;
    private UserDTO1 userDTO;
    private GetUserInforTokenPresenter mGetUserInforTokenPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_manage);
        getDateServer();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_manage_profile);
        mTxtNameProfile = findViewById(R.id.txt_name_profile);
        mTxtBirthDayProfile = findViewById(R.id.txt_email_profile);
        mTxtPhoneProfile = findViewById(R.id.txt_phone_profile);
        mTxtRoleProfile = findViewById(R.id.txt_role_profile);
        mBtnUpdate = findViewById(R.id.lnl_update_account);
    }
    private void getDateServer(){
        token = SharePreferenceUtils.getStringSharedPreference(ProfileManageActivity.this, BundleString.TOKEN);
        mGetUserInforTokenPresenter = new GetUserInforTokenPresenter(ProfileManageActivity.this,this);
        mGetUserInforTokenPresenter.getInforToken(token);
    }
    private void initData(){
        mTxtNameProfile.setText(userDTO.getUserName()+"");
        mTxtBirthDayProfile.setText(userDTO.getDateOfBirth()+"");
        mTxtPhoneProfile.setText(userDTO.getPhone()+"");
        if(userDTO.getRoleId() == 1){
            mTxtRoleProfile.setText("Admin");
        }
        if(userDTO.getRoleId() == 2){
            mTxtRoleProfile.setText("Manager");
        }
        if(userDTO.getRoleId() == 3){
            mTxtRoleProfile.setText("Worker");
        }

        mBtnClose.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_manage_profile:
                finish();
                break;
            case R.id.lnl_update_account:
                intentToUpdateAccount(userDTO);
                break;
        }
    }
    private void intentToUpdateAccount(UserDTO1 userDTO){
        Intent intent = new Intent(ProfileManageActivity.this,UpdateInforAccountActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BundleString.ACCOUNT,userDTO);
        intent.putExtras(bundle);
        startActivity(intent);
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(ProfileManageActivity.this,"Data fail");
    }
    @Override
    public void getInforSuccess(UserDTO1 mUserDTO) {
        userDTO = mUserDTO;
        initView();
        initData();
    }
}
