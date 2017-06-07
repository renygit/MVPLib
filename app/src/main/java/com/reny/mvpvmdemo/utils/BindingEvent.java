package com.reny.mvpvmdemo.utils;

import android.databinding.BindingAdapter;
import android.view.View;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by reny on 2017/1/5.
 */

public class BindingEvent {

    @BindingAdapter("auto")
    public static void setAutoLayoutSize(final View view, boolean enable){
        AutoUtils.autoSize(view);
    }

}
