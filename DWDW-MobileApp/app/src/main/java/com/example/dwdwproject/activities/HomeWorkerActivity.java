package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

public class HomeWorkerActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout mLnlAccountWorker, mLnlReportWorker, mLnlShiftWorker;
    private LinearLayout mBtnLogOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_worker);
        initVieẉ̣();
        initData();
    }

    private void initVieẉ̣() {
        mBtnLogOut = findViewById(R.id.lnl_log_out_worker);
        mLnlAccountWorker = findViewById(R.id.lnl_manage_account_worker);
        mLnlReportWorker = findViewById(R.id.lnl_manage_report_worker);
        mLnlShiftWorker = findViewById(R.id.lnl_manage_shift_worker);
    }

    private void initData() {
        mLnlAccountWorker.setOnClickListener(this);
        mLnlShiftWorker.setOnClickListener(this);
        mLnlReportWorker.setOnClickListener(this);
        mBtnLogOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.lnl_manage_account_worker:
                intentToProfileActivty();
                break;
            case R.id.lnl_manage_report_worker:
                intentToReportWorkerActivty();
                break;
            case R.id.lnl_manage_shift_worker:
                intentToShiftWorkerActivty();
                break;
            case R.id.lnl_log_out_worker:
                showLogoutDialog();
                break;
        }
    }
    private void intentToProfileActivty() {
        Intent intent = new Intent(HomeWorkerActivity.this, ProfileManageActivity.class);
        startActivity(intent);
    }
    private void intentToReportWorkerActivty() {
        Intent intent = new Intent(HomeWorkerActivity.this,WorkerReportActivity.class);
        startActivity(intent);
    }
    private void intentToShiftWorkerActivty() {
        Intent intent = new Intent(HomeWorkerActivity.this, WorkerShiftViewActivity.class);
        startActivity(intent);
    }

    private void showLogoutDialog() {
        final Dialog dialog = new Dialog(HomeWorkerActivity.this);
        dialog.setContentView(R.layout.alert_dialog_notify_sign_out);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button buttonOk = dialog.findViewById(R.id.btn_yes);
        Button buttonNo = dialog.findViewById(R.id.btn_no);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intentToLogOutActivity();

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

    private void intentToLogOutActivity(){
        Intent intent = new Intent(HomeWorkerActivity.this,LoginActivity.class);
        startActivity(intent);
    }

}