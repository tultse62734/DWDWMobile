package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.adapters.ChooseLocationAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Status;
import com.example.dwdwproject.presenters.locationsPresenters.GetAllLocationPresenter;
import com.example.dwdwproject.presenters.userPresenters.CreateUserPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;
import com.example.dwdwproject.views.userViews.GetUserView;

import java.util.ArrayList;
import java.util.List;

public class AdminCreateManagerActivity extends AppCompatActivity implements View.OnClickListener, GetUserView, GetAllLocatonView {
    private LinearLayout mBtnClose,mBtnCreateManager;
    EditText mEdtChoooseLocation;
    private String token;
    private List<Location> mLocationList;
    private RecyclerView mRecyclerView;
    private int posLocation;
    private CreateUserPresenter mCreateUserPresenter;
    private GetAllLocationPresenter getAllLocationPresenter;
    private ChooseLocationAdapter mLocationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_manager);
        initView();
    }
    private  void initView(){
        mBtnClose = findViewById(R.id.lnl_close_admin_add_manager);
        mEdtChoooseLocation = findViewById(R.id.edt_choose_location_add__manager_admin);
        token = SharePreferenceUtils.getStringSharedPreference(AdminCreateManagerActivity.this, BundleString.TOKEN);
        getAllLocationPresenter = new GetAllLocationPresenter(AdminCreateManagerActivity.this,this);
        getAllLocationPresenter.getAllLocation(token);
    }
    private void initData(){
        mBtnClose.setOnClickListener(this);
        mEdtChoooseLocation.setOnClickListener(this);
        mBtnCreateManager.setOnClickListener(this);
        //        mLocationList = new ArrayList<>();
//        mLocationList.add(new Location(1, "Khu A", "20-11-2020", true));
//        mLocationList.add(new Location(2, "Khu B", "12-10-2019", false));
//        mLocationList.add(new Location(3, "Khu C", "1-10-2019", true));
//        mLocationList.add(new Location(4, "Khu D", "1-10-2019", true));

    }
    private void getDataLocation() {

    }
    private void createUser(){
        mCreateUserPresenter = new CreateUserPresenter(AdminCreateManagerActivity.this,getApplication(),this);
        UserDTO userDTO = new UserDTO();
        mCreateUserPresenter.createUserToken(userDTO);
    }
        @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_admin_add_manager:
                finish();
                break;
            case R.id.edt_choose_location_add__manager_admin:
                showChooseLocationDialog();
                break;
            case R.id.lnl_submit_create_manager:
                createUser();
                break;
        }

    }
    private void showChooseLocationDialog() {
        final Dialog dialog = new Dialog(AdminCreateManagerActivity.this);
        dialog.setContentView(R.layout.alert_dialog_choose_location);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mRecyclerView = dialog.findViewById(R.id.rcv_choose_location);
        LinearLayout mBtnClose = dialog.findViewById(R.id.lnl_close_dialog_choose_location);
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mLocationAdapter = new ChooseLocationAdapter(AdminCreateManagerActivity.this,mLocationList);
        mRecyclerView.setAdapter(mLocationAdapter);
        mLocationAdapter.OnClickItemListener(new ChooseLocationAdapter.OnClickItem() {
            @Override
            public void OnClickItem(int position) {
                dialog.dismiss();
                posLocation = position;
                mEdtChoooseLocation.setText(mLocationList.get(position).getNameLocation()+"");
            }
        });
        dialog.show();
    }

    @Override
    public void getUserSuccess(UserDTO userDTO) {

    }

    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(AdminCreateManagerActivity.this,"Data is error");
    }
    @Override
    public void getAllLocationSuccess(List<LocationDTO> mLocationDTOList) {
        this.mLocationList = new ArrayList<>();
        for (int i = 0; i <mLocationDTOList.size() ; i++) {
            int locationId  = mLocationDTOList.get(i).getLocationId();
            String locationName = mLocationDTOList.get(i).getLocationCode();
            boolean isactive = mLocationDTOList.get(i).isActive();
            this.mLocationList.add(new Location(locationId,locationName,isactive));
        }
        initData();
    }
}
