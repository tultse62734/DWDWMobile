package com.example.dwdwproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.models.Manager;

import java.util.ArrayList;
import java.util.List;

public class ChooseManagerAdapter extends RecyclerView.Adapter<ChooseManagerAdapter.WorkerViewHolder>{
    private Context mContext;
    private List<Manager> mManagerList;
    private ManageAdapter.OnItemClickListener mListener;
    public ChooseManagerAdapter(Context mContext, List<Manager> mManagerList) {
        this.mContext = mContext;
        this.mManagerList = mManagerList;
    }
    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_manage, parent,false);
        WorkerViewHolder viewHolder = new WorkerViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, final int position) {
        holder.tvMobile.setText(mManagerList.get(position).getPhone());
        holder.tvName.setText(mManagerList.get(position).getName());
        holder.mLnlnRootManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    mListener.onItemClick(position);
                }
            }
        });
        holder.mTxtTime.setVisibility(View.GONE);
        holder.mTxtRole.setText(mManagerList.get(position).getRoleName());
        if(mManagerList.get(position).getLocationName()!=null){
            holder.mTxtLocation.setText(mManagerList.get(position).getLocationName());
        }
        else{
            holder.mTxtLocation.setText("No Assign");
        }
    }
    @Override
    public int getItemCount() {
        return mManagerList.size();
    }
    public void notify(List<Manager> managerList){
        mManagerList = new ArrayList<>();
        mManagerList = managerList;
        notifyDataSetChanged();
    }
    public class WorkerViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView mTxtTime,mTxtRole,mTxtLocation;
        LinearLayout mLnlnRootManager;
        TextView tvMobile;
        public WorkerViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.txt_name_worker);
            tvMobile = (TextView) itemView.findViewById(R.id.txt_phone_worker);
            mLnlnRootManager = itemView.findViewById(R.id.lnl_root_manage_manager);
            mTxtRole = itemView.findViewById(R.id.txt_role_user);
            mTxtTime = itemView.findViewById(R.id.txt_time_user);
            mTxtLocation = itemView.findViewById(R.id.txt_location_user);
        }
    }
    public void OnItemClickListener(ManageAdapter.OnItemClickListener itemClickListener){
        this.mListener = itemClickListener;
    }
    public  interface OnItemClickListener{
        void onItemClick(int pos);
    }
}
