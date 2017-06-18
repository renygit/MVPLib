package com.reny.mvpvmdemo.core;

import com.classic.common.MultipleStatusView;
import com.reny.mvpvmlib.base.BasePresenter;
import com.reny.mvpvmlib.base.IBaseView;

/**
 * Created by admin on 2017/6/6.
 */

public abstract class MyBasePresenter<V extends IBaseView, VM extends MyBaseViewModel> extends BasePresenter<V,VM> {

    public MyBasePresenter(V view, VM viewModel) {
        super(view, viewModel);
    }

    public void requestData(boolean isRefresh){
        if(getViewModel().firstLoadData){
            getViewModel().stateViewType.set(MultipleStatusView.STATUS_LOADING);
        }
        loadData(isRefresh);
    }

    public void loadData(boolean isRefresh) {

    }

}
