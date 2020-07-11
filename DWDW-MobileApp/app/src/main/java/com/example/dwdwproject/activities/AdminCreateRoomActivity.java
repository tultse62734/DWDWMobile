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
import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.adapters.ChooseLocationAdapter;
import com.example.dwdwproject.adapters.ChooseStatusAdapter;
import com.example.dwdwproject.adapters.LocationAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Status;
import com.example.dwdwproject.presenters.locationsPresenters.GetAllLocationPresenter;
import com.example.dwdwproject.presenters.roomPresenters.CreateRoomPresenter;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;
import com.example.dwdwproject.views.roomViews.GetRoomView;

import java.util.ArrayList;
import java.util.List;

public class AdminCreateRoomActivity extends AppCompatActivity implements View.OnClickListener, GetRoomView, GetAllLocatonView {
    LinearLayout mBtnClose;
    EditText mEdtChoooseLocation,mEdtStatusRoom;
    private List<Location> mLocationList;
    private List<Status> mStatusList;
    private RecyclerView mRecyclerView,mRecyclerView1;
    private int posLocation;
    private LinearLayout mBtnAddCreateRoom;
    private EditText mEdtRoomCode;
    private GetAllLocationPresenter mGetAllLocationPresenter;
    private CreateRoomPresenter mCreateRoomPresenter;
    private int posStatus;
    private ChooseLocationAdapter mLocationAdapter;
    private ChooseStatusAdapter mChooseStatusAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_room);
        initView();
        initData();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_admin_add_room);
        mEdtChoooseLocation = findViewById(R.id.edt_choose_location_add_room_admin);
        mEdtStatusRoom = findViewById(R.id.edt_choose_status_add__room_admin);
        mEdtRoomCode = findViewById(R.id.edit_create_input_room_code);
        mBtnAddCreateRoom = findViewById(R.id.lnl_submit_add_room_admin);
    }
    private void initData(){
        getDataLocation();
        mBtnClose.setOnClickListener(this);
        mEdtChoooseLocation.setOnClickListener(this);
        mEdtStatusRoom.setOnClickListener(this);
        mBtnAddCreateRoom.setOnClickListener(this);
    }
    private void getDataLocation(){
        mBtnClose.setOnClickListener(this);
//        mLocationList = new ArrayList<>();
        mStatusList = new ArrayList<>();
//        mLocationList.add(new Location(1,"Khu A","20-11-2020",true));
//        mLocationList.add(new Location(2,"Khu B","12-10-2019",false));
//        mLocationList.add(new Location(3,"Khu C","1-10-2019",true));
//        mLocationList.add(new Location(4,"Khu D","1-10-2019",true));
        mStatusList.add(new Status("Đang hoạt động",true));
        mStatusList.add(new Status("Không hoạt động",false));
        mGetAllLocationPresenter = new GetAllLocationPresenter(AdminCreateRoomActivity.this,getApplication(),this);
        mGetAllLocationPresenter.getTokenGetAllLocation();
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_admin_add_room:
                finish();
                break;
            case R.id.edt_choose_location_add_room_admin:
                showChooseLocationDialog();
                break;
            case R.id.edt_choose_status_add__room_admin:
                showChooseStatusRoomDialog();
                break;
            case R.id.lnl_submit_add_room_admin:
                createRoom();
                break;
        }

    }
    private void showChooseStatusRoomDialog(){
        final Dialog dialog = new Dialog(AdminCreateRoomActivity.this);
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
        mChooseStatusAdapter = new ChooseStatusAdapter(AdminCreateRoomActivity.this,mStatusList);
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
        final Dialog dialog = new Dialog(AdminCreateRoomActivity.this);
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
        mLocationAdapter = new ChooseLocationAdapter(AdminCreateRoomActivity.this,mLocationList);
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
    public void getRoomSuccess(RoomDTO mRoomDTO) {
        Intent intent = new Intent(AdminCreateRoomActivity.this,ManageRoomActivity.class);
        startActivity(intent);
    }

    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(AdminCreateRoomActivity.this,"Don't create Room successfully");
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
                getDataLocation();
            }
    }
    private void createRoom(){
        mCreateRoomPresenter = new CreateRoomPresenter(AdminCreateRoomActivity.this,getApplication(),this);
        RoomDTO mRoomDTO = new RoomDTO();
        mRoomDTO.setLocationId(mLocationList.get(posLocation).getLocationId());
        mRoomDTO.setRoomCode(mEdtRoomCode.getText().toString()+ "");
        mRoomDTO.setActive(mStatusList.get(posStatus).isStatus());
        mCreateRoomPresenter.createRoomToken(mRoomDTO);
    }
}
