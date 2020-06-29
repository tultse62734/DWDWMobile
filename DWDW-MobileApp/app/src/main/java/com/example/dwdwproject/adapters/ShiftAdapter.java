package com.example.dwdwproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.models.Shift;

import java.util.List;

public class ShiftAdapter extends RecyclerView.Adapter<ShiftAdapter.ShiftViewHolder>{
    private Context mContext;
    private List<Shift> mShiftList;
    public ShiftAdapter(Context mContext, List<Shift> mShiftList) {
        this.mContext = mContext;
        this.mShiftList = mShiftList;
    }
    @NonNull
    @Override
    public ShiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_shift, parent,false);
        ShiftViewHolder viewHolder = new ShiftViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ShiftViewHolder holder, int position) {
        holder.mTxtNameShift.setText(mShiftList.get(position).getShiftCode());
        holder.mTxtLocationShift.setText(mShiftList.get(position).getShiftLocation());
        holder.mTxtRoomShift.setText(mShiftList.get(position).getShiftRoom());
    }
    @Override
    public int getItemCount() {
        return mShiftList.size();
    }
    public  class ShiftViewHolder extends RecyclerView.ViewHolder{
        private TextView mTxtNameShift,mTxtLocationShift,mTxtRoomShift;
        public ShiftViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtNameShift = itemView.findViewById(R.id.txt_name_shift);
            mTxtLocationShift = itemView.findViewById(R.id.txt_location_shift);
            mTxtRoomShift = itemView.findViewById(R.id.txt_room_shift);
        }
    }
}
