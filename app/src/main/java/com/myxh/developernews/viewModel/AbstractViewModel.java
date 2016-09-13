package com.myxh.developernews.viewModel;

import android.util.Log;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by asus on 2016/8/7.
 */
public abstract class AbstractViewModel {

    private static final String TAG = AbstractViewModel.class.getSimpleName();
    private CompositeSubscription compositeSubscription;

    public final void subscribeToDataStore() {
        Log.i(TAG,"subscribeToDataStore()................");
        unsubscribeFromDataStore();
        compositeSubscription = new CompositeSubscription();
        subscribeToDataStoreInternal(compositeSubscription);
    }

    public void dispose() {
        Log.i(TAG,"dispose()................");
        if (compositeSubscription != null) {
            Log.v(TAG,"Disposing without calling unsubscribeFromDataStore first");
            unsubscribeFromDataStore();
        }
    }

    public void unsubscribeFromDataStore() {
        Log.i(TAG,"unsubscribeFromDataStore()................");
        if (compositeSubscription != null) {
            compositeSubscription.clear();
            compositeSubscription = null;
        }
    }

    protected abstract void subscribeToDataStoreInternal(CompositeSubscription compositeSubscription);

}
