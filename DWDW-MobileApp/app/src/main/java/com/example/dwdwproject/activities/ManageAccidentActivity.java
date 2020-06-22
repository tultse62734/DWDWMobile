package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.adapters.AccidentAdapter;
import com.example.dwdwproject.models.Accident;

import java.util.ArrayList;
import java.util.List;

public class ManageAccidentActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Accident> mAccidentList;
    private RecyclerView mRecyclerView;
    private AccidentAdapter mAccidentAdapter;
    private LinearLayout mBtnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_accident);
        initView();
        initData();
    }
    private void initView(){
        mRecyclerView = findViewById(R.id.rcv_accident);
        mBtnClose = findViewById(R.id.lnl_close_manage_accident);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mBtnClose.setOnClickListener(this);
        mAccidentList = new ArrayList<>();
        mAccidentList.add(new Accident(1,"Accident1","18-11-2020","Khu A","100",true));
        mAccidentList.add(new Accident(2,"Accident2","18-11-2020","Khu B","200",false));
        mAccidentList.add(new Accident(3,"Accident3","18-11-2020","Khu C","300",true));
        mAccidentList.add(new Accident(4,"Accident4","18-11-2020","Khu D","400",false));
        mAccidentList.add(new Accident(5,"Accident5","18-11-2020","Khu B","200",true));
        mAccidentList.add(new Accident(6,"Accident6","18-11-2020","Khu A","100",false));
        mAccidentList.add(new Accident(7,"Accident7","18-11-2020","Khu C","300",true));
        updateUI();
    }
    private void updateUI(){
        if(mAccidentAdapter == null){
            mAccidentAdapter = new AccidentAdapter(ManageAccidentActivity.this,mAccidentList);
            mRecyclerView.setAdapter(mAccidentAdapter);
            mAccidentAdapter.onItemClick(new AccidentAdapter.OnItemCkickListerner() {
                @Override
                public void onItemClick(int pos) {
                    Intent intent = new Intent(ManageAccidentActivity.this,AccidentReportDetailActivity.class);
                    startActivity(intent);
                }
            });
        }else {
            mAccidentAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.lnl_close_manage_accident:
                finish();
                break;
        }
    }
}