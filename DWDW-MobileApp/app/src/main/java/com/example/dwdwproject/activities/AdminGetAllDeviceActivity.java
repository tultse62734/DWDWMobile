package com.example.dwdwproject.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.adapters.DeviceAdapter;
import com.example.dwdwproject.models.Device;
import com.example.dwdwproject.presenters.devicesPresenters.GetAllDevicePresenter;
import com.example.dwdwproject.presenters.devicesPresenters.GetDeviceForManagerPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.devicesViews.GetAllDeviceView;

import java.util.ArrayList;
import java.util.List;
public class AdminGetAllDeviceActivity extends AppCompatActivity implements View.OnClickListener, GetAllDeviceView {
    private RecyclerView mRecyclerView;
    private DeviceAdapter mDeviceAdapter;
    private List<Device> mDeviceList;
    private List<DeviceDTO> mDtoList;
    private LinearLayout mBtnClose;
    private String token ;
    private SearchView mSearchView;
    private GetAllDevicePresenter mGetAllDevicePresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_get_all_device);
        initView();
        initData();
    }
    private void initView(){
            mRecyclerView = findViewById(R.id.rcv_admin_manage_all_device);
            mBtnClose = findViewById(R.id.lnl_close_admin_manage_getall_device);
            mSearchView = findViewById(R.id.search_view_admin_get_all_device);
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
                if(mDeviceAdapter!=null){
                    mDeviceAdapter.getFilter().filter(newText.toString());
                }
                return false;
            }
        });
        mGetAllDevicePresenter = new GetAllDevicePresenter(AdminGetAllDeviceActivity.this,this);
        token = SharePreferenceUtils.getStringSharedPreference(AdminGetAllDeviceActivity.this, BundleString.TOKEN);
        mGetAllDevicePresenter.getAllDevice(token);
    }
    @Override
    public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.lnl_close_admin_manage_getall_device:
                    finish();
                    break;
            }
    }
    private void updateUI(){
        if(mDeviceAdapter == null){
            mDeviceAdapter = new DeviceAdapter(AdminGetAllDeviceActivity.this,mDeviceList);
            mRecyclerView.setAdapter(mDeviceAdapter);
            mDeviceAdapter.onItemClickListerner(new DeviceAdapter.OnItemClickListenner() {
                @Override
                public void onItemCLick(int pos) {
                    Intent intent = new Intent(AdminGetAllDeviceActivity.this, AdminAssignDeviceActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleString.DEVICEASSGINT,mDtoList.get(pos));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }
        else {
            mDeviceAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void getAllDeviceSuccess(List<DeviceDTO> mDeviceDTOList) {
        if(mDeviceDTOList!=null){
            mDeviceList = new ArrayList<>();
            mDtoList = new ArrayList<>();
            mDtoList = mDeviceDTOList;
            for (int i = 0; i < mDeviceDTOList.size(); i++) {
                int deviceId = mDeviceDTOList.get(i).getDeviceId();
                String deviceName = mDeviceDTOList.get(i).getDeviceCode();
                boolean isActive = mDeviceDTOList.get(i).isActive();
                mDeviceList.add(new Device(deviceId,deviceName,isActive));
            }
            updateUI();
        }

    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(AdminGetAllDeviceActivity.this,message);

    }
}