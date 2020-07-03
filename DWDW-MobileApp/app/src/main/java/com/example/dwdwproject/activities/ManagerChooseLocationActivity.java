package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.adapters.ChooseLocationAdapter;
import com.example.dwdwproject.models.Location;

import java.util.ArrayList;
import java.util.List;

public class ManagerChooseLocationActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<Location> mLocationList;
    private ChooseLocationAdapter mChooseLocationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_choose_location);
        initVieẉ̣();
        initData();
    }
    private void initVieẉ̣(){
        mRecyclerView = findViewById(R.id.rcv_mamaner_choose_location);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_manager_choose_location, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if(mChooseLocationAdapter!=null){
                    mChooseLocationAdapter.getFilter().filter(newText.toString());
                }

                return false;
            }
        });
        return true;
    }
    private void initData(){
        mLocationList = new ArrayList<>();
        mLocationList.add(new Location(1, "Khu A", "20-11-2020", true));
        mLocationList.add(new Location(2, "Khu B", "12-10-2019", false));
        mLocationList.add(new Location(3, "Khu C", "1-10-2019", true));
        mLocationList.add(new Location(4, "Khu D", "1-10-2019", true));
        updateUI();
    }
    private void updateUI(){
        if(mChooseLocationAdapter == null){
            mChooseLocationAdapter = new ChooseLocationAdapter(ManagerChooseLocationActivity.this,mLocationList);
            mRecyclerView.setAdapter(mChooseLocationAdapter);
            mChooseLocationAdapter.OnClickItemListener(new ChooseLocationAdapter.OnClickItem() {
                @Override
                public void OnClickItem(int position) {
                    Intent intent = new Intent(ManagerChooseLocationActivity.this,HomeManagerActivity.class);
                    startActivity(intent);
                }
            });
        }
        else {
            mChooseLocationAdapter.notifyDataSetChanged();
        }
    }
}
