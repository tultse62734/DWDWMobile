package com.example.dwdwproject.activities;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.RecordDTO;
import com.example.dwdwproject.adapters.AccidentAdapter;
import com.example.dwdwproject.models.Accident;
import com.example.dwdwproject.presenters.recordsPresenters.GetRecordsByLocationIdPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.recordsViews.GetAllRecordsView;

import java.util.ArrayList;
import java.util.List;
public class PageAccidentFragment extends Fragment implements GetAllRecordsView {
    private View mView;
    private List<Accident> mAccidentList;
    private RecyclerView mRecyclerView;
    private AccidentAdapter mAccidentAdapter;
    private GetRecordsByLocationIdPresenter mRecordsByLocationIdPresenter;
    private int locationId;
    private String locationName;
    private String token;
    public PageAccidentFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationId = getArguments().getInt(BundleString.LOCATIONID);
        locationName = getArguments().getString(BundleString.LOCATIONNAME);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_page_accident, container, false);
        return mView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }
    // TODO: Rename method, update argument and hook method into UI event
    private void initView(){
        mRecyclerView = mView.findViewById(R.id.rcv_accident_admin);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        token = SharePreferenceUtils.getStringSharedPreference(getContext(), BundleString.TOKEN);
        mRecordsByLocationIdPresenter = new GetRecordsByLocationIdPresenter(getContext(),this);
        mRecordsByLocationIdPresenter.getRecordsByLocationId(token,locationId);
    }
    private void updateUI(){
        if(mAccidentAdapter == null){
            mAccidentAdapter = new AccidentAdapter(getContext(),mAccidentList);
            mRecyclerView.setAdapter(mAccidentAdapter);
            mAccidentAdapter.onItemClick(new AccidentAdapter.OnItemCkickListerner() {
                @Override
                public void onItemClick(int pos) {
                    Intent intent = new Intent(getContext(),AccidentReportDetailActivity.class);
                    startActivity(intent);
                }
            });
        }else {
            mAccidentAdapter.notifyDataSetChanged();
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
                        String recordName = "Accident" +i;
                        boolean isActive = true;
                        mAccidentList.add(new Accident(recordId,recordName,locationname,recordDate,image,isActive));
                }
                updateUI();
            }
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(getContext(),message);
    }
    public void reloadPage(){
        token = SharePreferenceUtils.getStringSharedPreference(getContext(), BundleString.TOKEN);
        mRecordsByLocationIdPresenter = new GetRecordsByLocationIdPresenter(getContext(),this);
        mRecordsByLocationIdPresenter.getRecordsByLocationId(token,locationId);
    }
}
