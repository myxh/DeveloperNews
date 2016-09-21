package com.myxh.developernews.viewModel;

import com.myxh.developernews.bean.GankData;
import com.myxh.developernews.common.MyApplication;
import com.myxh.developernews.data.DataLayer;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by asus on 2016/9/13.
 */
public class GankDataViewModel extends AbstractViewModel {

    private int year,month,day;

    //只缓存最近的请求
    private BehaviorSubject<GankData> repository = BehaviorSubject.create();

    @Inject
    DataLayer.GankDataStore mGankDataStore;

    public GankDataViewModel() {
        //依赖注入
        MyApplication.getAppContext().getDataComponent().inject(this);
    }

    @Override
    protected void subscribeToDataStoreInternal(CompositeSubscription compositeSubscription) {
        compositeSubscription.add(mGankDataStore
                .call(year,month,day)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repository));
    }

    /**
     * 设置请求参数
     * @param year
     * @param month
     * @param day
     * @return
     */
    public GankDataViewModel setParam(int year,int month, int day) {
        this.year= year;
        this.month = month;
        this.day = day;
        return this;
    }

    /**
     * 获取请求数据
     * @return
     */
    public BehaviorSubject<GankData> getRepository() {
        return repository;
    }
}