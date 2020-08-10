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

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.AssignUserDTO;
import com.example.dwdwproject.ResponseDTOs.DeviceDTO;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.adapters.ChooseLocationAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.presenters.devicesPresenters.AssignDevicePresenter;
import com.example.dwdwproject.presenters.locationsPresenters.GetAllLocationPresenter;
import com.example.dwdwproject.presenters.roomPresenters.GetAllRoomFromLocationPresenter;
import com.example.dwdwproject.presenters.userPresenters.AssignUserPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;
import com.example.dwdwproject.views.userViews.AssignUserView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AdminAssignUserActivity extends AppCompatActivity  implements  View.OnClickListener,com.borax12.materialdaterangepicker.date.DatePickerDialog.OnDateSetListener, GetAllLocatonView , AssignUserView {
    LinearLayout mBtnClose,mBtnAssginUser;
    private EditText mEdtUsername;
    TextView mEdtChoooseLocation,mEdtChooseTime;
    TextView mEditLocation;
    private List<Location> mLocationList;
    RecyclerView mRecyclerView;
    private ChooseLocationAdapter mLocationAdapter;
    private int locationId,poslocation;
    private UserDTO mUserDTO;
    private String token ;
    private GetAllLocationPresenter mLocationPresenter;
    private AssignUserPresenter mAssignUserPresenter;
    private String startTime,endTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_assign_user);
        getDataLocation();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_admin_assign_user);
        mEditLocation = findViewById(R.id.edt_choose_current_location_assign_admin_add_user);
        mEdtChoooseLocation = findViewById(R.id.edt_choose_location_assign_admin_add_user);
        mBtnAssginUser = findViewById(R.id.lnl_submit_assign_user_admin);
        mEdtUsername = findViewById(R.id.edit_assign_username);
        mEdtChooseTime = findViewById(R.id.edt_choose_time_assgin_admin_add_user);
    }
    private void initData(){
        mAssignUserPresenter = new AssignUserPresenter(AdminAssignUserActivity.this,this);
        mEdtChooseTime.setText(startTime +" - " + endTime+"");
        mEdtUsername.setText(mUserDTO.getUserName()+"");
        mEditLocation.setText(mUserDTO.getmLocationDTO().get(poslocation).toString()+"");
        mBtnClose.setOnClickListener(this);
        mEdtChoooseLocation.setOnClickListener(this);
        mEdtChooseTime.setOnClickListener(this);
        mBtnAssginUser.setOnClickListener(this);
        mEdtChooseTime.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_admin_assign_user:
                finish();
                break;
            case R.id.edt_choose_location_assign_admin_add_user:
                showChooseLocationDialog();
                break;
            case R.id.edt_choose_time_assgin_admin_add_user:
                clickOnRdOption();
                break;
            case R.id.lnl_submit_assign_user_admin:
                assignUser();
                break;
        }
    }
    private void assignUser(){
            AssignUserDTO userDTO = new AssignUserDTO();
            userDTO.setLocationId(locationId);
            userDTO.setUserId(mUserDTO.getUserId());
            userDTO.setStartDate(startTime);
            userDTO.setEndDate(endTime);
            mAssignUserPresenter.assignUser(token,userDTO);
    }
    private void getDataLocation(){
        Bundle bundle = getIntent().getExtras();
        mUserDTO  = (UserDTO) bundle.getSerializable(BundleString.MANAGERDETAIL);
        poslocation = (int)bundle.getInt(BundleString.USERASSIGNPOSTION,0);
        token = SharePreferenceUtils.getStringSharedPreference(AdminAssignUserActivity.this,BundleString.TOKEN);
        mLocationPresenter = new GetAllLocationPresenter(AdminAssignUserActivity.this,this);
        mLocationPresenter.getAllLocation(token);
        startTime = DateManagement.changeFormatDate1(mUserDTO.getStartDate())+"";
        endTime = DateManagement.changeFormatDate1(mUserDTO.getEndDate())+"";
    }
    @Override
    protected void onResume() {
        super.onResume();
        DatePickerDialog datePickerDialog = (DatePickerDialog) getFragmentManager().findFragmentByTag("Datepickerdialog");
        if (datePickerDialog != null) datePickerDialog.setOnDateSetListener(this);
        getDataLocation();
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
    public void clickOnRdOption() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = com.borax12.materialdaterangepicker.date.DatePickerDialog.newInstance(
                AdminAssignUserActivity.this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");
    }
    private void showFilterDateDialog(String message) {
        final Dialog dialog = new Dialog(AdminAssignUserActivity.this);
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
    private void showChooseLocationDialog() {
        final Dialog dialog = new Dialog(AdminAssignUserActivity.this);
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
        mLocationAdapter = new ChooseLocationAdapter(AdminAssignUserActivity.this,mLocationList);
        mRecyclerView.setAdapter(mLocationAdapter);
        mLocationAdapter.OnClickItemListener(new ChooseLocationAdapter.OnClickItem() {
            @Override
            public void OnClickItem(int position) {
                dialog.dismiss();
                locationId = mLocationList.get(position).getLocationId();
                mEdtChoooseLocation.setText(mLocationList.get(position).getNameLocation());
            }
        });
        dialog.show();
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
        DialogNotifyError.showErrorLoginDialog(AdminAssignUserActivity.this,message);
    }

    @Override
    public void getAssignUserSuccess(AssignUserDTO mAssignUserDTO) {
        finish();
    }
}