package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class AccidentReportDetailActivity extends AppCompatActivity implements View.OnClickListener, GetRecordView {
    private LinearLayout mBtnClose;
    private TextView mTxtRoom,mTxtDevice,mTxtLocation,mTxtDay,mTxtTime;
    private Accident mAccident;
    private TextView mTitle;
    private String title,token;
    private RecordDTO recordDTO;
    private ImageView mImageView;
    private GetRecordsByLocationIdAndTimePresenter mIdAndTimePresenter;
    Charset charset = StandardCharsets.UTF_16;

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
        mImageView = findViewById(R.id.image_accident_detail);
    }
    private void initData(){
        title = SharePreferenceUtils.getStringSharedPreference(AccidentReportDetailActivity.this,BundleString.LOCATIONNAME);
        Bundle bundle = getIntent().getExtras();
        mAccident = (Accident) bundle.getSerializable(BundleString.RECORDDETAIL);
        token = SharePreferenceUtils.getStringSharedPreference(AccidentReportDetailActivity.this,BundleString.TOKEN);
        mIdAndTimePresenter = new GetRecordsByLocationIdAndTimePresenter(AccidentReportDetailActivity.this,this);
        mBtnClose.setOnClickListener(this);
        mIdAndTimePresenter.getRecordById(token,mAccident.getAccidentId());
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
        DialogNotifyError.showErrorLoginDialog(AccidentReportDetailActivity.this,message);
    }
}
