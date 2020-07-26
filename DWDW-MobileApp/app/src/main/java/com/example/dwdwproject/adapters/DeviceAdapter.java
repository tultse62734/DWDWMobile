package com.example.dwdwproject.adapters;

import android.content.Context;
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
import com.example.dwdwproject.models.Device;
import com.example.dwdwproject.models.Location;

import java.util.ArrayList;
import java.util.List;

public class DeviceAdapter  extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder> implements Filterable {
    private Context mContext;
    private List<Device> mDeviceList;
    private List<Device> mLDeviceListFull;
    private OnItemClickListenner mListenner;
    public DeviceAdapter(Context mContext, List<Device> mDeviceList) {
        this.mContext = mContext;
        this.mDeviceList = mDeviceList;
        this.mLDeviceListFull = new ArrayList<>(mDeviceList);
    }
    @NonNull
    @Override
    public DeviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_device, parent,false);
        DeviceViewHolder viewHolder = new DeviceViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull DeviceViewHolder holder, final int position) {
        holder.mTxtNameDevice.setText(mDeviceList.get(position).getNameDevice());
        holder.mTxtCreateDateDevice.setText(mDeviceList.get(position).getCreateDate());
        holder.mLnlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListenner != null){
                    mListenner.onItemCLick(position);
                }
            }
        });
        if(mDeviceList.get(position).getLocationDevice()!=null){
            holder.mTxtLocationDevice.setText(mDeviceList.get(position).getLocationDevice());
        }
        else{
            holder.mTxtLocationDevice.setText("No Assign");
        }
        if(mDeviceList.get(position).getRoomCode()!=null){
            holder.mTxtRoomCode.setText(mDeviceList.get(position).getRoomCode());
        }
        else{
            holder.mTxtRoomCode.setText("No Assign");
        }
    }
    @Override
    public int getItemCount() {
        return mDeviceList.size();
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Device> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mLDeviceListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Device item : mLDeviceListFull) {
                    if (item.getNameDevice().toLowerCase().contains(filterPattern)) {
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
            mDeviceList.clear();
            mDeviceList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtLocationDevice,mTxtCreateDateDevice,mTxtNameDevice,mTxtRoomCode;
        LinearLayout mLnlRoot;
        public DeviceViewHolder(View itemView) {
            super(itemView);
            mLnlRoot = itemView.findViewById(R.id.lnl_root_manage_device);
            mTxtNameDevice = itemView.findViewById(R.id.txt_name_device);
            mTxtCreateDateDevice = itemView.findViewById(R.id.txt_create_date_device);
            mTxtLocationDevice = itemView.findViewById(R.id.txt_location_device);
            mTxtRoomCode = itemView.findViewById(R.id.txt_room_device);
        }
    }
    public void onItemClickListerner(OnItemClickListenner mListenner){
        this.mListenner = mListenner;
    }
    public interface OnItemClickListenner{
        void onItemCLick(int pos);
    }
    public void notifyChange(List<Device> mDevices){
        mDeviceList = new ArrayList<>();
        mDeviceList = mDevices;
        mLDeviceListFull = new ArrayList<>(mDeviceList);
        notifyDataSetChanged();
    }
}
