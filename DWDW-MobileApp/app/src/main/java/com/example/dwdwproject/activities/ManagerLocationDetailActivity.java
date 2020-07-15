package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.adapters.ChooseStatusAdapter;
import com.example.dwdwproject.models.Status;
import com.example.dwdwproject.presenters.locationsPresenters.UpdateLocationPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.SharePreferenceUtils;

import java.util.ArrayList;
import java.util.List;

public class ManagerLocationDetailActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout mBtnClose;
    TextView mEdtStatusLocation;
    private List<Status> mStatusList;
    private RecyclerView mRecyclerView1;
    private int posStatus;
    private EditText mTxtLocationCode;
    private LocationDTO mLocationDTO;
    private ChooseStatusAdapter mChooseStatusAdapter;
    private String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_location_detail);
        initView();
        initData();
    }
    private void initView(){
        Bundle bundle = getIntent().getExtras();
        mLocationDTO  =(LocationDTO) bundle.getSerializable(BundleString.LOCATIONDETAIL);
        mBtnClose = findViewById(R.id.lnl_close_manager_location_detail);
        mEdtStatusLocation = findViewById(R.id.edt_choose_status_update_location_manager_deta);
        mTxtLocationCode = findViewById(R.id.edt_name_location_update_location_manager);

        mTxtLocationCode.setText(mLocationDTO.getLocationCode());
        if(mLocationDTO.isActive()){
            mEdtStatusLocation.setText("Đang hoạt động");
        }
        else {
            mEdtStatusLocation.setText("Không hoạt động");
        }
    }
    private void initData(){

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.lnl_close_manager_location_detail:
                finish();
                break;
        }
    }

}