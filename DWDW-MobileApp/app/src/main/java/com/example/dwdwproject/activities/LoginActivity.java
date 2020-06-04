package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mBtnSignIn,mBtnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
            initView();
            initData();
        }
    private void initView(){
        mBtnSignUp = findViewById(R.id.lnl_sign_up);
        mBtnSignIn = findViewById(R.id.lnl_sign_in);
    }private void initData(){
        mBtnSignUp.setOnClickListener(this);
        mBtnSignIn.setOnClickListener(this);
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
                Intent intent1 = new Intent(LoginActivity.this,HomeAdminActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
