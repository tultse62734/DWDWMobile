package com.example.dwdwproject.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.ResponseDTOs.LocationRecord;
import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.adapters.DashboardAdapter;
import com.example.dwdwproject.listviewitems.BarChartItem;
import com.example.dwdwproject.listviewitems.ChartItem;
import com.example.dwdwproject.listviewitems.LineChartItem;
import com.example.dwdwproject.listviewitems.PieChartItem;
import com.example.dwdwproject.models.ItemDashBoard;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.presenters.locationsPresenters.GetAllLocationPresenter;
import com.example.dwdwproject.presenters.recordsPresenters.GetLocationRecordPresenter;
import com.example.dwdwproject.presenters.recordsPresenters.GetRecordsByLocationIdAndTimePresenter;
import com.example.dwdwproject.presenters.recordsPresenters.GetRecordsByLocationIdPresenter;
import com.example.dwdwproject.presenters.roomLocalPresenter.DeleteUserToRoomPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;
import com.example.dwdwproject.views.recordsViews.GetAllRecordsView;
import com.example.dwdwproject.views.recordsViews.GetLocationRecordView;
import com.example.dwdwproject.views.roomLocalViews.DeleteUserView;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;
public class AdminDashboardActivity extends AppCompatActivity implements View.OnClickListener, DeleteUserView, GetLocationRecordView {
    private RecyclerView mRecyclerView;
    private ListView mListView;
    private ArrayList<ChartItem> list;
    private List<ItemDashBoard> itemDashBoards;
    private LinearLayout mBtnLogout,mBtnFilter;
    private DashboardAdapter mDashboardAdapter;
    private DeleteUserToRoomPresenter mDeleteUserToRoomPresenter;
    private GetLocationRecordPresenter mRecordPresenter;
    private String token;
    private TextView mTxtTime;
    private String mStartTime = "";
    private String mEndTime = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        initView();
        initData();
    }
    private void initView(){
        setTitle("ListViewMultiChartActivity");
        mListView  = findViewById(R.id.listView1);
        mTxtTime = findViewById(R.id.txt_time_home_page);
        mBtnLogout = findViewById(R.id.lnl_log_out_dashboard);
        mRecyclerView = findViewById(R.id.rcv_item_dashboard);
        mBtnFilter = findViewById(R.id.lnl_filter_dashboarđ);
        mTxtTime.setText(BundleString.getSelectedDate(AdminDashboardActivity.this));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AdminDashboardActivity.this,LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    @Override
    protected void onResume() {
        super.onResume();
        String selectDay = BundleString.getSelectedDate(getApplicationContext());
        mTxtTime.setText(selectDay);
        checkTypeFilterDate(selectDay);
        token = SharePreferenceUtils.getStringSharedPreference(AdminDashboardActivity.this, BundleString.TOKEN);
        if(mStartTime.equalsIgnoreCase(mEndTime)){
            mRecordPresenter.getLocationRecord(token,mStartTime,mStartTime);
        }else {
            mRecordPresenter.getLocationRecord(token,mStartTime,mEndTime);
        }
    }
    private String splitToStartDayAndEndDay(String day) {
        String days = "";
        String[] tmp = day.split("- ");
        days = tmp[1];
        return days;
    }
    private String splitToStartDayAndEndDay1(String day) {
        String days = "";
        String[] tmp = day.split("- ");
        days = tmp[0];
        return days;
    }
    private void checkTypeFilterDate(String filterDate) {
        if (filterDate.contains(", ")) {
            splitFromToDay(filterDate);
        }else {
            mStartTime = splitToStartDayAndEndDay1(filterDate);
            mEndTime = splitToStartDayAndEndDay(filterDate);
        }
    }
    private void splitFromToDay(String filterDate) {
        String[] tmp = filterDate.split(", ");
        mStartTime = tmp[1];
        mEndTime = tmp[1];
    }
    private void initData() {
        mDeleteUserToRoomPresenter = new DeleteUserToRoomPresenter(getApplication(), this);
        mRecordPresenter = new GetLocationRecordPresenter(AdminDashboardActivity.this, this);
        mBtnLogout.setOnClickListener(this);
        mBtnFilter.setOnClickListener(this);
        itemDashBoards = new ArrayList<>();
        itemDashBoards.add(new ItemDashBoard("Profile", R.mipmap.ic_worker));
        itemDashBoards.add(new ItemDashBoard("Locations", R.mipmap.ic_project_management));
        itemDashBoards.add(new ItemDashBoard("Devices", R.mipmap.ic_security_camera));
        itemDashBoards.add(new ItemDashBoard("Workers", R.mipmap.ic_collaboration));
        itemDashBoards.add(new ItemDashBoard("Rooms", R.mipmap.ic_search_home));
        itemDashBoards.add(new ItemDashBoard("Record", R.mipmap.ic_emergency));
        update();
        String selectDay = BundleString.getSelectedDate(AdminDashboardActivity.this);
        checkTypeFilterDate(selectDay);
        token = SharePreferenceUtils.getStringSharedPreference(AdminDashboardActivity.this, BundleString.TOKEN);
        if(mStartTime.equalsIgnoreCase(mEndTime)){
            mRecordPresenter.getLocationRecord(token,mStartTime,mStartTime);
        }else {
            mRecordPresenter.getLocationRecord(token,mStartTime,mEndTime);
        }
    }
    private void update(){
        if(mDashboardAdapter == null){
            mDashboardAdapter = new DashboardAdapter(AdminDashboardActivity.this,itemDashBoards);
            mRecyclerView.setAdapter(mDashboardAdapter);
            mDashboardAdapter.OnItemClick(new DashboardAdapter.OnItemClickListenr() {
                @Override
                public void onItemClickListerner(int pos) {
                intentToActivity(pos);
                }
            });
        }else {
            mDashboardAdapter.notifyDataSetChanged();
        }
    }
    private void getData(){
        token = SharePreferenceUtils.getStringSharedPreference(AdminDashboardActivity.this, BundleString.TOKEN);

        int locationID = 1;
        String start = "14-07-2020", end = "17-07-2020";
    }
    private PieData generateDataPie(List<LocationRecord> mLocationRecords) {
        PieDataSet d = null;
        for (int i = 0; i <mLocationRecords.size() ; i++) {
            ArrayList<PieEntry> entries = new ArrayList<>();
            for (int j = 0; j < mLocationRecords.size(); j++) {
                entries.add(new PieEntry((float) mLocationRecords.get(j).getTotalRecord(), mLocationRecords.get(j).getLocationCode()));
            }
             d = new PieDataSet(entries, "Report");

            // space between slices
            d.setSliceSpace(2f);
            d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        }
        return new PieData(d);
    }
    private BarData generateDataBar(List<LocationRecord> mLocationRecords) {
        BarData cd = null;
        for (int i = 0; i <mLocationRecords.size() ; i++) {
            ArrayList<BarEntry> entries = new ArrayList<>();
            for (int j = 0; j < mLocationRecords.size(); j++) {
                entries.add(new BarEntry(j, (int)mLocationRecords.get(j).getTotalRecord()));
            }
            BarDataSet d = new BarDataSet(entries, "Location ");
            d.setColors(ColorTemplate.VORDIPLOM_COLORS);
            d.setHighLightAlpha(255);

            cd= new BarData(d);
            cd.setBarWidth(0.9f);

        }
        return cd;
    }
    private LineData generateDataLine(List<LocationRecord> mLocationRecords) {
        LineDataSet d1 = null;
        ArrayList<ILineDataSet> sets = new ArrayList<>();
        for (int i = 0; i < mLocationRecords.size(); i++) {

            ArrayList<Entry> values1 = new ArrayList<>();

            for (int j = 0; j < mLocationRecords.size(); j++) {
                values1.add(new Entry(j, mLocationRecords.get(j).getTotalRecord()));
            }
            d1=new LineDataSet(values1, "Location");
            d1.setLineWidth(2.5f);
            d1.setCircleRadius(4.5f);
            d1.setHighLightColor(Color.rgb(244, 117, 117));
            d1.setDrawValues(false);

            sets.add(d1);
        }
        return new LineData(sets);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_log_out_dashboard:
                showLogoutDialog();
                break;
            case R.id.lnl_filter_dashboarđ:
                intentToFilterActivity();
                break;
        }
    }
    @Override
    public void deleteUserToRoomSucces() {
        intentToLogOutActivity();
    }
    @Override
    public void showError(String message) {
    }


    @Override
    public void getLocationRecordSuccess(List<LocationRecord> mLocationRecordList) {
        list  = new ArrayList<>();;
        list.add(new PieChartItem(generateDataPie(mLocationRecordList), getApplicationContext()));
        list.add(new LineChartItem(generateDataLine(mLocationRecordList), getApplicationContext()));
        list.add(new BarChartItem(generateDataBar(mLocationRecordList), getApplicationContext()));
        ChartDataAdapter cda = new ChartDataAdapter(getApplicationContext(), list);
        mListView.setAdapter(cda);
    }

    /** adapter that supports 3 different item types */
    private class ChartDataAdapter extends ArrayAdapter<ChartItem> {
        public ChartDataAdapter(Context context, List<ChartItem> objects) {
            super(context, 0, objects);
        }
        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            //noinspection ConstantConditions
            return getItem(position).getView(position, convertView, getContext());
        }

        @Override
        public int getItemViewType(int position) {
            // return the views type
            ChartItem ci = getItem(position);
            return ci != null ? ci.getItemType() : 0;
        }

        @Override
        public int getViewTypeCount() {
            return 3; // we have 3 different item-types
        }
    }
    private void intentToActivity(int pos){
        int id = pos;
        switch (id) {
            case 0:
                intentToProfileActivity();
                break;
            case 1:
                intentToLocationActivity();
                break;
            case 2:
                intentToManagaDeviceActivity();
                break;
            case 3:
                intentManageWorḳerActivity();
                break;
            case 4:
                intentToManagerRoomActivity();
                break;
            case 5:
                intentToManageAccidentActivity();
                break;
        }

    }
    private void intentToManageAccidentActivity(){
        Intent intent = new Intent(AdminDashboardActivity.this,AdminAccidentManageActivity.class);
        startActivity(intent);
    }
    private void intentToFilterActivity(){
        Intent intent = new Intent(AdminDashboardActivity.this,ShiftDateFilterActivity.class);
        startActivity(intent);
    }
    private void intentManageWorḳerActivity(){
        Intent intent = new Intent(AdminDashboardActivity.this, ManageManagerActivity.class);
        startActivity(intent);
    }
    private void intentToLogOutActivity(){
        Intent intent = new Intent(AdminDashboardActivity.this,LoginActivity.class);
        finish();
        startActivity(intent);
    }
    private void intentToProfileActivity(){
        Intent intent = new Intent(AdminDashboardActivity.this,ProfileManageActivity.class);
        startActivity(intent);
    }
    private void intentToManagerRoomActivity(){
        Intent intent = new Intent(AdminDashboardActivity.this,ManageRoomActivity.class);
        startActivity(intent);
    }
    private void intentToLocationActivity(){
        Intent intent = new Intent(AdminDashboardActivity.this, ManageLocationActivity.class);
        startActivity(intent);
    }
    private void intentToManagaDeviceActivity(){
        Intent intent = new Intent(AdminDashboardActivity.this,ManageDeviceActivity.class);
        startActivity(intent);
    }
    private void showLogoutDialog() {
        final Dialog dialog = new Dialog(AdminDashboardActivity.this);
        dialog.setContentView(R.layout.alert_dialog_notify_sign_out);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk = dialog.findViewById(R.id.btn_yes);
        Button buttonNo = dialog.findViewById(R.id.btn_no);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                mDeleteUserToRoomPresenter.deleteUserTomRoom();
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
}