package com.example.dwdwproject.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.adapters.ChooseLocationAdapter;
import com.example.dwdwproject.adapters.ChooseStatusAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Status;
import com.example.dwdwproject.presenters.locationsPresenters.GetAllLocationPresenter;
import com.example.dwdwproject.presenters.userPresenters.CreateUserPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;
import com.example.dwdwproject.views.userViews.GetUserView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class AdminCreateManagerActivity extends AppCompatActivity implements View.OnClickListener,GetUserView{
    private LinearLayout mBtnClose,mBtnCreateManager;
    private EditText mTxtUsername,mTxtPassword,mTxtPhone;
    private TextView mTxtBirthDay,mTxtRole,mtxtGender;
    private String token;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView1;
    private int posLocation;
    private ChooseStatusAdapter mLocationAdapter;
    private ChooseStatusAdapter mLocationAdapter1;
    private List<Status> mStatusList;
    private String birthday;
    private int roleId,genderId;
    private int mYear,mMonth,mDay;
    private List<Status> mStatusList1;
    private CreateUserPresenter mCreateUserPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_create_manager);
        initView();
        initData();
    }
    private  void initView(){
        mBtnClose = findViewById(R.id.lnl_close_admin_add_manager);
        mBtnCreateManager = findViewById(R.id.lnl_submit_create_manager);
        token = SharePreferenceUtils.getStringSharedPreference(AdminCreateManagerActivity.this, BundleString.TOKEN);
        mTxtUsername = findViewById(R.id.edit_create_username);
        mTxtPassword = findViewById(R.id.edit_create_password);
        mTxtPhone = findViewById(R.id.edit_create_phone);
        mTxtBirthDay = findViewById(R.id.edt_choose_birthday_add__manager_admin);
        mTxtRole = findViewById(R.id.edt_choose_role_add__manager_admin);
        mtxtGender = findViewById(R.id.edt_choose_gender_add__manager_admin);
    }
    private void initData(){
        mCreateUserPresenter = new CreateUserPresenter(AdminCreateManagerActivity.this,this);
        mStatusList = new ArrayList<>();
        mStatusList1 = new ArrayList<>();
        mStatusList.add(new Status("Manager ",true,2));
        mStatusList.add(new Status("Worker",false,3));
        mStatusList1.add(new Status("Women",true,1));
        mStatusList1.add(new Status("Men",false,2));
        mBtnClose.setOnClickListener(this);
        mBtnCreateManager.setOnClickListener(this);
        mtxtGender.setOnClickListener(this);
        mTxtBirthDay.setOnClickListener(this);
        mTxtRole.setOnClickListener(this);
    }
    private void showChooseRoleDialog(){
        final Dialog dialog = new Dialog(AdminCreateManagerActivity.this);
        dialog.setContentView(R.layout.alert_dialog_choose_status);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mRecyclerView = dialog.findViewById(R.id.rcv_choose_status);
        LinearLayout mBtnClose = dialog.findViewById(R.id.lnl_close_dialog_choose_status);
        TextView mTextView = dialog.findViewById(R.id.txt_choose_dialog);
        mTextView.setText("Choose Role");
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mLocationAdapter = new ChooseStatusAdapter(AdminCreateManagerActivity.this,mStatusList);
        mRecyclerView.setAdapter(mLocationAdapter);
        mLocationAdapter.OnClickItemListener(new ChooseStatusAdapter.OnClickItem() {
            @Override
            public void OnClickItem(int position) {
                dialog.dismiss();
                roleId = mStatusList.get(position).getRoleId();
                mTxtRole.setText(mStatusList.get(position).getStatusName()+"");
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

                      birthday = DateManagement.fortmatIntToDate(year)+"-"+DateManagement.fortmatIntToDate(monthOfYear+1)+"-"+DateManagement.fortmatIntToDate(dayOfMonth);
                    mTxtBirthDay.setText(birthday);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    private void showChooseGenderDialog(){
        final Dialog dialog = new Dialog(AdminCreateManagerActivity.this);
        dialog.setContentView(R.layout.alert_dialog_choose_status);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mRecyclerView1 = dialog.findViewById(R.id.rcv_choose_status);
        TextView mTextView = dialog.findViewById(R.id.txt_choose_dialog);
        mTextView.setText("Choose Gender");
        LinearLayout mBtnClose = dialog.findViewById(R.id.lnl_close_dialog_choose_status);
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView1.setLayoutManager(layoutManager);
        mLocationAdapter1 = new ChooseStatusAdapter(AdminCreateManagerActivity.this,mStatusList1);
        mRecyclerView1.setAdapter(mLocationAdapter1);
        mLocationAdapter1.OnClickItemListener(new ChooseStatusAdapter.OnClickItem() {
            @Override
            public void OnClickItem(int position) {
                dialog.dismiss();
                genderId = mStatusList1.get(position).getRoleId();
                mtxtGender.setText(mStatusList1.get(position).getStatusName()+"");
            }
        });
        dialog.show();
    }
    private void createUser(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(mTxtUsername.getText().toString());
        userDTO.setPassword(mTxtPassword.getText().toString());
        userDTO.setPhone(mTxtPhone.getText().toString());
        userDTO.setDateOfBirth(birthday);
        userDTO.setGender(genderId);
        userDTO.setRoleId(roleId);
        mCreateUserPresenter.createUser(token,userDTO);
    }
        @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_admin_add_manager:
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
                break;
            case R.id.lnl_submit_create_manager:
                createUser();
                break;
            case R.id.edt_choose_birthday_add__manager_admin:
                chooseTimeBirthDay();
                break;
            case  R.id.edt_choose_role_add__manager_admin:
                showChooseRoleDialog();
                break;
            case R.id.edt_choose_gender_add__manager_admin:
                showChooseGenderDialog();
                break;
        }
    }
    @Override
    public void getUserSuccess(UserDTO userDTO) {
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(AdminCreateManagerActivity.this,message);
    }
}
