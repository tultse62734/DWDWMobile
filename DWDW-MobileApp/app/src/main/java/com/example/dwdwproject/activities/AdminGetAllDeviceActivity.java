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
import com.example.dwdwproject.adapters.DeviceAdapter;
import com.example.dwdwproject.models.Device;
import com.example.dwdwproject.presenters.devicesPresenters.GetAllDevicePresenter;
import com.example.dwdwproject.presenters.devicesPresenters.GetDeviceForManagerPresenter;
import com.example.dwdwproject.presenters.devicesPresenters.UpdateDevicePresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.devicesViews.GetAllDeviceView;
import com.example.dwdwproject.views.devicesViews.GetDeviceIDView;

import java.util.ArrayList;
import java.util.List;
public class AdminGetAllDeviceActivity extends AppCompatActivity implements View.OnClickListener, GetAllDeviceView, GetDeviceIDView {
    private RecyclerView mRecyclerView;
    private DeviceAdapter mDeviceAdapter;
    private List<Device> mDeviceList;
    private List<DeviceDTO> mDtoList;
    private LinearLayout mBtnClose;
    private String token ;
    private SearchView mSearchView;
    private GetAllDevicePresenter mGetAllDevicePresenter;
    private UpdateDevicePresenter mUpdateDevicePresenter;
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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AdminGetAllDeviceActivity.this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mUpdateDevicePresenter = new UpdateDevicePresenter(AdminGetAllDeviceActivity.this,this);
        mGetAllDevicePresenter = new GetAllDevicePresenter(AdminGetAllDeviceActivity.this,this);
        token = SharePreferenceUtils.getStringSharedPreference(AdminGetAllDeviceActivity.this, BundleString.TOKEN);
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

        mGetAllDevicePresenter.getAllDevice(token);
    }
    @Override
    protected void onResume() {
        super.onResume();
        mGetAllDevicePresenter.getAllDevice(token);
    }

    @Override
    public void onClick(View v) {
            int id = v.getId();
            switch (id){
                case R.id.lnl_close_admin_manage_getall_device:
                    Intent returnIntent = new Intent();
                    setResult(Activity.RESULT_CANCELED, returnIntent);
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
            mDeviceAdapter.onItemActiveClickListerner(new DeviceAdapter.OnItemActiveClickListerner() {
                @Override
                public void onItemActiveClick(int pos) {
                    if(mDtoList.get(pos).isActive()){
                        mDtoList.get(pos).setActive(false);
                        mUpdateDevicePresenter.updateDeviceStatus(token,mDtoList.get(pos));
                    }else{
                        mDtoList.get(pos).setActive(true);
                        mUpdateDevicePresenter.updateDeviceStatus(token,mDtoList.get(pos));
                    }
                }
            });
        }
        else {
            mDeviceAdapter.notifyChange(mDeviceList);
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
                String locationName = mDeviceDTOList.get(i).getLocationCode();
                boolean isActive = mDeviceDTOList.get(i).isActive();
                String roomName = mDeviceDTOList.get(i).getRoomCode();
                String creatDate = DateManagement.changeFormatDate1(mDeviceDTOList.get(i).getStartDate()) +" - " + DateManagement.changeFormatDate1(mDeviceDTOList.get(i).getEndDate());
                mDeviceList.add(new Device(deviceId,deviceName,creatDate,locationName,roomName,isActive));
            }
            updateUI();
        }
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(AdminGetAllDeviceActivity.this,message);

    }
    @Override
    public void getDeviceView(DeviceDTO mDeviceDTO) {
        mGetAllDevicePresenter.getAllDevice(token);
    }
}