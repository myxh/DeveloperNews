package com.myxh.developernews.viewModel;

import com.myxh.developernews.bean.ZhihuHotData;
import com.myxh.developernews.common.MyApplication;
import com.myxh.developernews.data.DataLayer;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by asus on 2016/9/19.
 */
public class ZhihuHotDataViewModel extends AbstractViewModel {

    //只缓存最近的请求
    private BehaviorSubject<ZhihuHotData> repository = BehaviorSubject.create();

    @Inject
    DataLayer.ZhihuHotDataStore mHotDataStore;

    public ZhihuHotDataViewModel() {
        //依赖注入
        MyApplication.getAppContext().getDataComponent().inject(this);
    }

    @Override
    protected void subscribeToDataStoreInternal(CompositeSubscription compositeSubscription) {
        compositeSubscription.add(mHotDataStore
                .call()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repository));
    }

    /**
     * 获取请求数据
     * @return
     */
    public BehaviorSubject<ZhihuHotData> getRepository() {
        return repository;
    }
}
