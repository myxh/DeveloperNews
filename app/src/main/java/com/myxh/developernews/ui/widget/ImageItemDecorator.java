package com.myxh.developernews.ui.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by asus on 2016/10/5.
 */

public class ImageItemDecorator extends RecyclerView.ItemDecoration {

    //图片间隙
    private int space;

    public ImageItemDecorator(int space) {
        this.space = space;
    }

    /**
     * 设置左右上下间隙
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = space;
        outRect.left = space;
        outRect.right = space;
        //第一行不设置top间隙
        if (parent.getChildLayoutPosition(view) != 0) {
            outRect.top = space;
        }
    }
}
