package com.myxh.developernews.util;

import com.myxh.developernews.R;

/**
 * Created by asus on 2016/8/6.
 */
public class UrlUtil {

    public static final String GANK_BASE_URL = "http://gank.io/api/";

    public static int getUrlIcon(String url) {
        if (url.contains("github")) {
            return R.drawable.ic_type_github;
        } else if (url.contains("csdn")) {
            return R.drawable.ic_type_csdn;
        } else if (url.contains("jianshu")) {
            return R.drawable.ic_type_jianshu;
        } else if (url.contains("zhihu")) {
            return R.drawable.ic_type_zhihu;
        } else if (url.contains("finalshares")) {
            return R.drawable.ic_type_finalshares;
        } else {
            return R.drawable.ic_type_web;
        }
    }

}
