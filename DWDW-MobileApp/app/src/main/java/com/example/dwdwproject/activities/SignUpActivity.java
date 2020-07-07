package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.dwdwproject.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    initView();
    initData();
    }
    private void initView(){

    }
    private void initData(){

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){

        }
    }
}
