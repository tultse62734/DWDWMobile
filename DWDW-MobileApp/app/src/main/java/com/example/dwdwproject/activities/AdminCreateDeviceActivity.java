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
import com.example.dwdwproject.adapters.LocationAdapter;
import com.example.dwdwproject.models.Location;

import java.util.ArrayList;
import java.util.List;

public class AdminCreateDeviceActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout mBtnClose;
    EditText mEdtChoooseLocation;
    private List<Location> mLocationList;
    RecyclerView mRecyclerView;
    private ChooseLocationAdapter mLocationAdapter;

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
    }
    private void initData(){
        mBtnClose.setOnClickListener(this);
        mEdtChoooseLocation.setOnClickListener(this);
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
            }

        }

    private void getDataLocation(){
        mBtnClose.setOnClickListener(this);
        mLocationList = new ArrayList<>();
        mLocationList.add(new Location(1,"Khu A","20-11-2020",true));
        mLocationList.add(new Location(2,"Khu B","12-10-2019",false));
        mLocationList.add(new Location(3,"Khu C","1-10-2019",true));
        mLocationList.add(new Location(4,"Khu D","1-10-2019",true));
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
        getDataLocation();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

        if(mLocationAdapter == null){
            mLocationAdapter = new ChooseLocationAdapter(AdminCreateDeviceActivity.this,mLocationList);
            mRecyclerView.setAdapter(mLocationAdapter);
            mLocationAdapter.OnClickItemListener(new ChooseLocationAdapter.OnClickItem() {
                @Override
                public void OnClickItem(int position) {
                    dialog.dismiss();
                    mEdtChoooseLocation.setText(mLocationList.get(position).getNameLocation()+"");
                }
            });
        }
        else {
            mLocationAdapter.notifyDataSetChanged();
        }
        dialog.show();

    }
}
