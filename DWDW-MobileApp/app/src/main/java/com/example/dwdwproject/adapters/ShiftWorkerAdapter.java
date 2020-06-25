package com.example.dwdwproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.models.WorkerShift;

import java.util.List;

public class ShiftWorkerAdapter extends RecyclerView.Adapter<ShiftWorkerAdapter.ShiftWorkerViewHoler>{
    private Context mContext;
    private List<WorkerShift> mWorkerShiftList;

    public ShiftWorkerAdapter(Context mContext, List<WorkerShift> mWorkerShiftList) {
        this.mContext = mContext;
        this.mWorkerShiftList = mWorkerShiftList;
    }

    @NonNull
    @Override
    public ShiftWorkerViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_shift_worker, parent,false);
        ShiftWorkerViewHoler viewHolder = new ShiftWorkerViewHoler(view);
        return viewHolder;    }

    @Override
    public void onBindViewHolder(@NonNull ShiftWorkerViewHoler holder, int position) {
            holder.mTxtShiftTime.setText(mWorkerShiftList.get(position).getShiftTime());
            holder.mTxtShiftRoom.setText("Phòng "+mWorkerShiftList.get(position).getShiftRoom());
            holder.mTxtDate.setText(mWorkerShiftList.get(position).getShiftDay());
            holder.mTxtNameManager.setText("Quản lý : "+mWorkerShiftList.get(position).getShiftNameManager());
            holder.mTxtShiftStartEndTime.setText(mWorkerShiftList.get(position).getShiftStart() + " - " +mWorkerShiftList.get(position).getShiftEnd());
            holder.mTxtShiftLocation.setText(mWorkerShiftList.get(position).getShiftLocation());
            holder.mTxtStatus.setText(mWorkerShiftList.get(position).getShiftStatus());
    }

    @Override
    public int getItemCount() {
        return  mWorkerShiftList.size();
    }

    public class ShiftWorkerViewHoler extends RecyclerView.ViewHolder{
        TextView mTxtShiftTime,mTxtShiftRoom,mTxtShiftLocation,mTxtShiftStartEndTime,mTxtStatus,mTxtDate,mTxtNameManager;
        public ShiftWorkerViewHoler(@NonNull View itemView) {
            super(itemView);
            mTxtShiftTime = itemView.findViewById(R.id.txt_shift_time);
            mTxtShiftRoom = itemView.findViewById(R.id.txt_shift_room_worker);
            mTxtShiftLocation = itemView.findViewById(R.id.txt_location_shift_worker);
            mTxtDate = itemView.findViewById(R.id.txt_day_shift_worker);
            mTxtNameManager  = itemView.findViewById(R.id.txt_name_manager_worker);
            mTxtStatus = itemView.findViewById(R.id.txt_status_shift);
            mTxtShiftStartEndTime = itemView.findViewById(R.id.txt_shift_startend_date_worker);
        }
    }
}
