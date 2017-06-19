package com.reny.mvpvmdemo.core;

import com.classic.common.MultipleStatusView;
import com.reny.mvpvmlib.base.RBasePresenter;
import com.reny.mvpvmlib.base.IRBaseView;

/**
 * Created by admin on 2017/6/6.
 */

public abstract class MyBasePresenter<V extends IRBaseView, VM extends MyBaseViewModel> extends RBasePresenter<V,VM> {

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
