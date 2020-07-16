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
public class ManagerLocationActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Location> mLocationList;
    private LocationAdapter mLocationAdapter;

    private List<LocationDTO> mLocationDTOS;
    private LinearLayout mBtnClose,mBtnAddLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_view_location);
        initView();
        initData();
    }
    private void initView(){
        mRecyclerView = findViewById(R.id.rcv_manager_location);
        mBtnClose = findViewById(R.id.lnl_close_manager_location);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){

    }
    private void updateUI(){
        if(mLocationAdapter == null){
            mLocationAdapter = new LocationAdapter(ManagerLocationActivity.this,mLocationList);
            mRecyclerView.setAdapter(mLocationAdapter);
            mLocationAdapter.OnClickItemListener(new LocationAdapter.OnClickItem() {
                @Override
                public void OnClickItem(int position) {
                    Intent intent  = new Intent(ManagerLocationActivity.this,ManagerLocationDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleString.LOCATIONDETAIL,mLocationDTOS.get(position));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        else {
            mLocationAdapter.notifyDataSetChanged();
        }
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

}
