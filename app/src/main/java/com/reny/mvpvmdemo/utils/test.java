package com.reny.mvpvmdemo.utils;

import android.databinding.BindingAdapter;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.reny.mvpvmdemo.R;
import com.reny.mvpvmdemo.core.MyBasePresenter;
import com.reny.mvpvmdemo.widget.recycler.RRecyclerView;

/**
 * Created by admin on 2017/6/28.
 */

public class test {

    @BindingAdapter({"isError", "presenter"})
    public static void setLoadMore(final RRecyclerView rv, boolean isError, final MyBasePresenter presenter){
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
    public static void setLoadMoreLoading(final RRecyclerView rv, boolean loading){
        if(!loading)rv.loadComplete();
    }

    @BindingAdapter("noMore")
    public static void setNoMore(final RRecyclerView rv, boolean noMore){
        rv.setNoMore(noMore);
    }

    @BindingAdapter("loadMoreEnabled")
    public static void setLoadMoreEnabled(final RRecyclerView rv, boolean enable){
        rv.setLoadMoreEnabled(enable);
    }

}
