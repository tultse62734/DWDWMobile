package com.example.dwdwproject.activities;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.ResponseDTOs.ShiftDTO;
import com.example.dwdwproject.adapters.AccidentAdapter;
import com.example.dwdwproject.models.Accident;
import com.example.dwdwproject.presenters.recordsPresenters.GetRecordsByLocationIdPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.ParseBytes;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.recordsViews.GetAllRecordsView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
public class ManageAccidentActivity extends AppCompatActivity implements View.OnClickListener, GetAllRecordsView {
    private List<Accident> mAccidentList;
    private RecyclerView mRecyclerView;
    private AccidentAdapter mAccidentAdapter;
    private LinearLayout mBtnClose;
    private String token,locationName,date;
    private ShiftDTO mShiftDTO;
    private List<RecordDTO> mRecordDTO;
    private int locationId;
    private String day,time;
    private GetRecordsByLocationIdPresenter mGetRecordsByLocationIdPresenter;
    private TextView mTxtTilte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_accident);
        initView();
        initData();
    }
    private void initView(){
        mTxtTilte = findViewById(R.id.txt_title_report);
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
        mTxtTilte.setText(locationName);
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
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(BundleString.RECORDDETAIL,mAccidentList.get(pos));
                        intent.putExtras(bundle);
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
            mRecordDTO = new ArrayList<>();
            mRecordDTO = mRecordDTOList;
            for (int i = 0; i <mRecordDTOList.size() ; i++) {
                int recordId= mRecordDTOList.get(i).getRecordId();
                String image  =mRecordDTOList.get(i).getImage();
                String locationname = locationName;
                String recordDate = splitFromToDay(mRecordDTOList.get(i).getRecordDateTime());
                String recordTime = splitFromToTime(mRecordDTOList.get(i).getRecordDateTime());
                String recordName = mShiftDTO.getFullname();
                String roomCode = mShiftDTO.getRoomCode();
                String status = mRecordDTOList.get(i).getStatus();
                mAccidentList.add(new Accident(recordId,recordName,recordDate,recordTime,locationname,image,roomCode,status));
            }
            updateUI();
        }
    }
    private String splitFromToDay(String filterDate) {
        String[] tmp = filterDate.split("T");
        day = tmp[0];
        return day;
    }
    private String splitFromToTime(String filterDate) {
        String[] tmp = filterDate.split("T");
        time = tmp[1];
        return time;
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(ManageAccidentActivity.this,message);
    }
}