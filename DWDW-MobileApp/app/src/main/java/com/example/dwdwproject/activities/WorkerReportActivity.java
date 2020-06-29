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

public class WorkerReportActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLnlClose,mLnlFilter;
    private List<Accident> mAccidentList;
    private RecyclerView mRecyclerView;
    private AccidentAdapter mAccidentAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_report);
        initView();
        initData();
    }
    private void initView(){
        mRecyclerView = findViewById(R.id.rcv_accident_worker);
        mLnlFilter = findViewById(R.id.lnl_filter_report_worker);
        mLnlClose = findViewById(R.id.lnl_close_manage_accident_worker);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mLnlClose.setOnClickListener(this);
        mLnlFilter.setOnClickListener(this);
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
            mAccidentAdapter = new AccidentAdapter(WorkerReportActivity.this,mAccidentList);
            mRecyclerView.setAdapter(mAccidentAdapter);
            mAccidentAdapter.onItemClick(new AccidentAdapter.OnItemCkickListerner() {
                @Override
                public void onItemClick(int pos) {
                    Intent intent = new Intent(WorkerReportActivity.this,AccidentReportDetailActivity.class);
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
        switch (id){
            case R.id.lnl_filter_report_worker:
                Intent intent = new Intent(WorkerReportActivity.this,ShiftDateFilterActivity.class);
                startActivity(intent);
                break;
            case R.id.lnl_close_manage_accident_worker:
                finish();
                break;
        }
    }
}
