package com.example.dwdwproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.activities.AdminDeviceDetailActivity;
import com.example.dwdwproject.activities.ManageDeviceActivity;
import com.example.dwdwproject.adapters.DeviceAdapter;
import com.example.dwdwproject.models.Device;
import com.example.dwdwproject.presenters.devicesPresenters.GetDeviceForAdminPresenter;
import com.example.dwdwproject.presenters.devicesPresenters.UpdateDevicePresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.devicesViews.GetAllDeviceView;
import com.example.dwdwproject.views.devicesViews.GetDeviceIDView;

import java.util.ArrayList;
import java.util.List;

public class PageFragment extends Fragment implements GetAllDeviceView , GetDeviceIDView {
    private View mView;
    private RecyclerView mRecyclerView;
    private List<Device> mDeviceList;
    private List<DeviceDTO> mDeviceDTOS;
    private SearchView mSearchView;
    private DeviceAdapter mDeviceAdapter;
    private String token;
    private int locationId;
    private GetDeviceForAdminPresenter mDeviceForAdminPresenter;
    private UpdateDevicePresenter mUpdateDevicePresenter;
    public PageFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationId = getArguments().getInt(BundleString.LOCATIONID);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_page, container, false);
        return mView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }
    // TODO: Rename method, update argument and hook method into UI event
    private void initView() {
        mSearchView = mView.findViewById(R.id.searchView_page_device);
        mRecyclerView = mView.findViewById(R.id.rcv_device);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mUpdateDevicePresenter = new UpdateDevicePresenter(getContext(),this);
//        mDeviceList = new ArrayList<>();
//        mDeviceList.add(new Device(1,"Camera 2MP","2020-11-20","Khu A"));
//        mDeviceList.add(new Device(2,"Camera 4MP","2020-11-20","Khu B"));
//        mDeviceList.add(new Device(3,"Camera 6MP","2020-11-20","Khu C"));
//        mDeviceList.add(new Device(4,"Camera 8MP","2020-11-20","Khu D"));
//        mDeviceList.add(new Device(5,"Camera 10MP","2020-11-20","Khu E"));
//        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
//        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
//        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
//        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
//        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
//        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
//        updateUI();
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
                return false;            }
        });
        token = SharePreferenceUtils.getStringSharedPreference(getContext(),BundleString.TOKEN);
        mDeviceForAdminPresenter = new GetDeviceForAdminPresenter(getContext(),this);
        mDeviceForAdminPresenter.getDeviceFromLocationForAd(token,locationId);
    }
    private void updateUI(){
        if(mDeviceAdapter == null){
            mDeviceAdapter = new DeviceAdapter(getContext(),mDeviceList);
            mRecyclerView.setAdapter(mDeviceAdapter);
            mDeviceAdapter.onItemClickListerner(new DeviceAdapter.OnItemClickListenner() {
                @Override
                public void onItemCLick(int pos) {
                    Intent intent = new Intent(getContext(), AdminDeviceDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleString.DEVICEDETAIL,mDeviceDTOS.get(pos));
                    intent.putExtras(bundle);
                    getActivity().startActivityForResult(intent,ManageDeviceActivity.UPDATE_DEVICE_CODE);
                }
            });
            mDeviceAdapter.onItemActiveClickListerner(new DeviceAdapter.OnItemActiveClickListerner() {
                @Override
                public void onItemActiveClick(int pos) {
                    if(mDeviceDTOS.get(pos).isActive()){
                        mDeviceDTOS.get(pos).setActive(false);
                        mUpdateDevicePresenter.updateDeviceStatus(token,mDeviceDTOS.get(pos));
                    }else{
                        mDeviceDTOS.get(pos).setActive(true);
                        mUpdateDevicePresenter.updateDeviceStatus(token,mDeviceDTOS.get(pos));
                    }
                }
            });
        }
        else {
            mDeviceAdapter.notifyChange(mDeviceList);
        }
    }
    public void reloadPage(){
        token = SharePreferenceUtils.getStringSharedPreference(getContext(),BundleString.TOKEN);
        mDeviceForAdminPresenter = new GetDeviceForAdminPresenter(getContext(),this);
        mDeviceForAdminPresenter.getDeviceFromLocationForAd(token,locationId);
    }
    @Override
    public void getAllDeviceSuccess(List<DeviceDTO> mDeviceDTOList) {
        if(mDeviceDTOList!=null){
            mDeviceList = new ArrayList<>();
            mDeviceDTOS = new ArrayList<>();
            mDeviceDTOS = mDeviceDTOList;
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
        DialogNotifyError.showErrorLoginDialog(getContext(),"Data Fail");
    }
    @Override
    public void getDeviceView(DeviceDTO mDeviceDTO) {
        mDeviceForAdminPresenter.getDeviceFromLocationForAd(token,locationId);
    }
}

