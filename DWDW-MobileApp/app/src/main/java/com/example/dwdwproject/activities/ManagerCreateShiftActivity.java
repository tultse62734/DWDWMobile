package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.adapters.ChooseManagerAdapter;
import com.example.dwdwproject.adapters.ChooseRoomAdapter;
import com.example.dwdwproject.adapters.ManageAdapter;
import com.example.dwdwproject.models.Manager;
import com.example.dwdwproject.models.Room;
import com.example.dwdwproject.presenters.roomPresenters.GetAllRoomFromLocationPresenter;
import com.example.dwdwproject.presenters.shiftPresenters.CreateShiftPresenter;
import com.example.dwdwproject.presenters.userPresenters.GetAllWorkerFromLocationByManagerPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.roomViews.GetListRoomView;
import com.example.dwdwproject.views.shiftsViews.CreateShiftView;
import com.example.dwdwproject.views.userViews.GetAllListUserView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ManagerCreateShiftActivity extends AppCompatActivity implements View.OnClickListener, GetListRoomView, GetAllListUserView, CreateShiftView {
    private LinearLayout mBtnCreate, mBtnClose;
    private TextView mTxtWorker, mTxtTime, mTxtLocation, mTxtRoom;
    private int locationId;
    private String locationCode;
    private String date;
    private int mYear,mMonth,mDay;
    private int roomId,workerId;
    private GetAllRoomFromLocationPresenter mGetAllRoomFromLocationPresenter;
    private GetAllWorkerFromLocationByManagerPresenter managerPresenter;
    private RecyclerView mRecyclerView,mRecyclerView1;
    private ChooseRoomAdapter mChooseRoomAdapter;
    private ChooseManagerAdapter managerAdapter;
    private List<Room> mRoomList;
    private List<Manager> managerList;
    private CreateShiftPresenter mCreateShiftPresenter;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_create_shift);
        getDataServer();
    }
    private void initView() {
        mBtnClose = findViewById(R.id.lnl_close_admin_create_shift);
        mBtnCreate = findViewById(R.id.lnl_submit_create_shift);
        mTxtWorker = findViewById(R.id.txt_choose_worker);
        mTxtLocation = findViewById(R.id.edt_choose_location_create_shift);
        mTxtRoom = findViewById(R.id.edt_choose_room_create_shift);
        mTxtTime = findViewById(R.id.edt_choose_time_create_shift);
    }
    public void getDataServer(){
        token  = SharePreferenceUtils.getStringSharedPreference(ManagerCreateShiftActivity.this,BundleString.TOKEN);
        locationId = SharePreferenceUtils.getIntSharedPreference(ManagerCreateShiftActivity.this,BundleString.LOCATIONID);
        locationCode = SharePreferenceUtils.getStringSharedPreference(ManagerCreateShiftActivity.this, BundleString.LOCATIONNAME);
        mCreateShiftPresenter = new CreateShiftPresenter(ManagerCreateShiftActivity.this,this);
        managerPresenter = new GetAllWorkerFromLocationByManagerPresenter(ManagerCreateShiftActivity.this,this);
        mGetAllRoomFromLocationPresenter = new GetAllRoomFromLocationPresenter(ManagerCreateShiftActivity.this,this);
        managerPresenter.getAllWorker(token,locationId);
    }
    private void initData() {
        mTxtLocation.setText(locationCode);
        mBtnCreate.setOnClickListener(this);
        mBtnClose.setOnClickListener(this);
        mTxtTime.setOnClickListener(this);
        mTxtRoom.setOnClickListener(this);
        mTxtWorker.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.lnl_close_admin_create_shift:
                finish();
                break;
            case R.id.lnl_submit_create_shift:
                createShift();
                break;
            case R.id.txt_choose_worker:
                showChooseManagerDialog();
                break;
            case R.id.edt_choose_time_create_shift:
                chooseTimeBirthDay();
                break;
            case R.id.edt_choose_room_create_shift:
               showChooseRoomDialog();
                break;
        }
    }
    private void createShift(){
        ShiftDTO mShiftDTO = new ShiftDTO();
        mShiftDTO.setWorkerId(workerId);
        mShiftDTO.setDate(date);
        mShiftDTO.setRoomId(roomId);
        mCreateShiftPresenter.createShifts(token,locationId,mShiftDTO);
    }
    private void showChooseRoomDialog() {
        final Dialog dialog = new Dialog(ManagerCreateShiftActivity.this);
        dialog.setContentView(R.layout.alert_dialog_choose_room);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mRecyclerView = dialog.findViewById(R.id.rcv_choose_room);
        LinearLayout mBtnClose = dialog.findViewById(R.id.lnl_close_dialog_choose_room);
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mChooseRoomAdapter = new ChooseRoomAdapter(ManagerCreateShiftActivity.this,mRoomList);
        mRecyclerView.setAdapter(mChooseRoomAdapter);
        mChooseRoomAdapter.OnClickItemListener(new ChooseRoomAdapter.OnClickItem() {
            @Override
            public void OnClickItem(int position) {
                dialog.dismiss();
                roomId = mRoomList.get(position).getRoomId();
                mTxtRoom.setText(mRoomList.get(position).getRoomName());
            }
        });
        dialog.show();
    }
    private void showChooseManagerDialog() {
        final Dialog dialog = new Dialog(ManagerCreateShiftActivity.this);
        dialog.setContentView(R.layout.alert_dialog_choose_manager);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mRecyclerView1 = dialog.findViewById(R.id.rcv_choose_worker);
        LinearLayout mBtnClose = dialog.findViewById(R.id.lnl_close_dialog_choose_worker);
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView1.setLayoutManager(layoutManager);
        managerAdapter = new ChooseManagerAdapter(ManagerCreateShiftActivity.this,managerList);
        mRecyclerView1.setAdapter(managerAdapter);
        managerAdapter.OnItemClickListener(new ManageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                dialog.dismiss();
                workerId = managerList.get(pos).getUserId();
                mTxtWorker.setText(managerList.get(pos).getName());
            }
        });
        dialog.show();
    }
    private void chooseTimeBirthDay(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        date = DateManagement.fortmatIntToDate(year)+"-"+DateManagement.fortmatIntToDate(monthOfYear+1)+"-"+DateManagement.fortmatIntToDate(dayOfMonth);
                        mTxtTime.setText(date);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
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
        initView();
        initData();
    }
    @Override
    public void getAllUserSuccess(List<UserDTO> userDTOList) {
        if(userDTOList!=null){
            this.managerList = new ArrayList<>();
            for (int i = 0; i <userDTOList.size() ; i++) {
                int userId = userDTOList.get(i).getUserId();
                String name  = userDTOList.get(i).getUserName();
                String phone = userDTOList.get(i).getPhone();
                String location = SharePreferenceUtils.getStringSharedPreference(ManagerCreateShiftActivity.this,BundleString.LOCATIONNAME);
                String roleName = "Worker";
                boolean isActive = userDTOList.get(i).isActive();
                managerList.add(new Manager(userId,name,phone,roleName,location,isActive));
            }
            mGetAllRoomFromLocationPresenter.getAllRoomFromLocationByManager(token,locationId);
        }

    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(ManagerCreateShiftActivity.this, message);
    }
    @Override
    public void createShiftSuccess() {
        finish();
    }
}
