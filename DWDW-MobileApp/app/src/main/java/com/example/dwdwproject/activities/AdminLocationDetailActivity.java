package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.adapters.ChooseStatusAdapter;
import com.example.dwdwproject.models.Status;
import com.example.dwdwproject.presenters.locationsPresenters.UpdateLocationPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.locationsViews.GetLocationView;
import com.example.dwdwproject.views.locationsViews.UpdateLocatonView;

import java.util.ArrayList;
import java.util.List;

public class AdminLocationDetailActivity extends AppCompatActivity implements View.OnClickListener, UpdateLocatonView {
    LinearLayout mBtnClose;
    TextView mEdtStatusLocation;
    private List<Status> mStatusList;
    private RecyclerView mRecyclerView1;
    private int posStatus;
    private LinearLayout mBtnUpdate;
    private EditText mTxtLocationCode;
    private LocationDTO mLocationDTO;
    private ChooseStatusAdapter mChooseStatusAdapter;
    private String token;
    private UpdateLocationPresenter mUpdateLocationPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_location_detail);
    initView();
    initData();
    }
    private void initView(){
        Bundle bundle = getIntent().getExtras();
        mLocationDTO  =(LocationDTO) bundle.getSerializable(BundleString.LOCATIONDETAIL);
        mBtnClose = findViewById(R.id.lnl_close_admin_location_detail);
        mEdtStatusLocation = findViewById(R.id.edt_choose_status_update_location_admin_deta);
        mTxtLocationCode = findViewById(R.id.edt_name_location_update_location_admin);
        mBtnUpdate = findViewById(R.id.lnl_submit_update_location_admin);
        mTxtLocationCode.setText(mLocationDTO.getLocationCode());
        if(mLocationDTO.isActive()){
            mEdtStatusLocation.setText("Đang hoạt động");
        }
        else {
            mEdtStatusLocation.setText("Không hoạt động");
        }
    }
    private void initData(){
        mUpdateLocationPresenter = new UpdateLocationPresenter(AdminLocationDetailActivity.this,this);
        getDataLocation();
        mBtnClose.setOnClickListener(this);
        mBtnUpdate.setOnClickListener(this);
        mEdtStatusLocation.setOnClickListener(this);
    }
    private void getDataLocation(){
        mBtnClose.setOnClickListener(this);
        mStatusList = new ArrayList<>();
        mStatusList.add(new Status("Đang hoạt động",true));
        mStatusList.add(new Status("Không hoạt động",false));
    }
    private void updateLocation(){
        String locationCode = mTxtLocationCode.getText().toString();
        token = SharePreferenceUtils.getStringSharedPreference(AdminLocationDetailActivity.this,BundleString.TOKEN);
        mLocationDTO.setLocationCode(locationCode);
        mUpdateLocationPresenter.updateLocation(token,mLocationDTO);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.lnl_close_admin_location_detail:
                finish();
                break;

            case R.id.edt_choose_status_update_location_admin_deta:
                showChooseStatusRoomDialog();
                break;
            case R.id.lnl_submit_update_location_admin:
                updateLocation();
                break;
        }
    }
    private void showChooseStatusRoomDialog(){
        final Dialog dialog = new Dialog(AdminLocationDetailActivity.this);
        dialog.setContentView(R.layout.alert_dialog_choose_status);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mRecyclerView1 = dialog.findViewById(R.id.rcv_choose_status);
        LinearLayout mBtnClose = dialog.findViewById(R.id.lnl_close_dialog_choose_status);
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView1.setLayoutManager(layoutManager);
        mChooseStatusAdapter = new ChooseStatusAdapter(AdminLocationDetailActivity.this,mStatusList);
        mRecyclerView1.setAdapter(mChooseStatusAdapter);
        mChooseStatusAdapter.OnClickItemListener(new ChooseStatusAdapter.OnClickItem() {
            @Override
            public void OnClickItem(int position) {
                dialog.dismiss();
                posStatus = position;
                mEdtStatusLocation.setText(mStatusList.get(position).getStatusName()+"");
            }
        });
        dialog.show();
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(AdminLocationDetailActivity.this,"Update Location Fail");
    }
    @Override
    public void updateLocationSuccess() {
        finish();
    }
}
