package com.reny.mvpvmdemo.utils;

import android.databinding.BindingAdapter;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBasePresenter;
import com.reny.mvpvmdemo.utils.img.ImageUtils;
import com.reny.mvpvmlib.utils.LogUtils;
import com.renygit.multistateview.MultiStateView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

/**
 * Created by reny on 2017/1/5.
 */

public class BindingEvent {

    @BindingAdapter("status")
    public static void setStatusHeight(final View view, Integer height){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                //父类不是ViewGroup类型的会报错
                ViewGroup.LayoutParams lp = view.getLayoutParams();
                lp.height = ResUtils.getDimenPx(R.dimen.status_bar_height);

                int resourceId = view.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
                if (resourceId > 0) {
                    //根据资源ID获取响应的尺寸值
                    lp.height = view.getContext().getResources().getDimensionPixelSize(resourceId);
                }

                view.setLayoutParams(lp);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

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
        rv.setHasFixedSize(true);
    }

    @BindingAdapter("presenter")
    public static void setRefreshPresenter(SmartRefreshLayout srl, final MyBasePresenter presenter){
        srl.setEnableAutoLoadmore(true);//开启自动加载功能（非必须）
        srl.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                presenter.loadData(true);
            }
        });
        srl.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                presenter.loadData(false);
            }
        });
        //触发自动刷新
        //srl.autoRefresh();
    }

    @BindingAdapter("loading")
    public static void setRefreshLoading(final SmartRefreshLayout srl, boolean loading){
        if(!loading){
            srl.finishRefresh();
            srl.finishLoadmore();
        }
    }

    @BindingAdapter("noMore")
    public static void setNoMore(final SmartRefreshLayout srl, boolean noMore){
        srl.setLoadmoreFinished(noMore);//将不会再次触发加载更多事件
    }

    @BindingAdapter("stateType")
    public static void setMultiStateType(final MultiStateView view, final int stateViewType){
        view.showViewByStatus(stateViewType);
    }

    @BindingAdapter("presenter")
    public static void setMultiPresenter(final MultiStateView view, final MyBasePresenter presenter){
        view.setOnRetryListener(new MultiStateView.OnRetryListener() {
            @Override
            public void onRetry() {
                presenter.retry();
            }
        });
    }

    @BindingAdapter("url")
    public static void setImgUrl(final View view, String url){
        ImageUtils.getInstance().disPlay(url, view);
    }

}
