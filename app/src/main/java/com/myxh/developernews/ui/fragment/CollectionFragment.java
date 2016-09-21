package com.myxh.developernews.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myxh.developernews.R;
import com.myxh.developernews.ui.base.BaseFragment;

/**
 * Created by asus on 2016/9/13.
 */
public class CollectionFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection,null);
        return view;
    }
}
