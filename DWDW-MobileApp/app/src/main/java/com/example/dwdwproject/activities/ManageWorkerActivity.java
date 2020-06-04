package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.dwdwproject.R;
import com.example.dwdwproject.adapters.WorkerAdapter;
import com.example.dwdwproject.models.Worker;

import java.util.ArrayList;
import java.util.List;

import in.myinnos.alphabetsindexfastscrollrecycler.IndexFastScrollRecyclerView;

public class ManageWorkerActivity extends AppCompatActivity {
    private List<Worker> mWorkerList;
    private IndexFastScrollRecyclerView recyclerView;
    private WorkerAdapter mWorkerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_worker);
        initView();
        initData();
    }
    private void initView(){
        recyclerView = (IndexFastScrollRecyclerView) findViewById(R.id.fast_scroller_recycler);
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
        mWorkerList = new ArrayList<>();
        mWorkerList.add(new Worker("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","A","01224959623","tultse62734@fpt.edu.vn"));
        mWorkerList.add(new Worker("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","B","01224959623","tultse62734@fpt.edu.vn"));
        mWorkerList.add(new Worker("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","C","01224959623","tultse62734@fpt.edu.vn"));
        mWorkerList.add(new Worker("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","D","01224959623","tultse62734@fpt.edu.vn"));
        mWorkerList.add(new Worker("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","E","01224959623","tultse62734@fpt.edu.vn"));
        mWorkerList.add(new Worker("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","M","01224959623","tultse62734@fpt.edu.vn"));
        mWorkerList.add(new Worker("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","N","01224959623","tultse62734@fpt.edu.vn"));
        mWorkerList.add(new Worker("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","T","01224959623","tultse62734@fpt.edu.vn"));
        mWorkerList.add(new Worker("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","U","01224959623","tultse62734@fpt.edu.vn"));
        mWorkerList.add(new Worker("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","V","01224959623","tultse62734@fpt.edu.vn"));
        mWorkerList.add(new Worker("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","Y","01224959623","tultse62734@fpt.edu.vn"));
        mWorkerList.add(new Worker("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","Z","01224959623","tultse62734@fpt.edu.vn"));
        mWorkerList.add(new Worker("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","K","01224959623","tultse62734@fpt.edu.vn"));
        updateUI();
    }
    private void updateUI(){
        if(mWorkerAdapter ==null){
            mWorkerAdapter = new WorkerAdapter(ManageWorkerActivity.this,mWorkerList);
            recyclerView.setAdapter(mWorkerAdapter);

        }else {
            mWorkerAdapter.notifyDataSetChanged();
        }
    }
}
