package com.reny.mvpvmdemo.core;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import com.reny.mvpvmdemo.utils.Network;
import com.reny.mvpvmdemo.utils.ToastUtils;
import com.reny.mvpvmlib.base.RBaseViewModel;
import com.reny.mvpvmlib.utils.LogUtils;
import com.renygit.multistateview.MultiStateView;

/**
 * Created by reny on 2017/6/8.
 * 有刷新和加载更多逻辑
 */

public class MyBaseViewModel extends RBaseViewModel {

    public boolean firstEnter = true;//是否第一次
    public ObservableBoolean loading = new ObservableBoolean(false);//是否正在下拉刷新或加载更多
    public ObservableInt stateViewType = new ObservableInt(MultiStateView.STATE_LOADING);
    //加载更多时没有更多数据
    public ObservableBoolean noMore = new ObservableBoolean(false);


    private void refreshComplete(boolean isEmpty){
        //重置两次是因为怕状态值和上次一样，就无法使界面产生变化
        loading.set(true);
        loading.set(false);

        noMore.set(!isEmpty);
        noMore.set(isEmpty);
    }

    protected void setDataState(boolean isEmpty) {
        if (firstEnter) {
            if (isEmpty) {
                stateViewType.set(MultiStateView.STATE_EMPTY);
            }
            else {
                stateViewType.set(MultiStateView.STATE_CONTENT);
                firstEnter = false;
            }
        }
        refreshComplete(isEmpty);
    }

    public void onError(Throwable e, boolean isRefresh){
        LogUtils.e("网络请求错误："+e.getMessage());
        if (firstEnter) {
            if(Network.isConnected()) {
                stateViewType.set(MultiStateView.STATE_ERROR);
                LogUtils.e("网络请求错误：MultiStateView.STATE_ERROR");
            }else {
                stateViewType.set(MultiStateView.STATE_NO_NETWORK);
                LogUtils.e("网络请求错误：MultiStateView.STATE_NO_NETWORK" + stateViewType.get());
            }
        }else{
            ToastUtils.showShort(isRefresh ? "刷新失败，请重试" : "加载失败，请重试");
            refreshComplete(false);
        }
    }

}
