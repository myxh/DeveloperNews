package com.myxh.developernews.ui.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myxh.developernews.R;
import com.myxh.developernews.bean.ZhihuDetailData;
import com.myxh.developernews.ui.base.BaseSwipeBackActivity;
import com.myxh.developernews.ui.widget.RevealBackgroundView;
import com.myxh.developernews.util.NetworkUtil;
import com.myxh.developernews.util.RxBinderUtil;
import com.myxh.developernews.viewModel.ZhihuDetailDataViewModel;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import static com.myxh.developernews.ui.fragment.HotFragment.LOCATION;
import static com.myxh.developernews.ui.fragment.HotFragment.NEWS_ID;

public class ZhihuDetailActivity extends BaseSwipeBackActivity implements RevealBackgroundView.OnStateChangeListener {

    private static final String TAG = ZhihuDetailActivity.class.getSimpleName();
    @BindView(R.id.detail_reveal_view)
    RevealBackgroundView mRevealView;
    @BindView(R.id.detail_iv_title)
    SimpleDraweeView mIvTitle;
    @BindView(R.id.detail_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.detail_toolbar_layout)
    CollapsingToolbarLayout mToolbarLayout;
    @BindView(R.id.detail_appbar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.detail_wv_content)
    WebView mContentWebView;
    @BindView(R.id.detail_nsv_content)
    NestedScrollView mNsvContent;
    @BindView(R.id.activity_zhihu_detail)
    CoordinatorLayout mActivityZhihuDetail;

    private ZhihuDetailData mDetailData;
    private ZhihuDetailDataViewModel mDetailViewModel;
    private int newsId;
    private int[] mLocation;
    private RxBinderUtil rxBinderUtil = new RxBinderUtil(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        initViews(savedInstanceState);
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_zhihu_detail;
    }

    private void initViews(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
//            layoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | layoutParams.flags;
//            setTranslucentStatus(true);
        }
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        parseIntent();

        mDetailViewModel = new ZhihuDetailDataViewModel();
        initWebView();
        mRevealView.setOnStateChangeListener(this);

        if (mLocation == null || savedInstanceState != null) {
            mRevealView.setToFinishedFrame();
        } else {
            mRevealView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    mRevealView.getViewTreeObserver().removeOnPreDrawListener(this);
                    mRevealView.startFromLocation(mLocation);
                    return true;
                }
            });
        }
        loadData();
    }

    /**
     * 设置状态栏是否透明
     * @param on
     */
    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void initAppbar() {
        mIvTitle.setImageURI(Uri.parse(mDetailData.getImage()));
        mToolbarLayout.setTitle(mDetailData.getTitle());
    }


    private void initWebView() {
        mContentWebView.setWebChromeClient(new WebChromeClient());
        WebSettings settings = mContentWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        //加载缓存，如果不存在就加载网络数据
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //app cache
        settings.setAppCacheEnabled(true);
        //dom storage
        settings.setDomStorageEnabled(true);
        //database cache
        settings.setDatabaseEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setSupportZoom(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

    private void loadWebView() {
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.txt\" " +
                "type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + mDetailData.getBody() + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        mContentWebView.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);
    }

    private void parseIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        newsId = bundle.getInt(NEWS_ID);
        mLocation = bundle.getIntArray(LOCATION);
    }

    private void loadData() {

        if (NetworkUtil.isNetConnected(this)) {
            resetViewModel();
            mDetailViewModel.setParams(newsId).subscribeToDataStore();
        } else {
            Toast.makeText(this, "没有联网哦~", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onStateChange(int state) {
        if (state == RevealBackgroundView.STATE_FINISHED) {
            mNsvContent.setVisibility(View.VISIBLE);
            mAppbarLayout.setVisibility(View.VISIBLE);
        } else {
            mNsvContent.setVisibility(View.GONE);
            mAppbarLayout.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mContentWebView != null) {
            mContentWebView.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mContentWebView != null) {
            mContentWebView.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mDetailViewModel.unsubscribeFromDataStore();
        mDetailViewModel.dispose();
        mDetailViewModel = null;
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mContentWebView != null) {
            mContentWebView.removeAllViews();
            mContentWebView.destroy();
        }
    }

    private void resetViewModel() {
        Log.i(TAG, "resetViewModel: ---------------------------------");
        mDetailViewModel.unsubscribeFromDataStore();
        mDetailViewModel.dispose();
        mDetailViewModel = null;
        mDetailViewModel = new ZhihuDetailDataViewModel();
        setViewModel(mDetailViewModel);
    }

    private void setViewModel(ZhihuDetailDataViewModel detailViewModel) {
        Log.i(TAG, "setViewModel: ----------------------------------");
        rxBinderUtil.clear();
        if (detailViewModel != null) {
            rxBinderUtil.bindProperty(detailViewModel.getRepository(), this::setRepository);
        }
    }

    private void setRepository(ZhihuDetailData detailData) {
        Log.i(TAG, "setRepository: ---------------------------------------");
        if (detailData == null) {
            Toast.makeText(this, "没有数据！" + detailData, Toast.LENGTH_SHORT).show();
            Logger.i("zhihuHotData.getRecent()= " + detailData.getTitle() + "");
        } else {
            mDetailData = detailData;
            getSupportActionBar().setTitle(detailData.getTitle());
            initAppbar();
            loadWebView();
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
                showShare();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(mDetailData.getTitle());
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl(mDetailData.getShareUrl());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(mDetailData.getTitle());
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(mDetailData.getImage());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(mDetailData.getShareUrl());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(mDetailData.getShareUrl());

        // 启动分享GUI
        oks.show(this);
    }
}
