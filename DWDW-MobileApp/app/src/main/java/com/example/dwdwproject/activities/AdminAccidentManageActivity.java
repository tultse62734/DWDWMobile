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
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.presenters.locationsPresenters.GetAllLocationPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

public class AdminAccidentManageActivity extends AppCompatActivity implements View.OnClickListener, GetAllLocatonView {
    LinearLayout mBtnClose;
    private FragmentPagerItemAdapter mAdapter;
    private ViewPager mViewPager;
    private List<Location> mLocationList;
    private SmartTabLayout mViewPagerTab;
    private GetAllLocationPresenter mGetAllLocationPresenter;
    private  String token ;
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
        token = SharePreferenceUtils.getStringSharedPreference(AdminAccidentManageActivity.this,BundleString.TOKEN);
        mGetAllLocationPresenter = new GetAllLocationPresenter(AdminAccidentManageActivity.this,this);
        mGetAllLocationPresenter.getAllLocation(token);
    }

    @Override
    protected void onResume() {
        super.onResume();
        reloadDataFragment();
    }

    private void getCategoryData(List<Location> locationList) {
        FragmentPagerItems.Creator creator = FragmentPagerItems.with(getApplicationContext());
        for (int i = 0; i <locationList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putInt(BundleString.LOCATIONID,locationList.get(i).getLocationId());
            bundle.putString(BundleString.LOCATIONNAME,locationList.get(i).getNameLocation());
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
    @Override
    public void getAllLocationSuccess(List<LocationDTO> mLocationDTOList) {
        if(mLocationDTOList!=null){
            this.mLocationList = new ArrayList<>();
            for (int i = 0; i <mLocationDTOList.size() ; i++) {
                int locationId  = mLocationDTOList.get(i).getLocationId();
                String locationName = mLocationDTOList.get(i).getLocationCode();
                boolean isactive = mLocationDTOList.get(i).isActive();
                this.mLocationList.add(new Location(locationId,locationName,isactive));
            }
            getCategoryData(mLocationList);
        }
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(AdminAccidentManageActivity.this,message);
    }
    private void reloadDataFragment() {
        for (int i = 0; i < mAdapter.getCount(); i++) {
            ((PageAccidentFragment) mAdapter.getPage(i)).reloadPage();
        }
    }
}
