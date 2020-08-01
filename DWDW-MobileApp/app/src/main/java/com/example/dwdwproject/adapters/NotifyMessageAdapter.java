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
import com.example.dwdwproject.models.NotifyMessage;

import java.util.ArrayList;
import java.util.List;

public class NotifyMessageAdapter extends RecyclerView.Adapter<NotifyMessageAdapter.ViewHolder> {
    private Context mContext;
    private List<NotifyMessage> mnNotifyMessageList;
    private OnItemClickListerner mClickListerner;
    public NotifyMessageAdapter(Context context, List<NotifyMessage> mnNotifyMessageList) {
        this.mContext = context;
        this.mnNotifyMessageList = mnNotifyMessageList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.row_notify_message, parent,false);
       ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mHeaderMessage.setText(mnNotifyMessageList.get(position).getHeader());
        holder.mBodyMessage.setText(mnNotifyMessageList.get(position).getMessage());
        holder.mLnlRootMesage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mClickListerner!=null){
                    mClickListerner.OnItemClick(position);
                }
            }
        });
        holder.mMinute.setText(mnNotifyMessageList.get(position).getMinute());
    }

    @Override
    public int getItemCount() {
        return mnNotifyMessageList.size();
    }
    public  void notify(List<NotifyMessage> messageList){
        mnNotifyMessageList = new ArrayList<>();
        mnNotifyMessageList = messageList;
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mHeaderMessage,mBodyMessage,mMinute;
        private LinearLayout mLnlRootMesage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mHeaderMessage = itemView.findViewById(R.id.txt_header_messaage);
            mBodyMessage = itemView.findViewById(R.id.txt_body_messaage);
            mLnlRootMesage = itemView.findViewById(R.id.lnl_root_message);
            mMinute = itemView.findViewById(R.id.txt_minute_messaage);
        }
    }
    public void OnItemClickListenner(OnItemClickListerner mListerner){
        this.mClickListerner = mListerner;
    }
    public interface  OnItemClickListerner{
        void OnItemClick(int pos);
    }
}
