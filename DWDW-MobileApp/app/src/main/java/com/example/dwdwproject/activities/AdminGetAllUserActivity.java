package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.adapters.DeviceAdapter;
import com.example.dwdwproject.adapters.ManageAdapter;
import com.example.dwdwproject.models.Device;
import com.example.dwdwproject.models.Manager;
import com.example.dwdwproject.models.User;
import com.example.dwdwproject.presenters.devicesPresenters.GetAllDevicePresenter;
import com.example.dwdwproject.presenters.userPresenters.AdminGetAllUserPresenter;
import com.example.dwdwproject.presenters.userPresenters.UpdateUserPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.userViews.GetAllListUserView;
import com.example.dwdwproject.views.userViews.GetUserView;

import java.util.ArrayList;
import java.util.List;

public class AdminGetAllUserActivity extends AppCompatActivity implements View.OnClickListener, GetAllListUserView, GetUserView {
    private RecyclerView mRecyclerView;
    private ManageAdapter manageAdapter;
    private List<Manager> mUserList;
    private List<UserDTO> mDtoList;
    private LinearLayout mBtnClose;
    private String token ;
    private SearchView mSearchView;
    private UpdateUserPresenter mUpdateUserPresenter;
    private AdminGetAllUserPresenter mAdminGetAllUserPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_get_all_user);
        initView();
        initData();
    }
    private void initView(){
        mRecyclerView = findViewById(R.id.rcv_admin_manage_all_user);
        mBtnClose = findViewById(R.id.lnl_close_admin_manage_getall_user);
        mSearchView = findViewById(R.id.search_view_admin_get_all_user);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AdminGetAllUserActivity.this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mBtnClose.setOnClickListener(this);
        mSearchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if(manageAdapter!=null){
                    manageAdapter.getFilter().filter(newText.toString());
                }
                return false;
            }
        });
        mUpdateUserPresenter = new UpdateUserPresenter(AdminGetAllUserActivity.this,this);
        mAdminGetAllUserPresenter = new AdminGetAllUserPresenter(AdminGetAllUserActivity.this,this);
        token = SharePreferenceUtils.getStringSharedPreference(AdminGetAllUserActivity.this, BundleString.TOKEN);
        mAdminGetAllUserPresenter.getAllUser(token);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mAdminGetAllUserPresenter.getAllUser(token);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_admin_manage_getall_user:
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
                break;
        }
    }
    private void updateUI(){
        if(manageAdapter ==null){
            manageAdapter = new ManageAdapter(AdminGetAllUserActivity.this, mUserList);
            mRecyclerView.setAdapter(manageAdapter);
            manageAdapter.OnItemClickListener(new ManageAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int pos) {
                    Intent intent = new Intent(AdminGetAllUserActivity.this,AdminAssignUserActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleString.MANAGERDETAIL,mDtoList.get(pos));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
            manageAdapter.OnItemActiveClickListener(new ManageAdapter.OnItemActiveClickListener() {
                @Override
                public void onItemActiveClick(int pos) {
                    if(mDtoList.get(pos).isActive()){
                        mUpdateUserPresenter.updateUseStatus(token,mDtoList.get(pos).getUserId(),false);
                    }else{
                        mUpdateUserPresenter.updateUseStatus(token,mDtoList.get(pos).getUserId(),true);
                    }
                }
            });

        }else {
            manageAdapter.notify(mUserList);
        }
    }
    @Override
    public void getAllUserSuccess(List<UserDTO> userDTOList) {
        if(userDTOList!=null){
            this.mUserList = new ArrayList<>();
            mDtoList = new ArrayList<>();
            mDtoList = userDTOList;
            for (int i = 0; i <userDTOList.size() ; i++) {
                int userId = userDTOList.get(i).getUserId();
                String name  = userDTOList.get(i).getUserName();
                String phone = userDTOList.get(i).getPhone();
                String creatDate = DateManagement.changeFormatDate1(userDTOList.get(i).getStartDate()) +" - " + DateManagement.changeFormatDate1(userDTOList.get(i).getEndDate());
                String location = userDTOList.get(i).getmLocationDTO().toString();
                String roleName = userDTOList.get(i).getmRole().getRoleName();
                boolean isActive = userDTOList.get(i).isActive();
                mUserList.add(new Manager(userId,name,phone,roleName,location,creatDate,isActive));
            }
            updateUI();
        }
    }

    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(AdminGetAllUserActivity.this,message);
    }

    @Override
    public void getUserSuccess(UserDTO userDTO) {
        mAdminGetAllUserPresenter.getAllUser(token);
    }
}