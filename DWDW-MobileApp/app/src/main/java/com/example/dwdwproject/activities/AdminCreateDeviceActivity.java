package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.example.dwdwproject.R;
import com.example.dwdwproject.adapters.ChooseLocationAdapter;
import com.example.dwdwproject.adapters.ChooseRoomAdapter;
import com.example.dwdwproject.adapters.LocationAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Room;
import com.example.dwdwproject.utils.DateManagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class AdminCreateDeviceActivity extends AppCompatActivity implements View.OnClickListener,com.borax12.materialdaterangepicker.date.DatePickerDialog.OnDateSetListener  {
    LinearLayout mBtnClose;
    TextView mEdtChoooseLocation,mEdtChoooseRoom,mEdtChooseTime;
    private List<Location> mLocationList;
    RecyclerView mRecyclerView;
    RecyclerView mRecyclerView1;
    private ChooseLocationAdapter mLocationAdapter;
    private ChooseRoomAdapter mChooseRoomAdapter;
    private int posLocation;
    private List<Room> mRoomList;
    private List<Room> mRoomListFromLocation;
    private int posRoom;
    private String startTime,endTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_device);
        initView();
        initData();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_admin_add_device);
        mEdtChoooseLocation = findViewById(R.id.edt_choose_location_add_admin);
        mEdtChoooseRoom = findViewById(R.id.edt_choose_room_add_admin);
        mEdtChooseTime = findViewById(R.id.edt_choose_time_add_admin);
    }
    private void initData(){
        getDataLocation();
        mBtnClose.setOnClickListener(this);
        mEdtChoooseLocation.setOnClickListener(this);
        mEdtChoooseRoom.setOnClickListener(this);
        mEdtChooseTime.setOnClickListener(this);
    }
    public void clickOnRdOption() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = com.borax12.materialdaterangepicker.date.DatePickerDialog.newInstance(
                AdminCreateDeviceActivity.this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }
    @Override
    public void onDateSet(com.borax12.materialdaterangepicker.date.DatePickerDialog view, int year, int month, int day, int yearEnd, int monthEnd, int dayEnd) {
        String starDay = DateManagement.fortmatIntToDate(day) + "/" + DateManagement.fortmatIntToDate((++month)) + "/" + year;
        String endDay = DateManagement.fortmatIntToDate(dayEnd) + "/" + DateManagement.fortmatIntToDate((++monthEnd)) + "/" + yearEnd;
        if (DateManagement.isDateAfter(starDay, endDay)) {
            startTime = starDay;
            endTime = endDay;
            mEdtChooseTime.setText(startTime + " _ " + endTime);
        } else {
            showFilterDateDialog("Vui lòng chọn lại ngày!!!");
        }
    }
    private void showFilterDateDialog(String message) {
        final Dialog dialog = new Dialog(AdminCreateDeviceActivity.this);
        dialog.setContentView(R.layout.alert_layout_notify_change_day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk = dialog.findViewById(R.id.btn_Ok_choose);
        TextView mTxtNotify = dialog.findViewById(R.id.txt_notify_choose_error);
        mTxtNotify.setText(message);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                clickOnRdOption();
            }
        });

        dialog.show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        DatePickerDialog datePickerDialog = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");
        if (datePickerDialog != null) datePickerDialog.setOnDateSetListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_admin_add_device:
                finish();
                break;
            case R.id.edt_choose_location_add_admin:
                showChooseLocationDialog();
                break;
            case R.id.edt_choose_room_add_admin:
                showChooseRoomDialog();
                break;
            case R.id.edt_choose_time_add_admin:
                clickOnRdOption();
                break;
            }
        }

    private void getDataLocation(){
        mBtnClose.setOnClickListener(this);
        mLocationList = new ArrayList<>();
        mRoomListFromLocation = new ArrayList<>();
        mRoomList = new ArrayList<>();
        mRoomList.add(new Room(1,"100","12-11-2020",true));
        mRoomList.add(new Room(1,"200","12-11-2020",true));
        mRoomList.add(new Room(1,"300","12-11-2020",true));
        mRoomList.add(new Room(1,"300","12-11-2020",true));
        mRoomList.add(new Room(1,"400","12-11-2020",true));
        mLocationList.add(new Location(1,"Khu A","20-11-2020",true));
        mLocationList.add(new Location(2,"Khu B","12-10-2019",false));
        mLocationList.add(new Location(3,"Khu C","1-10-2019",true));
        mLocationList.add(new Location(4,"Khu D","1-10-2019",true));
        for (int i = 0; i <mLocationList.size() ; i++) {
                mLocationList.get(i).setRoomList(mRoomList);
        }
    }
    private void showChooseLocationDialog() {
        final Dialog dialog = new Dialog(AdminCreateDeviceActivity.this);
        dialog.setContentView(R.layout.alert_dialog_choose_location);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
         mRecyclerView = dialog.findViewById(R.id.rcv_choose_location);
        LinearLayout mBtnClose = dialog.findViewById(R.id.lnl_close_dialog_choose_location);
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mLocationAdapter = new ChooseLocationAdapter(AdminCreateDeviceActivity.this,mLocationList);
            mRecyclerView.setAdapter(mLocationAdapter);
            mLocationAdapter.OnClickItemListener(new ChooseLocationAdapter.OnClickItem() {
                @Override
                public void OnClickItem(int position) {
                    dialog.dismiss();
                    posLocation = position;
                    mRoomListFromLocation = mLocationList.get(posLocation).getRoomList();
                    mEdtChoooseLocation.setText(mLocationList.get(position).getNameLocation()+"");
                }
            });

        dialog.show();

    }
    private void showChooseRoomDialog() {
        final Dialog dialog = new Dialog(AdminCreateDeviceActivity.this);
        dialog.setContentView(R.layout.alert_dialog_choose_room);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mRecyclerView1 = dialog.findViewById(R.id.rcv_choose_room);
        LinearLayout mBtnClose = dialog.findViewById(R.id.lnl_close_dialog_choose_room);
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView1.setLayoutManager(layoutManager);
            mChooseRoomAdapter = new ChooseRoomAdapter(AdminCreateDeviceActivity.this,mRoomListFromLocation);
            mRecyclerView1.setAdapter(mChooseRoomAdapter);
            mChooseRoomAdapter.OnClickItemListener(new ChooseRoomAdapter.OnClickItem() {
                @Override
                public void OnClickItem(int position) {
                    dialog.dismiss();
                    posRoom = position;
                    mEdtChoooseRoom.setText(mRoomListFromLocation.get(posRoom).getRoomName()+"");
                }
            });

        dialog.show();

    }
}
