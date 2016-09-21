package com.myxh.developernews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myxh.developernews.GankDataType;
import com.myxh.developernews.R;
import com.myxh.developernews.ui.adapter.TabViewPagerAdapter;
import com.myxh.developernews.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by asus on 2016/9/13.
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_app_bar_tab)
    TabLayout mTabLayout;
    @BindView(R.id.home_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.home_content_layout)
    CoordinatorLayout mHomeContentLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);

        initToolbar(mToolbar);
        initTabs();

        return view;
    }

    private void initToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        }
//        mToolbar.setNavigationIcon(R.mipmap.drawer_menu_icon);
        toolbar.setTitle(getString(R.string.gank));//设置主标题
    }

    private void initTabs(){
        TabViewPagerAdapter viewPagerAdapter = new TabViewPagerAdapter(getChildFragmentManager());

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

        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.gank_Android)));//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.gank_iOS)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.gank_FrontEnd)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.gank_recommend)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.gank_ExtendResource)));
        mTabLayout.addTab(mTabLayout.newTab().setText(getString(R.string.gank_App)));
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
