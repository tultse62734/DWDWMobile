package com.example.dwdwproject.activities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ParseException;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.adapters.ShiftAdapter;
import com.example.dwdwproject.models.Shift;
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
import java.util.Random;

public class PageShiftViewFragment extends Fragment {
    private View mView;
    private List<Shift> mShiftList;
    private RecyclerView mRecyclerView;
    private ShiftAdapter mShiftAdapter;
    private CompactCalendarView compactCalendarView;
    private SimpleDateFormat dateFormatForDisplaying = new SimpleDateFormat("dd-M-yyyy hh:mm:ss a", Locale.getDefault());
    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM - yyyy", Locale.getDefault());
    private TextView mTxtTime;
    private Calendar currentCalender = Calendar.getInstance(Locale.getDefault());

    public PageShiftViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_page_shift_view, container, false);
        return mView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVieẉ();initData();
    }

    private void initVieẉ(){
            mTxtTime = mView.findViewById(R.id.text_month_current);
        mRecyclerView = mView.findViewById(R.id.rcv_shift);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mShiftList = new ArrayList<>();
//        mShiftList.add(new Shift(1,"Nhân Viên A","Khu A","Room 101"));
//        mShiftList.add(new Shift(2,"Nhân Viên B","Khu B","Room 102"));
//        mShiftList.add(new Shift(3,"Nhân Viên C","Khu C","Room 103"));
//        mShiftList.add(new Shift(4,"Nhân Viên D","Khu D","Room 104"));
//        mShiftList.add(new Shift(5,"Nhân Viên E","Khu E","Room 105"));
//        mShiftList.add(new Shift(6,"Nhân Viên F","Khu F","Room 106"));
//        mShiftList.add(new Shift(7,"Nhân Viên H","Khu H","Room 107"));
        updateUI();
        setDataCalendar();
    }
    private void updateUI(){
        if(mShiftAdapter == null){
            mShiftAdapter = new ShiftAdapter(getContext(),mShiftList);
            mRecyclerView.setAdapter(mShiftAdapter);
        }
        else {
            mShiftAdapter.notifyDataSetChanged();
        }

    }
    public void setDataCalendar() {
         compactCalendarView = (CompactCalendarView)mView.findViewById(R.id.compactcalendar_view);
        // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
        // Use constants provided by Java Calendar class
        mTxtTime.setText(dateFormatForMonth.format(compactCalendarView.getFirstDayOfCurrentMonth()));
        compactCalendarView.setUseThreeLetterAbbreviation(false);
        compactCalendarView.setIsRtl(false);
        compactCalendarView.setUseThreeLetterAbbreviation(true);
        compactCalendarView.displayOtherMonthDays(false);
        loadEvents();
        // can also take a Date object
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                if(events.size()==0){
                    showFilterDateDialog("Không có ca làm vào ngày này");
                }
            }
            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                mTxtTime.setText(dateFormatForMonth.format(firstDayOfNewMonth));
            }
        });
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
        final Dialog dialog = new Dialog(getContext());
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

}
