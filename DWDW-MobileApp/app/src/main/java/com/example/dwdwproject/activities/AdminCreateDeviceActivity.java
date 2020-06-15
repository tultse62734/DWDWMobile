package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dwdwproject.R;
import com.example.dwdwproject.adapters.ChooseLocationAdapter;
import com.example.dwdwproject.adapters.ChooseRoomAdapter;
import com.example.dwdwproject.adapters.LocationAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Room;

import java.util.ArrayList;
import java.util.List;

public class AdminCreateDeviceActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout mBtnClose;
    EditText mEdtChoooseLocation,mEdtChoooseRoom;
    private List<Location> mLocationList;
    RecyclerView mRecyclerView;
    RecyclerView mRecyclerView1;
    private ChooseLocationAdapter mLocationAdapter;
    private ChooseRoomAdapter mChooseRoomAdapter;
    private int posLocation;
    private List<Room> mRoomList;
    private List<Room> mRoomListFromLocation;
    private int posRoom;
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

    }
    private void initData(){
        getDataLocation();
        mBtnClose.setOnClickListener(this);
        mEdtChoooseLocation.setOnClickListener(this);
        mEdtChoooseRoom.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_admin_add_device:
                finish();
                break;
            case R.id.edt_choose_location_add_admin:
                showChooseLocationDialog();
                break;
            case R.id.edt_choose_room_add_admin:
                showChooseRoomDialog();
                break;
            }

        }

    private void getDataLocation(){
        mBtnClose.setOnClickListener(this);
        mLocationList = new ArrayList<>();
        mRoomListFromLocation = new ArrayList<>();
        mRoomList = new ArrayList<>();
        mRoomList.add(new Room(1,"100","12-11-2020",true));
        mRoomList.add(new Room(1,"200","12-11-2020",true));
        mRoomList.add(new Room(1,"300","12-11-2020",true));
        mRoomList.add(new Room(1,"300","12-11-2020",true));
        mRoomList.add(new Room(1,"400","12-11-2020",true));
        mLocationList.add(new Location(1,"Khu A","20-11-2020",true));
        mLocationList.add(new Location(2,"Khu B","12-10-2019",false));
        mLocationList.add(new Location(3,"Khu C","1-10-2019",true));
        mLocationList.add(new Location(4,"Khu D","1-10-2019",true));
        for (int i = 0; i <mLocationList.size() ; i++) {
                mLocationList.get(i).setRoomList(mRoomList);
        }
    }
    private void showChooseLocationDialog() {
        final Dialog dialog = new Dialog(AdminCreateDeviceActivity.this);
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
        mLocationAdapter = new ChooseLocationAdapter(AdminCreateDeviceActivity.this,mLocationList);
            mRecyclerView.setAdapter(mLocationAdapter);
            mLocationAdapter.OnClickItemListener(new ChooseLocationAdapter.OnClickItem() {
                @Override
                public void OnClickItem(int position) {
                    dialog.dismiss();
                    posLocation = position;
                    mRoomListFromLocation = mLocationList.get(posLocation).getRoomList();
                    mEdtChoooseLocation.setText(mLocationList.get(position).getNameLocation()+"");
                }
            });

        dialog.show();

    }
    private void showChooseRoomDialog() {
        final Dialog dialog = new Dialog(AdminCreateDeviceActivity.this);
        dialog.setContentView(R.layout.alert_dialog_choose_room);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mRecyclerView1 = dialog.findViewById(R.id.rcv_choose_room);
        LinearLayout mBtnClose = dialog.findViewById(R.id.lnl_close_dialog_choose_room);
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView1.setLayoutManager(layoutManager);
            mChooseRoomAdapter = new ChooseRoomAdapter(AdminCreateDeviceActivity.this,mRoomListFromLocation);
            mRecyclerView1.setAdapter(mChooseRoomAdapter);
            mChooseRoomAdapter.OnClickItemListener(new ChooseRoomAdapter.OnClickItem() {
                @Override
                public void OnClickItem(int position) {
                    dialog.dismiss();
                    posRoom = position;
                    mEdtChoooseRoom.setText(mRoomListFromLocation.get(posRoom).getRoomName()+"");
                }
            });

        dialog.show();

    }
}
