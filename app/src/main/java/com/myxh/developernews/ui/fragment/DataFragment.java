package com.myxh.developernews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.myxh.developernews.GankDataType;
import com.myxh.developernews.R;
import com.myxh.developernews.bean.CategoryData;
import com.myxh.developernews.bean.Gank;
import com.myxh.developernews.ui.adapter.DataListAdapter;
import com.myxh.developernews.ui.base.BaseFragment;
import com.myxh.developernews.util.RxBinderUtil;
import com.myxh.developernews.viewModel.CategoryDataViewModel;
import com.orhanobut.logger.Logger;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/8/10.
 */
public class DataFragment extends BaseFragment {

    private static final String TAG = DataFragment.class.getSimpleName();
    private CategoryDataViewModel categoryDataViewModel;
    private RxBinderUtil rxBinderUtil = new RxBinderUtil(this);

    private RecyclerView recyclerView;
    private MaterialRefreshLayout refreshLayout;
    private List<Gank> datas = new ArrayList<>();
    private DataListAdapter adapter;

    private String dataType;
    private int limit = 10,page = 1;
    public static final String DATA_TYPE = "type";

    private boolean isFreshNew = false;
    private boolean isLoadMore = false;

   public static DataFragment newFragment(String dataType) {
       DataFragment dataFragment = new DataFragment();
       Bundle bundle = new Bundle();
       bundle.putString(DATA_TYPE,dataType);
       dataFragment.setArguments(bundle);
       return dataFragment;
   }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataType = getArguments().getString(DATA_TYPE, GankDataType.TYPE_ANDROID);
        Logger.i("数据类型："+dataType);
        categoryDataViewModel = new CategoryDataViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mainView = inflater.inflate(R.layout.fragment_data,container,false);
        Log.i(TAG, "onCreateView: --------------------------"+dataType);
        initViews(mainView);

        adapter = new DataListAdapter(datas,getActivity());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity()).build());

        loadData();
        return mainView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        categoryDataViewModel.unsubscribeFromDataStore();
        categoryDataViewModel.dispose();
        categoryDataViewModel = null;
    }

    private void initViews(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.fra_data_recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        refreshLayout = (MaterialRefreshLayout) view.findViewById(R.id.fra_data_refreshLayout);
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                isFreshNew = true;
                page = 1;
                loadData();
                materialRefreshLayout.finishRefresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                isLoadMore = true;
                page++;
                loadData();
                materialRefreshLayout.finishRefreshLoadMore();
            }
        });
    }

    private void loadData() {
        Log.i(TAG, "loadData: ----------------------"+dataType);
        resetViewModel();
        categoryDataViewModel.setParams(dataType,limit,page).subscribeToDataStore();
    }

    private void resetViewModel() {
        Log.i(TAG, "resetViewModel: ---------------------------------"+dataType);
        categoryDataViewModel.unsubscribeFromDataStore();
        categoryDataViewModel.dispose();
        categoryDataViewModel = null;
        categoryDataViewModel = new CategoryDataViewModel();
        setViewModel(categoryDataViewModel);
    }

    private void setViewModel(CategoryDataViewModel categoryDataModel) {
        Log.i(TAG, "setViewModel: ----------------------------------"+dataType);
        rxBinderUtil.clear();
        if (categoryDataModel != null) {
            rxBinderUtil.bindProperty(categoryDataModel.getRepository(),this::setRepository);
        }
    }

    private void setRepository(CategoryData categoryData) {
        Log.i(TAG, "setRepository: ---------------------------------------"+dataType);
        if (categoryData == null || categoryData.getDataList() == null || categoryData.getDataList().size()==0) {
            Toast.makeText(getActivity(),"没有数据！"+categoryData,Toast.LENGTH_SHORT).show();
            Logger.i("categoryData.getDataList()= "+categoryData.getDataList()+"");
        } else {
            if (isFreshNew) {
                isFreshNew = false;
                adapter.setDatas(categoryData.getDataList());
//                datas.clear();
//                datas.addAll(categoryData.getDataList());
            }
            if (isLoadMore) {
                isLoadMore = false;
                adapter.addDatas(categoryData.getDataList());
//                datas.addAll(categoryData.getDataList());
            }
        }
    }

}
