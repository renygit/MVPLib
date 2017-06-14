package com.reny.mvpvmdemo.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.classic.common.MultipleStatusView;
import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBasePresenter;
import com.reny.mvpvmdemo.utils.img.ImageUtils;
import com.zhy.autolayout.utils.AutoUtils;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Created by reny on 2017/1/5.
 */

public class BindingEvent {

    @BindingAdapter("auto")
    public static void setAutoLayoutSize(final View view, boolean enable){
        AutoUtils.autoSize(view);
    }


    @BindingAdapter("adapter")
    public static void setAdapter(final RecyclerView rv, RecyclerView.Adapter adapter){
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
    public static void setRefreshPresenter(PtrFrameLayout pfl, final MyBasePresenter presenter){
        // header
        final MaterialHeader header = new MaterialHeader(pfl.getContext());
        int[] colors = ResUtils.getArrInt(R.array.refresh_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, CommonUtils.dp2px(15), 0, CommonUtils.dp2px(10));
        header.setPtrFrameLayout(pfl);

        pfl.setLoadingMinTime(1000);
        pfl.setDurationToCloseHeader(1500);
        pfl.setHeaderView(header);
        pfl.addPtrUIHandler(header);

        pfl.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.loadData(true);
            }
        });
    }

    @BindingAdapter("loading")
    public static void setRefreshLoading(final PtrFrameLayout pfl, boolean loading){
        if(loading){
            pfl.postDelayed(new Runnable() {
                @Override
                public void run() {
                    pfl.autoRefresh(true);
                }
            }, 100);
        }else {
            pfl.refreshComplete();
        }
    }

    @BindingAdapter("url")
    public static void setImgUrl(final View view, String url){
        ImageUtils.getInstance().disPlay(url, view);
    }

}
