package com.example.dwdwproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SectionIndexer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.models.Manager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ManageAdapter extends RecyclerView.Adapter<ManageAdapter.WorkerViewHolder>implements SectionIndexer {
    private Context mContext;
    private List<Manager> mManagerList;
    private ArrayList<Integer> mSectionPositions;
    private OnItemClickListener mListener;
    public ManageAdapter(Context mContext, List<Manager> mManagerList) {
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
    public Object[] getSections() {
        List<String> sections = new ArrayList<>();
        mSectionPositions = new ArrayList<>();
        for (int i = 0, size = mManagerList.size(); i < size; i++) {
            String section = String.valueOf(mManagerList.get(i).getName().charAt(0)).toUpperCase();
            if (!sections.contains(section)) {
                sections.add(section);
                mSectionPositions.add(i);
            }
        }
        return sections.toArray(new String[0]);
    }

    @Override
    public int getPositionForSection(int sectionIndex) {
        return mSectionPositions.get(sectionIndex);

    }

    @Override
    public int getSectionForPosition(int position) {
        return 0;
    }

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
