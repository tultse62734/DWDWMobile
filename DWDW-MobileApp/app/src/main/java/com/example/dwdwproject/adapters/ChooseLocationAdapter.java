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

import java.util.ArrayList;
import java.util.List;
public class ChooseLocationAdapter extends RecyclerView.Adapter<ChooseLocationAdapter.ChooseLocationViewHolder>  implements Filterable {
    private Context mContext;
    private List<Location> mLocationList;
    private List<Location> mLocationListFull;
    private OnClickItem mOnClickItem;
    public ChooseLocationAdapter(Context mContext, List<Location> mLocationList) {
        this.mContext = mContext;
        this.mLocationList = mLocationList;
        mLocationListFull = new ArrayList<>(mLocationList);
    }

    @NonNull
    @Override
    public ChooseLocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_choose_location, parent,false);
        ChooseLocationViewHolder viewHolder = new ChooseLocationViewHolder(view);
        return viewHolder;    }
    @Override
    public void onBindViewHolder(@NonNull ChooseLocationViewHolder holder, final int position) {
        holder.mTxtNameLocation.setText(mLocationList.get(position).getNameLocation());
        if(mLocationList.get(position).isStatus()){
            holder.mTxtStatusLocation.setText("Đang hoạt động");
            holder.mTxtStatusLocation.setTextColor(Color.parseColor("#4CAF50"));
        }else {
            holder.mTxtStatusLocation.setText("Không hoạt động");
            holder.mTxtStatusLocation.setTextColor(Color.parseColor("#D81B21"));
        }

        holder.mLnlRootLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnClickItem!=null){
                    mOnClickItem.OnClickItem(position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mLocationList.size();
    }
    public void OnClickItemListener(OnClickItem mOnClickItem){
        this.mOnClickItem = mOnClickItem;
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
                for (Location item : mLocationListFull) {
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
    public class ChooseLocationViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtNameLocation,mTxtCreateDateLocation,mTxtStatusLocation;
        LinearLayout mLnlRootLocation;

        public ChooseLocationViewHolder(View itemView) {
            super(itemView);
            mTxtCreateDateLocation = itemView.findViewById(R.id.txt_choose_create_date_location);
            mTxtNameLocation = itemView.findViewById(R.id.txt_name_choose_location);
            mTxtStatusLocation = itemView.findViewById(R.id.txt_choose_status_location);
            mLnlRootLocation = itemView.findViewById(R.id.lnl_root_choose_location);
        }
    }
    public  void notifyChangeData(List<Location> mList){
        mLocationList = new ArrayList<>();
        mLocationList = mList;
        notifyDataSetChanged();
    }
    public interface OnClickItem{
        void OnClickItem(int position);
    }
}
