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
import com.example.dwdwproject.adapters.ChooseStatusAdapter;
import com.example.dwdwproject.models.Status;

import java.util.ArrayList;
import java.util.List;

public class AdminLocationDetailActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout mBtnClose;
    TextView mEdtStatusRoom;
    private List<Status> mStatusList;
    private RecyclerView mRecyclerView1;
    private int posStatus;
    private ChooseStatusAdapter mChooseStatusAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_location_detail);
    initView();
    initData();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_admin_location_detail);
        mEdtStatusRoom = findViewById(R.id.edt_choose_status_update_location_admin_detal);
    }
    private void initData(){
        getDataLocation();
        mBtnClose.setOnClickListener(this);
        mEdtStatusRoom.setOnClickListener(this);
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
        switch (id) {
            case R.id.lnl_close_admin_location_detail:
                finish();
                break;

            case R.id.edt_choose_status_update_location_admin_detal:
                showChooseStatusRoomDialog();
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
                mEdtStatusRoom.setText(mStatusList.get(position).getStatusName()+"");
            }
        });

        dialog.show();

    }
}
