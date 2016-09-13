package com.myxh.developernews.util;

import com.orhanobut.logger.Logger;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by asus on 2016/8/7.
 */
public class RxBinderUtil {

    private String tag;
    private static final CompositeSubscription compositeSubscription = new CompositeSubscription();

    public RxBinderUtil(Object target) {
        tag = target.getClass().getSimpleName();
    }

    public void clear() {
        compositeSubscription.clear();
    }

    public <U> void bindProperty(final Observable<U> observable,
                                 final Action1<U> setter) {
        compositeSubscription.add(subscribeSetter(observable,setter,tag));
    }

    private <U>Subscription subscribeSetter(final Observable<U> observable,
                                            final Action1<U> setter,
                                            final String tag) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SetterSubscriber<U>(setter,tag));
    }

    private static class SetterSubscriber<U> extends Subscriber<U>
    {
        private Action1<U> setter;
        private String tag;

        public SetterSubscriber(Action1<U> setter,String tag) {
            this.setter = setter;
            this.tag = tag;
        }

        @Override
        public void onCompleted() {
            Logger.i(tag+"onCompleted()...");
        }

        @Override
        public void onError(Throwable e) {
            Logger.i(tag+"onError()...");
            setter.call(null);
        }

        @Override
        public void onNext(U u) {
            setter.call(u);
        }

        @Override
        public void onStart() {
            super.onStart();
        }
    }
}
