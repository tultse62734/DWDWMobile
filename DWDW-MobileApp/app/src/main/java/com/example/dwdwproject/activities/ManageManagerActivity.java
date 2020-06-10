package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.adapters.ManageAdapter;
import com.example.dwdwproject.models.Manager;

import java.util.ArrayList;
import java.util.List;

import in.myinnos.alphabetsindexfastscrollrecycler.IndexFastScrollRecyclerView;

public class ManageManagerActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Manager> mManagerList;
    private IndexFastScrollRecyclerView recyclerView;
    private ManageAdapter mManageAdapter;
    private LinearLayout mBtnClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_worker);
        initView();
        initData();
    }
    private void initView(){
        recyclerView = (IndexFastScrollRecyclerView) findViewById(R.id.fast_scroller_recycler);
        mBtnClose = findViewById(R.id.lnl_close_manage_manage);
        recyclerView.setIndexBarTextColor(R.color.colorWhite);
        recyclerView.setIndexBarCornerRadius(20);

        recyclerView.setIndexBarTransparentValue((float) 0.4);

        recyclerView.setIndexbarMargin(10);

        recyclerView.setIndexTextSize(15);
        recyclerView.setIndexBarTextColor(R.color.colorWhite);
        recyclerView.setIndexBarColor("#33334c");

        recyclerView.setIndexBarColor(R.color.colorYellowOrange);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    private void  initData(){
        mBtnClose.setOnClickListener(this);
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
        if(mManageAdapter ==null){
            mManageAdapter = new ManageAdapter(ManageManagerActivity.this, mManagerList);
            recyclerView.setAdapter(mManageAdapter);

        }else {
            mManageAdapter.notifyDataSetChanged();
        }
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_manage_manage:
                finish();
                break;
        }
    }
}
