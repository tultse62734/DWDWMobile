package com.example.dwdwproject.adapters;

import android.annotation.SuppressLint;
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

import java.util.ArrayList;
import java.util.List;

public class LocationAssginAdapter extends RecyclerView.Adapter<LocationAssginAdapter.LocationViewHolder> implements Filterable {
    private Context mContext;
    private List<Location> mLocationList;
    private List<Location> mLocationListFull;
    private OnClickDeleteItem mDeleteItem;
    private OnClickActiveItem mClickActiveItem;
    private OnClickItem mOnClickItem;
    public LocationAssginAdapter(Context mContext, List<Location> mLocationList) {
        this.mContext = mContext;
        this.mLocationList = mLocationList;
        this.mLocationListFull = new ArrayList<>(mLocationList);
    }
    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_choose_item_location, parent,false);
        LocationViewHolder viewHolder = new LocationViewHolder(view);
        return viewHolder;    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, final int position) {
        holder.mTxtNameLocation.setText(mLocationList.get(position).getNameLocation());
        holder.mTxtCreateDateLocation.setText(mLocationList.get(position).getCreateDate());
        if(mLocationList.get(position).isStatus()){
            holder.mTxtStatusLocation.setText("Active");
            holder.mTxtStatusLocation.setTextColor(Color.parseColor("#4CAF50"));
        }else {
            holder.mTxtStatusLocation.setText("Deactive");
            holder.mTxtStatusLocation.setTextColor(Color.parseColor("#D81B21"));
        }
        holder.mLnlDeteleLcation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDeleteItem!=null){
                    mDeleteItem.OnClickDeleteItem(position);
                }
            }
        });
        holder.mLnlRootLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnClickItem!=null){
                    mOnClickItem.OnClickItem(position);
                }
            }
        });
        holder.mLnlActiveLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mClickActiveItem!=null){
                    mClickActiveItem.OnClickActiveItem(position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mLocationList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Location> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mLocationListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Location item : mLocationListFull){
                    if (item.getNameLocation().toLowerCase().contains(filterPattern)) {
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
            mLocationList.clear();
            mLocationList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class LocationViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtNameLocation,mTxtCreateDateLocation,mTxtStatusLocation;
        LinearLayout mLnlRootLocation,mLnlDeteleLcation,mLnlActiveLocation;

        public LocationViewHolder(View itemView) {
            super(itemView);
            mTxtCreateDateLocation = itemView.findViewById(R.id.txt_choose_create_date_location);
            mTxtNameLocation = itemView.findViewById(R.id.txt_choose_name_location);
            mTxtStatusLocation = itemView.findViewById(R.id.txt_choose_status_location);
            mLnlRootLocation = itemView.findViewById(R.id.lnl_choose_root_location_admin);
            mLnlDeteleLcation = itemView.findViewById(R.id.lnl_choose_delete_location_admin);
            mLnlActiveLocation = itemView.findViewById(R.id.lnl_choose_active_location_admin);
        }
    }
    public void OnClickDeleteItemListener(OnClickDeleteItem mDeleteItem){
        this.mDeleteItem = mDeleteItem;
    }
    public void OnClickItemListener(OnClickItem mOnClickItem){
        this.mOnClickItem = mOnClickItem;
    }
    public void OnClickActiveListerner(OnClickActiveItem mActiveItem){
        this.mClickActiveItem = mActiveItem;
    }
    public interface OnClickDeleteItem{
        void OnClickDeleteItem(int position);
    }
    public interface OnClickItem{
        void OnClickItem(int position);
    }
    public interface OnClickActiveItem{
        void OnClickActiveItem(int pos);
    }
    public void notify(List<Location> locationList){
        mLocationList = new ArrayList<>();
        mLocationList  = locationList;
        notifyDataSetChanged();
    }
}
