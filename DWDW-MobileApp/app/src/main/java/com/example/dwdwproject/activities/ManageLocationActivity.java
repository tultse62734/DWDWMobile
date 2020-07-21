package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dwdwproject.MainActivity;
import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.adapters.LocationAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.presenters.locationsPresenters.CreateLocationPresenter;
import com.example.dwdwproject.presenters.locationsPresenters.DeactiveLocationPresenter;
import com.example.dwdwproject.presenters.locationsPresenters.GetAllLocationPresenter;
import com.example.dwdwproject.presenters.locationsPresenters.UpdateActiveLocationPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.presenters.locationsPresenters.UpdateLocationPresenter;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.locationsViews.DeactiveLocatonView;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;
import com.example.dwdwproject.views.locationsViews.GetLocationView;
import com.example.dwdwproject.views.locationsViews.CreateLocatonView;
import com.example.dwdwproject.views.locationsViews.UpdateActiveLocationView;
import com.example.dwdwproject.views.locationsViews.UpdateLocatonView;

import java.util.ArrayList;
import java.util.List;
public class ManageLocationActivity extends AppCompatActivity implements View.OnClickListener, GetAllLocatonView, DeactiveLocatonView, UpdateActiveLocationView {
    private RecyclerView mRecyclerView;
    private List<Location> mLocationList;
    private LocationAdapter mLocationAdapter;
    private GetAllLocationPresenter getAllLocationPresenter;
    private String token;
    private SearchView mSearchView;
    private List<LocationDTO> mLocationDTOS;
    private LinearLayout mBtnClose,mBtnAddLocation;
    private DeactiveLocationPresenter  mDeactiveLocationPresenter;
    private UpdateActiveLocationPresenter mUpdateActiveLocationPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_admin);
        initView();
        initData();
    }
    private void initView(){
        mRecyclerView = findViewById(R.id.rcv_location);
        mSearchView  =findViewById(R.id.search_view_admin_manage_location);
        mBtnClose = findViewById(R.id.lnl_close_manage_location);
        mBtnAddLocation = findViewById(R.id.lnl_add_location_admin);
         RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        token =  SharePreferenceUtils.getStringSharedPreference(ManageLocationActivity.this,BundleString.TOKEN);
        mUpdateActiveLocationPresenter = new UpdateActiveLocationPresenter(ManageLocationActivity.this,this);
        mDeactiveLocationPresenter = new DeactiveLocationPresenter(ManageLocationActivity.this,this);
        mBtnClose.setOnClickListener(this);
        mBtnAddLocation.setOnClickListener(this);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if(mLocationAdapter!=null){
                    mLocationAdapter.getFilter().filter(newText.toString());
                }
                return false;
            }
        });
//        mLocationList = new ArrayList<>();
//        mLocationList.add(new Location(1,"Khu A","20-11-2020",true));
//        mLocationList.add(new Location(2,"Khu B","12-10-2019",false));
//        mLocationList.add(new Location(3,"Khu C","1-10-2019",true));
//        mLocationList.add(new Location(4,"Khu D","1-10-2019",true));
//
//        mLocationList.add(new Location(5,"Khu E","1-10-2019",false));
//
//        mLocationList.add(new Location(6,"Khu F","1-10-2019",true));
//
//        updateUI();
        getAllLocationPresenter = new GetAllLocationPresenter(ManageLocationActivity.this,this);
        getAllLocationPresenter.getAllLocation(token);
    }
    private void updateUI(){
        if(mLocationAdapter == null){
            mLocationAdapter = new LocationAdapter(ManageLocationActivity.this,mLocationList);
            mRecyclerView.setAdapter(mLocationAdapter);
            mLocationAdapter.OnClickDeleteItemListener(new LocationAdapter.OnClickDeleteItem() {
                @Override
                public void OnClickDeleteItem(int position) {
                    showDeactivetDialog("Do want to deactive this location ? ",mLocationList.get(position).getLocationId());
                }
            });
            mLocationAdapter.OnClickItemListener(new LocationAdapter.OnClickItem() {
                @Override
                public void OnClickItem(int position) {
                  Intent intent  = new Intent(ManageLocationActivity.this,AdminLocationDetailActivity.class);
                  Bundle bundle = new Bundle();
                  bundle.putSerializable(BundleString.LOCATIONDETAIL,mLocationDTOS.get(position));
                  intent.putExtras(bundle);
                  startActivity(intent);
                }
            });
            mLocationAdapter.OnClickActiveListerner(new LocationAdapter.OnClickActiveItem() {
                @Override
                public void OnClickActiveItem(int pos) {
                    showActivetDialog("Do want to Active this location ? ",mLocationList.get(pos).getLocationId());

                }
            });
        }
        else {
            mLocationAdapter.notify(mLocationList);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        getAllLocationPresenter.getAllLocation(token);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.lnl_close_manage_location:
                finish();
                break;
            case R.id.lnl_add_location_admin:
                Intent intent = new Intent(ManageLocationActivity.this, AdminCreateLocationActivity.class);
                startActivity(intent);
                break;
        }
    }
    private void showDeactivetDialog(String message, final int locationId) {
        final Dialog dialog = new Dialog(ManageLocationActivity.this);
        dialog.setContentView(R.layout.alert_dialog_notify_sign_out);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk = dialog.findViewById(R.id.btn_yes);
        Button buttonNo = dialog.findViewById(R.id.btn_no);
        TextView  textView = dialog.findViewById(R.id.txt_dia);
        textView.setText(message);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                mDeactiveLocationPresenter.deactiveLocation(token,locationId);
            }
        });
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void showActivetDialog(String message, final int locationId) {
        final Dialog dialog = new Dialog(ManageLocationActivity.this);
        dialog.setContentView(R.layout.alert_dialog_notify_sign_out);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk = dialog.findViewById(R.id.btn_yes);
        Button buttonNo = dialog.findViewById(R.id.btn_no);
        TextView  textView = dialog.findViewById(R.id.txt_dia);
        textView.setText(message);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                mUpdateActiveLocationPresenter.updateActiveLocation(token,locationId);
            }
        });
        buttonNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(ManageLocationActivity.this,message);
        }
    @Override
    public void getAllLocationSuccess(List<LocationDTO> mLocationDTOList) {
        if(mLocationDTOList!=null){
            this.mLocationDTOS = new ArrayList<>();
            this.mLocationDTOS = mLocationDTOList;
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
    public void deactiveLocationSuccess() {
        getAllLocationPresenter.getAllLocation(token);
    }
    @Override
    public void updateActiveLocationSuccess(LocationDTO mLocationDTO) {
        getAllLocationPresenter.getAllLocation(token);
    }
}
