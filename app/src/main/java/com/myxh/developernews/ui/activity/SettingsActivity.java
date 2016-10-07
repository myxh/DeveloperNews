package com.myxh.developernews.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.myxh.developernews.R;
import com.myxh.developernews.common.MyApplication;
import com.myxh.developernews.ui.base.BaseSwipeBackActivity;
import com.myxh.developernews.util.DataClearUtil;
import com.myxh.developernews.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by asus on 2016/8/11.
 */
public class SettingsActivity extends BaseSwipeBackActivity implements View.OnClickListener {

    @BindView(R.id.settings_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.settings_appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.settings_tv_cacheSize)
    TextView mTvCacheSize;
    @BindView(R.id.settings_cache_layout)
    RelativeLayout mCacheLayout;
    @BindView(R.id.settings_tv_version)
    TextView mTvVersion;
    @BindView(R.id.settings_version_layout)
    RelativeLayout mVersionLayout;
    @BindView(R.id.settings_contract_layout)
    RelativeLayout mContractLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_settings;
    }

    private void initView() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setTitle(R.string.action_settings);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mTvCacheSize.setText(DataClearUtil.getTotalCacheSize(this));
        mTvVersion.setText(MyApplication.getAppContext().getVersion());

        mCacheLayout.setOnClickListener(this);
        mContractLayout.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.settings_cache_layout:
                DataClearUtil.cleanApplicationData(this);
                ToastUtil.show(this,"缓存清理成功");
                mTvCacheSize.setText(DataClearUtil.getTotalCacheSize(this));
                break;
            case R.id.settings_contract_layout:
                sendEmail();
                break;
        }
    }

    private void sendEmail() {
        String address = "1076109002@qq.com";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + address));
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
        if (isIntentAvailable(this, intent)) {
            startActivity(intent);
        } else {
            ToastUtil.show(this,"请安装邮件App或者直接发送邮件到:" + address);
        }
    }

    private boolean isIntentAvailable(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }


}
