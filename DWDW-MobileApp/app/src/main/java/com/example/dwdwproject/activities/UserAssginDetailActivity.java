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

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.AssignUserDTO;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.adapters.LocationAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.presenters.userPresenters.DeAssignUsrToLocationPresenter;
import com.example.dwdwproject.presenters.userPresenters.SearchUserByIdPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.userViews.AssignUserView;
import com.example.dwdwproject.views.userViews.GetUserView;

import java.util.ArrayList;
import java.util.List;

public class UserAssginDetailActivity extends AppCompatActivity implements View.OnClickListener, GetUserView, AssignUserView {
    private UserDTO mUserDTO;
    private LinearLayout mBtnClose,mBtnAdd;
    private TextView mTxtUsername;
    private List<Location> mLocationList;
    private List<LocationDTO> mLocationDTOS;
    private RecyclerView mRecyclerView;
    private LocationAdapter mLocationAdapter;
    private SearchUserByIdPresenter mSearchUserByIdPresenter;
    private String token;
    private DeAssignUsrToLocationPresenter mAssignUsrToLocationPresenter;
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
        mSearchUserByIdPresenter = new SearchUserByIdPresenter(UserAssginDetailActivity.this,this);
        mAssignUsrToLocationPresenter = new DeAssignUsrToLocationPresenter(UserAssginDetailActivity.this,this);
        mBtnAdd.setOnClickListener(this);
        mBtnClose.setOnClickListener(this);
       Bundle bundle = getIntent().getExtras();
       mUserDTO = (UserDTO) bundle.getSerializable(BundleString.USERASSIGN);
       token = SharePreferenceUtils.getStringSharedPreference(UserAssginDetailActivity.this,BundleString.TOKEN);
       mSearchUserByIdPresenter.searchUserById(token,mUserDTO.getUserId());
    }
    private void updateUI(){
        if(mLocationAdapter ==null){
            mLocationAdapter = new LocationAdapter(UserAssginDetailActivity.this,mLocationList);
            mRecyclerView.setAdapter(mLocationAdapter);
            mLocationAdapter.OnClickDeleteItemListener(new LocationAdapter.OnClickDeleteItem() {
                @Override
                public void OnClickDeleteItem(int position) {
                    showDeactivetDialog("Do you want to deassgin location ",mLocationList.get(position).getLocationId());
                }
            });
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

    @Override
    public void getUserSuccess(UserDTO userDTO) {
        mTxtUsername.setText(mUserDTO.getUserName());
        mUserDTO = userDTO;
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

    @Override
    protected void onResume() {
        super.onResume();
        mSearchUserByIdPresenter.searchUserById(token,mUserDTO.getUserId());
    }

    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(UserAssginDetailActivity.this,message);
    }

    @Override
    public void getAssignUserSuccess(AssignUserDTO mAssignUserDTO) {
        mSearchUserByIdPresenter.searchUserById(token,mUserDTO.getUserId());
    }
    private void showDeactivetDialog(String message, final int locationId) {
        final Dialog dialog = new Dialog(UserAssginDetailActivity.this);
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
                mAssignUsrToLocationPresenter.deassginUser(token,mUserDTO.getUserId(),locationId);
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
