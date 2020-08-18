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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO1;
import com.example.dwdwproject.adapters.ChooseStatusAdapter;
import com.example.dwdwproject.models.Status;
import com.example.dwdwproject.presenters.GetUserInforTokenPresenter;
import com.example.dwdwproject.presenters.userPresenters.UpdateUserPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.CheckVaildateEditTexxt;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.GetUserInforTokenView;
import com.example.dwdwproject.views.userViews.GetUserView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
public class UpdateInforAccountActivity extends AppCompatActivity implements View.OnClickListener, GetUserInforTokenView {
    private LinearLayout mBtnClose,mBtnUpdateManager;
    private EditText mTxtPassword,mTxtPhone;
    private TextView mTxtBirthDay,mTxtRole,mtxtGender;
    private String token;
    private RecyclerView mRecyclerView1;
    private ChooseStatusAdapter mLocationAdapter1;
    private String password,phone,birthday;
    private int genderId;
    private int mYear,mMonth,mDay;
    private List<Status> mStatusList1;
    private UserDTO1 mUserDTO;
    private GetUserInforTokenPresenter mGetUserInforTokenPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_infor_account);
        initView();
        initData();
    }
    private void initView(){
        mBtnClose = findViewById(R.id.lnl_close_update_account);
        mBtnUpdateManager = findViewById(R.id.lnl_submit_update_account);
        mTxtPassword = (EditText)findViewById(R.id.edit_update_password_account);
        mTxtPhone = (EditText)findViewById(R.id.edit_update_phone_account);
        mTxtBirthDay = (TextView)findViewById(R.id.edt_choose_date_update_account);
        mTxtRole = (TextView)findViewById(R.id.edt_choose_role_update_account);
        mtxtGender = (TextView)findViewById(R.id.edt_choose_gender_update_account);
    }
    private void initData(){
        token = SharePreferenceUtils.getStringSharedPreference(UpdateInforAccountActivity.this, BundleString.TOKEN);
        Bundle bundle = getIntent().getExtras();
        mUserDTO = (UserDTO1) bundle.getSerializable(BundleString.ACCOUNT);
        phone = mUserDTO.getPhone()+"";
        mTxtPhone.setText(phone);
        if(mUserDTO.getDateOfBirth()!=null){
            birthday = mUserDTO.getDateOfBirth();
            mTxtBirthDay.setText(DateManagement.changeFormatDate1(birthday));
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
        mTxtPassword.setText(mUserDTO.getFullName());
        mTxtRole.setText(mUserDTO.getRoleName());
        mGetUserInforTokenPresenter = new GetUserInforTokenPresenter(UpdateInforAccountActivity.this,this);
        mStatusList1 = new ArrayList<>();
        mStatusList1.add(new Status("Women",true,1));
        mStatusList1.add(new Status("Men",false,2));
        mBtnClose.setOnClickListener(this);
        mBtnUpdateManager.setOnClickListener(this);
        mtxtGender.setOnClickListener(this);
        mTxtBirthDay.setOnClickListener(this);
        mTxtRole.setOnClickListener(this);
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
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());

        datePickerDialog.show();
    }
    private void showChooseGenderDialog(){
        final Dialog dialog = new Dialog(UpdateInforAccountActivity.this);
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
        mLocationAdapter1 = new ChooseStatusAdapter(UpdateInforAccountActivity.this,mStatusList1);
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
        if(CheckVaildateEditTexxt.checkEditTextNull(mTxtPassword.getText().toString())){
            DialogNotifyError.showErrorLoginDialog(UpdateInforAccountActivity.this,"FullName is't blank");
        }
        else if(!CheckVaildateEditTexxt.checkValidatePhone(mTxtPhone.getText().toString())){
            DialogNotifyError.showErrorLoginDialog(UpdateInforAccountActivity.this,"Not Valid Number Phone");
        }else if(CheckVaildateEditTexxt.checkEditTextNull(birthday)){
            DialogNotifyError.showErrorLoginDialog(UpdateInforAccountActivity.this,"Must to choose birday");
        }
        else if(genderId==0){
            DialogNotifyError.showErrorLoginDialog(UpdateInforAccountActivity.this,"Choose Gender First");
        }
        else {
            mUserDTO.setFullName(mTxtPassword.getText().toString());
            mUserDTO.setPhone(mTxtPhone.getText().toString());
            mUserDTO.setDateOfBirth(birthday);
            mUserDTO.setGender(genderId);
            mGetUserInforTokenPresenter.updateInfor(token,mUserDTO);
        }
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.lnl_close_update_account:
                finish();
                break;
            case R.id.lnl_submit_update_account:
                updateUser();
                break;
            case R.id.edt_choose_gender_update_account:
                showChooseGenderDialog();
                break;
            case R.id.edt_choose_date_update_account:
                chooseTimeBirthDay();
                break;
        }
    }
    @Override
    public void getInforSuccess(UserDTO1 mUserDTO) {
        finish();
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(UpdateInforAccountActivity.this,message);
    }
}