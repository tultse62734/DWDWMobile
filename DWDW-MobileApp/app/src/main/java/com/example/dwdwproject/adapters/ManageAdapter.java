package com.example.dwdwproject.adapters;

import android.content.Context;
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
        Picasso.get().load(mManagerList.get(position).getImageResourceId()).into(holder.ivProfile);
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
            notifyDataSetChanged();
        }
    };
    public class WorkerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfile;
        TextView tvName;
        LinearLayout mLnlnRootManager;
        TextView tvMobile;
        public WorkerViewHolder(View itemView) {
            super(itemView);
            ivProfile = (ImageView) itemView.findViewById(R.id.image_avata);
            tvName = (TextView) itemView.findViewById(R.id.txt_name_worker);
            tvMobile = (TextView) itemView.findViewById(R.id.txt_phone_worker);
            mLnlnRootManager = itemView.findViewById(R.id.lnl_root_manage_manager);
        }
    }
    public void OnItemClickListener(OnItemClickListener itemClickListener){
        this.mListener = itemClickListener;
    }
    public  interface OnItemClickListener{
        void onItemClick(int pos);
    }
}
