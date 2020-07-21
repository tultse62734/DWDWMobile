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
import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.adapters.ChooseLocationAdapter;
import com.example.dwdwproject.adapters.ChooseStatusAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Status;
import com.example.dwdwproject.presenters.locationsPresenters.GetAllLocationPresenter;
import com.example.dwdwproject.presenters.roomPresenters.UpdateRoomPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;
import com.example.dwdwproject.views.roomViews.GetRoomView;

import java.util.ArrayList;
import java.util.List;

public class AdminRoomDetailActivity extends AppCompatActivity implements View.OnClickListener, GetAllLocatonView, GetRoomView {
    LinearLayout mBtnClose;
    TextView mEdtChoooseLocation,mEdtStatusRoom;
    private List<Location> mLocationList;
    private List<Status> mStatusList;
    private RecyclerView mRecyclerView,mRecyclerView1;
    private int posLocation;
    private int posStatus;
    private String token;
    private RoomDTO mRoomDTO;
    private EditText mEdtRoomCode;
    private int locationId;
    private ChooseLocationAdapter mLocationAdapter;
    private GetAllLocationPresenter mAllLocationPresenter;
    private ChooseStatusAdapter mChooseStatusAdapter;
    private UpdateRoomPresenter mUpdateRoomPresenter;
    private LinearLayout mBtnUpdateRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_room_detail);
        getDataServer();
    }
    private void getDataServer(){
        token = SharePreferenceUtils.getStringSharedPreference(AdminRoomDetailActivity.this,BundleString.TOKEN);
        mAllLocationPresenter = new GetAllLocationPresenter(AdminRoomDetailActivity.this,this);
        mAllLocationPresenter.getAllLocation(token);
    }
    private void initView(){
        Bundle bundle = getIntent().getExtras();
        mRoomDTO  = (RoomDTO) bundle.getSerializable(BundleString.ROOMDETAIL);
        mBtnClose = findViewById(R.id.lnl_close_admin_room_detail);
        mEdtRoomCode = findViewById(R.id.edit_room_code_update);
        mBtnUpdateRoom = findViewById(R.id.lnl_submit_update_room_admin);
        mEdtChoooseLocation = findViewById(R.id.edt_choose_location_update_room_admin);
        mEdtStatusRoom = findViewById(R.id.edt_choose_status_update_room_admin);
        mEdtRoomCode.setText(mRoomDTO.getRoomCode());
        locationId = mRoomDTO.getLocationId();
        for (int i = 0; i <mLocationList.size() ; i++) {
                if(locationId == mLocationList.get(i).getLocationId()){
                    mEdtChoooseLocation.setText(mLocationList.get(i).getNameLocation()+"");
                }
        }
        mEdtStatusRoom.setText("Đang hoạt động");
    }
    private void initData(){
        getDataLocation();
        mBtnClose.setOnClickListener(this);
        mEdtChoooseLocation.setOnClickListener(this);
        mEdtStatusRoom.setOnClickListener(this);
        mBtnUpdateRoom.setOnClickListener(this);

    }
    private void getDataLocation(){
        mBtnClose.setOnClickListener(this);
        mStatusList = new ArrayList<>();
        mStatusList.add(new Status("Đang hoạt động",true));
        mStatusList.add(new Status("Không hoạt động",false));
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_admin_room_detail:
                finish();
                break;
            case R.id.edt_choose_location_update_room_admin:
                showChooseLocationDialog();
                break;
            case R.id.edt_choose_status_update_room_admin:
                showChooseStatusRoomDialog();
                break;
            case R.id.lnl_submit_update_room_admin:
                updateRoom();
                break;
        }
    }
    private void showChooseStatusRoomDialog(){
        final Dialog dialog = new Dialog(AdminRoomDetailActivity.this);
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
        mChooseStatusAdapter = new ChooseStatusAdapter(AdminRoomDetailActivity.this,mStatusList);
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
    private void showChooseLocationDialog() {
        final Dialog dialog = new Dialog(AdminRoomDetailActivity.this);
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
        mLocationAdapter = new ChooseLocationAdapter(AdminRoomDetailActivity.this,mLocationList);
        mRecyclerView.setAdapter(mLocationAdapter);
        mLocationAdapter.OnClickItemListener(new ChooseLocationAdapter.OnClickItem() {
            @Override
            public void OnClickItem(int position) {
                dialog.dismiss();
                posLocation = position;
                mRoomDTO.setLocationId(mLocationList.get(position).getLocationId());
                mEdtChoooseLocation.setText(mLocationList.get(position).getNameLocation()+"");
            }
        });

        dialog.show();
    }
    @Override
    public void getAllLocationSuccess(List<LocationDTO> mLocationDTOList) {
        if(mLocationDTOList!=null){
            this.mLocationList = new ArrayList<>();
            for (int i = 0; i <mLocationDTOList.size() ; i++) {
                int locationId  = mLocationDTOList.get(i).getLocationId();
                String locationName = mLocationDTOList.get(i).getLocationCode();
                boolean isactive = mLocationDTOList.get(i).isActive();
                this.mLocationList.add(new Location(locationId,locationName,isactive));
            }
            initView();
            initData();
        }
    }
    private void updateRoom(){
        token = SharePreferenceUtils.getStringSharedPreference(AdminRoomDetailActivity.this,BundleString.TOKEN);
        mUpdateRoomPresenter = new UpdateRoomPresenter(AdminRoomDetailActivity.this,this);
        mRoomDTO.setRoomCode(mEdtRoomCode.getText().toString());
        mRoomDTO.setLocationId(mRoomDTO.getLocationId());
        mUpdateRoomPresenter.updateRoom(token,mRoomDTO);
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(AdminRoomDetailActivity.this,"Update Room Fail");
    }
    @Override
    public void getRoomSuccess(RoomDTO mRoomDTO) {
        finish();
    }
}
