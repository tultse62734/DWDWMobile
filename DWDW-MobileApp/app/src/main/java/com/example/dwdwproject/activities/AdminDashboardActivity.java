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

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.adapters.DashboardAdapter;
import com.example.dwdwproject.listviewitems.BarChartItem;
import com.example.dwdwproject.listviewitems.ChartItem;
import com.example.dwdwproject.listviewitems.LineChartItem;
import com.example.dwdwproject.listviewitems.PieChartItem;
import com.example.dwdwproject.models.ItemDashBoard;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.presenters.locationsPresenters.GetAllLocationPresenter;
import com.example.dwdwproject.presenters.recordsPresenters.GetRecordsByLocationIdAndTimePresenter;
import com.example.dwdwproject.presenters.recordsPresenters.GetRecordsByLocationIdPresenter;
import com.example.dwdwproject.presenters.roomLocalPresenter.DeleteUserToRoomPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;
import com.example.dwdwproject.views.recordsViews.GetAllRecordsView;
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
public class AdminDashboardActivity extends AppCompatActivity implements View.OnClickListener, DeleteUserView, GetAllRecordsView, GetAllLocatonView {
    private RecyclerView mRecyclerView;
    private ListView mListView;
    private ArrayList<ChartItem> list;
    private List<ItemDashBoard> itemDashBoards;
    private LinearLayout mBtnLogout,mBtnFilter;
    private DashboardAdapter mDashboardAdapter;
    private DeleteUserToRoomPresenter mDeleteUserToRoomPresenter;
    private GetAllLocationPresenter mGetAllLocationPresenter;
    private GetRecordsByLocationIdAndTimePresenter mGetRecordsByLocationIdAndTimePresenterd;
    private String token;
    private List<LocationDTO> mLocationDTOS;
    private List<Location> mLocationList;
    private List<RecordDTO> mRecordDTO;
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
        mBtnLogout = findViewById(R.id.lnl_log_out_dashboard);
        mRecyclerView = findViewById(R.id.rcv_item_dashboard);
        mBtnFilter = findViewById(R.id.lnl_filter_dashboarđ);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(AdminDashboardActivity.this,LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mDeleteUserToRoomPresenter = new DeleteUserToRoomPresenter(getApplication(),this);
        mBtnLogout.setOnClickListener(this);
        mBtnFilter.setOnClickListener(this);
        list  = new ArrayList<>();;
        list.add(new LineChartItem(generateDataLine(), getApplicationContext()));
        list.add(new BarChartItem(generateDataBar(), getApplicationContext()));
        list.add(new PieChartItem(generateDataPie(), getApplicationContext()));
        itemDashBoards = new ArrayList<>();
        itemDashBoards.add(new ItemDashBoard("Profile",R.mipmap.ic_worker));
        itemDashBoards.add(new ItemDashBoard("18 Locations",R.mipmap.ic_project_management));
        itemDashBoards.add(new ItemDashBoard("17 Devices",R.mipmap.ic_security_camera));
        itemDashBoards.add(new ItemDashBoard("30 Workers",R.mipmap.ic_collaboration));
        itemDashBoards.add(new ItemDashBoard("15 Rooms",R.mipmap.ic_search_home));
        itemDashBoards.add(new ItemDashBoard("5 Record",R.mipmap.ic_emergency));
        ChartDataAdapter cda = new ChartDataAdapter(getApplicationContext(), list);
        mListView.setAdapter(cda);
        update();
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

        mGetAllLocationPresenter = new GetAllLocationPresenter(AdminDashboardActivity.this, this);

        int locationID = 1;
        String start = "14-07-2020", end = "17-07-2020";
        mGetRecordsByLocationIdAndTimePresenterd = new GetRecordsByLocationIdAndTimePresenter
                (AdminDashboardActivity.this, getApplication(),this);
        mGetRecordsByLocationIdAndTimePresenterd.getRecordsByLocationIdAndTime(token, locationID, start, end);

    }
    private PieData generateDataPie() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            entries.add(new PieEntry((float) ((Math.random() * 70) + 30), "Location " + (i+1)));
        }
        PieDataSet d = new PieDataSet(entries, "Accident Reports");

        // space between slices
        d.setSliceSpace(2f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        return new PieData(d);
    }

    private BarData generateDataBar() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry(i, (int) (Math.random() * 70) + 30));
        }

        BarDataSet d = new BarDataSet(entries, "Location ");
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setHighLightAlpha(255);

        BarData cd = new BarData(d);
        cd.setBarWidth(0.9f);
        return cd;
    }

    private LineData generateDataLine() {
        ArrayList<Entry> values1 = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            values1.add(new Entry(i, (int) (Math.random() * 65) + 40));
        }

        LineDataSet d1 = new LineDataSet(values1, "Location A");
        d1.setLineWidth(2.5f);
        d1.setCircleRadius(4.5f);
        d1.setHighLightColor(Color.rgb(244, 117, 117));
        d1.setDrawValues(false);

        ArrayList<Entry> values2 = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            values2.add(new Entry(i, values1.get(i).getY() - 30));
        }

        LineDataSet d2 = new LineDataSet(values2, "Location B");
        d2.setLineWidth(2.5f);
        d2.setCircleRadius(4.5f);
        d2.setHighLightColor(Color.rgb(244, 117, 117));
        d2.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        d2.setDrawValues(false);

        ArrayList<Entry> values3 = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            values3.add(new Entry(i, values1.get(i).getY() - 20));
        }

        LineDataSet d3 = new LineDataSet(values3, "Location C");
        d3.setLineWidth(2.5f);
        d3.setCircleRadius(4.5f);
        d3.setHighLightColor(Color.rgb(244, 117, 117));
        d3.setColor(ColorTemplate.VORDIPLOM_COLORS[1]);
        d3.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[1]);
        d3.setDrawValues(false);

        ArrayList<Entry> values4 = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            values4.add(new Entry(i, values1.get(i).getY() - 10));
        }
        LineDataSet d4 = new LineDataSet(values4, "Location D");
        d4.setLineWidth(2.5f);
        d4.setCircleRadius(4.5f);
        d4.setHighLightColor(Color.rgb(244, 117, 117));
        d4.setColor(ColorTemplate.VORDIPLOM_COLORS[2]);
        d4.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[2]);
        d4.setDrawValues(false);

        ArrayList<Entry> values5 = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            values5.add(new Entry(i, values1.get(i).getY() - 5));
        }
        LineDataSet d5 = new LineDataSet(values5, "Location E");
        d5.setLineWidth(2.5f);
        d5.setCircleRadius(4.5f);
        d5.setHighLightColor(Color.rgb(244, 117, 117));
        d5.setColor(ColorTemplate.VORDIPLOM_COLORS[4]);
        d5.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[4]);
        d5.setDrawValues(false);

        ArrayList<ILineDataSet> sets = new ArrayList<>();
        sets.add(d1);
        sets.add(d2);
        sets.add(d3);
        sets.add(d4);
        sets.add(d5);

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
        DialogNotifyError.showErrorLoginDialog(AdminDashboardActivity.this,"Logout Failed");
    }

    @Override
    public void getAllRecordSuccess(List<RecordDTO> mRecordDTOList) {
//chua xong
    }

    @Override
    public void getAllLocationSuccess(List<LocationDTO> mLocationDTOList) {
//chua xong
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