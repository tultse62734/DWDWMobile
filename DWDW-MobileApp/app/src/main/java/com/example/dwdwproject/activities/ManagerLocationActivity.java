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
import android.widget.Toast;

import com.example.dwdwproject.MainActivity;
import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.adapters.LocationAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.presenters.locationsPresenters.CreateLocationPresenter;
import com.example.dwdwproject.presenters.locationsPresenters.GetManagerLocationPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.presenters.locationsPresenters.UpdateLocationPresenter;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.locationsViews.GetManagerLocatonView;
import com.example.dwdwproject.views.locationsViews.GetLocationView;
import com.example.dwdwproject.views.locationsViews.CreateLocatonView;
import com.example.dwdwproject.views.locationsViews.UpdateLocatonView;

import java.util.ArrayList;
import java.util.List;
public class ManagerLocationActivity extends AppCompatActivity implements GetManagerLocatonView {
    private RecyclerView mRecyclerView;
    private List<Location> mLocationList;
    private LocationAdapter mLocationAdapter;
    private GetManagerLocationPresenter getManagerLocationPresenter;
    private String token;
    private List<LocationDTO> mLocationDTOS;
    private LinearLayout mBtnClose,mBtnAddLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_admin);
        initView();
        initData();
    }
    private void initView(){
        mRecyclerView = findViewById(R.id.rcv_location);
        mBtnClose = findViewById(R.id.lnl_close_manage_location);
        mBtnAddLocation = findViewById(R.id.lnl_add_location_admin);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        token =  SharePreferenceUtils.getStringSharedPreference(ManagerLocationActivity.this,BundleString.TOKEN);
        getManagerLocationPresenter = new GetManagerLocationPresenter(ManagerLocationActivity.this,this);
        getManagerLocationPresenter.getManagerLocation(token);
    }
    private void updateUI(){
        mLocationAdapter.notifyDataSetChanged();
    }

    private void showLogoutDialog(String message) {
        final Dialog dialog = new Dialog(ManagerLocationActivity.this);
        dialog.setContentView(R.layout.alert_dialog_notify_sign_out);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk = dialog.findViewById(R.id.btn_yes);
        Button buttonNo = dialog.findViewById(R.id.btn_no);
        TextView  textView = dialog.findViewById(R.id.txt_dia);
        textView.setText(message);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(ManagerLocationActivity.this,"Can't show data");
    }
    @Override
    public void getManagerLocationSuccess(List<LocationDTO> mLocationDTOList) {
        if(mLocationDTOList!=null){
            this.mLocationDTOS = new ArrayList<>();
            this.mLocationDTOS = mLocationDTOList;
            this.mLocationList = new ArrayList<>();
            for (int i = 0; i <mLocationDTOList.size() ; i++) {
                int locationId  = mLocationDTOList.get(i).getLocationId();
                String locationName = mLocationDTOList.get(i).getLocationCode();
                boolean isactive = mLocationDTOList.get(i).isActive();
                this.mLocationList.add(new Location(locationId,locationName,isactive));
            }
            updateUI();
        }
    }
}
