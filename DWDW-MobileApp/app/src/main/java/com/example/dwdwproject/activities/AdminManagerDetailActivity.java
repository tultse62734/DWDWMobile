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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.Role;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.adapters.ChooseLocationAdapter;
import com.example.dwdwproject.adapters.ChooseStatusAdapter;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Status;
import com.example.dwdwproject.presenters.userPresenters.CreateUserPresenter;
import com.example.dwdwproject.presenters.userPresenters.UpdateUserPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.userViews.GetUserView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
public class AdminManagerDetailActivity extends AppCompatActivity implements View.OnClickListener , GetUserView {
    private LinearLayout mBtnClose,mBtnUpdateManager;
    private EditText mTxtUsername,mTxtPhone;
    private TextView mTxtBirthDay,mTxtRole,mtxtGender;
    private String token;
    private RecyclerView mRecyclerView;
    private RecyclerView mRecyclerView1;
    private ChooseStatusAdapter mLocationAdapter;
    private ChooseStatusAdapter mLocationAdapter1;
    private List<Status> mStatusList;
    private String birthday;
    private int roleId,genderId;
    private int mYear,mMonth,mDay;
    private List<Status> mStatusList1;
    private UserDTO mUserDTO;
    private UpdateUserPresenter mUpdateUserPresenter;
    private LinearLayout mBtnUpdateStatus;
    private ImageView mImgUpdateStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_manager_detail);
        initView();
        initData();
    }
    private  void initView(){
        Bundle bundle = getIntent().getExtras();
        mUserDTO = (UserDTO)  bundle.getSerializable(BundleString.MANAGERDETAIL);
        mBtnClose = findViewById(R.id.lnl_close_admin_manager_detail);
        mBtnUpdateManager = findViewById(R.id.lnl_submit_update_user_admin);
        token = SharePreferenceUtils.getStringSharedPreference(AdminManagerDetailActivity.this, BundleString.TOKEN);
        mTxtUsername = findViewById(R.id.edit_update_username);
        mBtnUpdateStatus = findViewById(R.id.lnl_btn_update_status_user);
        mImgUpdateStatus = findViewById(R.id.image_btn_update_status_user);
        mTxtPhone = findViewById(R.id.edit_update_phone);
        mTxtBirthDay = findViewById(R.id.edt_choose_date_update_manager_admin);
        mTxtRole = findViewById(R.id.edt_choose_role_update_manager_admin);
        mtxtGender = findViewById(R.id.edt_choose_gender_update_manager_admin);
    }
    private void initData(){
        if(mUserDTO.isActive()){
            mImgUpdateStatus.setImageResource(R.mipmap.ic_minus_1);
        }else{
            mImgUpdateStatus.setImageResource(R.mipmap.ic_tick);
        }
        mTxtUsername.setText(mUserDTO.getFullname());
        mTxtPhone.setText(mUserDTO.getPhone());
        if(mUserDTO.getDateOfBirth()!=null){
            birthday = mUserDTO.getDateOfBirth()+"";
            mTxtBirthDay.setText(birthday);
        }
        if(mUserDTO.getGender()==1){
            mtxtGender.setText("Women");
            genderId = 1;
        }else if(mUserDTO.getGender()==2){
            genderId = 2;
            mtxtGender.setText("Men");
        }else{
            genderId = 0;
            mtxtGender.setText("Choose Gender");
        }
        roleId= mUserDTO.getmRole().getRoleId();
        mTxtRole.setText(mUserDTO.getmRole().getRoleName());
        mUpdateUserPresenter = new UpdateUserPresenter(AdminManagerDetailActivity.this,this);
        mStatusList = new ArrayList<>();
        mStatusList1 = new ArrayList<>();
        mStatusList.add(new Status("Manager ",true,2));
        mStatusList.add(new Status("Worker",false,3));
        mStatusList1.add(new Status("Women",true,1));
        mStatusList1.add(new Status("Men",false,2));
        mBtnClose.setOnClickListener(this);
        mBtnUpdateManager.setOnClickListener(this);
        mtxtGender.setOnClickListener(this);
        mTxtBirthDay.setOnClickListener(this);
        mTxtRole.setOnClickListener(this);
        mBtnUpdateStatus.setOnClickListener(this);
    }
    private void showChooseRoleDialog(){
        final Dialog dialog = new Dialog(AdminManagerDetailActivity.this);
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
        mLocationAdapter = new ChooseStatusAdapter(AdminManagerDetailActivity.this,mStatusList);
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
        final Dialog dialog = new Dialog(AdminManagerDetailActivity.this);
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
        mLocationAdapter1 = new ChooseStatusAdapter(AdminManagerDetailActivity.this,mStatusList1);
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
    private void updateUser(){
        if(genderId!=0){
            mUserDTO.setFullname(mTxtUsername.getText().toString());
            mUserDTO.setPhone(mTxtPhone.getText().toString());
            mUserDTO.setDateOfBirth(birthday);
            mUserDTO.setGender(genderId);
            Role role = new Role();
            role.setRoleId(roleId);
            mUserDTO.getmRole().setRoleId(roleId);
            mUpdateUserPresenter.updateUser(token,mUserDTO);
        }
        else {
            DialogNotifyError.showErrorLoginDialog(AdminManagerDetailActivity.this,"Choose Gender First");
        }

    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_admin_manager_detail:
                finish();
                break;
            case R.id.lnl_submit_update_user_admin:
                updateUser();
                break;
            case R.id.edt_choose_gender_update_manager_admin:
                showChooseGenderDialog();
                break;
            case R.id.edt_choose_role_update_manager_admin:
                showChooseRoleDialog();
                break;
            case R.id.edt_choose_date_update_manager_admin:
                chooseTimeBirthDay();
                break;
            case R.id.lnl_btn_update_status_user:
                updateUserStatus();
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
        DialogNotifyError.showErrorLoginDialog(AdminManagerDetailActivity.this,message);
    }

    private void updateUserStatus(){
            if(mUserDTO.isActive()){
                mUpdateUserPresenter.updateUseStatus(token,mUserDTO.getUserId(),false);
            }else{
                mUpdateUserPresenter.updateUseStatus(token,mUserDTO.getUserId(),true);
            }

    }
}
