package com.myxh.developernews.ui.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;

import com.myxh.developernews.R;

/**
 * Created by asus on 2016/8/11.
 */
public abstract class ToolbarActivity extends BaseActivity {

//    protected AppBarLayout mAppBar;
    protected Toolbar mToolbar;
    protected boolean isHiddeToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());

        //设置透明状态栏
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | layoutParams.flags;
        }*/
    }

    protected abstract int getContentViewId();

    /**
     * 设置Toolbar
     * @param toolbarView
     */
    protected void setToolbar(View toolbarView) {
        mToolbar = (Toolbar) toolbarView.findViewById(R.id.toolbar);
        if (mToolbar == null) {
            throw new IllegalStateException("activity must contain a toolbar");
        }
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getString(R.string.login));
        // 给左上角图标的左边加上一个返回的图标
        actionBar.setDisplayHomeAsUpEnabled(true);
        //使左上角图标是否显示，如果设成false，则没有程序图标，仅仅就个标题，否则，显示应用程序图标
        actionBar.setDisplayShowHomeEnabled(true);
        //决定左上角的图标是否可以点击。没有向左的小图标。true 图标可以点击  false 不可以点击。
        actionBar.setHomeButtonEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setToolbarTitle(CharSequence title) {
        mToolbar.setTitle(title);
    }

    public void hideOrShowToolbar() {
        mToolbar.animate()
                .translationY(isHiddeToolbar ? 0 : -mToolbar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();
        isHiddeToolbar = !isHiddeToolbar;
    }

}
