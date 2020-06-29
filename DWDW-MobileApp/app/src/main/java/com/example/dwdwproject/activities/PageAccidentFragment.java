package com.example.dwdwproject.activities;

import android.content.Context;
import android.content.Intent;
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
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.adapters.AccidentAdapter;
import com.example.dwdwproject.models.Accident;

import java.util.ArrayList;
import java.util.List;

public class PageAccidentFragment extends Fragment {
    private View mView;
    private List<Accident> mAccidentList;
    private RecyclerView mRecyclerView;
    private AccidentAdapter mAccidentAdapter;
    public PageAccidentFragment() {
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
        mView = inflater.inflate(R.layout.fragment_page_accident, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }
    // TODO: Rename method, update argument and hook method into UI event

    private void initView(){
        mRecyclerView = mView.findViewById(R.id.rcv_accident_admin);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mAccidentList = new ArrayList<>();
        mAccidentList.add(new Accident(1,"Accident1","18-11-2020","Khu A","100",true));
        mAccidentList.add(new Accident(2,"Accident2","18-11-2020","Khu B","200",false));
        mAccidentList.add(new Accident(3,"Accident3","18-11-2020","Khu C","300",true));
        mAccidentList.add(new Accident(4,"Accident4","18-11-2020","Khu D","400",false));
        mAccidentList.add(new Accident(5,"Accident5","18-11-2020","Khu B","200",true));
        mAccidentList.add(new Accident(6,"Accident6","18-11-2020","Khu A","100",false));
        mAccidentList.add(new Accident(7,"Accident7","18-11-2020","Khu C","300",true));
        updateUI();
    }
    private void updateUI(){
        if(mAccidentAdapter == null){
            mAccidentAdapter = new AccidentAdapter(getContext(),mAccidentList);
            mRecyclerView.setAdapter(mAccidentAdapter);
            mAccidentAdapter.onItemClick(new AccidentAdapter.OnItemCkickListerner() {
                @Override
                public void onItemClick(int pos) {
                    Intent intent = new Intent(getContext(),AccidentReportDetailActivity.class);
                    startActivity(intent);
                }
            });
        }else {
            mAccidentAdapter.notifyDataSetChanged();
        }
    }

}
