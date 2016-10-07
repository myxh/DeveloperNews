package com.myxh.developernews.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myxh.developernews.R;
import com.myxh.developernews.bean.CategoryData;
import com.myxh.developernews.ui.adapter.ViewPagerAdapter;
import com.myxh.developernews.ui.base.BaseSwipeBackActivity;
import com.myxh.developernews.util.FrescoUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class ImageActivity extends BaseSwipeBackActivity implements ViewPager.OnPageChangeListener {

    private static final String IMG_DATAS = "img_datas";
    private static final String IMG_INDEX = "img_index";

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_app_bar_tab)
    TabLayout mAppBarTab;
    @BindView(R.id.image_pager)
    ViewPager mImagePager;

    private String mTitle;
    private String mUrl;

    private ViewPagerAdapter mPagerAdapter;
    private List<View> mViews;
    private ArrayList<CategoryData.ResultsBean> mImages;
    private int mPosition;

    public static void startImageActivity(Activity context, ArrayList<CategoryData.ResultsBean> datas, int position) {
        Intent intent = new Intent(context,ImageActivity.class);
        intent.putParcelableArrayListExtra(IMG_DATAS,datas);
        intent.putExtra(IMG_INDEX,position);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        initView();
        initData();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_image;
    }

    private void initData() {
        parseIntent();
        getSupportActionBar().setTitle(mTitle);

        mViews = new ArrayList<>();
        for (int i = 0; i < mImages.size(); i++) {
            View view = getLayoutInflater().inflate(R.layout.item_fragment_girl,null);
            SimpleDraweeView image = (SimpleDraweeView) view.findViewById(R.id.girl_pic);
            image.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            image.setImageURI(Uri.parse(mImages.get(i).getUrl()));
            mViews.add(view);
        }
        mPagerAdapter = new ViewPagerAdapter(mViews);
        mImagePager.setAdapter(mPagerAdapter);
        mImagePager.setCurrentItem(mPosition);
    }

    private void initView() {
//        View view = findViewById(R.id.image_appbar_layout);
//        setToolbar(view);
        if (mToolbar != null) {
            mToolbar.setBackgroundColor(getResources().getColor(R.color.colorAccent));
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        mAppBarTab.setVisibility(View.GONE);
        mImagePager.addOnPageChangeListener(this);
    }

    private void parseIntent() {
        Intent intent = getIntent();
        mImages = intent.getParcelableArrayListExtra(IMG_DATAS);
        mPosition = intent.getIntExtra(IMG_INDEX,0);
        mTitle = mImages.get(mPosition).getDesc();
        mUrl = mImages.get(mPosition).getUrl();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_image, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                FrescoUtil.saveImageToDisk(this,mUrl,mTitle);
                break;
            case R.id.menu_share:
                showShare();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        if (position == )
        mTitle = mImages.get(position).getDesc();
        mUrl = mImages.get(position).getUrl();
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

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
        oks.setTitleUrl(mUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(mTitle);
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(mUrl);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(mUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(mUrl);

        // 启动分享GUI
        oks.show(this);
    }
}
