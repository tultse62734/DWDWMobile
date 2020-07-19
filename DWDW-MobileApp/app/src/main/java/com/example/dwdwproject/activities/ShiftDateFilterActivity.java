package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.example.dwdwproject.R;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DateManagement;
import com.example.dwdwproject.utils.SharePreferenceUtils;

import java.util.Calendar;
public class ShiftDateFilterActivity extends AppCompatActivity implements com.borax12.materialdaterangepicker.date.DatePickerDialog.OnDateSetListener{
    private RadioGroup mRadioGroup;
    private RadioButton mRadioButton;
    private RadioButton mRadioButtonOption;
    private Button mBtnDone;
    private LinearLayout mBtnClose;
    private String mFilterDate;
    private String result ;
    private String mPickDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_date_filter);
        initView();
        initData();
    }
    private void initView(){
        mRadioButtonOption = (RadioButton) findViewById(R.id.rdOption);
        mRadioButtonOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickOnRdOption();
            }
        });
        mRadioGroup = findViewById(R.id.rdGroup);
        mBtnClose = findViewById(R.id.lnl_close_manage_shift_filter);
        mBtnDone = findViewById(R.id.button_done);
        mBtnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doneClick();
            }
        });
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void clickOnRdOption() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = com.borax12.materialdaterangepicker.date.DatePickerDialog.newInstance(
                ShiftDateFilterActivity.this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show(getFragmentManager(), "Datepickerdialog");

    }

    @Override
    public void onDateSet(com.borax12.materialdaterangepicker.date.DatePickerDialog view, int year, int month, int day, int yearEnd, int monthEnd, int dayEnd) {
        String starDay = DateManagement.fortmatIntToDate(day) + "-" + DateManagement.fortmatIntToDate((++month)) + "-" + year;
        String endDay = DateManagement.fortmatIntToDate(dayEnd) + "-" + DateManagement.fortmatIntToDate((++monthEnd)) + "-" + yearEnd;
        if (DateManagement.isDateAfter(starDay, endDay)) {
            mPickDate = starDay + "-" + endDay;
        } else {
            showFilterDateDialog("Vui lòng chọn lại ngày!!!");
        }
    }
    private void showFilterDateDialog(String message) {
        final Dialog dialog = new Dialog(ShiftDateFilterActivity.this);
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
    private void doneClick() {
        int selectedId = mRadioGroup.getCheckedRadioButtonId();
        if (mRadioGroup.getCheckedRadioButtonId() == mRadioButtonOption.getId()) {
            if (mPickDate == null) {
                mFilterDate = BundleString.getSelectedDate(this);
            } else {
                mFilterDate = mPickDate;
            }
        } else if (mFilterDate == null) {
            mRadioButton = findViewById(mRadioGroup.getCheckedRadioButtonId());
            if (mRadioButton != null) {
                mFilterDate = (String) mRadioButton.getText().toString();
            } else {
                mFilterDate = SharePreferenceUtils.getStringSharedPreference(this, BundleString.FILTER_DATE_IS_SELECTE);
            }
        }
        if (mFilterDate.equalsIgnoreCase("Hôm nay")) {
            result = DateManagement.getToday();
        } else if (mFilterDate.equalsIgnoreCase("Hôm qua")) {
            result = DateManagement.getYesterDay();
        } else if (mFilterDate.equalsIgnoreCase("Tháng trước")) {
            result = DateManagement.getStartPreviosMonthDateString() + "- " + DateManagement.getEndPreviosMonthDateString();
        } else if (mFilterDate.equalsIgnoreCase("Tháng này")) {
            result = DateManagement.getStartThisMonthDateString() + "- " + DateManagement.getEndThisMonthDateString();
        } else if (mFilterDate.equalsIgnoreCase("Tuần trước")) {
            result = DateManagement.getStartPreviosWeekDateString() + "- " + DateManagement.getEndPreviosWeekDateString();
        } else if (mFilterDate.equalsIgnoreCase("Tuần này")) {
            result = DateManagement.getStartThisWeekDateString() + "- " + DateManagement.getEndThisWeekDateString();
        }
        SharePreferenceUtils.saveStringSharedPreference(this, BundleString.FILTER_DATE_IS_SELECTE, result);
        finish();
    }
    private void initData(){

    }
}
