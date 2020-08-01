package com.example.dwdwproject.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.adapters.AccidentAdapter;
import com.example.dwdwproject.models.Accident;
import com.example.dwdwproject.presenters.recordsPresenters.GetRecordsByLocationIdPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.recordsViews.GetAllRecordsView;

import java.util.ArrayList;
import java.util.List;
public class ManageAccidentActivity extends AppCompatActivity implements View.OnClickListener, GetAllRecordsView {
    private List<Accident> mAccidentList;
    private RecyclerView mRecyclerView;
    private AccidentAdapter mAccidentAdapter;
    private LinearLayout mBtnClose;
    private String token,locationName,date;
    private ShiftDTO mShiftDTO;
    private int locationId;

    private GetRecordsByLocationIdPresenter mGetRecordsByLocationIdPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_accident);
        initView();
        initData();
    }
    private void initView(){
        mRecyclerView = findViewById(R.id.rcv_accident);
        mBtnClose = findViewById(R.id.lnl_close_manage_accident);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        Bundle bundle = getIntent().getExtras();
        mShiftDTO = (ShiftDTO) bundle.getSerializable("WORKERRECORD");
        mBtnClose.setOnClickListener(this);
        date = BundleString.getSelectedDate(ManageAccidentActivity.this);
        token = SharePreferenceUtils.getStringSharedPreference(ManageAccidentActivity.this, BundleString.TOKEN);
        locationId = SharePreferenceUtils.getIntSharedPreference(ManageAccidentActivity.this,BundleString.LOCATIONID);
        locationName  = SharePreferenceUtils.getStringSharedPreference(ManageAccidentActivity.this,BundleString.LOCATIONNAME);
        mGetRecordsByLocationIdPresenter = new GetRecordsByLocationIdPresenter(ManageAccidentActivity.this,this);
        mGetRecordsByLocationIdPresenter.getRecordByWorkerDate(token,mShiftDTO.getWorkerId(),date);
        }
        private void updateUI(){
            if(mAccidentAdapter == null){
                mAccidentAdapter = new AccidentAdapter(ManageAccidentActivity.this,mAccidentList);
                mRecyclerView.setAdapter(mAccidentAdapter);
                mAccidentAdapter.onItemClick(new AccidentAdapter.OnItemCkickListerner() {
                    @Override
                    public void onItemClick(int pos) {
                        Intent intent = new Intent(ManageAccidentActivity.this,AccidentReportDetailActivity.class);
                        startActivity(intent);
                    }
                });
            }else {
                mAccidentAdapter.notifyDataSetChanged();
            }
        }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.lnl_close_manage_accident:
                finish();
                break;
        }
    }
    @Override
    public void getAllRecordSuccess(List<RecordDTO> mRecordDTOList) {
        if(mRecordDTOList!=null){
            mAccidentList = new ArrayList<>();
            for (int i = 0; i <mRecordDTOList.size() ; i++) {
                int recordId= mRecordDTOList.get(i).getRecordId();
                String image  =mRecordDTOList.get(i).getImage();
                String locationname = locationName;
                String recordDate = mRecordDTOList.get(i).getRecordDateTime();
                String recordName = mShiftDTO.getUsername();
                String roomCode = mShiftDTO.getRoomCode();
                boolean isActive = true;
                mAccidentList.add(new Accident(recordId,recordName,locationname,recordDate,image,roomCode,isActive));
            }
            updateUI();
        }
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(ManageAccidentActivity.this,message);
    }
}