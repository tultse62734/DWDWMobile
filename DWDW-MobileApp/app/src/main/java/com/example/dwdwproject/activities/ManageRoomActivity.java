package com.example.dwdwproject.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.PageFragment;
import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Status;
import com.example.dwdwproject.presenters.locationsPresenters.GetAllLocationPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentStatePagerItemAdapter;

import java.util.ArrayList;
import java.util.List;

public class ManageRoomActivity extends AppCompatActivity implements View.OnClickListener, GetAllLocatonView {
    LinearLayout mBtnClose,mBtnAddRoomAdmin;
    private FragmentStatePagerItemAdapter mAdapter;
    private ViewPager mViewPager;
    private GetAllLocationPresenter mGetAllLocationPresenter;
    private List<Location> mLocationList;
    private String token ;
    private SmartTabLayout mViewPagerTab;
    public final static int CREATE_ROOM_CODE = 133;
    public final static int GET_ALL_ROOM_CODE = 132;
    public final static int UPDATE_ROOM_CODE = 131;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_room);
        initView();
        initData();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_manage_room);
        mBtnAddRoomAdmin = findViewById(R.id.lnl_add_room_admin);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CREATE_ROOM_CODE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                mGetAllLocationPresenter.getAllLocation(token);
            }
        }
        if (requestCode == GET_ALL_ROOM_CODE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                mGetAllLocationPresenter.getAllLocation(token);
            }
        }if (requestCode == UPDATE_ROOM_CODE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                mGetAllLocationPresenter.getAllLocation(token);
            }
        }
    }
    private void initData(){
        mBtnClose.setOnClickListener(this);
        mBtnAddRoomAdmin.setOnClickListener(this);
//        mLocationList = new ArrayList<>();
//        mLocationList.add(new Location(1,"Khu A","18-11-2019",true));
//        mLocationList.add(new Location(2,"Khu B","18-11-2019",true));
//        mLocationList.add(new Location(3,"Khu C","18-11-2019",true));
//        mLocationList.add(new Location(4,"Khu D","18-11-2019",true));
       token =  SharePreferenceUtils.getStringSharedPreference(ManageRoomActivity.this,BundleString.TOKEN);
        mGetAllLocationPresenter = new GetAllLocationPresenter(ManageRoomActivity.this,this);
        mGetAllLocationPresenter.getAllLocation(token);
    }
    private void getCategoryData(List<Location> locationList) {
        FragmentPagerItems.Creator creator = FragmentPagerItems.with(getApplicationContext());
        for (int i = 0; i <locationList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(BundleString.LOCATIONID,locationList.get(i).getLocationId());
            creator.add(locationList.get(i).getNameLocation(), PageRoomFragment.class, bundle);
        }
        mAdapter = new FragmentStatePagerItemAdapter(getSupportFragmentManager(),
                creator.create());
        mViewPager = (ViewPager) findViewById(R.id.viewpager_room);
        mViewPager.setOffscreenPageLimit(locationList.size());
        mViewPager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        //set viewPager for SmartTabLayout
        mViewPagerTab = (SmartTabLayout) findViewById(R.id.view_pager_tab_manage_room);
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
        reloadDataFragment();

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
            case R.id.lnl_close_manage_room:
                finish();
                break;
            case R.id.lnl_add_room_admin:
                Intent intent = new Intent(ManageRoomActivity.this,AdminCreateRoomActivity.class);
                startActivityForResult(intent,CREATE_ROOM_CODE);
                break;
        }
    }
    @Override
    public void getAllLocationSuccess(List<LocationDTO> mLocationDTOList) {
        mLocationList = new ArrayList<>();
        for (int i = 0; i <mLocationDTOList.size() ; i++) {
            int id = mLocationDTOList.get(i).getLocationId();
            String name = mLocationDTOList.get(i).getLocationCode();
            boolean isActive = mLocationDTOList.get(i).isActive();
            mLocationList.add(new Location(id,name, isActive));
        }
        getCategoryData(mLocationList);
        reloadDataFragment();
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(ManageRoomActivity.this,"Data is error");
    }
    private void reloadDataFragment() {
        for (int i = 0; i < mAdapter.getCount(); i++) {
            ((PageRoomFragment) mAdapter.getPage(i)).reloadPage();
        }
        mAdapter.notifyDataSetChanged();
    }
}
