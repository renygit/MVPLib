package com.reny.mvpvmdemo.core;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import com.classic.common.MultipleStatusView;
import com.reny.mvpvmdemo.utils.Network;
import com.reny.mvpvmdemo.utils.ToastUtils;
import com.reny.mvpvmlib.base.BaseViewModel;
import com.reny.mvpvmlib.utils.LogUtils;

/**
 * Created by reny on 2017/6/8.
 * 有刷新和加载更多逻辑
 */

public class MyBaseViewModel extends BaseViewModel {

    public boolean firstLoadData = true;//是否第一次加载数据
    public ObservableBoolean loading = new ObservableBoolean(false);//是否正在下拉刷新或加载更多
    public ObservableInt stateViewType = new ObservableInt(MultipleStatusView.STATUS_LOADING);

    //加载更多时出错
    public ObservableBoolean isError = new ObservableBoolean(false);
    //加载更多时没有更多数据
    public ObservableBoolean noMore = new ObservableBoolean(false);


    private void refreshComplete(boolean isEmpty, boolean error){
        //重置两次是因为怕状态值和上次一样，就无法使界面产生变化
        loading.set(true);
        loading.set(false);

        noMore.set(!isEmpty);
        noMore.set(isEmpty);

        isError.set(!error);
        isError.set(error);
    }

    protected void setDataState(boolean isEmpty) {
        if (firstLoadData) {
            if (isEmpty) {
                stateViewType.set(MultipleStatusView.STATUS_EMPTY);
            }
            else {
                stateViewType.set(MultipleStatusView.STATUS_CONTENT);
                firstLoadData = false;//第一次加载数据成功
            }
        }
        refreshComplete(isEmpty, false);
    }

    public void onError(Throwable e, boolean isRefresh){
        LogUtils.e("网络请求错误："+e.getMessage());
        if (firstLoadData) {
            if(Network.isConnected()) {
                stateViewType.set(MultipleStatusView.STATUS_ERROR);
            }else {
                stateViewType.set(MultipleStatusView.STATUS_NO_NETWORK);
            }
        }else{
            if(isRefresh){
                ToastUtils.showShort("刷新失败，请重试");
            }
            refreshComplete(false, true);
        }
    }

}
