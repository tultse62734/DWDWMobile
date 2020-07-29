package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
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
import android.widget.Toast;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.adapters.ChooseLocationAdapter;
import com.example.dwdwproject.adapters.ChooseRoomAdapter;
import com.example.dwdwproject.adapters.LocationAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Room;
import com.example.dwdwproject.presenters.devicesPresenters.CreateDevivePresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.devicesViews.CreateDeviceView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class AdminCreateDeviceActivity extends AppCompatActivity implements View.OnClickListener,CreateDeviceView {
    LinearLayout mBtnClose,mBtnAddDevice;
    private EditText mEdtDeviceCode;
    TextView mEdtChoooseLocation,mEdtChoooseRoom,mEdtChooseTime;
    private DeviceDTO mDeviceDTO;
    private String token ;
    private CreateDevivePresenter mCreateDevivePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_device);
        initView();
        initData();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_admin_add_device);
        mEdtChoooseLocation = findViewById(R.id.edt_choose_location_add_admin);
        mEdtChoooseRoom = findViewById(R.id.edt_choose_room_add_admin);
        mEdtChooseTime = findViewById(R.id.edt_choose_time_add_admin);
        mBtnAddDevice = findViewById(R.id.lnl_submit_add_device_admin);
        mEdtDeviceCode = findViewById(R.id.edit_create_device_code);
    }
    private void initData(){
        mCreateDevivePresenter = new CreateDevivePresenter(AdminCreateDeviceActivity.this,this);
        mBtnClose.setOnClickListener(this);
        mEdtChoooseLocation.setOnClickListener(this);
        mEdtChoooseRoom.setOnClickListener(this);
        mEdtChooseTime.setOnClickListener(this);
        mBtnAddDevice.setOnClickListener(this);
    }
    private void createDevice(){
        String deviceCode = mEdtDeviceCode.getText().toString();
        mDeviceDTO = new DeviceDTO();
        mDeviceDTO.setDeviceCode(deviceCode);
        token = SharePreferenceUtils.getStringSharedPreference(AdminCreateDeviceActivity.this, BundleString.TOKEN);
        mCreateDevivePresenter.createDevice(token,mDeviceDTO);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_admin_add_device:
                finish();
                break;
            case R.id.lnl_submit_add_device_admin:
                createDevice();
                break;
        }
        }
    @Override
    public void createDeviceSuccess(DeviceDTO mDeviceDTO) {
        intentToAdminGetAllDevice();
    }
    private void showNotifyDialog(final DeviceDTO deviceDTO,String messgae) {
        final Dialog dialog = new Dialog(AdminCreateDeviceActivity.this);
        dialog.setContentView(R.layout.alert_dialog_notify_sign_out);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk = dialog.findViewById(R.id.btn_yes);
        TextView mtxtMessge= dialog.findViewById(R.id.txt_dia);
        mtxtMessge.setText(messgae);
        Button buttonNo = dialog.findViewById(R.id.btn_no);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                intentToAdminAssignDevice(deviceDTO);
            }
        });
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                intentToAdminGetAllDevice();
            }
        });
        dialog.show();
    }
    private void intentToAdminGetAllDevice(){
        Intent returnIntent1 = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent1);
        finish();
    }
    private void intentToAdminAssignDevice(DeviceDTO mDeviceDTO){
        Intent intent = new Intent(AdminCreateDeviceActivity.this,AdminAssignDeviceActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(BundleString.DEVICEASSGINT,mDeviceDTO);
        intent.putExtras(bundle);
        finish();
        startActivity(intent);
    }
    @Override
    public void showError(String message) {
            DialogNotifyError.showErrorLoginDialog(AdminCreateDeviceActivity.this,message);
    }
}
