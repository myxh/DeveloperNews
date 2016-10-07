package com.myxh.developernews.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by asus on 2016/8/6.
 */
@Module
public class DataStoreModule {
    @Provides
    public DataLayer.GankDataStore getGankData(DataLayer dataLayer) {
        return dataLayer::getGankData;
    }

    @Provides
    public DataLayer.CategoryDataStore getCategoryData(DataLayer dataLayer) {
        return dataLayer::getCategoryData;
    }

    @Provides
    public DataLayer.ZhihuHotDataStore getZhihuHotData(DataLayer dataLayer) {
        return dataLayer::getZhihuHotData;
    }

    @Provides
    public DataLayer.ZhihuDetailDataStore getZhihuDetailData(DataLayer dataLayer) {
        return dataLayer::getZhihuDetailData;
    }

    @Singleton
    @Provides
    public DataLayer provideDataLayer() {
        return new DataLayer();
    }
}
