package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;

public class AccidentReportDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mBtnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident_report_detail);
        initView();
        initData();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_dismiss);

    }
    private void initData(){
        mBtnClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_dismiss:
                finish();
                break;
        }
    }
}
