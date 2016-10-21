package com.myxh.developernews.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.myxh.developernews.R;
import com.myxh.developernews.bean.CategoryData;

import java.util.List;

/**
 * Created by asus on 2016/9/24.
 */

public class GirlAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<CategoryData.ResultsBean> mDataList;
    private OnPictureClickLisenter mOnPictureClickLisenter;

    //记录列表是否正在滑动
    private boolean isScrolling = false;

    public GirlAdapter(List<CategoryData.ResultsBean> dataList, Context context) {
        mDataList = dataList;
        mContext = context;
    }

    public void addDatas(List<CategoryData.ResultsBean> datas) {
        this.mDataList.addAll(datas);
        notifyDataSetChanged();
    }

    public interface OnPictureClickLisenter {
        void onPictureClick(View view, int position);
    }

    public void setOnPictureClickLisenter(OnPictureClickLisenter onPictureClickLisenter) {
        mOnPictureClickLisenter = onPictureClickLisenter;
    }

    /**
     * 设置滑动标志位
     * @param isScrolling
     */
    public void setScrollingState(boolean isScrolling) {
        this.isScrolling = isScrolling;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_fragment_girl,parent,false);
        return new GirlItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof GirlItemViewHolder) {
            String url = mDataList.get(position).getUrl();
            if (!isScrolling) {
                ((GirlItemViewHolder) holder).mDraweeView.setImageURI(Uri.parse(url));
            }
            ((GirlItemViewHolder) holder).mDraweeView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnPictureClickLisenter != null) {
                        mOnPictureClickLisenter.onPictureClick(v,position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class GirlItemViewHolder extends RecyclerView.ViewHolder
    {
        public SimpleDraweeView mDraweeView;

        public GirlItemViewHolder(View itemView) {
            super(itemView);
            mDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.girl_pic);
            //设置宽高比例
            mDraweeView.setAspectRatio(0.9f);
            //设置对齐方式
            mDraweeView.getHierarchy().setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        }
    }
}
