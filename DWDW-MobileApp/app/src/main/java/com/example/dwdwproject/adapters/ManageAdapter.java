package com.example.dwdwproject.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.models.Location;
import com.example.dwdwproject.models.Manager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
public class ManageAdapter extends RecyclerView.Adapter<ManageAdapter.WorkerViewHolder> implements Filterable {
    private Context mContext;
    private List<Manager> mManagerList;
    private List<Manager> mManagerListFull;
    private OnItemClickListener mListener;
    private OnItemActiveClickListener mClickListener;
    public ManageAdapter(Context mContext, List<Manager> mManagerList) {
        this.mContext = mContext;
        this.mManagerList = mManagerList;
        this.mManagerListFull = new ArrayList<>(mManagerList);
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
            holder.mLnlActive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mClickListener!=null){
                        mClickListener.onItemActiveClick(position);
                    }
                }
            });
            holder.mTxtTime.setText(mManagerList.get(position).getCreateTime());
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
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Manager> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mManagerListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Manager item : mManagerListFull){
                    if (item.getName().toLowerCase().contains(filterPattern)) {
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
            mManagerList.clear();
            mManagerList.addAll((List) results.values);
            mManagerListFull = new ArrayList<>(mManagerList);
            notifyDataSetChanged();
        }
    };
    public void notify(List<Manager> managerList){
        mManagerList = new ArrayList<>();
        mManagerList = managerList;
        mManagerListFull = new ArrayList<>(managerList);
        notifyDataSetChanged();
    }
    public class WorkerViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView mTxtTime,mTxtRole,mTxtLocation;
        LinearLayout mLnlnRootManager,mLnlActive;
        TextView tvMobile,txtActive;
        TextView mTxtActive;
        public WorkerViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.txt_name_worker);
            tvMobile = (TextView) itemView.findViewById(R.id.txt_phone_worker);
            mLnlnRootManager = itemView.findViewById(R.id.lnl_root_manage_manager);
            mTxtRole = itemView.findViewById(R.id.txt_role_user);
            mTxtTime = itemView.findViewById(R.id.txt_time_user);
            mTxtLocation = itemView.findViewById(R.id.txt_location_user);
            mLnlActive = itemView.findViewById(R.id.lnl_active_user);
            mTxtActive = itemView.findViewById(R.id.txt_active_user);
            txtActive = itemView.findViewById(R.id.txt_name_active_user);
        }
    }

    public void OnItemClickListener(OnItemClickListener itemClickListener){
        this.mListener = itemClickListener;
    }
    public  void OnItemActiveClickListener(OnItemActiveClickListener mListener){
        this.mClickListener = mListener;
    }
    public  interface OnItemClickListener{
        void onItemClick(int pos);
    }
    public interface  OnItemActiveClickListener{
        void onItemActiveClick(int pos);
    }
}
