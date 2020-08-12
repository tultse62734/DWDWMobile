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
import com.example.dwdwproject.models.Accident;

import java.util.ArrayList;
import java.util.List;

public class AccidentAdapter extends RecyclerView.Adapter<AccidentAdapter.AccidentViewHolder>{
    private Context mContext;
    private List<Accident> mAccidentList;
    private OnItemCkickListerner mListerner;
    public AccidentAdapter(Context mContext, List<Accident> mAccidentList) {
        this.mContext = mContext;
        this.mAccidentList = mAccidentList;
    }

    @NonNull
    @Override
    public AccidentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_accident, parent,false);
        AccidentViewHolder viewHolder = new AccidentViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull AccidentViewHolder holder, final int position) {
        int number = position + 1;
        holder.mTxtNumberReport.setText("Report "+ number);
        holder.mTxtNameReport.setText(mAccidentList.get(position).getAccidentName());
        holder.mTxtDateReport.setText("Day : " + mAccidentList.get(position).getAccidentDate());
        holder.mTxtLocationReport.setText(mAccidentList.get(position).getLocationAccident());
        holder.mTxtTime.setText("Time : "+ mAccidentList.get(position).getTimeDate());
        if(mAccidentList.get(position).isStatus()){
            holder.mTxtStatusReport.setText("Active");
            holder.mTxtStatusReport.setTextColor(Color.parseColor("#4CAF50"));
        }else {
            holder.mTxtStatusReport.setText("Deactive");
            holder.mTxtStatusReport.setTextColor(Color.parseColor("#D81B21"));
        }
        holder.mLnlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListerner!=null){
                    mListerner.onItemClick(position);
                }
            }
        });
        holder.mTxtRoomReport.setText(mAccidentList.get(position).getRoomAccident());
    }
    public void notify(List<Accident> mAccidents){
        mAccidentList = new ArrayList<>();
        mAccidentList = mAccidents;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mAccidentList.size();
    }

    public class  AccidentViewHolder extends RecyclerView.ViewHolder{
        private TextView mTxtNumberReport,mTxtNameReport,mTxtDateReport,mTxtLocationReport,mTxtRoomReport,mTxtStatusReport;
        private LinearLayout mLnlRoot;
        private TextView mTxtTime;
        public AccidentViewHolder(@NonNull View itemView) {
            super(itemView);
           mTxtNumberReport = itemView.findViewById(R.id.txt_number_accident);
            mTxtNameReport = itemView.findViewById(R.id.txt_name_accident);
            mTxtDateReport = itemView.findViewById(R.id.txt_date_accident);
            mTxtLocationReport = itemView.findViewById(R.id.txt_location_accident);
            mTxtRoomReport = itemView.findViewById(R.id.txt_room_accident);
            mTxtStatusReport = itemView.findViewById(R.id.txt_status_accident);
            mLnlRoot = itemView.findViewById(R.id.lnl_root_manage_accident);
            mTxtTime = itemView.findViewById(R.id.txt_time_accident);
        }
    }
    public void onItemClick(OnItemCkickListerner onItemCkickListerner){
        this.mListerner = onItemCkickListerner;
    }
    public interface OnItemCkickListerner{
        public void onItemClick(int pos);
    }
}
