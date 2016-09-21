package com.myxh.developernews.ui.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.myxh.developernews.R;
import com.myxh.developernews.ui.base.BaseActivity;
import com.myxh.developernews.ui.fragment.CollectionFragment;
import com.myxh.developernews.ui.fragment.HomeFragment;
import com.myxh.developernews.ui.fragment.HotFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String KEY_STATUS_FRAGMENT_INDEX = "key_status_fragment_index";

    @BindView(R.id.main_content)
    FrameLayout mMainContent;
    @BindView(R.id.main_nav)
    NavigationView mNavigationView;
    @BindView(R.id.main_drawer)
    DrawerLayout drawer;

    private SimpleDraweeView navAvatar;
    private TextView navNickname;
    private TextView navUsername;

    //记录第一次点击Back键时间
    private long firstTime;
    //当前Fragment序号
    private int currentFragmentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initUI();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_content,new HomeFragment()).commit();
    }

    public void replaceFragment() {
        switch (currentFragmentIndex) {
            case 0:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content,new HomeFragment()).commit();
                break;
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content,new HotFragment()).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.main_content,new CollectionFragment()).commit();
                break;
            case 3:
                break;
            case 4:
                break;
        }
    }

    private void initUI() {

        //设置透明状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
            layoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | layoutParams.flags;
        }

        initNavigationView();
    }

    @Override
    public void setSupportActionBar(@Nullable Toolbar toolbar) {
        super.setSupportActionBar(toolbar);
        initDrawer(toolbar);
    }

    private void initDrawer(Toolbar toolbar) {
        drawer = (DrawerLayout) findViewById(R.id.main_drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initNavigationView() {
        mNavigationView.setNavigationItemSelectedListener(this);
        this.navAvatar = (SimpleDraweeView) mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_iv_avatar);
        this.navNickname = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_tv_nickname);
        this.navUsername = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_tv_username);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.nav_home:
                currentFragmentIndex = 0;
                replaceFragment();
                drawer.closeDrawer(GravityCompat.START);
                item.setChecked(true);
                break;
            case R.id.nav_hot:
                currentFragmentIndex = 1;
                replaceFragment();
                drawer.closeDrawer(GravityCompat.START);
                item.setChecked(true);
                break;
            case R.id.nav_collection:
                currentFragmentIndex = 2;
                replaceFragment();
                drawer.closeDrawer(GravityCompat.START);
                item.setChecked(true);
                break;
            case R.id.nav_girl:
                currentFragmentIndex = 3;
                replaceFragment();
                drawer.closeDrawer(GravityCompat.START);
                item.setChecked(true);
                break;
            case R.id.nav_video:
                currentFragmentIndex = 4;
                replaceFragment();
                drawer.closeDrawer(GravityCompat.START);
                item.setChecked(true);
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