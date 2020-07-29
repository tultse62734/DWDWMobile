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
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.adapters.ChooseLocationAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.presenters.locationsPresenters.GetAllLocationPresenter;
import com.example.dwdwproject.presenters.locationsPresenters.GetManagerLocationPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;
import com.example.dwdwproject.views.locationsViews.GetManagerLocatonView;

import java.util.ArrayList;
import java.util.List;
public class ManagerChooseLocationActivity extends AppCompatActivity  implements GetManagerLocatonView {
    private RecyclerView mRecyclerView;
    private List<Location> mLocationList;
    private GetManagerLocationPresenter getManagerLocationPresenter;
    private String token;
    private int intentHome;
    private ChooseLocationAdapter mChooseLocationAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_choose_location);
        initVieẉ̣();
        initData();
    }
    private void initVieẉ̣(){
        intentHome = SharePreferenceUtils.getIntSharedPreference(ManagerChooseLocationActivity.this,BundleString.INTENTHOME);
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
//        mLocationList = new ArrayList<>();
//        mLocationList.add(new Location(1, "Khu A", "20-11-2020", true));
//        mLocationList.add(new Location(2, "Khu B", "12-10-2019", false));
//        mLocationList.add(new Location(3, "Khu C", "1-10-2019", true));
//        mLocationList.add(new Location(4, "Khu D", "1-10-2019", true));
//        updateUI();
        token =  SharePreferenceUtils.getStringSharedPreference(ManagerChooseLocationActivity.this,BundleString.TOKEN);
        getManagerLocationPresenter = new GetManagerLocationPresenter(ManagerChooseLocationActivity.this,this);
        getManagerLocationPresenter.getManagerLocation(token);
    }
    private void updateUI(){
        if(mChooseLocationAdapter == null){
            mChooseLocationAdapter = new ChooseLocationAdapter(ManagerChooseLocationActivity.this,mLocationList);
            mRecyclerView.setAdapter(mChooseLocationAdapter);
            mChooseLocationAdapter.OnClickItemListener(new ChooseLocationAdapter.OnClickItem() {
                @Override
                public void OnClickItem(int position) {
                    if(intentHome ==1){
                        Intent intent = new Intent(ManagerChooseLocationActivity.this,HomeManagerActivity.class);
                        SharePreferenceUtils.saveIntSharedPreference(ManagerChooseLocationActivity.this, BundleString.LOCATIONID,mLocationList.get(position).getLocationId());
                        SharePreferenceUtils.saveStringSharedPreference(ManagerChooseLocationActivity.this, BundleString.LOCATIONNAME,mLocationList.get(position).getNameLocation());

                        startActivity(intent);
                    }else if(intentHome ==2) {
                        Intent intent = new Intent(ManagerChooseLocationActivity.this,HomeWorkerActivity.class);
                        SharePreferenceUtils.saveIntSharedPreference(ManagerChooseLocationActivity.this, BundleString.LOCATIONID,mLocationList.get(position).getLocationId());
                        SharePreferenceUtils.saveStringSharedPreference(ManagerChooseLocationActivity.this, BundleString.LOCATIONNAME,mLocationList.get(position).getNameLocation());
                        startActivity(intent);
                    }
                }
            });
        }
        else {
            mChooseLocationAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getManagerLocationSuccess(List<LocationDTO> mLocationDTOList) {
        if(mLocationDTOList!=null){
            this.mLocationList = new ArrayList<>();
            for (int i = 0; i <mLocationDTOList.size() ; i++) {
                int locationId  = mLocationDTOList.get(i).getLocationId();
                String locationName = mLocationDTOList.get(i).getLocationCode();
                boolean isactive = mLocationDTOList.get(i).isActive();
                this.mLocationList.add(new Location(locationId,locationName,isactive));
            }
            updateUI();
        }
    }

    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(ManagerChooseLocationActivity.this,message);
    }
}
