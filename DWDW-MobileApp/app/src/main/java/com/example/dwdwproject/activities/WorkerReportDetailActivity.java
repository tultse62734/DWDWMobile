package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.ConfirmReasonDTO;
import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.models.Accident;
import com.example.dwdwproject.presenters.recordsPresenters.ConfirmRecordPresenter;
import com.example.dwdwproject.presenters.recordsPresenters.GetRecordsByLocationIdAndTimePresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.CheckVaildateEditTexxt;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.ParseBytes;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.recordsViews.ConfirmRecordView;
import com.example.dwdwproject.views.recordsViews.GetRecordView;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class WorkerReportDetailActivity extends AppCompatActivity implements View.OnClickListener , GetRecordView , ConfirmRecordView {
    private LinearLayout mBtnClose,mBtnConfirm;
    private TextView mTxtRoom,mTxtDevice,mTxtLocation,mTxtDay,mTxtTime;
    private Accident mAccident;
    private TextView mTitle,mTxtReasion;
    private String title,token;
    private RecordDTO recordDTO;
    private ImageView mImageView;
    private String reason;
    private GetRecordsByLocationIdAndTimePresenter mIdAndTimePresenter;
    Charset charset = StandardCharsets.UTF_16;
    private ConfirmRecordPresenter confirmRecordPresenter;

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
        confirmRecordPresenter = new ConfirmRecordPresenter(WorkerReportDetailActivity.this,this);
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
                showDialogConfirm();
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
    private void showDialogConfirm(){
        final Dialog dialog = new Dialog(WorkerReportDetailActivity.this);
        dialog.setContentView(R.layout.alert_layout_cofirm_reason);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk = dialog.findViewById(R.id.btn_sumbit_confirm);
        final EditText mEdtReason = dialog.findViewById(R.id.edit_reasion);
        Button buttonCancel = dialog.findViewById(R.id.btn_cancel_confirm);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckVaildateEditTexxt.checkEditTextNull(mEdtReason.getText().toString())){
                    DialogNotifyError.showErrorLoginDialog(WorkerReportDetailActivity.this,"Please Input Commnent");
                }else{
                        confirmReason(mEdtReason.getText().toString());
                }
            }
        });
        dialog.show();
    }
    private void confirmReason(String reasonString){
        ConfirmReasonDTO mConfirmReasonDTO = new ConfirmReasonDTO();
        mConfirmReasonDTO.setRecordId(recordDTO.getRecordId());
        mConfirmReasonDTO.setRecordDateTime(recordDTO.getRecordDateTime());
        mConfirmReasonDTO.setComment(reasonString);
        confirmRecordPresenter.confirmRecord(token,mConfirmReasonDTO);
    }
    @Override
    public void confirmRecordSuccess(ConfirmReasonDTO mConfirmReasonDTO) {
       finish();
    }
}