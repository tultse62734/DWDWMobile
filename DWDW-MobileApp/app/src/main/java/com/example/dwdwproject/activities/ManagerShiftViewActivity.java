package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.ShiftTime;
import com.example.dwdwproject.utils.BundleString;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

public class ManagerShiftViewActivity extends AppCompatActivity implements View.OnClickListener {
    private FragmentPagerItemAdapter mAdapter;
    private ViewPager mViewPager;
    private List<ShiftTime> shiftTimeList;
    private SmartTabLayout mViewPagerTab;
    private LinearLayout mBtnClose,mBtnAddShiftWorker,mBtnFilter;
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
    }
    private void initData(){
        mBtnClose.setOnClickListener(this);
        mBtnFilter.setOnClickListener(this);
        mBtnAddShiftWorker.setOnClickListener(this);
        shiftTimeList = new ArrayList<>();
        shiftTimeList.add(new ShiftTime(1,"Ca sáng","08:00 AM","12:00 PM"));
        shiftTimeList.add(new ShiftTime(1,"Ca trưa","12:00 AM","17:00 PM"));
        shiftTimeList.add(new ShiftTime(1,"Ca chiều","18:00 AM","24:00 PM"));
        getCategoryData(shiftTimeList);
    }
    private void getCategoryData(List<ShiftTime> mShiftTimes) {
        FragmentPagerItems.Creator creator = FragmentPagerItems.with(getApplicationContext());
        for (int i = 0; i <mShiftTimes.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(BundleString.LOCATION_INFO,mShiftTimes.get(i));
            creator.add(mShiftTimes.get(i).getTimeCode(), PageShiftViewFragment.class, bundle);
        }
        mAdapter = new FragmentPagerItemAdapter(getSupportFragmentManager(),
                creator.create());
        mViewPager = (ViewPager) findViewById(R.id.viewpager_shift);
        mViewPager.setOffscreenPageLimit(mShiftTimes.size());
        mViewPager.setAdapter(mAdapter);
        //set viewPager for SmartTabLayout
        mViewPagerTab = (SmartTabLayout) findViewById(R.id.view_pager_tab_manage_shift);
        mViewPagerTab.setViewPager(mViewPager);
        TextView view = (TextView) mViewPagerTab.getTabAt(0);
        view.setBackground(getResources().getDrawable(R.color.colorOrange));
        view.setTextColor(getResources().getColor(R.color.colorWhite));
        mViewPagerTab.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                setColorForTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //set color for tab
    private void setColorForTab(int position) {
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            TextView view = (TextView) mViewPagerTab.getTabAt(i);
            view.setBackground(getResources().getDrawable(R.color.colorWhite));
            view.setTextColor(getResources().getColor(R.color.colorOrange));
        }
        TextView view = (TextView) mViewPagerTab.getTabAt(position);
        view.setBackground(getResources().getDrawable(R.color.colorOrange));
        view.setTextColor(getResources().getColor(R.color.colorWhite));
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
                Intent intent1 = new Intent(ManagerShiftViewActivity.this,ShiftDateFilterActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
