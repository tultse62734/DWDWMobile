package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

public class HomeWorkerActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mBtnMManage,mBtnLogout,mBtnProfile,mBtnLocation,mBtnDevice;
    private LinearLayout mBtnmenu1,mBtnmenu2,mBtnmenu3,mBtnmenu4;
    private TapBarMenu tapBarMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_worker);
        initVieẉ̣();
        initData();
    }
    private void initVieẉ̣(){

    }
    private void initData(){

    }

    @Override
    public void onClick(View v) {

    }
}
