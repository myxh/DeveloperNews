package com.myxh.developernews.network;

import com.myxh.developernews.bean.CategoryData;
import com.myxh.developernews.bean.GankData;
import com.myxh.developernews.bean.GirlData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by asus on 2016/8/6.
 */
public interface GankApi {

    @GET("day/{year}/{month}/{day}")
    Observable<GankData> getGankData(@Path("year") int year,
                                     @Path("month") int month,
                                     @Path("day") int day);

    @GET("data/{type}/{limit}/{page}")
    Observable<CategoryData> getCategoryData(@Path("type")String type,
                                             @Path("limit")int limit,
                                             @Path("page")int page);
    @GET("data/Android/{limit}/{page}")
    Observable<CategoryData> getAndroidData(@Path("limit")int limit,
                                     @Path("page")int page);
//    @GET("/data/iOS/{limit}/{page}")
//    Observable<GirlData> getIOSData(@Path("limit")int limit,
//                                     @Path("page")int page);

}
