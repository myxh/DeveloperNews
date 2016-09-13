package com.myxh.developernews.common;

import com.myxh.developernews.data.DataStoreModule;
import com.myxh.developernews.viewModel.CategoryDataViewModel;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by asus on 2016/8/6.
 */
@Singleton
@Component(modules = {DataStoreModule.class})
public interface GankComponent {

    void inject(CategoryDataViewModel categoryDataViewModel);

    final class Initializer
    {
        public static GankComponent init() {
            return DaggerGankComponent.builder().build();
        }
    }

}
