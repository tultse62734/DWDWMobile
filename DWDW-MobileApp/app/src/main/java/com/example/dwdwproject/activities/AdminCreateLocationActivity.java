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

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.adapters.ChooseStatusAdapter;
import com.example.dwdwproject.models.Status;
import com.example.dwdwproject.presenters.locationsPresenters.CreateLocationPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.locationsViews.CreateLocatonView;

import java.util.ArrayList;
import java.util.List;

public class AdminCreateLocationActivity extends AppCompatActivity implements View.OnClickListener, CreateLocatonView {
    LinearLayout mBtnClose,mBtnAddLocation;
    EditText mEdtStatusRoom;
    private List<Status> mStatusList;
    private RecyclerView mRecyclerView1;
    private int posStatus;
    private CreateLocationPresenter mCreateLocationPresenter;
    private String token ;
    private EditText mEdtLocationCode;
    private ChooseStatusAdapter mChooseStatusAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_location);
        initView();initData();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_admin_add_location);
        mEdtStatusRoom = findViewById(R.id.edt_choose_status_add__location_admin);
        mEdtLocationCode = findViewById(R.id.edt_location_code_add_location_admin);
        mBtnAddLocation = findViewById(R.id.lnl_submit_add_location_admin);
    }
    private void initData(){
        getDataLocation();
        mBtnClose.setOnClickListener(this);
        mEdtStatusRoom.setOnClickListener(this);
        mBtnAddLocation.setOnClickListener(this);
    }
    private void getDataLocation(){
        mBtnClose.setOnClickListener(this);
        mStatusList = new ArrayList<>();
        mStatusList.add(new Status("Đang hoạt động",true));
        mStatusList.add(new Status("Không hoạt động",false));
    }
    private void createLocation (){
        String locationCode = mEdtLocationCode.getText().toString();
        LocationDTO mLocationDTO = new LocationDTO();
        mLocationDTO.setLocationCode(locationCode);
        token = SharePreferenceUtils.getStringSharedPreference(AdminCreateLocationActivity.this, BundleString.TOKEN);
        mCreateLocationPresenter = new CreateLocationPresenter(AdminCreateLocationActivity.this,getApplication(), this);
        mCreateLocationPresenter.createLocation(token,mLocationDTO);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.lnl_close_admin_add_location:
                finish();
                break;
            case R.id.edt_choose_status_add__location_admin:
                showChooseStatusRoomDialog();
                break;
            case R.id.lnl_submit_add_location_admin:
                createLocation();
                break;
        }
    }
        private void showChooseStatusRoomDialog(){
            final Dialog dialog = new Dialog(AdminCreateLocationActivity.this);
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
            mChooseStatusAdapter = new ChooseStatusAdapter(AdminCreateLocationActivity.this,mStatusList);
            mRecyclerView1.setAdapter(mChooseStatusAdapter);
            mChooseStatusAdapter.OnClickItemListener(new ChooseStatusAdapter.OnClickItem() {
                @Override
                public void OnClickItem(int position) {
                    dialog.dismiss();
                    posStatus = position;
                    mEdtStatusRoom.setText(mStatusList.get(position).getStatusName()+"");
                }
            });
            dialog.show();
        }
    @Override
    public void createLocationSuccess() {
        finish();

    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(AdminCreateLocationActivity.this,"Create Location Fail");
    }
}
