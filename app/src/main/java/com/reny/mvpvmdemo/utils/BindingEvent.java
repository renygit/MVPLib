package com.reny.mvpvmdemo.utils;

import android.databinding.BindingAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.classic.common.MultipleStatusView;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBasePresenter;
import com.reny.mvpvmdemo.utils.img.ImageUtils;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Created by reny on 2017/1/5.
 */

public class BindingEvent {

    /*@BindingAdapter("auto")
    public static void setAutoLayoutSize(final View view, boolean enable){
        AutoUtils.autoSize(view);
    }*/


    @BindingAdapter("adapter")
    public static void setAdapter(final RecyclerView rv, RecyclerView.Adapter adapter){
        if(null != adapter) {
            rv.setAdapter(adapter);
        }
    }
    @BindingAdapter("layoutManager")
    public static void setLayoutManager(final RecyclerView rv, RecyclerView.LayoutManager layoutManager){
        if(null == layoutManager)layoutManager = new LinearLayoutManager(rv.getContext());
        rv.setLayoutManager(layoutManager);
    }

    @BindingAdapter({"isError", "presenter"})
    public static void setLoadMore(final LRecyclerView rv, boolean isError, final MyBasePresenter presenter){
        //rv.setPullRefreshEnabled(false);
        //LRecyclerView支持LoadMoreView自定义 这里使用默认的
        rv.setFooterViewColor(R.color.load_more, R.color.load_more ,R.color.bg_color);
        //设置底部加载文字提示
        rv.setFooterViewHint("加载中","我是底线","加载失败，点击重试");
        rv.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        rv.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.requestData(false);
            }
        });

        if(isError) {
            rv.setOnNetWorkErrorListener(new OnNetWorkErrorListener() {
                @Override
                public void reload() {
                    presenter.requestData(false);
                }
            });
        }

    }

    @BindingAdapter("loading")
    public static void setLoadMoreLoading(final LRecyclerView rv, boolean loading){
        if(!loading)rv.refreshComplete(ResUtils.getInteger(R.integer.load_count));
    }

    @BindingAdapter("noMore")
    public static void setNoMore(final LRecyclerView rv, boolean noMore){
        rv.setNoMore(noMore);
    }

    @BindingAdapter("loadMoreEnabled")
    public static void setLoadMoreEnabled(final LRecyclerView rv, boolean enable){
        rv.setLoadMoreEnabled(enable);
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
                view.showError();
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
                presenter.requestData(true);
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

        final boolean[] canRefresh = {true};

        pfl.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.requestData(true);
            }
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if(content instanceof  LRecyclerView){
                    ((LRecyclerView) content).setLScrollListener(new LRecyclerView.LScrollListener() {
                        @Override
                        public void onScrollUp() {
                            canRefresh[0] = false;
                        }
                        @Override
                        public void onScrollDown() {}
                        @Override
                        public void onScrolled(int distanceX, int distanceY) {
                            if(distanceY <= 0){
                                canRefresh[0] = true;
                            }
                        }
                        @Override
                        public void onScrollStateChanged(int state) {
                        }
                    });
                    return canRefresh[0];
                }else {
                    return super.checkCanDoRefresh(frame, content, header);
                }
            }
        });
    }

    @BindingAdapter("loading")
    public static void setRefreshLoading(final PtrFrameLayout pfl, boolean loading){
        if(!loading){
            pfl.refreshComplete();
        }
    }

    @BindingAdapter("url")
    public static void setImgUrl(final View view, String url){
        ImageUtils.getInstance().disPlay(url, view);
    }

}
