package com.myxh.developernews.ui.activity;

import android.os.Bundle;

import com.myxh.developernews.R;
import com.myxh.developernews.ui.base.ToolbarActivity;

/**
 * Created by asus on 2016/8/11.
 */
public class CollectionActivity extends ToolbarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
    }

    @Override
    protected int getContentViewId() {
        return 0;
    }
}
