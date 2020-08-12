package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.adapters.WorkerRecordAdapter;
import com.example.dwdwproject.models.Shift;
import com.example.dwdwproject.models.WorkerRecord;
import com.example.dwdwproject.presenters.shiftPresenters.GetAllShiftFromLocationPresenter;
import com.example.dwdwproject.presenters.shiftPresenters.UpdateShiftActivePresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.GridSpacingItemDecoration;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.shiftsViews.GetShiftManagerView;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ManagerUserRecordivity extends AppCompatActivity implements View.OnClickListener, GetShiftManagerView {
    private RecyclerView mRecyclerView;
    private List<WorkerRecord> mWorkerRecordList;
    private WorkerRecordAdapter mRecordAdapter;
    private CompactCalendarView compactCalendarView;
    private GetAllShiftFromLocationPresenter mGetAllShiftFromLocationPresenter;
    private String token;
    private List<ShiftDTO> mShiftDTO;
    private int locationId;
    private String date;
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());

    private LinearLayout mBtnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_user_recordivity);
        initVieẉ̣();
        initData();
    }
    private void initVieẉ̣(){
        mBtnClose = findViewById(R.id.lnl_close_worker_record);
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
        token = SharePreferenceUtils.getStringSharedPreference(ManagerUserRecordivity.this, BundleString.TOKEN);
        locationId = SharePreferenceUtils.getIntSharedPreference(ManagerUserRecordivity.this,BundleString.LOCATIONID);
        date = BundleString.getSelectedDate(ManagerUserRecordivity.this);
        setDataCalendar();
        mBtnClose.setOnClickListener(this);
        mGetAllShiftFromLocationPresenter = new GetAllShiftFromLocationPresenter(ManagerUserRecordivity.this,this);
        mGetAllShiftFromLocationPresenter.getAllShiftFromLocation(token,locationId,date);
    }
    private void  updataUI(){
        if(mRecordAdapter==null){
            mRecordAdapter = new WorkerRecordAdapter(ManagerUserRecordivity.this,mWorkerRecordList);
            mRecyclerView.setAdapter(mRecordAdapter);
            mRecordAdapter.OnItemClickListernner(new WorkerRecordAdapter.OnItemClickListerner() {
                @Override
                public void onItemClick(int pos) {
                    Intent intent = new Intent(ManagerUserRecordivity.this,ManageAccidentActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("WORKERRECORD", (Serializable) mShiftDTO.get(pos));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        else {
            mRecordAdapter.notify(mWorkerRecordList);
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
                String datechoose   = dateFormatForDisplaying.format(dateClicked);
                date = DateManagement.changeDateStringToString(datechoose);
                SharePreferenceUtils.saveStringSharedPreference(ManagerUserRecordivity.this,BundleString.FILTER_DATE_IS_SELECTE,date);
                mGetAllShiftFromLocationPresenter.getAllShiftFromLocation(token,locationId,date);
            }
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
    private void loadData(){
        token = SharePreferenceUtils.getStringSharedPreference(ManagerUserRecordivity.this,BundleString.TOKEN);
        locationId = SharePreferenceUtils.getIntSharedPreference(ManagerUserRecordivity.this,BundleString.LOCATIONID);
        date = BundleString.getSelectedDate(ManagerUserRecordivity.this);
        mGetAllShiftFromLocationPresenter.getAllShiftFromLocation(token,locationId,date);

    }
    @Override
    public void getShiftManagerSuccess(List<ShiftDTO> mShiftDTOList) {
        if(mShiftDTOList!=null ||mShiftDTOList.size()!=0){
            mWorkerRecordList = new ArrayList<>();
            mShiftDTO = new ArrayList<>();
            mShiftDTO = mShiftDTOList;
            for (int i = 0; i <mShiftDTOList.size() ; i++) {
                String locationName = SharePreferenceUtils.getStringSharedPreference(ManagerUserRecordivity.this,BundleString.LOCATIONNAME);
                String username =mShiftDTOList.get(i).getUsername();
                String roomCode = mShiftDTOList.get(i).getRoomCode();
                mWorkerRecordList.add(new WorkerRecord(username,locationName,roomCode));
            }
           updataUI();
        }
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(ManagerUserRecordivity.this,"No Shift");

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_worker_record:
                finish();
                break;
        }
    }
}