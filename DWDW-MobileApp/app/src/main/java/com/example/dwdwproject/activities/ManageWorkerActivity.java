package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.adapters.LocationAdapter;
import com.example.dwdwproject.adapters.ManageAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Manager;

import java.util.ArrayList;
import java.util.List;

public class ManageWorkerActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Manager> mManagerList;
    private ManageAdapter manageAdapter;
    private RecyclerView mRecyclerView;
    private LinearLayout mBtnLose,mBtnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_worker2);
        initView();
        initData();
    }
    private void initView(){
        mBtnAdd = findViewById(R.id.lnl_add_worker_admin);
        mBtnLose = findViewById(R.id.lnl_close_manage_worker);
        mRecyclerView = findViewById(R.id.rcv_worker);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ManageWorkerActivity.this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mBtnAdd.setOnClickListener(this);
        mBtnLose.setOnClickListener(this);
        mManagerList = new ArrayList<>();
        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","A","01224959623","tultse62734@fpt.edu.vn"));
        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","B","01224959623","tultse62734@fpt.edu.vn"));
        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","C","01224959623","tultse62734@fpt.edu.vn"));
        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","D","01224959623","tultse62734@fpt.edu.vn"));
        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","E","01224959623","tultse62734@fpt.edu.vn"));
        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","M","01224959623","tultse62734@fpt.edu.vn"));
        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","N","01224959623","tultse62734@fpt.edu.vn"));
        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","T","01224959623","tultse62734@fpt.edu.vn"));
        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","U","01224959623","tultse62734@fpt.edu.vn"));
        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","V","01224959623","tultse62734@fpt.edu.vn"));
        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","Y","01224959623","tultse62734@fpt.edu.vn"));
        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","Z","01224959623","tultse62734@fpt.edu.vn"));
        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","K","01224959623","tultse62734@fpt.edu.vn"));
        updateUI();
    }
    private void updateUI(){
        if(manageAdapter ==null){
            manageAdapter = new ManageAdapter(ManageWorkerActivity.this,mManagerList);
            mRecyclerView.setAdapter(manageAdapter);
            manageAdapter.OnItemClickListener(new ManageAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int pos) {
                    intentToAdminWorkerDetailActivity();
                }
            });
        }
        else {
            manageAdapter.notifyDataSetChanged();
        }
    }
    private void intentToAdminWorkerDetailActivity(){
        Intent intent = new Intent(ManageWorkerActivity.this,AdminWorkerDetailActivity.class);
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_manage_worker:
                finish();
                break;
            case R.id.lnl_add_worker_admin:
                intentToAddManagerActivty();
                break;
        }
    }
    private void intentToAddManagerActivty(){
        Intent intent = new Intent(ManageWorkerActivity.this,AdminCreateManagerActivity.class);
        startActivity(intent);
    }

}
