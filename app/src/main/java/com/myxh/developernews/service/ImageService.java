package com.myxh.developernews.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

import com.myxh.developernews.bean.CategoryData;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2016/10/5.
 */

public class ImageService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ImageService(String name) {
        super(name);
    }

    public static void startService(Context context, List<CategoryData> datas, String subtype) {
        Intent intent = new Intent(context, ImageService.class);
        intent.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) datas);
        intent.putExtra("type", subtype);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent == null) {
            return;
        }

        List<CategoryData> datas = intent.getParcelableArrayListExtra("data");
        String subtype = intent.getStringExtra("type");
        handleCategoryData(datas, subtype);
    }

    private void handleCategoryData(List<CategoryData> datas, String type) {
        if (datas.size() == 0) {
            EventBus.getDefault().post("finish");
            return;
        }
        for (CategoryData data : datas) {

//            Bitmap bitmap = ImageLoader.load(this, data.getUrl());
//            if (bitmap != null) {
//                data.setWidth(bitmap.getWidth());
//                data.setHeight(bitmap.getHeight());
//            }

//            data.setSubtype(subtype);
        }
        EventBus.getDefault().post(datas);
    }
}
