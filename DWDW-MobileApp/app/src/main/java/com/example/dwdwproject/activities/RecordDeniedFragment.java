package com.example.dwdwproject.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.adapters.AccidentAdapter;
import com.example.dwdwproject.models.Accident;
import com.example.dwdwproject.presenters.recordsPresenters.GetRecordByManager;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.recordsViews.GetAllRecordsView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecordDeniedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordDeniedFragment extends Fragment implements GetAllRecordsView {
    private View mView;
    private List<Accident> mAccidentList;
    private RecyclerView mRecyclerView;
    private AccidentAdapter mAccidentAdapter;
    private GetRecordByManager mIdAndTimePresenter;
    private int locationId;
    private String locationName;
    private String token;
    private String date = "";
    private String time,day;
    private String mStartTime,mEndTime;
    private int workerId;
    public RecordDeniedFragment() {
        // Required empty public constructor
    }

    public static RecordSleepFragment newInstance() {
        RecordSleepFragment fragment = new RecordSleepFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_record_denied, container, false);
        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }
    private void initView(){
        mRecyclerView = mView.findViewById(R.id.rcv_accident_admin);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mIdAndTimePresenter = new GetRecordByManager(getContext(),this);
        String selectDay = BundleString.getSelectedDate(getContext());
        checkTypeFilterDate(selectDay);
        token = SharePreferenceUtils.getStringSharedPreference(getContext(), BundleString.TOKEN);
        mIdAndTimePresenter.getSleepRecordByLocationManager(token,workerId,locationId,date);
    }
    private void updateUI(){
        if(mAccidentAdapter == null){
            mAccidentAdapter = new AccidentAdapter(getContext(),mAccidentList);
            mRecyclerView.setAdapter(mAccidentAdapter);
            mAccidentAdapter.onItemClick(new AccidentAdapter.OnItemCkickListerner() {
                @Override
                public void onItemClick(int pos) {
                    Intent intent = new Intent(getContext(),AccidentReportDetailActivity.class);
                    Bundle bundle = new Bundle();
                    mAccidentList.get(pos).setImage(mAccidentList.get(pos).getImage());
                    bundle.putSerializable(BundleString.RECORDDETAIL,mAccidentList.get(pos));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });
        }else {
            mAccidentAdapter.notify(mAccidentList);
        }
    }
    private String parseBitmapToBytes(){
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.bg_login);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray.toString();
    }
    @Override
    public void getAllRecordSuccess(List<RecordDTO> mRecordDTOList) {
        if(mRecordDTOList!=null){
            mAccidentList = new ArrayList<>();
            for (int i = 0; i <mRecordDTOList.size() ; i++) {
                int recordId= mRecordDTOList.get(i).getRecordId();
                String image  =mRecordDTOList.get(i).getImage();
                String locationname = locationName;
                String recordDate = splitFromDay(mRecordDTOList.get(i).getRecordDateTime());
                String recordTime = splitFromToTime(mRecordDTOList.get(i).getRecordDateTime());
                String recordName = "Accident" +i;
                String roomCode = mRecordDTOList.get(i).getRoomCode();
                String status = mRecordDTOList.get(i).getStatus();
                mAccidentList.add(new Accident(recordId,recordName,recordDate,recordTime,locationname,image,roomCode,status));
            }
            updateUI();
        }
    }
    @Override
    public void showError(String message) {
    }
    public void reloadPage() {
        token = SharePreferenceUtils.getStringSharedPreference(getContext(), BundleString.TOKEN);
        mIdAndTimePresenter.getSleepRecordByLocationManager(token,workerId ,locationId, date);
    }
    private String splitToStartDayAndEndDay(String day) {
        String days = "";
        String[] tmp = day.split("- ");
        days = tmp[1];
        return days;
    }
    private String splitToStartDayAndEndDay1(String day) {
        String days = "";
        String[] tmp = day.split("- ");
        days = tmp[0];
        return days;
    }
    private String splitFromDay(String filterDate) {
        String[] tmp = filterDate.split("T");
        day = tmp[0];
        return day;
    }
    private String splitFromToTime(String filterDate) {
        String[] tmp = filterDate.split("T");
        time = tmp[1];
        return time;
    }
    private void checkTypeFilterDate(String filterDate) {
        if (filterDate.contains(", ")) {
            splitFromToDay(filterDate);
        }else {
            mStartTime = splitToStartDayAndEndDay1(filterDate);
            mEndTime = splitToStartDayAndEndDay(filterDate);
        }
    }
    private void splitFromToDay(String filterDate) {
        String[] tmp = filterDate.split(", ");
        mStartTime = tmp[1];
        mEndTime = tmp[1];
    }
}