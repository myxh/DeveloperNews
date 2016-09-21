package com.myxh.developernews.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myxh.developernews.R;
import com.myxh.developernews.bean.ZhihuHotData;

import java.util.List;

/**
 * Created by asus on 2016/9/20.
 */
public class HotDataListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ZhihuHotData.RecentBean> dataList;
    private Context mContext;

    public HotDataListAdapter(List<ZhihuHotData.RecentBean> dataList, Context context) {
        this.dataList = dataList;
        mContext = context;
    }

    public void setDatas(List<ZhihuHotData.RecentBean> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void addDatas(List<ZhihuHotData.RecentBean> dataList) {
        this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_hot,null);
        HotItemViewHolder hotItemViewHolder = new HotItemViewHolder(view);
        return hotItemViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((HotItemViewHolder) holder).zhihuIcon.setImageURI(Uri.parse(dataList.get(position).getThumbnail()));
        ((HotItemViewHolder) holder).zhihuDesc.setText(dataList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class HotItemViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView zhihuIcon;
        public TextView zhihuDesc;

        public HotItemViewHolder(View itemView) {
            super(itemView);
            zhihuIcon = (SimpleDraweeView) itemView.findViewById(R.id.hot_item_icon);
            zhihuDesc = (TextView) itemView.findViewById(R.id.hot_item_tv_desc);
        }
    }
}
