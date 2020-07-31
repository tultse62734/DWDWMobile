package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.adapters.WorkerRecordAdapter;
import com.example.dwdwproject.models.WorkerRecord;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.GridSpacingItemDecoration;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ManagerUserRecordivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<WorkerRecord> mWorkerRecordList;
    private WorkerRecordAdapter mRecordAdapter;
    private CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private TextView mTxtTime;
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_user_recordivity);
        initVieẉ̣();
        initData();
    }
    private void initVieẉ̣(){
        mRecyclerView = findViewById(R.id.rcv_user_record);
        mRecyclerView.setHasFixedSize(true);
        int numberOfColumns = calculateNumberOfColumns(ManagerUserRecordivity.this);
        GridLayoutManager gridLayoutManager = new
                GridLayoutManager(ManagerUserRecordivity.this, numberOfColumns);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.dp20);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration
                (numberOfColumns, spacingInPixels, true));
    }
    private int calculateNumberOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 180);
        return noOfColumns;
    }
    private void initData(){
        setDataCalendar();
        mWorkerRecordList = new ArrayList<>();
        mWorkerRecordList.add(new WorkerRecord("Worker1",12,"LocationA","A1"));
        mWorkerRecordList.add(new WorkerRecord("Worker2",13,"LocationA","A2"));
        mWorkerRecordList.add(new WorkerRecord("Worker3",14,"LocationA","A3"));
        mWorkerRecordList.add(new WorkerRecord("Worker4",15,"LocationA","A4"));
        updataUI();

    }
    private void  updataUI(){
        if(mRecordAdapter==null){
            mRecordAdapter = new WorkerRecordAdapter(ManagerUserRecordivity.this,mWorkerRecordList);
            mRecyclerView.setAdapter(mRecordAdapter);
        }
        else {
            mRecordAdapter.notifyDataSetChanged();
        }
    }
    public void setDataCalendar() {
        compactCalendarView = (CompactCalendarView)findViewById(R.id.compactcalendar_view_worker_record);
        // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
        // Use constants provided by Java Calendar class
        compactCalendarView.setUseThreeLetterAbbreviation(false);
        compactCalendarView.setIsRtl(false);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        compactCalendarView.displayOtherMonthDays(false);
        // can also take a Date object
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {

            }
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
            }
        });
    }

}