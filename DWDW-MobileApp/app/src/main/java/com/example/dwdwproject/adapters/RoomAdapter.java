package com.example.dwdwproject.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> implements Filterable {
    private Context mContext;
    private List<Room> mRoomList;
    private List<Room> mRoomListFull;
    private OnItemClickListerner mListerner;
    public RoomAdapter(Context mContext, List<Room> mRoomList) {
        this.mContext = mContext;
        this.mRoomList = mRoomList;
        this.mRoomListFull = new ArrayList<>(mRoomList);
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

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Room> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mRoomListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Room item : mRoomListFull){
                    if (item.getRoomName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mRoomList.clear();
            mRoomList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

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
