package com.myxh.developernews.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by asus on 2016/8/6.
 */
public class GankData extends BaseData {

    public Result results;
    public List<String> category;

    public class Result {
        @SerializedName("Android") public List<Gank> androidList;
        @SerializedName("休息视频") public List<Gank> restVideoList;
        @SerializedName("iOS") public List<Gank> iOSList;
        @SerializedName("福利") public List<Gank> girlList;
        @SerializedName("拓展资源") public List<Gank> extendSourceList;
        @SerializedName("瞎推荐") public List<Gank> recommendList;
        @SerializedName("App") public List<Gank> appList;
    }

}
