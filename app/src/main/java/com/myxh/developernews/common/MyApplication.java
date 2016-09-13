package com.myxh.developernews.common;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by asus on 2016/8/6.
 */
public class MyApplication extends Application {

    private static MyApplication appContext;
    private GankComponent gankComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        gankComponent = GankComponent.Initializer.init();
        Fresco.initialize(this);
    }

    public GankComponent getGankComponent() {
        return gankComponent;
    }

    public static MyApplication getAppContext() {
        if (null == appContext) {
            appContext = new MyApplication();
        }
        return appContext;
    }

    /**
     * 获取当前程序版本信息
     */
    public String getVersion() {
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(),0);
            String version = info.versionName;
            return "V "+version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "unknown";
        }
    }
}
