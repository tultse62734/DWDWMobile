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

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder>{
    private Context mContext;
    private List<Room> mRoomList;
    private OnItemClickListerner mListerner;
    public RoomAdapter(Context mContext, List<Room> mRoomList) {
        this.mContext = mContext;
        this.mRoomList = mRoomList;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_room, parent,false);
        RoomViewHolder viewHolder = new RoomViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, final int position) {
        holder.mTxtNameRoom.setText(mRoomList.get(position).getRoomName()+"");
        holder.mTxtCreateDateRoom.setText(mRoomList.get(position).getRoomCreateDate()+"");
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
                if(mListerner!=null){
                    mListerner.onItemClick(position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mRoomList.size();
    }
    public class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtNameRoom,mTxtCreateDateRoom,mTxtStatusRoom;
        LinearLayout mLnlRootRoom;
        public RoomViewHolder(View itemView) {
            super(itemView);
            mTxtNameRoom = itemView.findViewById(R.id.txt_name_room);
            mTxtCreateDateRoom = itemView.findViewById(R.id.txt_create_date_room);
            mTxtStatusRoom = itemView.findViewById(R.id.txt_status_room);
            mLnlRootRoom = itemView.findViewById(R.id.lnl_root_manage_room);
        }
    }
    public void OnItemClickListerner(OnItemClickListerner itemClickListerner){
        this.mListerner = itemClickListerner;
    }
    public  interface OnItemClickListerner{
        void onItemClick(int pos);
    }
}
