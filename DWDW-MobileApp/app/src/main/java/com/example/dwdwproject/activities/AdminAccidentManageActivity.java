package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.dwdwproject.PageFragment;
import com.example.dwdwproject.R;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.utils.BundleString;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

public class AdminAccidentManageActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout mBtnClose;
    private FragmentPagerItemAdapter mAdapter;
    private ViewPager mViewPager;
    private List<Location> mLocationList;
    private SmartTabLayout mViewPagerTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_accident_manage);
        initView();
        initData();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_manage_accident_admin);

    }
    private void initData(){
        mBtnClose.setOnClickListener(this);
        mLocationList = new ArrayList<>();
        mLocationList.add(new Location(1,"Khu A","18-11-2019",true));
        mLocationList.add(new Location(2,"Khu B","18-11-2019",true));
        mLocationList.add(new Location(3,"Khu C","18-11-2019",true));
        mLocationList.add(new Location(4,"Khu D","18-11-2019",true));
        getCategoryData(mLocationList);
    }
    private void getCategoryData(List<Location> locationList) {
        FragmentPagerItems.Creator creator = FragmentPagerItems.with(getApplicationContext());
        for (int i = 0; i <locationList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(BundleString.LOCATION_INFO,locationList.get(i));
            creator.add(locationList.get(i).getNameLocation(), PageAccidentFragment.class, bundle);
        }
        mAdapter = new FragmentPagerItemAdapter(getSupportFragmentManager(),
                creator.create());
        mViewPager = (ViewPager) findViewById(R.id.viewpager_admin_accident);
        mViewPager.setOffscreenPageLimit(locationList.size());
        mViewPager.setAdapter(mAdapter);
        //set viewPager for SmartTabLayout
        mViewPagerTab = (SmartTabLayout) findViewById(R.id.view_pager_tab_accident_admin);
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
        int id  = v.getId();
        switch (id){
            case R.id.lnl_close_manage_accident_admin:
                finish();
                break;

        }
    }
}
