package com.reny.mvpvmdemo.utils;

import android.databinding.BindingAdapter;
import android.view.View;

import com.liaoinstan.springview.widget.SpringView;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by reny on 2017/1/5.
 */

public class BindingEvent {

    @BindingAdapter("auto")
    public static void setAutoLayoutSize(final View view, boolean enable){
        AutoUtils.autoSize(view);
    }


    @BindingAdapter("initSpring")
    public static void initMultiState(final SpringView view, boolean enable){
        AutoUtils.autoSize(view);
    }

    @BindingAdapter("initSpring")
    public static void initSpringView(final SpringView view, boolean enable){
        AutoUtils.autoSize(view);
    }

    @BindingAdapter("initSpring")
    public static void load(final SpringView view, boolean enable){
        AutoUtils.autoSize(view);
    }

}
