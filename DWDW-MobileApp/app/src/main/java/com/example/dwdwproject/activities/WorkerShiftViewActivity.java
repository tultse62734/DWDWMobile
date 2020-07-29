package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.adapters.ShiftAdapter;
import com.example.dwdwproject.adapters.ShiftWorkerAdapter;
import com.example.dwdwproject.models.Shift;
import com.example.dwdwproject.models.WorkerShift;
import com.example.dwdwproject.presenters.shiftPresenters.GetShiftWorkerPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.shiftsViews.GetShiftManagerView;
import com.example.dwdwproject.views.shiftsViews.GetShiftWorkerView;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class WorkerShiftViewActivity extends AppCompatActivity implements View.OnClickListener, GetShiftWorkerView {
    private View mView;
    private LinearLayout mBtnClose,mBtnFilter;
    private List<WorkerShift> mWorkerShiftList;
    private RecyclerView mRecyclerView;
    private ShiftWorkerAdapter mWorkerAdapter;
    private CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private TextView mTxtTime;
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private GetShiftWorkerPresenter mGetShiftWorkerPresenter;
    private String token;
    private int locationId;
    private List<ShiftDTO> mShiftDTOS;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker_shift_view);
        initView();
        initData();
    }
    private void initView(){
        mGetShiftWorkerPresenter = new GetShiftWorkerPresenter(WorkerShiftViewActivity.this,this);
        mBtnFilter = findViewById(R.id.lnl_filter_shift_worker);
        mTxtTime = findViewById(R.id.txt_shif_time_worker_time);
        mBtnClose = findViewById(R.id.lnl_close_manage_shift_worker);
        mRecyclerView = findViewById(R.id.rcv_shift_worker);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(WorkerShiftViewActivity.this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        token = SharePreferenceUtils.getStringSharedPreference(WorkerShiftViewActivity.this, BundleString.TOKEN);
        locationId = SharePreferenceUtils.getIntSharedPreference(WorkerShiftViewActivity.this,BundleString.LOCATIONID);
        date = BundleString.getSelectedDate(WorkerShiftViewActivity.this);
        mBtnClose.setOnClickListener(this);
        mBtnFilter.setOnClickListener(this);
            setDataCalendar();
        mGetShiftWorkerPresenter.getShiftsWorker(token,locationId,date);
    }
    private void updateUI(){
        if(mWorkerAdapter == null){
            mWorkerAdapter = new ShiftWorkerAdapter(WorkerShiftViewActivity.this,mWorkerShiftList);
            mRecyclerView.setAdapter(mWorkerAdapter);
        }else {
            mWorkerAdapter.notifyDataSetChanged();
        }
    }
    public void setDataCalendar() {
        compactCalendarView = (CompactCalendarView)findViewById(R.id.compactcalendar_view_shift);
        mTxtTime.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        compactCalendarView.setUseThreeLetterAbbreviation(false);
        compactCalendarView.setIsRtl(false);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        compactCalendarView.displayOtherMonthDays(false);
        // can also take a Date object
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                date = DateManagement.changeDateStringToString(dateClicked.toString());
                mGetShiftWorkerPresenter.getShiftsWorker(token,locationId,date);
            }
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                mTxtTime.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }
    private void loadEvents() {
        addEvents(23,Calendar.JUNE, 2020);
        addEvents(24,Calendar.JUNE, 2020);
        addEvents(25,Calendar.JUNE, 2020);
        addEvents(26,Calendar.JUNE, 2020);
        addEvents(27,Calendar.JUNE, 2020);
        addEvents(28,Calendar.JUNE, 2020);

    }

    private void logEventsByMonth(CompactCalendarView compactCalendarView) {
        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        currentCalender.set(Calendar.MONTH, Calendar.AUGUST);
        List<String> dates = new ArrayList<>();
        for (Event e : compactCalendarView.getEventsForMonth(new Date())) {
            dates.add(dateFormatForDisplaying.format(e.getTimeInMillis()));
        }
    }

    private void addEvents(int day,int month, int year) {
        currentCalender.setTime(new Date());
        currentCalender.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDayOfMonth = currentCalender.getTime();
        for (int i = 0; i < 6; i++) {
            currentCalender.setTime(firstDayOfMonth);
            if (month > -1) {
                currentCalender.set(Calendar.MONTH, month);
            }
            if (year > -1) {
                currentCalender.set(Calendar.ERA, GregorianCalendar.AD);
                currentCalender.set(Calendar.YEAR, year);
            }
            if(day >-1){
                currentCalender.add(Calendar.DATE, day);
            }
            setToMidnight(currentCalender);
            long timeInMillis = currentCalender.getTimeInMillis();

            List<Event> events = getEvents(timeInMillis, i);

            compactCalendarView.addEvents(events);
        }
    }
    private List<Event> getEvents(long timeInMillis, int day) {
        if (day < 2) {
            return Arrays.asList(new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event at " + new Date(timeInMillis)));
        } else if ( day > 2 && day <= 4) {
            return Arrays.asList(
                    new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event at " + new Date(timeInMillis)),
                    new Event(Color.argb(255, 100, 68, 65), timeInMillis, "Event 2 at " + new Date(timeInMillis)));
        } else {
            return Arrays.asList(
                    new Event(Color.argb(255, 169, 68, 65), timeInMillis, "Event at " + new Date(timeInMillis) ),
                    new Event(Color.argb(255, 100, 68, 65), timeInMillis, "Event 2 at " + new Date(timeInMillis)),
                    new Event(Color.argb(255, 70, 68, 65), timeInMillis, "Event 3 at " + new Date(timeInMillis)));
        }
    }

    private void setToMidnight(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }
    private void showFilterDateDialog(String message) {
        final Dialog dialog = new Dialog(WorkerShiftViewActivity.this);
        dialog.setContentView(R.layout.alert_layout_notify_change_day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk = dialog.findViewById(R.id.btn_Ok_choose);
        TextView mTxtNotify = dialog.findViewById(R.id.txt_notify_choose_error);
        mTxtNotify.setText(message);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_manage_shift_worker:
                finish();
                break;
            case R.id.lnl_filter_shift_worker:
                Intent intent = new Intent(WorkerShiftViewActivity.this,ShiftFilterChooseLocationActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void loadData(){
        token = SharePreferenceUtils.getStringSharedPreference(WorkerShiftViewActivity.this,BundleString.TOKEN);
        locationId = SharePreferenceUtils.getIntSharedPreference(WorkerShiftViewActivity.this,BundleString.LOCATIONID);
        date = BundleString.getSelectedDate(WorkerShiftViewActivity.this);
        mGetShiftWorkerPresenter.getShiftsWorker(token,locationId,date);

    }

    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(WorkerShiftViewActivity.this,message);
    }

    @Override
    public void getShiftWorkerSuccess(List<ShiftDTO> mShiftDTOList) {
        if(mShiftDTOList!=null ||mShiftDTOList.size()!=0){
            mWorkerShiftList = new ArrayList<>();
            mShiftDTOS = new ArrayList<>();
            mShiftDTOS = mShiftDTOList;
            for (int i = 0; i <mShiftDTOList.size() ; i++) {
                int shiftId = mShiftDTOList.get(i).getShiftId();
                String locationName = SharePreferenceUtils.getStringSharedPreference(WorkerShiftViewActivity.this,BundleString.LOCATIONNAME);
                String username =mShiftDTOList.get(i).getUsername();
                String roomCode = mShiftDTOList.get(i).getRoomCode();
                String dateShift = mShiftDTOList.get(i).getDate();
                mWorkerShiftList.add(new WorkerShift(shiftId,roomCode,username,locationName,dateShift));

            }
            updateUI();
        }
        else{
            DialogNotifyError.showErrorLoginDialog(WorkerShiftViewActivity.this,"No Shift");
        }
    }
}
