package com.myxh.developernews.viewModel;

import com.myxh.developernews.bean.ZhihuDetailData;
import com.myxh.developernews.common.MyApplication;
import com.myxh.developernews.data.DataLayer;

import javax.inject.Inject;

import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by asus on 2016/9/23.
 */

public class ZhihuDetailDataViewModel extends AbstractViewModel {

    private int newsId;

    private BehaviorSubject<ZhihuDetailData> repository = BehaviorSubject.create();

    @Inject
    DataLayer.ZhihuDetailDataStore mDetailDataStore;

    public ZhihuDetailDataViewModel() {
        MyApplication.getAppContext().getDataComponent().inject(this);
    }

    @Override
    protected void subscribeToDataStoreInternal(CompositeSubscription compositeSubscription) {
        compositeSubscription.add(mDetailDataStore.call(newsId)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(repository));
    }

    public ZhihuDetailDataViewModel setParams(int newsId) {
        this.newsId = newsId;
        return this;
    }

    public BehaviorSubject<ZhihuDetailData> getRepository() {
        return repository;
    }
}
