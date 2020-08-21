package com.example.dwdwproject.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.AssignDeviceDTO;
import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.adapters.ChooseLocationAdapter;
import com.example.dwdwproject.adapters.ChooseRoomAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Room;
import com.example.dwdwproject.presenters.devicesPresenters.AssignDevicePresenter;
import com.example.dwdwproject.presenters.devicesPresenters.CreateDevivePresenter;
import com.example.dwdwproject.presenters.locationsPresenters.GetAllLocationPresenter;
import com.example.dwdwproject.presenters.roomPresenters.GetAllRoomFromLocationPresenter;
import com.example.dwdwproject.presenters.roomPresenters.GetAllRoomPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.devicesViews.AssginDeviceView;
import com.example.dwdwproject.views.devicesViews.CreateDeviceView;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;
import com.example.dwdwproject.views.roomViews.GetListRoomView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class AdminAssignDeviceActivity extends AppCompatActivity implements  View.OnClickListener,com.borax12.materialdaterangepicker.date.DatePickerDialog.OnDateSetListener, GetAllLocatonView , GetListRoomView , AssginDeviceView {
    LinearLayout mBtnClose,mBtnAddDevice;
    private EditText mEdtDeviceCode;
    TextView mEdtChoooseLocation,mEdtChoooseRoom,mEdtChooseTime;
    private List<Location> mLocationList;
    RecyclerView mRecyclerView;
    RecyclerView mRecyclerView1;
    private ChooseLocationAdapter mLocationAdapter;
    private ChooseRoomAdapter mChooseRoomAdapter;
    private int posLocation;
    private List<Room> mRoomList;
    private int posRoom;
    private DeviceDTO mDeviceDTO;
    private String token ;
    private int roomId;
    private GetAllLocationPresenter mLocationPresenter;
    private GetAllRoomPresenter mRoomFromLocationPresenter;
    private AssignDevicePresenter mAssignDevicePresenter;
    private String startTime,endTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign_device);
        getDataLocation();
    }
    private void getDataLocation(){
        Bundle bundle = getIntent().getExtras();
        mDeviceDTO  = (DeviceDTO) bundle.getSerializable(BundleString.DEVICEASSGINT);
        token = SharePreferenceUtils.getStringSharedPreference(AdminAssignDeviceActivity.this,BundleString.TOKEN);
        mLocationPresenter = new GetAllLocationPresenter(AdminAssignDeviceActivity.this,this);
        mRoomFromLocationPresenter = new GetAllRoomPresenter(AdminAssignDeviceActivity.this,this);
        mAssignDevicePresenter = new AssignDevicePresenter(AdminAssignDeviceActivity.this,this);
        mLocationPresenter.getAllLocation(token);
        startTime = DateManagement.changeFormatDate1(mDeviceDTO.getStartDate())+"";
        roomId = mDeviceDTO.getRoomId();
        endTime = DateManagement.changeFormatDate1(mDeviceDTO.getEndDate())+"";
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_admin_assign_device);
        mEdtChoooseLocation = findViewById(R.id.edt_choose_location_assign_admin);
        mEdtChoooseRoom = findViewById(R.id.edt_choose_room_assign_admin);
        mBtnAddDevice = findViewById(R.id.lnl_submit_assign_device_admin);
        mEdtDeviceCode = findViewById(R.id.edit_assign_device_code);
        mEdtChooseTime = findViewById(R.id.edt_choose_time_assgin_admin);
    }
    private void initData(){
        mEdtChooseTime.setText(startTime +" - " + endTime+"");
        mEdtDeviceCode.setText(mDeviceDTO.getDeviceCode()+"");
        mEdtChoooseLocation.setText(mDeviceDTO.getLocationCode()+"");
        mEdtChoooseRoom.setText(mDeviceDTO.getRoomCode()+"");
        mBtnClose.setOnClickListener(this);
        mEdtChoooseLocation.setOnClickListener(this);
        mEdtChoooseRoom.setOnClickListener(this);
        mEdtChooseTime.setOnClickListener(this);
        mBtnAddDevice.setOnClickListener(this);
        mEdtChooseTime.setOnClickListener(this);
    }
    public void clickOnRdOption() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = com.borax12.materialdaterangepicker.date.DatePickerDialog.newInstance(
                AdminAssignDeviceActivity.this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }
    private void showFilterDateDialog(String message) {
        final Dialog dialog = new Dialog(AdminAssignDeviceActivity.this);
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
        getDataLocation();
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_admin_assign_device:
                finish();
                break;
            case R.id.edt_choose_location_assign_admin:
                showChooseLocationDialog();
                break;
            case R.id.edt_choose_time_assgin_admin:
                clickOnRdOption();
                break;
            case R.id.edt_choose_room_assign_admin:
                if(mRoomList!=null){
                    showChooseRoomDialog();
                }else{
                    DialogNotifyError.showErrorLoginDialog(AdminAssignDeviceActivity.this,"Choose Location First");
                }

                break;
            case R.id.lnl_submit_assign_device_admin:
                assignDevice();
                break;
        }
    }
    private void showChooseLocationDialog() {
        final Dialog dialog = new Dialog(AdminAssignDeviceActivity.this);
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
        mLocationAdapter = new ChooseLocationAdapter(AdminAssignDeviceActivity.this,mLocationList);
        mRecyclerView.setAdapter(mLocationAdapter);
        mLocationAdapter.OnClickItemListener(new ChooseLocationAdapter.OnClickItem() {
            @Override
            public void OnClickItem(int position) {
                dialog.dismiss();
                posLocation = position;
                mEdtChoooseLocation.setText(mLocationList.get(position).getNameLocation());
                mRoomFromLocationPresenter.getAllRoom(token,mLocationList.get(position).getLocationId());
            }
        });
        dialog.show();
    }
    private void showChooseRoomDialog() {
        final Dialog dialog = new Dialog(AdminAssignDeviceActivity.this);
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
        mChooseRoomAdapter = new ChooseRoomAdapter(AdminAssignDeviceActivity.this,mRoomList);
        mRecyclerView1.setAdapter(mChooseRoomAdapter);
        mChooseRoomAdapter.OnClickItemListener(new ChooseRoomAdapter.OnClickItem() {
            @Override
            public void OnClickItem(int position) {
                dialog.dismiss();
                posRoom = position;
                roomId = mRoomList.get(posRoom).getRoomId();
                mEdtChoooseRoom.setText(mRoomList.get(position).getRoomName());
                mEdtChoooseRoom.setText(mRoomList.get(posRoom).getRoomName()+"");
            }
        });
        dialog.show();
    }
    private void assignDevice(){
        AssignDeviceDTO mAssignDeviceDTO = new AssignDeviceDTO();
        mAssignDeviceDTO.setDeviceId(mDeviceDTO.getDeviceId());
        mAssignDeviceDTO.setRoomId(roomId);
        mAssignDeviceDTO.setDateStart(startTime);
        mAssignDeviceDTO.setDateEnd(endTime);
        mAssignDevicePresenter.assignDevice(token,mAssignDeviceDTO);
    }
    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
        String starDay =   year + "-" + DateManagement.fortmatIntToDate((++monthOfYear)) + "-" +DateManagement.fortmatIntToDate(dayOfMonth) ;
        String endDay = yearEnd + "-" + DateManagement.fortmatIntToDate((++monthOfYearEnd)) + "-" +DateManagement.fortmatIntToDate(dayOfMonthEnd) ;
        if (DateManagement.isDateAfter(starDay, endDay)) {
            startTime = starDay;
            endTime = endDay;
            mEdtChooseTime.setText(startTime + " _ " + endTime);
        } else {
            showFilterDateDialog("Vui lòng chọn lại ngày!!!");
        }
    }
    @Override
    public void getAllLocationSuccess(List<LocationDTO> mLocationDTOList) {
        if(mLocationDTOList!=null){
            this.mLocationList = new ArrayList<>();
            for (int i = 0; i <mLocationDTOList.size() ; i++) {
                int locationId  = mLocationDTOList.get(i).getLocationId();
                String locationName = mLocationDTOList.get(i).getLocationCode();
                boolean isactive = mLocationDTOList.get(i).isActive();
                this.mLocationList.add(new Location(locationId,locationName,isactive));
            }
        }
        initView();
        initData();
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(AdminAssignDeviceActivity.this,message);
    }
    @Override
    public void getListRoomSuccess(List<RoomDTO> mRoomDTOList) {
        if(mRoomDTOList!=null){
            this.mRoomList = new ArrayList<>();
                for (int i = 0; i <mRoomDTOList.size() ; i++) {
                int roomId = mRoomDTOList.get(i).getRoomId();
                String roomCode = mRoomDTOList.get(i).getRoomCode();
                boolean isActive = mRoomDTOList.get(i).isActive();
                mRoomList.add(new Room(roomId,roomCode,"12-12-2020",isActive));
            }
        }
    }
    @Override
    public void assignDeviceSuccess(AssignDeviceDTO mAssignDeviceDTO) {
        finish();
    }
}