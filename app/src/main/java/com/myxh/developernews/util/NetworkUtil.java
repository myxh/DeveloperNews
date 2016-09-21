package com.myxh.developernews.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

/**
 * Created by asus on 2016/9/20.
 */

public class NetworkUtil {

    public static boolean isNetConnected(@NonNull Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null) {
            return false;
        } else {
            return info.isAvailable();
        }
    }
}
