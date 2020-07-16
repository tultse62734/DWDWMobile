package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.presenters.devicesPresenters.CreateDevivePresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.devicesViews.CreateDeviceView;

public class AdminCreateDeviceActivity extends AppCompatActivity implements View.OnClickListener,CreateDeviceView {
    LinearLayout mBtnClose,mBtnAddDevice;
    private String token ;
    private EditText mEdtDeviceCode;
    private CreateDevivePresenter mCreateDevivePresenter;
    private DeviceDTO mDeviceDTO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_device);
        initView();
        initData();
    }
    private void initView(){
        mBtnAddDevice = findViewById(R.id.lnl_submit_add_device_admin);
        mBtnClose = findViewById(R.id.lnl_close_admin_add_device);
        mEdtDeviceCode = findViewById(R.id.edit_create_device_code);
    }
    private void initData(){
        mCreateDevivePresenter = new CreateDevivePresenter(AdminCreateDeviceActivity.this,this);
        mBtnAddDevice.setOnClickListener(this);
        mBtnClose.setOnClickListener(this);
    }
    private void createDevice(){
        String deviceCode = mEdtDeviceCode.getText().toString();
        mDeviceDTO = new DeviceDTO();
        mDeviceDTO.setDeviceCode(deviceCode);
        token = SharePreferenceUtils.getStringSharedPreference(AdminCreateDeviceActivity.this, BundleString.TOKEN);
        mCreateDevivePresenter.createDevice(token,mDeviceDTO);
    }
    @Override
    public void createDeviceSuccess(DeviceDTO mDeviceDTO) {
        Intent intent = new Intent(AdminCreateDeviceActivity.this,AdminAssignDevicesActivity.class);
        finish();
        startActivity(intent);
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(AdminCreateDeviceActivity.this,message);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_submit_add_device_admin:
                createDevice();
                break;
            case R.id.lnl_close_admin_add_device:
                finish();
                break;
        }
    }
}