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
