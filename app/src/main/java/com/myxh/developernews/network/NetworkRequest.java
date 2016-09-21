package com.myxh.developernews.network;

import android.util.Log;

import com.myxh.developernews.GankDataType;
import com.myxh.developernews.bean.CategoryData;
import com.myxh.developernews.bean.Gank;
import com.myxh.developernews.bean.GankData;
import com.myxh.developernews.bean.ZhihuHotData;
import com.myxh.developernews.util.UrlUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by asus on 2016/8/6.
 */
public class NetworkRequest {

    private static final String TAG = "NetworkRequest";
    private GankApi gankApi;
    private ZhihuApi mZhihuApi;

    public NetworkRequest() {
        Log.i(TAG,"NetworkRequest构造函数-----------------------------------------");
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);//打印网络请求和结果
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)//添加拦截器
                .retryOnConnectionFailure(true)//出现错误重试
                .readTimeout(15, TimeUnit.SECONDS)
                .connectTimeout(15,TimeUnit.SECONDS)
                .build();

        Retrofit gankRetrofit = new Retrofit.Builder()
                .baseUrl(UrlUtil.GANK_BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        Retrofit zhihuRetrofit = new Retrofit.Builder()
                .baseUrl(UrlUtil.ZHIHU_BASIC_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        gankApi = gankRetrofit.create(GankApi.class);
        mZhihuApi = zhihuRetrofit.create(ZhihuApi.class);

        /*gankApi.getCategoryData(GankDataType.TYPE_ANDROID,10,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoryData>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG,"onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG,"onError()");
                    }

                    @Override
                    public void onNext(CategoryData categoryData) {
                        if (categoryData != null) {
                            for (Gank gank : categoryData.getDataList()) {
                                Log.i(TAG,gank.getType()+":"+gank.getDesc());
                            }
                        }
                    }
                });*/
        gankApi.getAndroidData(10,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CategoryData>() {
                    @Override
                    public void onCompleted() {
                        Log.i(TAG,"request completed.....");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG,"request error.....");
                    }

                    @Override
                    public void onNext(CategoryData categoryData) {
                        if (categoryData != null) {
                            for (Gank gank : categoryData.getDataList()) {
                                Log.i(TAG,gank.getType()+":"+gank.getDesc());
                            }
                        }
                    }
                });
    }

    public Observable<GankData> getGankData(int year, int month, int day) {
        return gankApi.getGankData(year, month, day);
    }

    public Observable<CategoryData> getCategoryData(String type, int limit, int page) {
        return gankApi.getCategoryData(type, limit, page);
    }

    public Observable<ZhihuHotData> getZhihuHotData() {
        return mZhihuApi.getZhihuHotData();
    }
}