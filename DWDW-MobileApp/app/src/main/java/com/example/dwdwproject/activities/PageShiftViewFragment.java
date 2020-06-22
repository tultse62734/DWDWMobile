package com.example.dwdwproject.activities;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dwdwproject.R;
import com.example.dwdwproject.adapters.RoomAdapter;
import com.example.dwdwproject.adapters.ShiftAdapter;
import com.example.dwdwproject.models.Shift;

import java.util.ArrayList;
import java.util.List;

public class PageShiftViewFragment extends Fragment {
    private View mView;
    private List<Shift> mShiftList;
    private RecyclerView mRecyclerView;
    private ShiftAdapter mShiftAdapter;
    public PageShiftViewFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_page_shift_view, container, false);
        return mView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initVieẉ();initData();
    }

    private void initVieẉ(){
        mRecyclerView = mView.findViewById(R.id.rcv_shift);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mShiftList = new ArrayList<>();
        mShiftList.add(new Shift(1,"Nhân Viên A","Khu A","Room 101"));
        mShiftList.add(new Shift(2,"Nhân Viên B","Khu B","Room 102"));
        mShiftList.add(new Shift(3,"Nhân Viên C","Khu C","Room 103"));
        mShiftList.add(new Shift(4,"Nhân Viên D","Khu D","Room 104"));
        mShiftList.add(new Shift(5,"Nhân Viên E","Khu E","Room 105"));
        mShiftList.add(new Shift(6,"Nhân Viên F","Khu F","Room 106"));
        mShiftList.add(new Shift(7,"Nhân Viên H","Khu H","Room 107"));
        updateUI();
    }
    private void updateUI(){
        if(mShiftAdapter == null){
            mShiftAdapter = new ShiftAdapter(getContext(),mShiftList);
            mRecyclerView.setAdapter(mShiftAdapter);
        }
        else {
            mShiftAdapter.notifyDataSetChanged();
        }

    }
}
