package com.myxh.developernews.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myxh.developernews.GankDataType;
import com.myxh.developernews.R;
import com.myxh.developernews.ui.adapter.TabViewPagerAdapter;
import com.myxh.developernews.ui.base.BaseActivity;
import com.myxh.developernews.ui.fragment.DataFragment;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar mToolbar;
    private DrawerLayout drawer;
    private NavigationView mNavigationView;
    private TabLayout mTabLayout;

    private SimpleDraweeView navAvatar;
    private TextView navNickname;
    private TextView navUsername;
    private long firstTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
//        mToolbar.setNavigationIcon(R.mipmap.drawer_menu_icon);
        mToolbar.setTitle(getString(R.string.app_name));//设置主标题
    }


    private void initUI(){

        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | layoutParams.flags;
        }

        initToolbar();
        initDrawer();
        initNavigationView();
        initTabs();
    }

    private void initDrawer() {
        drawer = (DrawerLayout) findViewById(R.id.main_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,mToolbar,R.string.drawer_open,R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initNavigationView() {
        mNavigationView = (NavigationView) findViewById(R.id.main_nav);
        mNavigationView.setNavigationItemSelectedListener(this);
        this.navAvatar = (SimpleDraweeView) mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_iv_avatar);
        this.navNickname = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_tv_nickname);
        this.navUsername = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_tv_username);
    }

    private void initTabs(){
        ViewPager mViewPager = (ViewPager) findViewById(R.id.main_app_bar_viewpager);
        TabViewPagerAdapter viewPagerAdapter = new TabViewPagerAdapter(getSupportFragmentManager());

        DataFragment androidFragment = DataFragment.newFragment(GankDataType.TYPE_ANDROID);
        DataFragment iosFragment = DataFragment.newFragment(GankDataType.TYPE_IOS);
        DataFragment frontendFragment = DataFragment.newFragment(GankDataType.TYPE_FRONT_END);
        DataFragment recommendFragment = DataFragment.newFragment(GankDataType.TYPE_RECOMMEND);
        DataFragment extendFragment = DataFragment.newFragment(GankDataType.TYPE_RESOURCES);
        DataFragment appFragment = DataFragment.newFragment(GankDataType.TYPE_APP);
        //添加Fragment
        viewPagerAdapter.addFragment(androidFragment, getString(R.string.gank_Android));
        viewPagerAdapter.addFragment(iosFragment, getString(R.string.gank_iOS));
        viewPagerAdapter.addFragment(frontendFragment, getString(R.string.gank_FrontEnd));
        viewPagerAdapter.addFragment(recommendFragment, getString(R.string.gank_recommend));
        viewPagerAdapter.addFragment(extendFragment, getString(R.string.gank_ExtendResource));
        viewPagerAdapter.addFragment(appFragment, getString(R.string.gank_App));
        mViewPager.setAdapter(viewPagerAdapter);//设置适配器

        mTabLayout = (TabLayout) findViewById(R.id.main_app_bar_tab);
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.gank_Android)));//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.gank_iOS)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.gank_FrontEnd)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.gank_recommend)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.gank_ExtendResource)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.gank_App)));
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.nav_hot:
                break;
            case R.id.nav_collection:
                break;
            case R.id.nav_girl:
                break;
            case R.id.nav_video:
                break;
            case R.id.nav_settings:
                openActivity(SettingsActivity.class);
                break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            Snackbar sb = Snackbar.make(mNavigationView, "再按一次退出", Snackbar.LENGTH_SHORT);
            sb.getView().setBackgroundColor(Color.BLACK);
            sb.show();
            firstTime = secondTime;
        } else {
            finish();
        }
    }
}