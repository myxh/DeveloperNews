package com.myxh.developernews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.myxh.developernews.GankDataType;
import com.myxh.developernews.R;
import com.myxh.developernews.bean.CategoryData;
import com.myxh.developernews.ui.activity.MainActivity;
import com.myxh.developernews.ui.adapter.GirlAdapter;
import com.myxh.developernews.ui.base.BaseFragment;
import com.myxh.developernews.util.RxBinderUtil;
import com.myxh.developernews.viewModel.CategoryDataViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by asus on 2016/8/11.
 */
public class GirlFragment extends BaseFragment {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_app_bar_tab)
    TabLayout mAppBarTab;
    @BindView(R.id.fra_common_layout)
    FrameLayout mCommonLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content_layout, null);
        ButterKnife.bind(this, view);

        initView();

        getChildFragmentManager().beginTransaction().replace(R.id.fra_common_layout,DataFragment.newFragment(GankDataType.TYPE_GIRL)).commit();
        return view;
    }

    private void initView() {
        if (mToolbar != null) {
            ((MainActivity) getActivity()).setSupportActionBar(mToolbar);
            ((MainActivity) getActivity()).getSupportActionBar().setTitle(R.string.action_girl);
        }
        mAppBarTab.setVisibility(View.GONE);
    }
}
