package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.adapters.AccidentAdapter;
import com.example.dwdwproject.models.Accident;
import com.example.dwdwproject.presenters.recordsPresenters.GetRecordByWorker;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.recordsViews.GetAllRecordsView;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class WorkerHistoryActivity extends AppCompatActivity implements View.OnClickListener, GetAllRecordsView {
    private LinearLayout mLnlClose;
    private List<Accident> mAccidentList;
    private RecyclerView mRecyclerView;
    private AccidentAdapter mAccidentAdapter;
    private CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private String date,token;
    private int locationId;
    private String locationName;
    private String time,day;
    private GetRecordByWorker mGetRecordByWorker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_history);
        initView();
        initData();
    }
    private void initView(){
        mRecyclerView = findViewById(R.id.rcv_accident_worker_history);
        mLnlClose = findViewById(R.id.lnl_close_manage_accident_worker_history);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mGetRecordByWorker = new GetRecordByWorker(WorkerHistoryActivity.this,this);
        token = SharePreferenceUtils.getStringSharedPreference(WorkerHistoryActivity.this, BundleString.TOKEN);
        locationId = SharePreferenceUtils.getIntSharedPreference(WorkerHistoryActivity.this,BundleString.LOCATIONID);
        locationName= SharePreferenceUtils.getStringSharedPreference(WorkerHistoryActivity.this,BundleString.LOCATIONNAME);
        date = BundleString.getSelectedDate(WorkerHistoryActivity.this);
        setDataCalendar();
        mLnlClose.setOnClickListener(this);
        mGetRecordByWorker.getConfirmRecordByWorker(token,locationId,date);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void updateUI(){
        if(mAccidentAdapter == null){
            mAccidentAdapter = new AccidentAdapter(WorkerHistoryActivity.this,mAccidentList);
            mRecyclerView.setAdapter(mAccidentAdapter);
            mAccidentAdapter.onItemClick(new AccidentAdapter.OnItemCkickListerner() {
                @Override
                public void onItemClick(int pos) {
                    Intent intent = new Intent(WorkerHistoryActivity.this,AccidentReportDetailActivity.class);
                    Bundle bundle = new Bundle();
                    mAccidentList.get(pos).setImage(mAccidentList.get(pos).getImage());
                    bundle.putSerializable(BundleString.RECORDDETAIL,mAccidentList.get(pos));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }else {
            mAccidentAdapter.notify(mAccidentList);
        }
    }
    public void setDataCalendar() {
        compactCalendarView = (CompactCalendarView)findViewById(R.id.compactcalendar_worker_record_history);
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
                SharePreferenceUtils.saveStringSharedPreference(WorkerHistoryActivity.this,BundleString.FILTER_DATE_IS_SELECTE,date);
                mGetRecordByWorker.getConfirmRecordByWorker(token,locationId,date);
            }
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
            }
        });
    }
    private void loadData(){
        token = SharePreferenceUtils.getStringSharedPreference(WorkerHistoryActivity.this, BundleString.TOKEN);
        locationId = SharePreferenceUtils.getIntSharedPreference(WorkerHistoryActivity.this,BundleString.LOCATIONID);
        date = BundleString.getSelectedDate(WorkerHistoryActivity.this);
        mGetRecordByWorker = new GetRecordByWorker(WorkerHistoryActivity.this,this);
        mGetRecordByWorker.getConfirmRecordByWorker(token,locationId,date);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_manage_accident_worker_history:
                finish();
                break;
        }
    }
    @Override
    public void getAllRecordSuccess(List<RecordDTO> mRecordDTOList) {
        if(mRecordDTOList!=null){
            mAccidentList = new ArrayList<>();
            for (int i = 0; i <mRecordDTOList.size() ; i++) {
                int recordId= mRecordDTOList.get(i).getRecordId();
                String image  =mRecordDTOList.get(i).getImage();
                String locationname = locationName;
                String recordDate = splitFromDay(mRecordDTOList.get(i).getRecordDateTime());
                String recordTime = splitFromToTime(mRecordDTOList.get(i).getRecordDateTime());
                String recordName = mRecordDTOList.get(i).getFullname();
                String roomCode = mRecordDTOList.get(i).getRoomCode();
                String status = mRecordDTOList.get(i).getStatus();
                mAccidentList.add(new Accident(recordId,recordName,recordDate,recordTime,locationname,image,roomCode,status));
            }
            updateUI();
        }
    }
    private String splitFromDay(String filterDate) {
        String[] tmp = filterDate.split("T");
        day = tmp[0];
        return day;
    }
    private String splitFromToTime(String filterDate) {
        String[] tmp = filterDate.split("T");
        time = tmp[1];
        return time;
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(WorkerHistoryActivity.this,message);
    }
}