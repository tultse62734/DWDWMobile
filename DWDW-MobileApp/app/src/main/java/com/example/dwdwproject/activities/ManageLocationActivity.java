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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dwdwproject.R;
import com.example.dwdwproject.adapters.LocationAdapter;
import com.example.dwdwproject.models.Location;

import java.util.ArrayList;
import java.util.List;
public class ManageLocationActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private List<Location> mLocationList;
    private LocationAdapter mLocationAdapter;
    private LinearLayout mBtnClose,mBtnAddLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_admin);
        initView();
        initData();
    }
    private void initView(){
        mRecyclerView = findViewById(R.id.rcv_location);
        mBtnClose = findViewById(R.id.lnl_close_manage_location);
        mBtnAddLocation = findViewById(R.id.lnl_add_location_admin);
         RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

    }
    private void initData(){
        mBtnClose.setOnClickListener(this);
        mBtnAddLocation.setOnClickListener(this);
        mLocationList = new ArrayList<>();
        mLocationList.add(new Location(1,"Khu A","20-11-2020",true));
        mLocationList.add(new Location(2,"Khu B","12-10-2019",false));
        mLocationList.add(new Location(3,"Khu C","1-10-2019",true));
        mLocationList.add(new Location(4,"Khu D","1-10-2019",true));

        mLocationList.add(new Location(5,"Khu E","1-10-2019",false));

        mLocationList.add(new Location(6,"Khu F","1-10-2019",true));

        updateUI();
    }
    private void updateUI(){
        if(mLocationAdapter == null){
            mLocationAdapter = new LocationAdapter(ManageLocationActivity.this,mLocationList);
            mRecyclerView.setAdapter(mLocationAdapter);
            mLocationAdapter.OnClickDeleteItemListener(new LocationAdapter.OnClickDeleteItem() {
                @Override
                public void OnClickDeleteItem(int position) {
                    showLogoutDialog();
                }
            });
            mLocationAdapter.OnClickItemListener(new LocationAdapter.OnClickItem() {
                @Override
                public void OnClickItem(int position) {
                  Intent intent  = new Intent(ManageLocationActivity.this,AdminLocationDetailActivity.class);
                  startActivity(intent);
                }
            });
        }
        else {
            mLocationAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.lnl_close_manage_location:
                finish();
                break;
            case R.id.lnl_add_location_admin:
                Intent intent = new Intent(ManageLocationActivity.this, AdminCreateLocationActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void showLogoutDialog() {
        final Dialog dialog = new Dialog(ManageLocationActivity.this);
        dialog.setContentView(R.layout.alert_dialog_notify_sign_out);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk = dialog.findViewById(R.id.btn_yes);
        Button buttonNo = dialog.findViewById(R.id.btn_no);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

}
