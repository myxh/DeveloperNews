package com.myxh.developernews.ui.activity;

import android.os.Bundle;

import com.myxh.developernews.R;
import com.myxh.developernews.ui.base.BaseSwipeBackActivity;
import com.myxh.developernews.ui.base.ToolbarActivity;

/**
 * Created by asus on 2016/8/11.
 */
public class SettingsActivity extends BaseSwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    @Override
    public void setToolbarTitle(CharSequence title) {
        super.setToolbarTitle(title);
    }
}
