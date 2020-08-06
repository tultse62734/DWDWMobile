package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.adapters.LocationAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.utils.BundleString;

import java.util.ArrayList;
import java.util.List;

public class UserAssginDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private UserDTO mUserDTO;
    private LinearLayout mBtnClose,mBtnAdd;
    private TextView mTxtUsername;
    private List<Location> mLocationList;
    private List<LocationDTO> mLocationDTOS;
    private RecyclerView mRecyclerView;
    private LocationAdapter mLocationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_assgin_detail);
        initView();
        initData();
    }
    private void initView(){
        mTxtUsername = findViewById(R.id.edit_user_assign_username);
        mRecyclerView = findViewById(R.id.rcv_user_assign);
        mBtnClose = findViewById(R.id.lnl_close_admin_user_assign);
        mBtnAdd = findViewById(R.id.lnl_add_user_assign);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(UserAssginDetailActivity.this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mBtnAdd.setOnClickListener(this);
        mBtnClose.setOnClickListener(this);
       Bundle bundle = getIntent().getExtras();
       mUserDTO = (UserDTO) bundle.getSerializable(BundleString.USERASSIGN);
       mTxtUsername.setText(mUserDTO.getUserName());
       mLocationDTOS = new ArrayList<>();
       mLocationList =new ArrayList<>();
       mLocationDTOS = mUserDTO.getmLocationDTO();
       if(mLocationDTOS!=null){
           for (int i = 0; i <mLocationDTOS.size() ; i++) {
               int locationId  = mLocationDTOS.get(i).getLocationId();
               String locationName = mLocationDTOS.get(i).getLocationCode();
               boolean isactive = mLocationDTOS.get(i).isActive();
               this.mLocationList.add(new Location(locationId,locationName,isactive));
           }
           updateUI();
       }
    }
    private void updateUI(){
        if(mLocationAdapter ==null){
            mLocationAdapter = new LocationAdapter(UserAssginDetailActivity.this,mLocationList);
            mRecyclerView.setAdapter(mLocationAdapter);
        }
        else {
            mLocationAdapter.notify(mLocationList);
        }
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_admin_user_assign:
                finish();
                break;
            case R.id.lnl_add_user_assign:
                Intent intent = new Intent(UserAssginDetailActivity.this,AdminAssignUserActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(BundleString.MANAGERDETAIL,mUserDTO);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
        }
    }
}
