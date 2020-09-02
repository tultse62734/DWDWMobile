package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.TypeRecord;
import com.example.dwdwproject.models.Manager;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentStatePagerItemAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RecordManagerActivity extends AppCompatActivity  implements View.OnClickListener{
    private FragmentStatePagerItemAdapter mAdapter;
    private Manager mManagerList;
    LinearLayout mBtnClose;
    private ViewPager mViewPager;
    private SmartTabLayout mViewPagerTab;
    private String token;
    private List<TypeRecord> mStringList;
    private LinearLayout mBtnFilter;
    private int mYear;
    private int mMonth;
    private int mDay;
    private String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_manager);
        initView();
        initData();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_manage_record_manager);
        mBtnFilter = findViewById(R.id.lnl_filter_manager_record_user);
    }
    private void initData(){
        Bundle bundle = getIntent().getExtras();
        mManagerList = (Manager)bundle.getSerializable(BundleString.MANAGERDETAIL);
        token = SharePreferenceUtils.getStringSharedPreference(RecordManagerActivity.this, BundleString.TOKEN);
        mBtnClose.setOnClickListener(this);
        mBtnFilter.setOnClickListener(this);
        mStringList = new ArrayList<>();
        mStringList.add(new TypeRecord(1,"SLEEP"));
        mStringList.add(new TypeRecord(2,"DENIED"));
        getCategoryData(mStringList);
    }
    private void getCategoryData(List<TypeRecord> mStringList) {
        FragmentPagerItems.Creator creator = FragmentPagerItems.with(getApplicationContext());
        for (int i = 0; i <mStringList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putInt(BundleString.WORKERID,mManagerList.getUserId());
            bundle.putInt(BundleString.RECORDID,mStringList.get(i).getTypeId());
            creator.add(mStringList.get(i).getRecordType(), RecordSleepFragment.class, bundle);
        }
        mAdapter = new FragmentStatePagerItemAdapter(getSupportFragmentManager(),
                creator.create());
        mViewPager = (ViewPager) findViewById(R.id.viewpager_record);
        mViewPager.setOffscreenPageLimit(mStringList.size());
        mViewPager.setAdapter(mAdapter);
        //set viewPager for SmartTabLayout
        mViewPagerTab = (SmartTabLayout) findViewById(R.id.view_pager_tab_record);
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
    private void chooseTimeBirthDay(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        date = DateManagement.fortmatIntToDate(year)+"-"+DateManagement.fortmatIntToDate(monthOfYear+1)+"-"+DateManagement.fortmatIntToDate(dayOfMonth);
                        SharePreferenceUtils.saveStringSharedPreference(RecordManagerActivity.this,BundleString.FILTER_DATE_IS_SELECTE,date);
                        getCategoryData(mStringList);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    @Override
    public void onClick(View v) {
        int id  = v.getId();
        switch (id){
            case R.id.lnl_close_manage_record_manager:
                finish();
                break;
            case R.id.lnl_filter_manager_record_user:
                chooseTimeBirthDay();
                break;
        }
    }

}