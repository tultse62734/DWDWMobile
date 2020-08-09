package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.models.Accident;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.SharePreferenceUtils;

public class AccidentReportDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mBtnClose;
    private TextView mTxtRoom,mTxtDevice,mTxtLocation,mTxtDay,mTxtTime;
    private Accident mAccident;
    private TextView mTitle;
    private String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accident_report_detail);
        initView();
        initData();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_dismiss);
        mTxtRoom = findViewById(R.id.txt_record_room_detail);
        mTxtDevice = findViewById(R.id.txt_record_device_detail);
        mTxtDay = findViewById(R.id.txt_record_day_detail);
        mTxtLocation = findViewById(R.id.txt_record_location_detail);
        mTxtTime = findViewById(R.id.txt_record_time_detail);
        mTitle = findViewById(R.id.txt_title_report_detail);
    }
    private void initData(){
        title = SharePreferenceUtils.getStringSharedPreference(AccidentReportDetailActivity.this,BundleString.LOCATIONNAME);
        Bundle bundle = getIntent().getExtras();
        mAccident = (Accident) bundle.getSerializable(BundleString.RECORDDETAIL);
        mTxtRoom.setText(mAccident.getRoomAccident());
        mTxtLocation.setText(mAccident.getLocationAccident());
        mTxtDevice.setText(mAccident.getAccidentName());
        mTxtDay.setText(mAccident.getAccidentDate());
        mTxtTime.setText(mAccident.getTimeDate());
        mTitle.setText(title);
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
