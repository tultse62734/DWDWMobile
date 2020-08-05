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
import com.example.dwdwproject.models.WorkerRecord;

import java.util.List;

public class WorkerRecordAdapter extends RecyclerView.Adapter<WorkerRecordAdapter.ViewHolder>{
    private Context mContext;
    private List<WorkerRecord> mWorkerRecords;
    private OnItemClickListerner onItemClickListerner;

    public WorkerRecordAdapter(Context mContext, List<WorkerRecord> mWorkerRecords) {
        this.mContext = mContext;
        this.mWorkerRecords = mWorkerRecords;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_worker_record, parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            holder.mTxtNumber.setText(mWorkerRecords.get(position).getTotalRecord()+"");
            holder.mTxtUsername.setText(mWorkerRecords.get(position).getUsername());
            holder.mTxtRoomCode.setText(mWorkerRecords.get(position).getRoomCode());
            holder.mLnlRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(onItemClickListerner!=null){
                        onItemClickListerner.onItemClick(position);
                    }
                }
            });
    }
    @Override
    public int getItemCount() {
        return mWorkerRecords.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mTxtNumber,mTxtRoomCode,mTxtUsername;
        private LinearLayout mLnlRoot;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTxtNumber = itemView.findViewById(R.id.txt_number_worker_record);
            mTxtRoomCode = itemView.findViewById(R.id.txt_room_worker_record);
            mTxtUsername  = itemView.findViewById(R.id.txt_username_worker_record);
            mLnlRoot = itemView.findViewById(R.id.lnl_root_worker_record);
        }
    }
    public void OnItemClickListernner(OnItemClickListerner mListerner){
        this.onItemClickListerner = mListerner;
    }
    public  interface OnItemClickListerner{
        void onItemClick(int pos);
    }
}
