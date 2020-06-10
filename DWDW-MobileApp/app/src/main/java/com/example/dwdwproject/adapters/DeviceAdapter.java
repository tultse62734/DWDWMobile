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
import com.example.dwdwproject.models.Device;

import java.util.List;

public class DeviceAdapter  extends RecyclerView.Adapter<DeviceAdapter.DeviceViewHolder>{
    private Context mContext;
    private List<Device> mDeviceList;

    public DeviceAdapter(Context mContext, List<Device> mDeviceList) {
        this.mContext = mContext;
        this.mDeviceList = mDeviceList;
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
        holder.mTxtLocationDevice.setText(mDeviceList.get(position).getLocationDevice());

    }
    @Override
    public int getItemCount() {
        return mDeviceList.size();
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtLocationDevice,mTxtCreateDateDevice,mTxtNameDevice;
        public DeviceViewHolder(View itemView) {
            super(itemView);
            mTxtNameDevice = itemView.findViewById(R.id.txt_name_device);
            mTxtCreateDateDevice = itemView.findViewById(R.id.txt_create_date_device);
            mTxtLocationDevice = itemView.findViewById(R.id.txt_location_device);

        }
    }
}
