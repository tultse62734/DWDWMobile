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
import com.example.dwdwproject.models.Room;

import java.util.ArrayList;
import java.util.List;

public class PageRoomFragment extends Fragment {
    private View mView;
    private List<Room> mRoomList;
    private RecyclerView mRecyclerView;
    private RoomAdapter mRoomAdapter;
    public PageRoomFragment() {
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
        mView =  inflater.inflate(R.layout.fragment_page_room, container, false);
        return  mView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    private void initView(){
        mRecyclerView = mView.findViewById(R.id.rcv_room);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mRoomList = new ArrayList<>();
        mRoomList.add(new Room(1,"100","12-12-2020",true));
        mRoomList.add(new Room(2,"200","12-12-2020",false));
        mRoomList.add(new Room(3,"300","12-12-2020",false));
        mRoomList.add(new Room(4,"400","12-12-2020",true));
        mRoomList.add(new Room(5,"500","12-12-2020",true));
        mRoomList.add(new Room(6,"600","12-12-2020",true));
        updateUI();
    }
    private void updateUI(){
        if(mRoomAdapter == null){
            mRoomAdapter = new RoomAdapter(getContext(),mRoomList);
            mRecyclerView.setAdapter(mRoomAdapter);
        }
        else {
            mRoomAdapter.notifyDataSetChanged();
        }

    }
}
