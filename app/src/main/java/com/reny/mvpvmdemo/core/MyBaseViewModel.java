package com.reny.mvpvmdemo.core;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;

import com.classic.common.MultipleStatusView;
import com.reny.mvpvmdemo.utils.ToastUtils;
import com.reny.mvpvmlib.base.BaseViewModel;

import java.util.List;

/**
 * Created by reny on 2017/6/8.
 */

public class MyBaseViewModel extends BaseViewModel {

    public boolean firstLoadData = true;
    public ObservableBoolean loading = new ObservableBoolean(false);
    public ObservableInt stateViewType = new ObservableInt(MultipleStatusView.STATUS_LOADING);

    public void setDataState(Object datas) {
        boolean noData = (null == datas);
        if (datas instanceof List) {
            noData = (((List) datas).size() == 0);
        }
        if (firstLoadData) {
            if (noData) stateViewType.set(MultipleStatusView.STATUS_EMPTY);
            else {
                stateViewType.set(MultipleStatusView.STATUS_CONTENT);
                firstLoadData = false;//第一次加载数据成功
            }
        }
        loading.set(true);
        loading.set(false);
    }

    public void onError(Throwable e){
        if (firstLoadData) {
            stateViewType.set(MultipleStatusView.STATUS_ERROR);
        }else{
            loading.set(false);
            ToastUtils.showShort("加载失败");
        }
    }

}
