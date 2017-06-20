package com.reny.mvpdemo.utils;

import android.view.View;

import com.classic.common.MultipleStatusView;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.reny.mvpdemo.R;
import com.reny.mvpdemo.core.MyBasePresenter;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.MaterialHeader;

/**
 * Created by reny on 2017/1/5.
 */

public class BindingEvent {

    public static void setLoadMore(final LRecyclerView rv, final MyBasePresenter presenter){
        //rv.setPullRefreshEnabled(false);
        //LRecyclerView支持LoadMoreView自定义 这里使用默认的
        rv.setFooterViewColor(R.color.load_more, R.color.load_more ,R.color.bg_color);
        //设置底部加载文字提示
        rv.setFooterViewHint("加载中","我是底线","加载失败，点击重试");
        rv.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        rv.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                presenter.loadData(false);
            }
        });

    }

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
                presenter.loadData(true);
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

}
