package com.myxh.developernews.viewModel;

import android.util.Log;

import com.myxh.developernews.bean.CategoryData;
import com.myxh.developernews.common.MyApplication;
import com.myxh.developernews.data.DataLayer;

import javax.inject.Inject;

import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by asus on 2016/8/7.
 */
public class CategoryDataViewModel extends AbstractViewModel {

    private static final String TAG = CategoryDataViewModel.class.getSimpleName();
    private String type;
    private int limit,page;
    private BehaviorSubject<CategoryData> repository = BehaviorSubject.create();

    @Inject
    DataLayer.CategoryDataStore categoryDataStore;

    public CategoryDataViewModel() {
        Log.i(TAG,"CategoryDataViewModel构造函数-----------------------------------------------");
        MyApplication.getAppContext().getGankComponent().inject(this);
    }

    @Override
    public void subscribeToDataStoreInternal(CompositeSubscription compositeSubscription) {
        Log.i(TAG,"subscribeToDataStoreInternal()调用................");
        compositeSubscription.add(categoryDataStore.call(type,limit,page)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(repository));
    }

    public CategoryDataViewModel setParams(String type, int limit, int page) {
        this.type = type;
        this.limit = limit;
        this.page = page;
        return this;
    }

    public BehaviorSubject<CategoryData> getRepository() {
        return repository;
    }
}
