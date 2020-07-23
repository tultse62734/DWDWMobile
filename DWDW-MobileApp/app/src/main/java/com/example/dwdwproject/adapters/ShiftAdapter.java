package com.example.dwdwproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.models.Shift;

import java.util.ArrayList;
import java.util.List;

public class ShiftAdapter extends RecyclerView.Adapter<ShiftAdapter.ShiftViewHolder>{
    private Context mContext;
    private List<Shift> mShiftList;
    private OnItemClickListerner mClickListerner;
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
    public void onBindViewHolder(@NonNull ShiftViewHolder holder, final int position) {
        holder.mTxtNameShift.setText(mShiftList.get(position).getShiftCode());
        holder.mTxtLocationShift.setText(mShiftList.get(position).getShiftLocation());
        holder.mTxtRoomShift.setText(mShiftList.get(position).getShiftRoom());
        holder.lnlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mClickListerner!=null){
                    mClickListerner.onItemClick(position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mShiftList.size();
    }
    public  class ShiftViewHolder extends RecyclerView.ViewHolder{
        private TextView mTxtNameShift,mTxtLocationShift,mTxtRoomShift;
        private LinearLayout lnlRoot;
        public ShiftViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtNameShift = itemView.findViewById(R.id.txt_name_shift);
            mTxtLocationShift = itemView.findViewById(R.id.txt_location_shift);
            mTxtRoomShift = itemView.findViewById(R.id.txt_room_shift);
            lnlRoot = itemView.findViewById(R.id.lnl_root_shift);
        }
    }
    public void notify(List<Shift> shiftList){
        mShiftList = new ArrayList<>();
        mShiftList = shiftList;
        notifyDataSetChanged();
    }
    public void OnItemClick(OnItemClickListerner mClickListerner){
        this.mClickListerner = mClickListerner;
    }
    public interface  OnItemClickListerner{
        void onItemClick(int pos);
    }
}
