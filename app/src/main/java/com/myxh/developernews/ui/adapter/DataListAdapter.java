package com.myxh.developernews.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myxh.developernews.R;
import com.myxh.developernews.bean.CategoryData;
import com.myxh.developernews.bean.Gank;
import com.myxh.developernews.util.UrlUtil;

import java.util.List;

/**
 * Created by asus on 2016/8/11.
 */
public class DataListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Gank> datas;
    private Context context;
    private String itemType;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    public DataListAdapter(List<Gank> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }

    public void setDatas(List<Gank> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public void addDaas(List<Gank> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_fragment_header,parent,false);
            DataHeaderViewHolder headerViewHolder = new DataHeaderViewHolder(view);
            return headerViewHolder;
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_fragment_data,parent,false);
            DataItemViewHolder itemViewHolder = new DataItemViewHolder(view);
            return itemViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof DataHeaderViewHolder) {

        } else if (holder instanceof DataItemViewHolder) {
            ((DataItemViewHolder) holder).itemIcon.
                    setImageResource(UrlUtil.getUrlIcon(datas.get(position).getUrl()));
            ((DataItemViewHolder) holder).itemTitle.setText(datas.get(position).getDesc());
            ((DataItemViewHolder) holder).itemWho.setText(datas.get(position).getWho());
        }
    }

    @Override
    public int getItemCount() {
        return datas==null ? 0 : datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TYPE_ITEM;
    }
}

class DataHeaderViewHolder extends RecyclerView.ViewHolder{
    public TextView date;

    public DataHeaderViewHolder(View itemView) {
        super(itemView);
        date = (TextView) itemView.findViewById(R.id.item_fra_header_date);
    }
}

class DataItemViewHolder extends RecyclerView.ViewHolder {
    public ImageView itemIcon;
    public TextView itemTitle;
    public TextView itemWho;

    public DataItemViewHolder(View itemView) {
        super(itemView);
        itemIcon = (ImageView) itemView.findViewById(R.id.item_fra_data_icon);
        itemTitle = (TextView) itemView.findViewById(R.id.item_fra_data_title);
        itemWho = (TextView) itemView.findViewById(R.id.item_fra_data_who);
    }
}
