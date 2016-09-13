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

    @Singleton
    @Provides
    public DataLayer provideDataLayer() {
        return new DataLayer();
    }
}
