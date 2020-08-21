package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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

public class WorkerReportActivity extends AppCompatActivity implements View.OnClickListener, GetAllRecordsView {
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
        setContentView(R.layout.activity_worker_report);
        initView();
        initData();
    }
    private void initView(){
        mRecyclerView = findViewById(R.id.rcv_accident_worker);
        mLnlClose = findViewById(R.id.lnl_close_manage_accident_worker);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mGetRecordByWorker = new GetRecordByWorker(WorkerReportActivity.this,this);
        token = SharePreferenceUtils.getStringSharedPreference(WorkerReportActivity.this, BundleString.TOKEN);
        locationId = SharePreferenceUtils.getIntSharedPreference(WorkerReportActivity.this,BundleString.LOCATIONID);
        locationName= SharePreferenceUtils.getStringSharedPreference(WorkerReportActivity.this,BundleString.LOCATIONNAME);
        date = BundleString.getSelectedDate(WorkerReportActivity.this);
        setDataCalendar();
        mLnlClose.setOnClickListener(this);
        mGetRecordByWorker.getRecordByWorker(token,locationId,date);
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void updateUI(){
        if(mAccidentAdapter == null){
            mAccidentAdapter = new AccidentAdapter(WorkerReportActivity.this,mAccidentList);
            mRecyclerView.setAdapter(mAccidentAdapter);
            mAccidentAdapter.onItemClick(new AccidentAdapter.OnItemCkickListerner() {
                @Override
                public void onItemClick(int pos) {
                    Intent intent = new Intent(WorkerReportActivity.this,WorkerReportDetailActivity.class);
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
        compactCalendarView = (CompactCalendarView)findViewById(R.id.compactcalendar_worker_record);
        compactCalendarView.setUseThreeLetterAbbreviation(false);
        compactCalendarView.setIsRtl(false);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        compactCalendarView.displayOtherMonthDays(false);
        // can also take a Date object
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                String dateChoose = dateFormatForDisplaying.format(dateClicked.toString());
                date = DateManagement.changeDateStringToString(dateChoose);
                mGetRecordByWorker.getRecordByWorker(token,locationId,date);
            }
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
            }
        });
    }
    private void loadData(){
        token = SharePreferenceUtils.getStringSharedPreference(WorkerReportActivity.this, BundleString.TOKEN);
        locationId = SharePreferenceUtils.getIntSharedPreference(WorkerReportActivity.this,BundleString.LOCATIONID);
        date = BundleString.getSelectedDate(WorkerReportActivity.this);
        mGetRecordByWorker = new GetRecordByWorker(WorkerReportActivity.this,this);
        mGetRecordByWorker.getRecordByWorker(token,locationId,date);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_manage_accident_worker:
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
                String recordName = "Accident" +i;
                String roomCode = mRecordDTOList.get(i).getRoomCode();
                boolean isActive = true;
                mAccidentList.add(new Accident(recordId,recordName,recordDate,recordTime,locationname,image,roomCode,isActive));
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
        DialogNotifyError.showErrorLoginDialog(WorkerReportActivity.this,message);
    }
}
