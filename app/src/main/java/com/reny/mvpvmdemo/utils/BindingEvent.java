package com.reny.mvpvmdemo.utils;

import android.databinding.BindingAdapter;
import android.view.View;

import com.classic.common.MultipleStatusView;
import com.liaoinstan.springview.container.RotationFooter;
import com.liaoinstan.springview.container.RotationHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.reny.mvpvmdemo.core.MyBasePresenter;
import com.reny.mvpvmdemo.entity.other.StateViewType;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * Created by reny on 2017/1/5.
 */

public class BindingEvent {

    @BindingAdapter("auto")
    public static void setAutoLayoutSize(final View view, boolean enable){
        AutoUtils.autoSize(view);
    }


    @BindingAdapter({"multiState", "presenter"})
    public static void setMultiState(final MultipleStatusView view, final StateViewType stateType, final MyBasePresenter presenter){
        switch (stateType.getState()){
            case LOADING:
                view.showLoading();
                break;
            case EMPTY:
                view.showEmpty();
                break;
            case ERROR:
                view.showEmpty();
                break;
            case NONET:
                view.showNoNetwork();
                break;
            case SHOWCONTENT:
                view.showContent();
                break;
        }
        view.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stateType.getState() != StateViewType.StateType.LOADING && stateType.getState() != StateViewType.StateType.SHOWCONTENT){
                    presenter.loadData(true);
                }
            }
        });
    }

    @BindingAdapter("presenter")
    public static void initSpringView(final SpringView view, final MyBasePresenter presenter){
        view.setHeader(new RotationHeader(view.getContext()));
        view.setFooter(new RotationFooter(view.getContext()));
    }

    @BindingAdapter("initSpring")
    public static void load(final SpringView view, boolean enable){
        AutoUtils.autoSize(view);
    }

}
