package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

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
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Shift;
import com.example.dwdwproject.models.ShiftTime;
import com.example.dwdwproject.presenters.shiftPresenters.GetAllShiftFromLocationPresenter;
import com.example.dwdwproject.presenters.shiftPresenters.UpdateShiftActivePresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.shiftsViews.GetShiftManagerView;
import com.example.dwdwproject.views.shiftsViews.UpdateShiftActiveView;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class ManagerShiftViewActivity extends AppCompatActivity implements View.OnClickListener, GetShiftManagerView, UpdateShiftActiveView {
    private LinearLayout mBtnClose,mBtnAddShiftWorker,mBtnFilter;
    private List<Shift> mShiftList;
    private List<ShiftDTO> mShiftDTOS;
    private RecyclerView mRecyclerView;
    private ShiftAdapter mShiftAdapter;
    private CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private TextView mTxtTime;
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());
    private GetAllShiftFromLocationPresenter mGetAllShiftFromLocationPresenter;
    private String token;
    private boolean isActive;
    private int locationId;
    private UpdateShiftActivePresenter mUpdateShiftActivePresenter;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_shift_view);
        initView();
        initData();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_manage_shift);
        mBtnAddShiftWorker = findViewById(R.id.lnl_add_shift_worker);
        mBtnFilter = findViewById(R.id.lnl_filter_shift_worker);
        mTxtTime = findViewById(R.id.text_month_current);
        mRecyclerView = findViewById(R.id.rcv_shift);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ManagerShiftViewActivity.this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        token = SharePreferenceUtils.getStringSharedPreference(ManagerShiftViewActivity.this,BundleString.TOKEN);
        locationId = SharePreferenceUtils.getIntSharedPreference(ManagerShiftViewActivity.this,BundleString.LOCATIONID);
        date = BundleString.getSelectedDate(ManagerShiftViewActivity.this);
        mTxtTime.setText(date);
        mUpdateShiftActivePresenter = new UpdateShiftActivePresenter(ManagerShiftViewActivity.this,this);
        mGetAllShiftFromLocationPresenter = new GetAllShiftFromLocationPresenter(ManagerShiftViewActivity.this,this);
        mBtnClose.setOnClickListener(this);
        mBtnFilter.setOnClickListener(this);
        mBtnAddShiftWorker.setOnClickListener(this);
        setDataCalendar();
        mGetAllShiftFromLocationPresenter.getAllShiftFromLocation(token,locationId,date);
    }
    private void loadData(){
        token = SharePreferenceUtils.getStringSharedPreference(ManagerShiftViewActivity.this,BundleString.TOKEN);
        locationId = SharePreferenceUtils.getIntSharedPreference(ManagerShiftViewActivity.this,BundleString.LOCATIONID);
        date = BundleString.getSelectedDate(ManagerShiftViewActivity.this);
        mGetAllShiftFromLocationPresenter.getAllShiftFromLocation(token,locationId,date);

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_manage_shift:
                finish();
                break;
            case  R.id.lnl_add_shift_worker:
                Intent intent = new Intent(ManagerShiftViewActivity.this,ManagerCreateShiftActivity.class);
                startActivity(intent);
                break;
            case  R.id.lnl_filter_shift_worker:
                Intent intent1 = new Intent(ManagerShiftViewActivity.this,ShiftFilterChooseLocationActivity.class);
                startActivity(intent1);
                break;
        }
    }
    private void updateUI(){
        if(mShiftAdapter == null){
            mShiftAdapter = new ShiftAdapter(ManagerShiftViewActivity.this,mShiftList);
            mRecyclerView.setAdapter(mShiftAdapter);
            mShiftAdapter.OnItemClick(new ShiftAdapter.OnItemClickListerner() {
                @Override
                public void onItemClick(int pos) {
                    Intent intent = new Intent(ManagerShiftViewActivity.this,ManagerUpdateShiftActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleString.SHIFTDETAIL,mShiftDTOS.get(pos));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            mShiftAdapter.OnItemAciveClickListener(new ShiftAdapter.OnItemActiveClick() {
                @Override
                public void onItemActiveClick(int pos) {
                    if(mShiftDTOS.get(pos).isActive()){
                        mShiftDTOS.get(pos).setActive(false);
                        mUpdateShiftActivePresenter.updateShiftsActive(token,mShiftDTOS.get(pos));
                    }else {
                        mShiftDTOS.get(pos).setActive(true);
                        mUpdateShiftActivePresenter.updateShiftsActive(token,mShiftDTOS.get(pos));
                    }

                }
            });
        }
        else {
            mShiftAdapter.notify(mShiftList);
        }
    }
    public void setDataCalendar() {
        compactCalendarView = (CompactCalendarView)findViewById(R.id.compactcalendar_view);
        // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
        // Use constants provided by Java Calendar class
        mTxtTime.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        compactCalendarView.setUseThreeLetterAbbreviation(false);
        compactCalendarView.setIsRtl(false);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        compactCalendarView.displayOtherMonthDays(false);
        // can also take a Date object
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                String dateChoose = dateFormatForDisplaying.format(dateClicked);
                date = DateManagement.changeDateStringToString(dateChoose);
                mGetAllShiftFromLocationPresenter.getAllShiftFromLocation(token,locationId,date);
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
        addEvents(23, Calendar.JUNE, 2020);
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
        final Dialog dialog = new Dialog(ManagerShiftViewActivity.this);
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
    public void getShiftManagerSuccess(List<ShiftDTO> mShiftDTOList) {
        if(mShiftDTOList!=null ||mShiftDTOList.size()!=0){
            mShiftList = new ArrayList<>();
            mShiftDTOS = new ArrayList<>();
            mShiftDTOS = mShiftDTOList;
            for (int i = 0; i <mShiftDTOList.size() ; i++) {
                 int shiftId = mShiftDTOList.get(i).getShiftId();
                 String locationName = SharePreferenceUtils.getStringSharedPreference(ManagerShiftViewActivity.this,BundleString.LOCATIONNAME);
                 String username =mShiftDTOList.get(i).getFullname();
                 String roomCode = mShiftDTOList.get(i).getRoomCode();
                 boolean isActive = mShiftDTOList.get(i).isActive();
                mShiftList.add(new Shift(shiftId,username,locationName,roomCode,isActive));
            }
            updateUI();
        }
        else{
            DialogNotifyError.showErrorLoginDialog(ManagerShiftViewActivity.this,"No Shift");
        }
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(ManagerShiftViewActivity.this,message);
    }

    @Override
    public void updateShiftActiveSuccess() {
        mGetAllShiftFromLocationPresenter.getAllShiftFromLocation(token,locationId,date);
    }
}
