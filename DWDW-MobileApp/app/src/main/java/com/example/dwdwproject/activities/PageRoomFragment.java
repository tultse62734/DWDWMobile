package com.example.dwdwproject.activities;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.dwdwproject.R;
import com.example.dwdwproject.ResponseDTOs.RoomDTO;
import com.example.dwdwproject.ResponseDTOs.UserDTO;
import com.example.dwdwproject.adapters.RoomAdapter;
import com.example.dwdwproject.models.Room;
import com.example.dwdwproject.presenters.roomPresenters.GetAllRoomFromLocationPresenter;
import com.example.dwdwproject.presenters.roomPresenters.UpdateRoomPresenter;
import com.example.dwdwproject.utils.BundleString;
import com.example.dwdwproject.utils.DialogNotifyError;
import com.example.dwdwproject.utils.SharePreferenceUtils;
import com.example.dwdwproject.views.roomViews.GetListRoomView;
import com.example.dwdwproject.views.roomViews.GetRoomView;
import com.example.dwdwproject.views.userViews.GetUserView;

import java.util.ArrayList;
import java.util.List;
public class PageRoomFragment extends Fragment implements GetListRoomView , GetRoomView {
    private View mView;
    private List<Room> mRoomList;
    private RecyclerView mRecyclerView;
    private GetAllRoomFromLocationPresenter mRoomFromLocationPresenter;
    private RoomAdapter mRoomAdapter;
    private int locationId;
    private SearchView mSearchView;
    private String token ;
    private List<RoomDTO> mRoomDTOS;
    private UpdateRoomPresenter mUpdateRoomPresenter;
    public PageRoomFragment() {
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
        mView =  inflater.inflate(R.layout.fragment_page_room, container, false);
        return  mView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
    }
    private void initView(){
        mSearchView = mView.findViewById(R.id.search_view_page_room);
        mRecyclerView = mView.findViewById(R.id.rcv_room);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
    }
    private void initData(){
        mUpdateRoomPresenter = new UpdateRoomPresenter(getContext(),this);
//        mRoomList = new ArrayList<>();
//        mRoomList.add(new Room(1,"100","12-12-2020",true));
//        mRoomList.add(new Room(2,"200","12-12-2020",false));
//        mRoomList.add(new Room(3,"300","12-12-2020",false));
//        mRoomList.add(new Room(4,"400","12-12-2020",true));
//        mRoomList.add(new Room(5,"500","12-12-2020",true));
//        mRoomList.add(new Room(6,"600","12-12-2020",true));
//        updateUI();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(mRoomAdapter!=null){
                    mRoomAdapter.getFilter().filter(newText);
                }
                return false;
            }
        });
        token =  SharePreferenceUtils.getStringSharedPreference(getContext(),BundleString.TOKEN);
        mRoomFromLocationPresenter = new GetAllRoomFromLocationPresenter(getContext(),this);
        mRoomFromLocationPresenter.getAllRoomFromLocation(token,locationId);
    }
    private void updateUI(){
        if(mRoomAdapter == null){
            mRoomAdapter = new RoomAdapter(getContext(),mRoomList);
            mRecyclerView.setAdapter(mRoomAdapter);
            mRoomAdapter.OnItemClickListerner(new RoomAdapter.OnItemClickListerner() {
                @Override
                public void onItemClick(int pos) {
                    Intent intent = new Intent(getContext(),AdminRoomDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(BundleString.ROOMDETAIL,mRoomDTOS.get(pos));
                    intent.putExtras(bundle);
                    getActivity().startActivityForResult(intent,ManageRoomActivity.UPDATE_ROOM_CODE);
                }
            });
            mRoomAdapter.OnItemActiveClickListerner(new RoomAdapter.OnItemActiveClickListerner() {
                @Override
                public void onItemActiveClick(int pos) {
                    if(mRoomDTOS.get(pos).isActive()){
                        mRoomDTOS.get(pos).setActive(false);
                        mUpdateRoomPresenter.updateRoomStatus(token,mRoomDTOS.get(pos));
                    }else{
                        mRoomDTOS.get(pos).setActive(true);
                        mUpdateRoomPresenter.updateRoomStatus(token,mRoomDTOS.get(pos));                    }
                }
            });
        }
        else {
            mRoomAdapter.notify(mRoomList);
        }

    }
    @Override
    public void getListRoomSuccess(List<RoomDTO> mRoomDTOList) {
        if(mRoomDTOList!=null){
            this.mRoomList = new ArrayList<>();
            this.mRoomDTOS = new ArrayList<>();
            this.mRoomDTOS = mRoomDTOList;
            for (int i = 0; i <mRoomDTOList.size() ; i++) {
                int roomId = mRoomDTOList.get(i).getRoomId();
                String roomCode = mRoomDTOList.get(i).getRoomCode();
                boolean isActive = mRoomDTOList.get(i).isActive();
                mRoomList.add(new Room(roomId,roomCode,"12-12-2020",isActive));
            }
            updateUI();
        }
    }
    @Override
    public void showError(String message) {
        DialogNotifyError.showErrorLoginDialog(getContext(),"Data is error");
    }
    public void reloadPage(){
        token =  SharePreferenceUtils.getStringSharedPreference(getContext(),BundleString.TOKEN);
        mRoomFromLocationPresenter = new GetAllRoomFromLocationPresenter(getContext(),this);
        mRoomFromLocationPresenter.getAllRoomFromLocation(token,locationId);
    }
    @Override
    public void getRoomSuccess(RoomDTO mRoomDTO) {
        mRoomFromLocationPresenter.getAllRoomFromLocation(token,locationId);

    }
}
