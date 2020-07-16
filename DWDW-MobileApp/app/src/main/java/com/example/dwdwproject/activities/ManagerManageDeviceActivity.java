package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.adapters.DeviceAdapter;
import com.example.dwdwproject.models.Device;
import com.example.dwdwproject.models.Room;
import com.example.dwdwproject.presenters.devicesPresenters.GetDeviceForManagerPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.devicesViews.GetAllDeviceView;

import java.util.ArrayList;
import java.util.List;

public class ManagerManageDeviceActivity extends AppCompatActivity implements View.OnClickListener, GetAllDeviceView {
    private RecyclerView mRecyclerView;
    private DeviceAdapter mDeviceAdapter;
    private List<Device> mDeviceList;
    private GetDeviceForManagerPresenter deviceForManagerPresenter;
    private LinearLayout mBtnClose;
    private String token ;
    private int locationId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_manage_device);
        initView();
        initData();
    }
    private void initView (){
        mBtnClose = findViewById(R.id.lnl_close_manager_manage_device);
        mRecyclerView = findViewById(R.id.rcv_manager_manage_device);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

    }
    private void initData(){
        deviceForManagerPresenter = new GetDeviceForManagerPresenter(ManagerManageDeviceActivity.this,this);
        mBtnClose.setOnClickListener(this);
//        mDeviceList = new ArrayList<>();
//        mDeviceList.add(new Device(1,"Camera 2MP","2020-11-20","Khu A"));
//        mDeviceList.add(new Device(2,"Camera 4MP","2020-11-20","Khu B"));
//        mDeviceList.add(new Device(3,"Camera 6MP","2020-11-20","Khu C"));
//        mDeviceList.add(new Device(4,"Camera 8MP","2020-11-20","Khu D"));
//        mDeviceList.add(new Device(5,"Camera 10MP","2020-11-20","Khu E"));
//        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
//        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
//        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
//        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
//        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
//        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
//        updateUI();
    // từ từ để t chỉnh cái
        locationId = SharePreferenceUtils.getIntSharedPreference(ManagerManageDeviceActivity.this, BundleString.LOCATIONID);
        token = SharePreferenceUtils.getStringSharedPreference(ManagerManageDeviceActivity.this,BundleString.TOKEN);
        deviceForManagerPresenter.getDeviceFromLocationForManager(token,locationId);
    }
    private void updateUI(){
        if(mDeviceAdapter == null){
            mDeviceAdapter = new DeviceAdapter(ManagerManageDeviceActivity.this,mDeviceList);
            mRecyclerView.setAdapter(mDeviceAdapter);
            mDeviceAdapter.onItemClickListerner(new DeviceAdapter.OnItemClickListenner() {
                @Override
                public void onItemCLick(int pos) {
                    Intent intent = new Intent(ManagerManageDeviceActivity.this, AdminDeviceDetailActivity.class);
                    startActivity(intent);
                }
            });
        }
        else {
            mDeviceAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_manager_manage_device:
                finish();
                break;
        }
    }
    @Override
    public void getAllDeviceSuccess(List<DeviceDTO> mDeviceDTOList) {
        if(mDeviceDTOList!=null){
            mDeviceList = new ArrayList<>();
            for (int i = 0; i < mDeviceDTOList.size(); i++) {
                int deviceId = mDeviceDTOList.get(i).getDeviceId();
                String deviceName = mDeviceDTOList.get(i).getDeviceCode();
                boolean isActive = mDeviceDTOList.get(i).isActive();
                mDeviceList.add(new Device(deviceId,deviceName,isActive));
            }
            updateUI();
        }
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(ManagerManageDeviceActivity.this,"Data is error");
    }
}
