package com.example.dwdwproject.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.example.dwdwproject.adapters.ManageAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Manager;
import com.example.dwdwproject.presenters.locationsPresenters.GetAllLocationPresenter;
import com.example.dwdwproject.presenters.locationsPresenters.GetLocationByIdPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;
import com.example.dwdwproject.views.locationsViews.GetLocationView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentStatePagerItemAdapter;

import java.util.ArrayList;
import java.util.List;

import in.myinnos.alphabetsindexfastscrollrecycler.IndexFastScrollRecyclerView;

public class ManageManagerActivity extends AppCompatActivity implements View.OnClickListener, GetAllLocatonView {
    private FragmentStatePagerItemAdapter mAdapter;
    private ViewPager mViewPager;
    private List<Location> mLocationList;
    private SmartTabLayout mViewPagerTab;
    private String token;
    private LinearLayout mBtnClose,mBtnAddManagerAdmin,mBtnFilterManager;
    private GetAllLocationPresenter mGetLocationByIdPresenter;
    public final static int CREATE_MANAGER_CODE = 123;
    public final static int GET_ALL_MANAGER_CODE = 122;
    public final static int UPDATE_MANAGER_CODE = 121;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_worker);
        initView();
        initData();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_manage_manage);
        mBtnAddManagerAdmin = findViewById(R.id.lnl_add_manager_admin);
        mBtnFilterManager  =findViewById(R.id.lnl_get_all_manager_admin);
    }
    private void  initData(){
        token = SharePreferenceUtils.getStringSharedPreference(ManageManagerActivity.this,BundleString.TOKEN);
        mGetLocationByIdPresenter  = new GetAllLocationPresenter(ManageManagerActivity.this,this);
        mBtnAddManagerAdmin.setOnClickListener(this);
        mBtnFilterManager.setOnClickListener(this);
        mBtnClose.setOnClickListener(this);
//        mLocationList = new ArrayList<>();
//        mLocationList.add(new Location(1,"Khu A","18-11-2019",true));
//        mLocationList.add(new Location(2,"Khu B","18-11-2019",true));
//        mLocationList.add(new Location(3,"Khu C","18-11-2019",true));
//        mLocationList.add(new Location(4,"Khu D","18-11-2019",true));
//        getCategoryData(mLocationList);

        mGetLocationByIdPresenter.getAllLocation(token);
    }
    private void getCategoryData(List<Location> locationList) {
        FragmentPagerItems.Creator creator = FragmentPagerItems.with(getApplicationContext());
        for (int i = 0; i <locationList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putInt(BundleString.LOCATIONID,locationList.get(i).getLocationId());
            creator.add(locationList.get(i).getNameLocation(), PageManagerFragment.class, bundle);
        }
        mAdapter = new FragmentStatePagerItemAdapter(getSupportFragmentManager(),
                creator.create());
        mViewPager = (ViewPager) findViewById(R.id.viewpager_manager);
        mViewPager.setOffscreenPageLimit(locationList.size());
        mViewPager.setAdapter(mAdapter);
        //set viewPager for SmartTabLayout
        mViewPagerTab = (SmartTabLayout) findViewById(R.id.view_pager_tab_manage_manager);
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CREATE_MANAGER_CODE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                mGetLocationByIdPresenter.getAllLocation(token);
            }
        }
        if (requestCode == GET_ALL_MANAGER_CODE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                mGetLocationByIdPresenter.getAllLocation(token);
            }
        }if (requestCode == UPDATE_MANAGER_CODE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                mGetLocationByIdPresenter.getAllLocation(token);
            }
        }
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_manage_manage:
                finish();
                break;
            case  R.id.lnl_add_manager_admin:
                Intent intent = new Intent(ManageManagerActivity.this,AdminCreateManagerActivity.class);
                startActivityForResult(intent,CREATE_MANAGER_CODE);
                break;
            case R.id.lnl_get_all_manager_admin:
                Intent intent1 = new Intent(ManageManagerActivity.this,AdminGetAllUserActivity.class);
                startActivityForResult(intent1,GET_ALL_MANAGER_CODE);
                break;
        }
    }
    @Override
    public void getAllLocationSuccess(List<LocationDTO> mLocationDTOList) {
        if(mLocationDTOList !=null){
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
        DialogNotifyError.showErrorLoginDialog(ManageManagerActivity.this,"Data");
    }
    private void reloadDataFragment() {
        for (int i = 0; i < mAdapter.getCount(); i++) {
            ((PageManagerFragment) mAdapter.getPage(i)).reloadPage();
        }
        mAdapter.notifyDataSetChanged();
    }
}
