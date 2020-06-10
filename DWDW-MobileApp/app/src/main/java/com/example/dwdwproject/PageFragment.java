package com.example.dwdwproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwdwproject.activities.ManageDeviceActivity;
import com.example.dwdwproject.adapters.DeviceAdapter;
import com.example.dwdwproject.models.Device;

import java.util.ArrayList;
import java.util.List;

public class PageFragment extends Fragment{
    private View mView;
    private RecyclerView mRecyclerView;
    private List<Device> mDeviceList;
    private DeviceAdapter mDeviceAdapter;
    public PageFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
        mRecyclerView = mView.findViewById(R.id.rcv_device);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mDeviceList = new ArrayList<>();
        mDeviceList.add(new Device(1,"Camera 2MP","2020-11-20","Khu A"));
        mDeviceList.add(new Device(2,"Camera 4MP","2020-11-20","Khu B"));
        mDeviceList.add(new Device(3,"Camera 6MP","2020-11-20","Khu C"));
        mDeviceList.add(new Device(4,"Camera 8MP","2020-11-20","Khu D"));
        mDeviceList.add(new Device(5,"Camera 10MP","2020-11-20","Khu E"));
        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));
        mDeviceList.add(new Device(6,"Camera 12MP","2020-11-20","Khu F"));

        updateUI();
    }
    private void updateUI(){
        if(mDeviceAdapter == null){
            mDeviceAdapter = new DeviceAdapter(getContext(),mDeviceList);
            mRecyclerView.setAdapter(mDeviceAdapter);
        }
        else {
            mDeviceAdapter.notifyDataSetChanged();
        }

    }

}

