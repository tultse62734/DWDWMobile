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
import com.example.dwdwproject.models.Location;

import java.util.ArrayList;
import java.util.List;

public class AdminManagerDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mBtnClose;
    TextView mEdtChoooseLocation;
    private List<Location> mLocationList;
    private RecyclerView mRecyclerView;
    private int posLocation;
    private ChooseLocationAdapter mLocationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manager_detail);
        initView();
        initData();
    }

    private  void initView(){
        mBtnClose = findViewById(R.id.lnl_close_admin_manager_detail);
        mEdtChoooseLocation = findViewById(R.id.edt_choose_location_update_manager_admin);
    }
    private void initData(){
        getDataLocation();
        mBtnClose.setOnClickListener(this);
        mEdtChoooseLocation.setOnClickListener(this);
    }
    private void getDataLocation() {
        mBtnClose.setOnClickListener(this);
        mLocationList = new ArrayList<>();
        mLocationList.add(new Location(1, "Khu A", "20-11-2020", true));
        mLocationList.add(new Location(2, "Khu B", "12-10-2019", false));
        mLocationList.add(new Location(3, "Khu C", "1-10-2019", true));
        mLocationList.add(new Location(4, "Khu D", "1-10-2019", true));
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_admin_manager_detail:
                finish();
                break;
            case R.id.edt_choose_location_update_manager_admin:
                showChooseLocationDialog();
                break;
        }

    }
    private void showChooseLocationDialog() {
        final Dialog dialog = new Dialog(AdminManagerDetailActivity.this);
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
        mLocationAdapter = new ChooseLocationAdapter(AdminManagerDetailActivity.this,mLocationList);
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
