package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.LoginDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.models.ReponseDTO;
import com.example.dwdwproject.presenters.GetUserInforTokenPresenter;
import com.example.dwdwproject.presenters.LoginPresenter;
import com.example.dwdwproject.presenters.roomLocalPresenter.AddUserToRoomPresenter;
import com.example.dwdwproject.rooms.entities.UserItemEntities;
import com.example.dwdwproject.views.GetUserInforTokenView;
import com.example.dwdwproject.views.LoginView;
import com.example.dwdwproject.views.roomLocalViews.AddUserToRoomView;
import com.google.firebase.iid.FirebaseInstanceId;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginView, AddUserToRoomView {
    private LinearLayout mBtnSignIn,mBtnSignUp;
    private String username,password;
    private EditText mEdtUsername,mEdtPassword;
    private LoginPresenter mLoginPresenter;
    private AddUserToRoomPresenter mAddUserToRoomPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
            initView();
            initData();
        }
    private void initView(){
        mEdtUsername = findViewById(R.id.edt_username);
        mEdtPassword = findViewById(R.id.edt_password);
        mBtnSignUp = findViewById(R.id.lnl_sign_up);
        mBtnSignIn = findViewById(R.id.lnl_sign_in);
    }
    private void initData(){
        mBtnSignUp.setOnClickListener(this);
        mBtnSignIn.setOnClickListener(this);
    }
    private void checkLogin(){
        username  = mEdtUsername.getText().toString();
        password = mEdtPassword.getText().toString();
        if(username.length()== 0 && username.equalsIgnoreCase("")){
            showErrorLoginDialog("Tài khoản không được để trống");
        }
        else if(password.length()==0 && password.equalsIgnoreCase("")){
            showErrorLoginDialog("Mẩu khẩu không được để trống");
        }
        else {
//            if(username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("123")){
//                intentToHomeAdminActivtity();
//            }
//
//            else if(username.equalsIgnoreCase("worker") && password.equalsIgnoreCase("123")){
//                intentToHomeWorkerActivity();
//            }
//
//            else if(username.equalsIgnoreCase("manager") && password.equalsIgnoreCase("123")){
//                intentToHomeManageActivity();
//            }
//            else {
//                showErrorLoginDialog("Tài khoản hoặc mật khẩu không chính xác");
//            }
            mLoginPresenter = new LoginPresenter(LoginActivity.this,this);
            LoginDTO mLoginDTO = new LoginDTO();
            mLoginDTO.setUsername(username);
            mLoginDTO.setPassword(password);
            mLoginPresenter.login(mLoginDTO);
        }
    }
    private void showErrorLoginDialog(String message) {
        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.alert_layout_notify_change_day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk = dialog.findViewById(R.id.btn_Ok_choose);
        TextView mTxtNotify = dialog.findViewById(R.id.txt_notify_choose_error);
        mTxtNotify.setText(message);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_sign_up:
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
                break;
            case R.id.lnl_sign_in:
                checkLogin();
                break;
        }
    }
    private void intentToHomeAdminActivtity(){
        Intent intent = new Intent(LoginActivity.this,HomeAdminActivity.class);
        startActivity(intent);
    }
    private void intentToHomeManageActivity(){
        Intent intent = new Intent(LoginActivity.this,ManagerChooseLocationActivity.class);
        startActivity(intent);
    }
    private void intentToHomeWorkerActivity(){
        Intent intent = new Intent(LoginActivity.this,HomeWorkerActivity.class);
        startActivity(intent);
    }

    @Override
    public void loginSuccessString̣̣̣(String token) {
        mAddUserToRoomPresenter = new AddUserToRoomPresenter(LoginActivity.this,getApplication(),this);
        mAddUserToRoomPresenter.getUserInfor(token);
    }

    @Override
    public void loginSuccess(ReponseDTO mReponseDTO) {
//        if(mReponseDTO!=null){ String deviveToken = FirebaseInstanceId.getInstance().getToken();
//           String token =  mReponseDTO.getToken();

        }

    @Override
    public void showError(String message) {
        showErrorLoginDialog("Đăng nhập không thành công");
    }
    private void intentToHomeActivityByRole(int role){
            if(role ==1){
                intentToHomeAdminActivtity();
            }
            else if(role ==3){
                intentToHomeWorkerActivity();
            }
            else if(role ==2){
                intentToHomeManageActivity();
            }
            else {
                showErrorLoginDialog("Không có role tồn tại");
            }
    }
    @Override
    public void addUserSuccesṣ̣(UserItemEntities mUserItemEntities) {
           intentToHomeActivityByRole(mUserItemEntities.getUser().getRoleId());
    }
}
