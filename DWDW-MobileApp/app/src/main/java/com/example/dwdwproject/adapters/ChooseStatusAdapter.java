package com.example.dwdwproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.models.Status;

import java.util.List;

public class ChooseStatusAdapter extends RecyclerView.Adapter<ChooseStatusAdapter.ChooseStatusViewHolder> {
    private Context mContext;
    private List<Status> mStringList;
    private OnClickItem mOnClickItem;
    public ChooseStatusAdapter(Context mContext, List<Status> mStringList) {
        this.mContext = mContext;
        this.mStringList = mStringList;
    }

    @NonNull
    @Override
    public ChooseStatusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_choose_status, parent,false);
        ChooseStatusViewHolder viewHolder = new ChooseStatusViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ChooseStatusViewHolder holder,final int position) {
        holder.mTxtNameStatus.setText(mStringList.get(position).getStatusName());

        if(mStringList.get(position).isStatus() == true){
            holder.mImgStatus.setImageResource(R.mipmap.ic_tick);
        }
        else {
            holder.mImgStatus.setImageResource(R.mipmap.ic_minus);
        }
        holder.mLnlRootStatus.setOnClickListener(new View.OnClickListener() {
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
        return mStringList.size();
    }
    public void OnClickItemListener(OnClickItem mOnClickItem){
        this.mOnClickItem = mOnClickItem;
    }

    public class ChooseStatusViewHolder extends RecyclerView.ViewHolder {
        TextView mTxtNameStatus;
        LinearLayout mLnlRootStatus;
        private ImageView mImgStatus;
        public ChooseStatusViewHolder(View itemView) {
            super(itemView);
            mTxtNameStatus = itemView.findViewById(R.id.txt_name_choose_status);
            mImgStatus = itemView.findViewById(R.id.img_choose_satus);
            mLnlRootStatus = itemView.findViewById(R.id.lnl_root_choose_satus);
        }
    }
    public interface OnClickItem{
        void OnClickItem(int position);
    }
}
