package com.example.dwdwproject.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.models.Room;

import java.util.ArrayList;
import java.util.List;

public class ChooseRoomAdapter extends RecyclerView.Adapter<ChooseRoomAdapter.ChooseRoomViewHolder>  {
    private Context mContext;
    private List<Room> mRoomList;
    private OnClickItem mOnClickItem;
    public ChooseRoomAdapter(Context mContext, List<Room> mRoomList) {
        this.mContext = mContext;
        this.mRoomList = mRoomList;
    }

    @NonNull
    @Override
    public ChooseRoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_choose_room, parent,false);
        ChooseRoomViewHolder viewHolder = new ChooseRoomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChooseRoomViewHolder holder,final int position) {
        holder.mTxtNameRoom.setText(mRoomList.get(position).getRoomName());
        holder.mTxtCreateDateRoom.setText(mRoomList.get(position).getRoomCreateDate());
        if(mRoomList.get(position).isStatus()){
            holder.mTxtStatusRoom.setText("Đang hoạt động");
            holder.mTxtStatusRoom.setTextColor(Color.parseColor("#4CAF50"));
        }else {
            holder.mTxtStatusRoom.setText("Không hoạt động");
            holder.mTxtStatusRoom.setTextColor(Color.parseColor("#D81B21"));
        }
        holder.mLnlRootRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnClickItem!=null){
                    mOnClickItem.OnClickItem(position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mRoomList.size();
    }
    public void OnClickItemListener(OnClickItem mOnClickItem){
        this.mOnClickItem = mOnClickItem;
    }

    public class ChooseRoomViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtNameRoom,mTxtCreateDateRoom,mTxtStatusRoom;
        LinearLayout mLnlRootRoom;

        public ChooseRoomViewHolder(View itemView) {
            super(itemView);
            mTxtCreateDateRoom = itemView.findViewById(R.id.txt_choose_create_date_room);
            mTxtNameRoom = itemView.findViewById(R.id.txt_name_choose_room);
            mTxtStatusRoom = itemView.findViewById(R.id.txt_choose_status_room);
            mLnlRootRoom = itemView.findViewById(R.id.lnl_root_choose_room);
        }
    }
    public  void notifyChangeData(List<Room> mList){
        mRoomList = new ArrayList<>();
        mRoomList = mList;
        notifyDataSetChanged();
    }
    public interface OnClickItem{
        void OnClickItem(int position);
    }
}
