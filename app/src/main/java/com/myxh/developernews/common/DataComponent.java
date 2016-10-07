package com.myxh.developernews.common;

import com.myxh.developernews.data.DataStoreModule;
import com.myxh.developernews.viewModel.CategoryDataViewModel;
import com.myxh.developernews.viewModel.GankDataViewModel;
import com.myxh.developernews.viewModel.ZhihuDetailDataViewModel;
import com.myxh.developernews.viewModel.ZhihuHotDataViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by asus on 2016/8/6.
 */
@Singleton
@Component(modules = {DataStoreModule.class})
public interface DataComponent {

    void inject(GankDataViewModel gankDataViewModel);
    void inject(CategoryDataViewModel categoryDataViewModel);
    void inject(ZhihuHotDataViewModel zhihuHotDataViewModel);
    void inject(ZhihuDetailDataViewModel zhihuDetailDataViewModel);

    final class Initializer
    {
        public static DataComponent init() {
            return DaggerDataComponent.builder().build();
        }
    }

}
