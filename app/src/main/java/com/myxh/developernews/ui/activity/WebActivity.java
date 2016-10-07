package com.myxh.developernews.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.myxh.developernews.R;
import com.myxh.developernews.bean.CategoryData;
import com.myxh.developernews.ui.base.BaseSwipeBackActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class WebActivity extends BaseSwipeBackActivity {

    public static final String KEY_ID = "key_id";
    public static final String KEY_TITLE = "key_title";
    public static final String KEY_URL = "key_url";
    public static final String KEY_TYPE = "key_type";
    public static final String KEY_WHO = "key_who";

    @BindView(R.id.web_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.web_progress_bar)
    ProgressBar mProgressBar;
    @BindView(R.id.web_appbar)
    AppBarLayout mAppbar;
    @BindView(R.id.web_wv_content)
    WebView mWvContent;
    @BindView(R.id.web_refresh_layout)
    MaterialRefreshLayout mRefreshLayout;
    @BindView(R.id.activity_web)
    CoordinatorLayout mActivityWeb;

    private String mObjectId;
    private String mTitle;
    private String mURl;
    private String mType;
    private String mWho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        
        initWithIntentData();
    }

    private void initWithIntentData() {
        Intent intent = getIntent();
        mObjectId = intent.getStringExtra(KEY_ID);
        mTitle = intent.getStringExtra(KEY_TITLE);
        mURl = intent.getStringExtra(KEY_URL);
        mType = intent.getStringExtra(KEY_TYPE);
        mWho = intent.getStringExtra(KEY_WHO);

        initView();
        initWebView();
        mWvContent.loadUrl(mURl);
    }

    private void initWebView() {
        mWvContent.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    //加载完成
                    if (mProgressBar != null && mRefreshLayout != null) {
                        mProgressBar.setVisibility(View.GONE);
                        mProgressBar.setProgress(0);
                        mRefreshLayout.finishRefresh();
                    }
                } else {
                    //加载中
                    if (mProgressBar != null) {
                        mProgressBar.setVisibility(View.VISIBLE);
                        mProgressBar.setProgress(newProgress);
                    }
                }
            }
        });
        mWvContent.getSettings().setJavaScriptEnabled(true);
        mWvContent.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWvContent.getSettings().setSupportZoom(true);
        mWvContent.getSettings().setDisplayZoomControls(true);
    }

    private void initView() {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(mTitle);
        }
        mRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                mWvContent.reload();
            }
        });
    }


    @Override
    protected int getContentViewId() {
        return R.layout.activity_web;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mWvContent != null) {
            mWvContent.onPause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mWvContent != null) {
            mWvContent.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mWvContent != null) {
            mWvContent.removeAllViews();
            mWvContent.destroy();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
                showShare();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if(mWvContent.canGoBack()){
                mWvContent.goBack();
            }else{
                scrollToFinishActivity();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public static void startWebActivity(Activity context, CategoryData.ResultsBean result) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(KEY_ID, result.get_id());
        intent.putExtra(KEY_TITLE, result.getDesc());
        intent.putExtra(KEY_TYPE, result.getType());
        intent.putExtra(KEY_URL, result.getUrl());
        intent.putExtra(KEY_WHO, result.getWho());
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(mTitle);
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(mURl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(this);
    }

}
