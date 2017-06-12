package com.reny.mvpvmdemo.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.classic.common.MultipleStatusView;
import com.liaoinstan.springview.container.AcFunFooter;
import com.liaoinstan.springview.container.AliFooter;
import com.liaoinstan.springview.container.MeituanFooter;
import com.liaoinstan.springview.container.RotationFooter;
import com.liaoinstan.springview.container.RotationHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.reny.mvpvmdemo.core.MyBasePresenter;
import com.reny.mvpvmdemo.utils.img.ImageUtils;
import com.zhy.autolayout.utils.AutoUtils;

import cn.bingoogolapple.androidcommon.adapter.BGABindingRecyclerViewAdapter;

/**
 * Created by reny on 2017/1/5.
 */

public class BindingEvent {

    @BindingAdapter("auto")
    public static void setAutoLayoutSize(final View view, boolean enable){
        AutoUtils.autoSize(view);
    }


    @BindingAdapter("adapter")
    public static void setAdapter(final RecyclerView rv, BGABindingRecyclerViewAdapter adapter){
        rv.setAdapter(adapter);
    }
    @BindingAdapter("layoutManager")
    public static void setLayoutManager(final RecyclerView rv, RecyclerView.LayoutManager layoutManager){
        if(null == layoutManager)layoutManager = new LinearLayoutManager(rv.getContext());
        rv.setLayoutManager(layoutManager);
    }


    @BindingAdapter({"multiState", "presenter"})
    public static void setMultiState(final MultipleStatusView view, final int stateViewType, final MyBasePresenter presenter){
        switch (stateViewType){
            case MultipleStatusView.STATUS_LOADING:
                view.showLoading();
                break;
            case MultipleStatusView.STATUS_EMPTY:
                view.showEmpty();
                break;
            case MultipleStatusView.STATUS_ERROR:
                view.showEmpty();
                break;
            case MultipleStatusView.STATUS_NO_NETWORK:
                view.showNoNetwork();
                break;
            case MultipleStatusView.STATUS_CONTENT:
                view.showContent();
                break;
        }
        view.setOnRetryClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stateViewType != MultipleStatusView.STATUS_LOADING && stateViewType != MultipleStatusView.STATUS_CONTENT){
                    presenter.loadData(true);
                }
            }
        });
    }

    @BindingAdapter("presenter")
    public static void initSpringView(final SpringView view, final MyBasePresenter presenter){
        view.setHeader(new RotationHeader(view.getContext()));
        view.setFooter(new RotationFooter(view.getContext()));
        view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                presenter.loadData(true);
            }
            @Override
            public void onLoadmore() {
                presenter.loadData(false);
            }
        });
    }

    @BindingAdapter("loading")
    public static void setSpringViewLoading(final SpringView view, boolean loading){
        if(loading){
            view.callFresh();
        }else {
            view.onFinishFreshAndLoad();
        }
    }

    @BindingAdapter("url")
    public static void setImgUrl(final View view, String url){
        ImageUtils.getInstance().disPlay(url, view);
    }

}
