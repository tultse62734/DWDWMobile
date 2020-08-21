package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.models.Accident;
import com.example.dwdwproject.presenters.recordsPresenters.GetRecordsByLocationIdAndTimePresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.ParseBytes;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.recordsViews.GetRecordView;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class WorkerReportDetailActivity extends AppCompatActivity implements View.OnClickListener , GetRecordView {
    private LinearLayout mBtnClose,mBtnConfirm;
    private TextView mTxtRoom,mTxtDevice,mTxtLocation,mTxtDay,mTxtTime;
    private Accident mAccident;
    private TextView mTitle,mTxtReasion;
    private String title,token;
    private RecordDTO recordDTO;
    private ImageView mImageView;
    private GetRecordsByLocationIdAndTimePresenter mIdAndTimePresenter;
    Charset charset = StandardCharsets.UTF_16;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_report_detail);
        initView();
        initData();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_worker_dismiss_record);
        mTxtRoom = findViewById(R.id.txt_worker_record_room_detail);
        mTxtDevice = findViewById(R.id.txt_worker_record_device_detail);
        mTxtDay = findViewById(R.id.txt_worker_record_day_detail);
        mTxtLocation = findViewById(R.id.txt_worker_record_location_detail);
        mTxtTime = findViewById(R.id.txt_worker_record_time_detail);
        mTitle = findViewById(R.id.txt_title_worker_report_detail);
        mImageView = findViewById(R.id.image_worker_accident_detail);
        mTxtReasion = findViewById(R.id.txt_worker_record_reasion);
        mBtnConfirm = findViewById(R.id.lnl_btn_confirm_record);
    }
    private void initData(){
        title = SharePreferenceUtils.getStringSharedPreference(WorkerReportDetailActivity.this, BundleString.LOCATIONNAME);
        Bundle bundle = getIntent().getExtras();
        mAccident = (Accident) bundle.getSerializable(BundleString.RECORDDETAIL);
        token = SharePreferenceUtils.getStringSharedPreference(WorkerReportDetailActivity.this,BundleString.TOKEN);
        mIdAndTimePresenter = new GetRecordsByLocationIdAndTimePresenter(WorkerReportDetailActivity.this,this);
        mBtnClose.setOnClickListener(this);
        mBtnConfirm.setOnClickListener(this);
        mIdAndTimePresenter.getRecordById(token,mAccident.getAccidentId());
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_worker_dismiss_record:
                finish();
                break;
            case R.id.lnl_btn_confirm_record:
                break;
        }
    }
    @Override
    public void getRecodeSucessfull(RecordDTO mRecordDTO) {
        this.recordDTO = mRecordDTO;
        mTxtRoom.setText(recordDTO.getRoomCode());
        mTxtLocation.setText(mAccident.getLocationAccident());
        mTxtDevice.setText(recordDTO.getFullname());
        mTxtDay.setText(mAccident.getAccidentDate());
        mTxtTime.setText(mAccident.getTimeDate());
        mTitle.setText(title);
        if(recordDTO.getImage()!=null){
            mImageView.setImageBitmap(ParseBytes.StringToBitMap(recordDTO.getImage()));
        }
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(WorkerReportDetailActivity.this,message);
    }
}