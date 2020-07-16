package com.example.dwdwproject.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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

import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.LocationDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.adapters.ManageAdapter;
import com.example.dwdwproject.models.Manager;
import com.example.dwdwproject.presenters.locationsPresenters.GetAllLocationPresenter;
import com.example.dwdwproject.presenters.userPresenters.GetAllUserFromLocationByAdPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.views.locationsViews.GetAllLocatonView;
import com.example.dwdwproject.views.roomLocalViews.GetInfoUserView;
import com.example.dwdwproject.views.userViews.GetAllListUserView;

import java.util.ArrayList;
import java.util.List;

import in.myinnos.alphabetsindexfastscrollrecycler.IndexFastScrollRecyclerView;

public class PageManagerFragment extends Fragment implements GetAllListUserView {
    private View mView;
    private List<Manager> mManagerList;
    private IndexFastScrollRecyclerView mRecyclerView;
    private ManageAdapter manageAdapter;
    private GetAllUserFromLocationByAdPresenter mAdPresenter;
    private int locationId;
    public PageManagerFragment() {
    }
        @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationId = getArguments().getInt(BundleString.LOCATIONID);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_page_manager, container, false);
        return  mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }

    // TODO: Rename method, update argument and hook method into UI event
    private void initView(){
        mRecyclerView = mView.findViewById(R.id.fast_scroller_recycler);
        mRecyclerView.setIndexBarCornerRadius(3);
        mRecyclerView.setIndexBarTransparentValue((float) 0.4);
        mRecyclerView.setIndexbarMargin(4);
        mRecyclerView.setIndexBarColor("#FF9800");
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);

    }
    private void initData(){
//        mManagerList = new ArrayList<>();
//        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","A","01224959623","tultse62734@fpt.edu.vn"));
//        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","B","01224959623","tultse62734@fpt.edu.vn"));
//        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","C","01224959623","tultse62734@fpt.edu.vn"));
//        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","D","01224959623","tultse62734@fpt.edu.vn"));
//        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","E","01224959623","tultse62734@fpt.edu.vn"));
//        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","M","01224959623","tultse62734@fpt.edu.vn"));
//        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","N","01224959623","tultse62734@fpt.edu.vn"));
//        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","T","01224959623","tultse62734@fpt.edu.vn"));
//        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","U","01224959623","tultse62734@fpt.edu.vn"));
//        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","V","01224959623","tultse62734@fpt.edu.vn"));
//        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","Y","01224959623","tultse62734@fpt.edu.vn"));
//        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","Z","01224959623","tultse62734@fpt.edu.vn"));
//        mManagerList.add(new Manager("https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg","K","01224959623","tultse62734@fpt.edu.vn"));
//        updateUI();
        mAdPresenter = new GetAllUserFromLocationByAdPresenter(getContext(),getActivity().getApplication(),this);
        mAdPresenter.AdminGetAllUserfromLocationToken(locationId);
    }
    private void updateUI(){
        if(manageAdapter ==null){
            manageAdapter = new ManageAdapter(getContext(), mManagerList);
            mRecyclerView.setAdapter(manageAdapter);
            manageAdapter.OnItemClickListener(new ManageAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int pos) {
                    Intent intent = new Intent(getContext(),AdminManagerDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleString.MANAGERDETAIL,mManagerList.get(pos));
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

        }else {
            manageAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getAllUserSuccess(List<UserDTO> userDTOList) {
        if(userDTOList!=null){
            this.mManagerList = new ArrayList<>();
            for (int i = 0; i <userDTOList.size() ; i++) {
                String image = "https://secure.img1-fg.wfcdn.com/im/02238154/compr-r85/8470/84707680/pokemon-pikachu-wall-decal.jpg";
                String name  = userDTOList.get(i).getUserName();
                String phone = userDTOList.get(i).getPhone();
                String dateOfBirth = userDTOList.get(i).getDateOfBirth();
                mManagerList.add(new Manager(image,name,phone,dateOfBirth));
            }
            updateUI();
        }
    }

    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(getContext(),"Data is error");
    }

}
