package com.example.dwdwproject.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.PageFragment;
import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.adapters.DeviceAdapter;
import com.example.dwdwproject.models.Device;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.presenters.devicesPresenters.GetAllDevicePresenter;
import com.example.dwdwproject.presenters.locationsPresenters.GetAllLocationPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.devicesViews.GetAllDeviceView;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

public class ManageDeviceActivity extends AppCompatActivity implements View.OnClickListener, GetAllLocatonView {
    LinearLayout mBtnClose,mBtnAdđeviceAdmin,mBtnGetAllDevice;
    private FragmentPagerItemAdapter mAdapter;
    private ViewPager mViewPager;
    private List<Location> mLocationList;
    private GetAllLocationPresenter mGetAllLocationPresenter;
    private SmartTabLayout mViewPagerTab;
    private String token;
    public final static int CREATE_DEVICE_CODE = 113;
    public final static int GET_ALL_DEVICE_CODE = 112;
    public final static int UPDATE_DEVICE_CODE = 111;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_device);
        initView();
        initData();
    }
    @Override
    public void onClick(View v) {
        int id  = v.getId();
        switch (id){
            case R.id.lnl_close_manage_device:
                finish();
                break;
            case R.id.lnl_add_device_admin:
                Intent intent = new Intent(ManageDeviceActivity.this,AdminCreateDeviceActivity.class);
                startActivityForResult(intent,CREATE_DEVICE_CODE);
                break;
            case R.id.lnl_get_all_device_admin:
                Intent intent1 = new Intent(ManageDeviceActivity.this,AdminGetAllDeviceActivity.class);
                startActivityForResult(intent1,GET_ALL_DEVICE_CODE);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CREATE_DEVICE_CODE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                mGetAllLocationPresenter.getAllLocation(token);
            }
        }
        if (requestCode == GET_ALL_DEVICE_CODE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                mGetAllLocationPresenter.getAllLocation(token);
            }
        }if (requestCode == UPDATE_DEVICE_CODE) {
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
                mGetAllLocationPresenter.getAllLocation(token);
            }
        }
    }

    private void initView(){
        mBtnGetAllDevice = findViewById(R.id.lnl_get_all_device_admin);
        mBtnClose = findViewById(R.id.lnl_close_manage_device);
        mBtnAdđeviceAdmin = findViewById(R.id.lnl_add_device_admin);
    }
    private void initData(){
        mBtnClose.setOnClickListener(this);
        mBtnGetAllDevice.setOnClickListener(this);
        mBtnAdđeviceAdmin.setOnClickListener(this);
//        mLocationList = new ArrayList<>();
//        mLocationList.add(new Location(1,"Khu A","18-11-2019",true));
//        mLocationList.add(new Location(2,"Khu B","18-11-2019",true));
//        mLocationList.add(new Location(3,"Khu C","18-11-2019",true));
//        mLocationList.add(new Location(4,"Khu D","18-11-2019",true));
//        getCategoryData(mLocationList);
        token = SharePreferenceUtils.getStringSharedPreference(ManageDeviceActivity.this,BundleString.TOKEN);
        mGetAllLocationPresenter  = new GetAllLocationPresenter(ManageDeviceActivity.this,this);
        mGetAllLocationPresenter.getAllLocation(token);
    }
    private void getCategoryData(List<Location> locationList) {
        FragmentPagerItems.Creator creator = FragmentPagerItems.with(getApplicationContext());
        for (int i = 0; i <locationList.size(); i++) {
            Bundle bundle = new Bundle();
            bundle.putInt(BundleString.LOCATIONID,locationList.get(i).getLocationId());
            creator.add(locationList.get(i).getNameLocation(), PageFragment.class, bundle);
        }
        mAdapter = new FragmentPagerItemAdapter(getSupportFragmentManager(),
                creator.create());
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(locationList.size());
        mViewPager.setAdapter(mAdapter);
        //set viewPager for SmartTabLayout
        mViewPagerTab = (SmartTabLayout) findViewById(R.id.view_pager_tab);
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
    DialogNotifyError.showErrorLoginDialog(ManageDeviceActivity.this,"Data is error");
    }
    private void reloadDataFragment() {
        for (int i = 0; i < mAdapter.getCount(); i++) {
            ((PageFragment) mAdapter.getPage(i)).reloadPage();
        }
        mAdapter.notifyDataSetChanged();
    }
}
