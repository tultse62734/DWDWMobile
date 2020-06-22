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
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.adapters.ChooseLocationAdapter;
import com.example.dwdwproject.adapters.ChooseStatusAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Status;

import java.util.ArrayList;
import java.util.List;

public class AdminRoomDetailActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout mBtnClose;
    TextView mEdtChoooseLocation,mEdtStatusRoom;
    private List<Location> mLocationList;
    private List<Status> mStatusList;
    private RecyclerView mRecyclerView,mRecyclerView1;
    private int posLocation;
    private int posStatus;
    private ChooseLocationAdapter mLocationAdapter;
    private ChooseStatusAdapter mChooseStatusAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_room_detail);
        initView();
        initData();
    }

    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_admin_room_detail);
        mEdtChoooseLocation = findViewById(R.id.edt_choose_location_update_room_admin);
        mEdtStatusRoom = findViewById(R.id.edt_choose_status_update_room_admin);
    }
    private void initData(){
        getDataLocation();
        mBtnClose.setOnClickListener(this);
        mEdtChoooseLocation.setOnClickListener(this);
        mEdtStatusRoom.setOnClickListener(this);
    }
    private void getDataLocation(){
        mBtnClose.setOnClickListener(this);
        mLocationList = new ArrayList<>();
        mStatusList = new ArrayList<>();
        mLocationList.add(new Location(1,"Khu A","20-11-2020",true));
        mLocationList.add(new Location(2,"Khu B","12-10-2019",false));
        mLocationList.add(new Location(3,"Khu C","1-10-2019",true));
        mLocationList.add(new Location(4,"Khu D","1-10-2019",true));
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
                mEdtChoooseLocation.setText(mLocationList.get(position).getNameLocation()+"");
            }
        });

        dialog.show();

    }
}
