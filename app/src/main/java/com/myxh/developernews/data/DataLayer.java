package com.myxh.developernews.data;

import android.util.Log;

import com.myxh.developernews.bean.CategoryData;
import com.myxh.developernews.bean.GankData;
import com.myxh.developernews.bean.ZhihuDetailData;
import com.myxh.developernews.bean.ZhihuHotData;
import com.myxh.developernews.network.NetworkRequest;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by asus on 2016/8/6.
 */
public class DataLayer {

    private static final String TAG = DataLayer.class.getSimpleName();
    private NetworkRequest networkRequest;

    public DataLayer() {
        Log.i(TAG,"DataLayer构造函数-------------------------------------------------------");
        networkRequest = new NetworkRequest();
    }

    public Observable<GankData> getGankData(int year, int month, int day) {
        Log.i(TAG,"getGankData().................");
        Observable<GankData> observable = networkRequest.getGankData(year, month, day);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
        return observable;
    }

    public Observable<CategoryData> getCategoryData(String type, int limit, int page) {
        Log.i(TAG,"getCategoryData()..................");
        Observable<CategoryData> observable = networkRequest.getCategoryData(type, limit, page);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
        return observable;
    }

    public Observable<ZhihuHotData> getZhihuHotData() {
        Log.i(TAG, "getZhihuHotData: ------------------------------");
        Observable<ZhihuHotData> observable = networkRequest.getZhihuHotData();
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
        return observable;
    }

    public Observable<ZhihuDetailData> getZhihuDetailData(int newsId) {
        Log.i(TAG, "getZhihuDetailData: ----------------------------------");
        Observable<ZhihuDetailData> observable = networkRequest.getZhihuDetailData(newsId);
        observable
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
        return observable;
    }

    public static interface GankDataStore
    {
        Observable<GankData> call(int year, int month, int day);
    }

    public static interface CategoryDataStore
    {
        Observable<CategoryData> call(String type, int limit, int page);
    }

    public static interface ZhihuHotDataStore
    {
        Observable<ZhihuHotData> call();
    }

    public static interface ZhihuDetailDataStore
    {
        Observable<ZhihuDetailData> call(int newsId);
    }

}
