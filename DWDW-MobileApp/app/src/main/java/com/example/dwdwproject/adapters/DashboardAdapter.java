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
import com.example.dwdwproject.models.ItemDashBoard;
import java.util.List;
public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder>{
    private Context mContext;
    private List<ItemDashBoard> mItemDashBoardList;
    private OnItemClickListenr mOnItemClickListenr;
    public DashboardAdapter(Context mContext, List<ItemDashBoard> mItemDashBoardList) {
        this.mContext = mContext;
        this.mItemDashBoardList = mItemDashBoardList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_dashboard_adapter, parent,false);
        DashboardAdapter.ViewHolder viewHolder = new DashboardAdapter.ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.mImgItem.setImageResource(mItemDashBoardList.get(position).getImage());
        holder.mTxtNameItem.setText(mItemDashBoardList.get(position).getTitlle());
        holder.mLnlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListenr !=null){
                    mOnItemClickListenr.onItemClickListerner(position);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return mItemDashBoardList.size();
    }
    public class  ViewHolder extends RecyclerView.ViewHolder{
        private LinearLayout mLnlRoot;
        private TextView mTxtNameItem;
        private ImageView mImgItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mLnlRoot = itemView.findViewById(R.id.lnl_root_item);
            mTxtNameItem = itemView.findViewById(R.id.txt_name_item_dash);
            mImgItem  = (ImageView) itemView.findViewById(R.id.img_item_dash);
        }
    }
    public void OnItemClick(OnItemClickListenr mListenr){
        this.mOnItemClickListenr = mListenr;
    }
    public interface OnItemClickListenr{
        void onItemClickListerner(int pos);

    }
}
