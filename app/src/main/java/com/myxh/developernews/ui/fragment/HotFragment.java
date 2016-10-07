package com.myxh.developernews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.myxh.developernews.R;
import com.myxh.developernews.bean.ZhihuHotData;
import com.myxh.developernews.ui.activity.MainActivity;
import com.myxh.developernews.ui.activity.ZhihuDetailActivity;
import com.myxh.developernews.ui.adapter.HotDataListAdapter;
import com.myxh.developernews.ui.base.BaseFragment;
import com.myxh.developernews.ui.widget.SpacesItemDecoration;
import com.myxh.developernews.util.RxBinderUtil;
import com.myxh.developernews.viewModel.ZhihuHotDataViewModel;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by asus on 2016/9/13.
 */
public class HotFragment extends BaseFragment implements HotDataListAdapter.OnItemClickListener {

    private static final String TAG = HotFragment.class.getSimpleName();
    public static final String NEWS_ID = "newsId";
    public static final String LOCATION = "location";
    @BindView(R.id.hot_recycler_view)
    RecyclerView mHotRecyclerView;
    @BindView(R.id.hot_refresh_layout)
    MaterialRefreshLayout mHotRefreshLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.main_app_bar_tab)
    TabLayout mMainAppBarTab;

    private RxBinderUtil rxBinderUtil = new RxBinderUtil(this);
    private ZhihuHotDataViewModel mHotDataViewModel;

    private boolean isFreshNew = false;
    private boolean isLoadMore = false;
    private List<ZhihuHotData.RecentBean> datas = new ArrayList<>();
    private HotDataListAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHotDataViewModel = new ZhihuHotDataViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, null);
        ButterKnife.bind(this, view);

        initToolbar(mToolbar);
        initViews();

        mAdapter = new HotDataListAdapter(datas, getActivity());
        mHotRecyclerView.setAdapter(mAdapter);
        mHotRecyclerView.addItemDecoration(new SpacesItemDecoration((int) (Math.random() * 5 + 15)));
        mAdapter.setOnItemClickListener(this);

        isFreshNew = true;
        loadData();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHotDataViewModel.unsubscribeFromDataStore();
        mHotDataViewModel.dispose();
        mHotDataViewModel = null;
    }

    private void initToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            Log.i(TAG, "initToolbar: -------------------------------");
            ((MainActivity) getActivity()).setSupportActionBar(toolbar);

            toolbar.setTitle(getString(R.string.hot));//设置主标题
        }
    }

    private void initViews() {
        mMainAppBarTab.setVisibility(View.GONE);
        mHotRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mHotRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mHotRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                isFreshNew = true;
                loadData();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                isLoadMore = true;
                loadData();
            }
        });
    }

    private void loadData() {
        resetViewModel();
        mHotDataViewModel.subscribeToDataStore();
    }

    private void resetViewModel() {
        Log.i(TAG, "resetViewModel: ---------------------------------");
        mHotDataViewModel.unsubscribeFromDataStore();
        mHotDataViewModel.dispose();
        mHotDataViewModel = null;
        mHotDataViewModel = new ZhihuHotDataViewModel();
        setViewModel(mHotDataViewModel);
    }

    private void setViewModel(ZhihuHotDataViewModel zhihuHotDataViewModel) {
        Log.i(TAG, "setViewModel: ----------------------------------");
        rxBinderUtil.clear();
        if (zhihuHotDataViewModel != null) {
            rxBinderUtil.bindProperty(zhihuHotDataViewModel.getRepository(), this::setRepository);
        }
    }

    private void setRepository(ZhihuHotData zhihuHotData) {
        Log.i(TAG, "setRepository: ---------------------------------------");
        if (zhihuHotData == null || zhihuHotData.getRecent() == null || zhihuHotData.getRecent().size() == 0) {
            Toast.makeText(getActivity(), "没有数据！" + zhihuHotData, Toast.LENGTH_SHORT).show();
            Logger.i("zhihuHotData.getRecent()= " + zhihuHotData.getRecent() + "");
        } else {
            if (isFreshNew) {
                isFreshNew = false;
                mHotRefreshLayout.finishRefresh();
                datas.clear();
                datas.addAll(zhihuHotData.getRecent());
            }
            if (isLoadMore) {
                isLoadMore = false;
                mHotRefreshLayout.finishRefreshLoadMore();
                datas.addAll(zhihuHotData.getRecent());
            }
            mAdapter.setDatas(datas);
        }
    }

    private int[] getClickLocation(View v) {
        int[] clickLocation = new int[2];
        v.getLocationOnScreen(clickLocation);
        clickLocation[0] += v.getWidth() / 2;

        return clickLocation;
    }

    @Override
    public void onItemClick(View view, int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(NEWS_ID, datas.get(position).getNews_id());
        bundle.putIntArray(LOCATION, getClickLocation(view));
//        bundle.putInt(LOCATION,position);
        openActivity(ZhihuDetailActivity.class,bundle);
    }
}
