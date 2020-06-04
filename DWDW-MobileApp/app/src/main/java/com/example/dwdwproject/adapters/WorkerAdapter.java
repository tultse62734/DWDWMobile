package com.example.dwdwproject.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dwdwproject.R;
import com.example.dwdwproject.models.Worker;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder>implements SectionIndexer {
    private Context mContext;
    private List<Worker> mWorkerList;
    private ArrayList<Integer> mSectionPositions;

    public WorkerAdapter(Context mContext, List<Worker> mWorkerList) {
        this.mContext = mContext;
        this.mWorkerList = mWorkerList;
    }

    @NonNull
    @Override
    public WorkerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_item_worker, parent,false);
        WorkerViewHolder viewHolder = new WorkerViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull WorkerViewHolder holder, int position) {
        Picasso.get().load(mWorkerList.get(position).getImageResourceId()).into(holder.ivProfile);
            holder.tvMobile.setText(mWorkerList.get(position).getPhone());
            holder.tvName.setText(mWorkerList.get(position).getName());
    }
    @Override
    public int getItemCount() {
        return mWorkerList.size();

    }


    @Override
    public Object[] getSections() {
        List<String> sections = new ArrayList<>();
        mSectionPositions = new ArrayList<>();
        for (int i = 0, size = mWorkerList.size(); i < size; i++) {
            String section = String.valueOf(mWorkerList.get(i).getName().charAt(0)).toUpperCase();
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
        TextView tvMobile;
        public WorkerViewHolder(View itemView) {
            super(itemView);
            ivProfile = (ImageView) itemView.findViewById(R.id.image_avata);
            tvName = (TextView) itemView.findViewById(R.id.txt_name_worker);
            tvMobile = (TextView) itemView.findViewById(R.id.txt_phone_worker);
        }
    }

}
