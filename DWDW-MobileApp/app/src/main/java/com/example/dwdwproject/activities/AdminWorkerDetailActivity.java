package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.adapters.LocationAdapter;
import com.example.dwdwproject.models.Location;

import java.util.ArrayList;
import java.util.List;

public class AdminWorkerDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mBtnClose;
    private List<Location> mLocationList;
    private LocationAdapter mLocationAdapter;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_worker_detail);
        initView();
        initData();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_manage_worker_detail:
                finish();
                break;

        }
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_manage_worker_detail);
        mRecyclerView = findViewById(R.id.rcv_location_detail);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AdminWorkerDetailActivity.this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mBtnClose.setOnClickListener(this);
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
        if(mLocationAdapter ==null){
            mLocationAdapter = new LocationAdapter(AdminWorkerDetailActivity.this,mLocationList);
            mRecyclerView.setAdapter(mLocationAdapter);
        }
        else {
            mLocationAdapter.notifyDataSetChanged();
        }
    }
}
